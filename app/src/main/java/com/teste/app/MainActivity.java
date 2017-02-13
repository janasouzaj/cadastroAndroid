package com.teste.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText txt_email, txt_password;
    Button btn_login;
    TextView lbl_forgetPassword;
    Context context;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        txt_email = (EditText) findViewById(R.id.txt_email);
        txt_password = (EditText) findViewById(R.id.txt_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        lbl_forgetPassword = (TextView) findViewById(R.id.lbl_forgetPassword);
    }

    public void Login(View view) {
        //Toast.makeText(this, "Cliquei no botão", Toast.LENGTH_SHORT).show();
        final String usuario = txt_email.getText().toString();
        final String senha = txt_password.getText().toString();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                String url = "http://192.168.231.2/20171sem/inf4m/testeLogin/api_login.php";

                HashMap<String, String> parametros = new HashMap<>();
                parametros.put("email", usuario);
                parametros.put("senha", senha);

                String retorno = Http.post(url, parametros);
                Log.d("Login", retorno);

                Gson gson = new Gson();
                //pega a string passada manda para a classe 'Usuario' e o retorna como um objeto
                Usuario usuarioLogado = gson.fromJson(retorno, Usuario.class);

                Log.d("Login", usuarioLogado.getNome());

                //Sessao.usuarioLogado = usuarioLogado;

                sharedPreferences.edit().putString("nomeUsuario", usuarioLogado.getNome()).apply();
                sharedPreferences.edit().putString("emailUsuario", usuarioLogado.getEmail()).apply();

                Intent intent = new Intent(context, Home.class);
                startActivity(intent);
                /*intent.putExtra("nomeUsuario", usuarioLogado.getNome());
                intent.putExtra("emailUsuario", usuarioLogado.getEmail());
                intent.putExtra("senhaUsuario", usuarioLogado.getSenha());*/

                return null;
            }
        }.execute();
    }

    public void Cadastro(View view) {
        //Toast.makeText(this, "Cliquei no cadastro", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, Cadastro.class);
        startActivity(intent);
    }

    //Alteração git 08:21 13/02/2017
}