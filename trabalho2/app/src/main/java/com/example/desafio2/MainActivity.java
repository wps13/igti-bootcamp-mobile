package com.example.desafio2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText editDependentes, editSalarioBruto, editOutrosDescontos;
    Double salarioBruto, outrosDescontos;
    int dependentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editSalarioBruto = (EditText) findViewById(R.id.editSalarioBruto);
        editDependentes = (EditText) findViewById(R.id.editDependentes);
        editOutrosDescontos = (EditText) findViewById(R.id.editOutrosDescontos);
        editSalarioBruto.setText("0.00");
        editDependentes.setText("0");
        editOutrosDescontos.setText("0.00");
    }

    public void handleClick(View view){
        Intent resultadoActivity = new Intent(this, resultado.class);


        salarioBruto =  Double.parseDouble(editSalarioBruto.getText().toString());

        dependentes = Integer.parseInt(editDependentes.getText().toString());

        outrosDescontos =  Double.parseDouble(editOutrosDescontos.getText().toString());

        resultadoActivity.putExtra("salario_bruto", salarioBruto);
        resultadoActivity.putExtra("dependentes", dependentes);
        resultadoActivity.putExtra("descontos", outrosDescontos);
        startActivity(resultadoActivity);
    }

}