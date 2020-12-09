package com.example.desafio2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.DecimalFormat;

public class resultado extends AppCompatActivity {
    Double salario_liquido = 0.00;
    Double descontos_porcento = 0.0;
    Double inss = 0.0;
    Double irpf = 0.0;
    Double salario_bruto;
    int dependentes;
    Double descontos;

    final Double ALIQUOTA1_INSS = 0.075;
    final Double ALIQUOTA2_INSS = 0.09;
    final Double ALIQUOTA3_INSS = 0.12;
    final Double ALIQUOTA4_INSS = 0.14;

    final Double DEDUCAO_ALIQUOTA2_INSS = 15.67;
    final Double DEDUCAO_ALIQUOTA3_INSS = 78.36;
    final Double DEDUCAO_ALIQUOTA4_INSS = 141.05;

    final Double MAXIMA_CONTRIBUICAO_INSS = 713.10;

    final Double ALIQUOTA1_IRPF = 0.075;
    final Double ALIQUOTA2_IRPF = 0.15;
    final Double ALIQUOTA3_IRPF = 0.225;
    final Double ALIQUOTA4_IRPF = 0.275;

    final Double DEDUCAO_ALIQUOTA1_IRPF = 142.80;
    final Double DEDUCAO_ALIQUOTA2_IRPF = 354.80;
    final Double DEDUCAO_ALIQUOTA3_IRPF = 636.13;
    final Double DEDUCAO_ALIQUOTA4_IRPF = 869.36;

    final Double DEDUCAO_IRPF_POR_DEPENDENTE = 189.59;

    final Double TETO_ALIQUOTA1_INSS = 1045.00;
    final Double TETO_ALIQUOTA2_INSS = 2089.60;
    final Double TETO_ALIQUOTA3_INSS = 3134.40;
    final Double TETO_ALIQUOTA4_INSS = 6101.06;

    final Double TETO_SEM_CONTRIBUICAO_IRPF = 1903.98;
    final Double TETO_ALIQUOTA1_IRPF = 2826.65;
    final Double TETO_ALIQUOTA2_IRPF = 3751.05;
    final Double TETO_ALIQUOTA3_IRPF = 4664.68;

    TextView textViewSalarioLiquido, textViewDescontosPorcento, textViewOutrosDescontos,
            textViewSalarioBruto, textViewINSS, textViewIRPF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        textViewDescontosPorcento = (TextView) findViewById(R.id.textViewDescontosPorcento);
        textViewOutrosDescontos = (TextView) findViewById(R.id.textViewOutrosDescontos);
        textViewSalarioBruto = (TextView) findViewById(R.id.textViewSalarioBruto);
        textViewSalarioLiquido = (TextView) findViewById(R.id.textViewSalarioLiquido);
        textViewINSS = (TextView) findViewById(R.id.textViewINSS);
        textViewIRPF = (TextView) findViewById(R.id.textViewIRPF);
    }

    Double calculaINSS(Double salario) {
        if(salario <= TETO_ALIQUOTA1_INSS) {
            return salario * ALIQUOTA1_INSS;
        }
        else if(salario <= TETO_ALIQUOTA2_INSS) {
            return (salario*ALIQUOTA2_INSS) - DEDUCAO_ALIQUOTA2_INSS;
        } else if (salario <= TETO_ALIQUOTA3_INSS) {
            return (salario * ALIQUOTA3_INSS) - DEDUCAO_ALIQUOTA3_INSS;
        } else if (salario <= TETO_ALIQUOTA4_INSS) {
            return (salario*ALIQUOTA4_INSS) - DEDUCAO_ALIQUOTA4_INSS;
        } else {
            return (MAXIMA_CONTRIBUICAO_INSS);
        }
    }

    double calculaIRPF(Double inssCalculado, Double salario, int numDependentes){
        Double baseCalculo = salario - inssCalculado -
                (numDependentes * DEDUCAO_IRPF_POR_DEPENDENTE);
        if(baseCalculo <= TETO_SEM_CONTRIBUICAO_IRPF) {
            return 0;
        } else if(baseCalculo <= TETO_ALIQUOTA1_IRPF) {
            return (baseCalculo*ALIQUOTA1_IRPF) - DEDUCAO_ALIQUOTA1_IRPF;
        } else if(baseCalculo <= TETO_ALIQUOTA2_IRPF) {
            return (baseCalculo*ALIQUOTA2_IRPF) - DEDUCAO_ALIQUOTA2_IRPF;
        } else if(baseCalculo <= TETO_ALIQUOTA3_IRPF){
            return (baseCalculo*ALIQUOTA3_IRPF) - DEDUCAO_ALIQUOTA3_IRPF;
        } else {
            return (baseCalculo*ALIQUOTA4_IRPF) - DEDUCAO_ALIQUOTA4_IRPF;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        DecimalFormat precision = new DecimalFormat("0.00");

        this.salario_bruto = getIntent().getDoubleExtra("salario_bruto",0);
        this.dependentes = getIntent().getIntExtra("dependentes", 0);
        this.descontos = getIntent().getDoubleExtra("descontos", 0.0);
        this.inss = calculaINSS(this.salario_bruto);
        this.irpf = calculaIRPF(this.inss, this.salario_bruto, this.dependentes);
        this.salario_liquido = this.salario_bruto - this.inss - this.irpf - this.descontos;
        this.descontos_porcento = (this.salario_liquido * 100)/this.salario_bruto;

        textViewSalarioBruto.setText(precision.format(this.salario_bruto));
        textViewINSS.setText(precision.format(this.inss));
        textViewIRPF.setText(precision.format(this.irpf));
        textViewOutrosDescontos.setText(precision.format(this.descontos));
        textViewSalarioLiquido.setText(precision.format(this.salario_liquido));
        textViewDescontosPorcento.setText(precision.format(this.descontos_porcento));
    }

    public void handleClickReturn(View view) {
        finish();
    }
}