package com.example.forca;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class TelaJogo extends AppCompatActivity implements View.OnClickListener{
    private ImageView imagem;
    private ArrayList<Integer> listaImagens, listaIdButtons;
    private ArrayList<String> listaPalavras;
    private int indiceListaImagens, contaAcerto, contaErro;

    private TextView texto, txAcerto, txErro;

    private String palavra;

    private char[] estado;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_jogo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imagem = findViewById(R.id.imageView);
        txAcerto = findViewById(R.id.txAcerto);
        txErro = findViewById(R.id.txErro);
        contaAcerto = 0;
        contaErro = 0;
        indiceListaImagens = -1;
        listaImagens = new ArrayList<Integer>();
        listaImagens.add(R.drawable.forca_1_9);
        listaImagens.add(R.drawable.forca_2_9);
        listaImagens.add(R.drawable.forca_3_9);
        listaImagens.add(R.drawable.forca_4_9);
        listaImagens.add(R.drawable.forca_5_9);
        listaImagens.add(R.drawable.forca_6_9);
        listaImagens.add(R.drawable.forca_7_9);
        listaImagens.add(R.drawable.forca_9_9);
        listaImagens.add(R.drawable.forca_10_9);
        listaImagens.add(R.drawable.forca_11_9);

        b1 = findViewById(R.id.id2);
        b1.setOnClickListener((this));

        listaPalavras = new ArrayList<String>();
        listaPalavras.add("MUSICA");
        listaPalavras.add("CUIDADO");
        listaPalavras.add("RUAS");
        listaPalavras.add("XXXTENTACION");
        listaPalavras.add("BABILONIA");
        listaPalavras.add("MARMANJO");
        listaPalavras.add("ZACARIAS");
        listaPalavras.add("PATETA");
        listaPalavras.add("PALMITO");
        listaPalavras.add("BOOYAH");
        listaPalavras.add("CALVICE");
        listaPalavras.add("SUJO");
        listaPalavras.add("SONHOS");

        texto = findViewById(R.id.textView3);

        listaIdButtons = new ArrayList<Integer>();
        listaIdButtons.add(R.id.id2);
        listaIdButtons.add(R.id.id3);
        listaIdButtons.add(R.id.id4);
        listaIdButtons.add(R.id.id5);
        listaIdButtons.add(R.id.id6);
        listaIdButtons.add(R.id.id7);
        listaIdButtons.add(R.id.id8);
        listaIdButtons.add(R.id.id9);
        listaIdButtons.add(R.id.id10);
        listaIdButtons.add(R.id.id11);
        listaIdButtons.add(R.id.id12);
        listaIdButtons.add(R.id.id13);
        listaIdButtons.add(R.id.id14);
        listaIdButtons.add(R.id.id15);
        listaIdButtons.add(R.id.id16);
        listaIdButtons.add(R.id.id17);
        listaIdButtons.add(R.id.id18);
        listaIdButtons.add(R.id.id19);
        listaIdButtons.add(R.id.id20);
        listaIdButtons.add(R.id.id21);
        listaIdButtons.add(R.id.id22);
        listaIdButtons.add(R.id.id23);
        listaIdButtons.add(R.id.id24);
        listaIdButtons.add(R.id.id25);
        listaIdButtons.add(R.id.id26);
        listaIdButtons.add(R.id.id27);


        for (int j = 0; j<listaIdButtons.size();j++) {
            Button b = findViewById(listaIdButtons.get(j));
            b.setOnClickListener(this);
        }
        inicializaJogo();


    }
    public void inicializaJogo(){
        imagem.setImageResource(R.drawable.forca_0_9);
        indiceListaImagens = 0;
        palavra = sorteiaPalavra();
        estado = new char[palavra.length()];
        for(int i =0; i<estado.length;i++){
            estado[i] = '_';
        }
        contaErro = 0;
        contaAcerto = 0;
        txAcerto.setText(Integer.toString(contaAcerto));
        txErro.setText(Integer.toString(contaErro)+"/"+Integer.toString(listaImagens.size()));
        atualizaTexto();

        for (int j = 0; j<listaIdButtons.size();j++) {
            Button b = findViewById(listaIdButtons.get(j));
            b.setEnabled(true);
        }

    }
    public void verificaLetra(char c){
        boolean status = false;
        for(int i=0; i<palavra.length();i++){
            if (palavra.charAt(i)==c) {
                status = true;
                estado[i] = c;
            }
        }
        if(!status){
            atualizarForca();
            contaErro++;
            txErro.setText(Integer.toString(contaErro)+"/"+Integer.toString(listaImagens.size()));
        }
        else {
            atualizaTexto();
            contaAcerto++;
            txAcerto.setText(Integer.toString(contaAcerto));
        }
        checaSeTerminou();

    }
    public void checaSeTerminou(){
        boolean verifica = false;
        for (int i = 0; i < estado.length; i++){
            if (estado[i] == '_') {
                verifica = true;
            }
        }
        if (verifica) {
            AlertDialog.Builder caixa = new AlertDialog.Builder(this);
            caixa.setTitle("Voce ganhou");
            caixa.setMessage("Jogar novamente");
            caixa.setPositiveButton("Jogar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    inicializaJogo();
                }
            });
            caixa.show();
        }
        if (contaErro >= listaImagens.size()){
            AlertDialog.Builder caixa = new AlertDialog.Builder(this);
            caixa.setTitle("Voce perdeu seu bosta");
            caixa.setMessage("Jogar novamente");
            caixa.setPositiveButton("Jogar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    inicializaJogo();
                }
            });
            caixa.show();
        }
    }

    public void atualizaTexto(){
        String temporaria = new String();
        temporaria = " ";
        for (int i =0; i<estado.length; i++){
            temporaria+= estado[i] + " ";

        }
        texto.setText(temporaria);
    }
    public String sorteiaPalavra(){
        String retorno = new String();
        Collections.shuffle(listaPalavras);
        retorno = listaPalavras.get(0);
        return retorno;
    }
    public void atualizarForca(){
        indiceListaImagens++;
        imagem.setImageResource(listaImagens.get(indiceListaImagens));

    }

    @Override
    public void onClick(View v) {
        Button b = (Button) v;
        verificaLetra(b.getText().toString().charAt(0));
        b.setEnabled(false);


    }
}