const fetch = require("node-fetch");

const fetchData = async () => {
    const result = await fetch("https://igti-film.herokuapp.com/api/accounts")
    const results = await result.json();
        
}

fetchData();
