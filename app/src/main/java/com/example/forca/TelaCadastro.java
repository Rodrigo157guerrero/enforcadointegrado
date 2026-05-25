package com.example.forca;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaCadastro extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private EditText textoDaPalavra;
    private Button btnCadastrar, btnListar;
    private RadioGroup grupo;

    private BD bd;
    private String categoriaSelecionada, palavra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bd = new BD(TelaCadastro.this);

        textoDaPalavra = findViewById(R.id.textPalavra);
        btnCadastrar = findViewById(R.id.button);
        btnListar = findViewById(R.id.button2);
        btnListar.setOnClickListener(this);
        grupo = findViewById(R.id.id_group);
        grupo.setOnCheckedChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == btnCadastrar){

            String texto = textoDaPalavra.getText().toString();


            boolean temTextoDigitado = false;

            if (texto.isEmpty()){
                Toast.makeText(this,"ta faltando palavra", Toast.LENGTH_SHORT);
            } else {
                temTextoDigitado = true;
            }


            RadioButton r = findViewById(R.id.radioButton);
            RadioButton r2 = findViewById(R.id.radioButton2);
            RadioButton r3 = findViewById(R.id.radioButton3);
            RadioButton r4 = findViewById(R.id.radioButton4);
            RadioButton r5 = findViewById(R.id.radioButton5);


            boolean temRadioChecado = false;
            if(r.isChecked()||r2.isChecked()||r3.isChecked()||r4.isChecked()||r5.isChecked()){
                temRadioChecado = true;
            } else {
                Toast.makeText(this,"faltou a categoria", Toast.LENGTH_SHORT);
            }
            if (temTextoDigitado && temRadioChecado)
            {

                Palavra palavra1 = new Palavra();
                palavra1.setPalavraDigitada(texto);
                bd.salvarPalavra(palavra1);

            }

        }



        if(v ==btnListar){

        }

    }

    @Override
    public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
        if(group == grupo){
            RadioButton temporario = findViewById(checkedId);
            Toast.makeText(TelaCadastro.this, temporario.getText().toString(),Toast.LENGTH_SHORT).show();
        }

    }
}