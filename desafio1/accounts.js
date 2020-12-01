const fetch = require("node-fetch");

// data structure
// {"id": number, "agencia": number, "conta": number, "name": string, "balance": number}

const main = async () => {
  try {
    const resultResponse = await fetch(
      "https://igti-film.herokuapp.com/api/accounts"
    );

    const result = await resultResponse.json();

    const sumBalances = result.reduce(
      (total, current) => total + current.balance,
      0
    );
    console.log(`sum balances: ${sumBalances}`);

    const accountBalanceOver100 = result.filter(
      (account) => account.balance > 100
    );
    console.log(
      `accounts with balance over R$ 100: ${accountBalanceOver100.length}`
    );

    const accountBalanceOver100InAgencia33 = accountBalanceOver100.filter(
      (account) => account.agencia === 33
    );
    console.log(
      `accounts with balance over R$ 100 in agencia 33: ${accountBalanceOver100InAgencia33.length}`
    );

    const clientBiggerBalanceAgencia10 = result
      .filter((account) => account.agencia === 10)
      .reduce((biggestBalanceAccount, currentAccount) => {
        const result = Math.max(
          biggestBalanceAccount.balance || 0,
          currentAccount.balance
        );

        switch (true) {
          case result === biggestBalanceAccount.balance:
            return biggestBalanceAccount;
          case result === currentAccount.balance:
            return currentAccount;
        }
        return accountToReturn;
      }, 0);
    console.log(
      `client with biggest balance in agencia 10: ${clientBiggerBalanceAgencia10.name}`
    );

    const clientsAgencia47 = result.filter((account) => account.agencia === 47);

    console.log("total clients in agencia 47:", clientsAgencia47.length);

    const clientsWithMariaInName = clientsAgencia47.filter((account) =>
      account.name.includes("Maria")
    );
    console.log(
      "clients with Maria in Name in agencia 47:",
      clientsWithMariaInName.length
    );
    const sortedAgencia47ClientsByLowestBalance = clientsAgencia47.sort(
      (accountA, accountB) => accountA.balance - accountB.balance
    );
    console.log(
      `client with smallest balance in agencia 47: ${sortedAgencia47ClientsByLowestBalance[0].name}`
    );

    const firstThreeClientsWithLowestBalanceName = sortedAgencia47ClientsByLowestBalance
      .slice(0, 3)
      .map((client) => client.name)
      .join(",");

    console.log(
      "clients with lowest balance in agencia 47:",
      firstThreeClientsWithLowestBalanceName
    );

    const sortedAccountByHighestBalance = result.sort(
      (accountA, accountB) => accountB.balance - accountA.balance
    );
    console.log(
      "agencia whose account has the overral highest balance:",
      sortedAccountByHighestBalance[0].agencia
    );

    const sortedAccountByHighestId = result.sort(
      (accountA, accountB) => accountB.id - accountA.id
    );
    console.log("highest id:", sortedAccountByHighestId[0].id);

    const agencias = result
      .map((account) => account.agencia)
      .filter((value, index, self) => self.indexOf(value) === index);

    console.log("agencias", JSON.stringify(agencias));

    const highestBalanceInAgencia47 = result
      .filter((account) => account.agencia === 47)
      .sort((accountA, accountB) => accountB.balance - accountA.balance)[0]
      .balance;
    const highestBalanceInAgencia33 = result
      .filter((account) => account.agencia === 33)
      .sort((accountA, accountB) => accountB.balance - accountA.balance)[0]
      .balance;
    const highestBalanceInAgencia25 = result
      .filter((account) => account.agencia === 25)
      .sort((accountA, accountB) => accountB.balance - accountA.balance)[0]
      .balance;

    const sumHighestBalanceInAllAgencias =
      highestBalanceInAgencia47 +
      clientBiggerBalanceAgencia10.balance +
      highestBalanceInAgencia33 +
      highestBalanceInAgencia25;
    console.log(
      "sumHighestBalanceInAllAgencias",
      sumHighestBalanceInAllAgencias
    );
  } catch (error) {
    console.error(error);
  }
};

main();
