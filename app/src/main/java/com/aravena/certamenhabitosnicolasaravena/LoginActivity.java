package com.aravena.certamenhabitosnicolasaravena;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aravena.certamenhabitosnicolasaravena.models.Usuario;
import com.aravena.certamenhabitosnicolasaravena.sqlite.DbUsuario;

public class LoginActivity extends AppCompatActivity {
    TextView tvRegistro,tvRegistro2;
    public static Usuario user = null;
    EditText txtClaveLogin, txtEmailLogin;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try{
            this.getSupportActionBar().hide();
        }catch (Exception e){

        }
        tvRegistro = findViewById(R.id.tvRegistrate);
        tvRegistro2 = findViewById(R.id.tvRegistrate2);
        txtClaveLogin = findViewById(R.id.txtClaveLogin);
        txtEmailLogin = findViewById(R.id.txtEmailLogin);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmailLogin.getText().toString();
                String clave = txtClaveLogin.getText().toString();

                DbUsuario bduser = new DbUsuario(LoginActivity.this);

                user = bduser.login(email, clave);

               if( user == null ){
                    Toast.makeText(LoginActivity.this,
                            "Credenciales no validas", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this,
                            "Bienvenid@ "+user.getNombres(), Toast.LENGTH_LONG).show();
                   startActivity(new Intent(LoginActivity.this, MainActivity.class));



                }



            }
        });
        tvRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Register.class);
                LoginActivity.this.startActivity(intent);
            }
        });
        tvRegistro2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Register.class);
                LoginActivity.this.startActivity(intent);
            }
        });


    }




}