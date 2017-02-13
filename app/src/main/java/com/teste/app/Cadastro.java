package com.teste.app;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Cadastro extends AppCompatActivity {
    EditText txt_name, txt_email, txt_password, txt_confirm;
    Button btn_register;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txt_name = (EditText) findViewById(R.id.txt_name);
        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_password = (EditText) findViewById(R.id.txt_password);
        txt_confirm = (EditText) findViewById(R.id.txt_confirm);
        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cadastrar(v);
            }
        });

        context = this;
    }

    public void Cadastrar(View view) {
        boolean valido = true;

        if(txt_name.getText().toString().isEmpty()){
            txt_name.setError("Campo obrigatório!");
            valido = false;
        }
        if(txt_email.getText().toString().isEmpty()){
            txt_email.setError("Campo obrigatório!");
            valido = false;
        }
        if(txt_password.getText().toString().isEmpty()){
            txt_password.setError("Campo obrigatório!");
            valido = false;
        }
        if(txt_confirm.getText().toString().isEmpty()){
            txt_confirm.setError("Campo obrigatório!");
            valido = false;
        }

        if(valido){
            //pegando o texto digitado pelo usuário e convertendo para String
            final String nome = txt_name.getText().toString();
            final String email = txt_email.getText().toString();
            final String senha = txt_password.getText().toString();

            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    //
                    String url = "http://192.168.231.2/20171sem/inf4m/testeLogin/api_cadastro.php";

                    HashMap<String, String> parametros= new HashMap<>();
                    parametros.put("nome", nome);
                    parametros.put("email", email);
                    parametros.put("senha", senha);

                    String resultado = Http.post(url, parametros);

                    Log.d("cadastrar", resultado);

                    return null;
                }
            }.execute();
            //Reedireciona o usuário para a tela principal de login do app, após realizar cadastro
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
        }
    }
}