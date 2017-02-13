package com.teste.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    TextView txt_nomeUsuario, txt_emailUsuario;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        txt_nomeUsuario = (TextView) findViewById(R.id.txt_nomeUsuario);
        txt_emailUsuario = (TextView) findViewById(R.id.txt_emailUsuario);

        String nomeUsuario = sharedPreferences.getString("nomeUsuario", "");
        String emailUsuario = sharedPreferences.getString("emailUsuario", "");

        Intent intent = getIntent();

        /*String nomeUsuario = intent.getStringExtra("nomeUsuario");
        String emailUsuario = intent.getStringExtra("emailUsuario");

        txt_nomeUsuario.setText(Sessao.usuarioLogado.getNome());
        txt_emailUsuario.setText(Sessao.usuarioLogado.getEmail());*/

        txt_nomeUsuario.setText(nomeUsuario);
        txt_emailUsuario.setText(emailUsuario);
    }
}