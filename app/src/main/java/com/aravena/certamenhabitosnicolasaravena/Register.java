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

public class Register extends AppCompatActivity {
    EditText txtNombre,txtApellido,txtEmail,txtPass,txtPass2;
    TextView tvIniciar;
    Button registrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        try {
            this.getSupportActionBar().hide();
        } catch (Exception e) {

        }
        tvIniciar = findViewById(R.id.tvIniciarSesion);
        tvIniciar.setOnClickListener(onClickIniciar());
        txtNombre = findViewById(R.id.txtRNombre);
        txtApellido = findViewById(R.id.txtRApellido);
        txtEmail = findViewById(R.id.txtREmail);
        txtPass = findViewById(R.id.txtRPass);
        registrar = findViewById(R.id.btnRegistrarse);
        txtPass2= findViewById(R.id.txtRPass1);


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                String apellido = txtApellido.getText().toString();
                String email = txtEmail.getText().toString();
                String pass = txtPass.getText().toString();
                String pass2 = txtPass2.getText().toString();

                Usuario u = new Usuario(nombre,apellido,email,pass);
                DbUsuario dbuser = new DbUsuario(getApplicationContext());



                if (nombre.equals("")){
                    Toast.makeText(getApplicationContext(), "No ingresaste un nombre", Toast.LENGTH_SHORT).show();
                }else if(apellido.equals("")){
                    Toast.makeText(getApplicationContext(), "No ingresaste un apellido", Toast.LENGTH_SHORT).show();
                }else if (email.equals("")){
                    Toast.makeText(getApplicationContext(), "No ingresaste un email", Toast.LENGTH_SHORT).show();
                }else if(pass.equals("")){
                    Toast.makeText(getApplicationContext(), "No ingresaste una contrasña", Toast.LENGTH_SHORT).show();
                }else if(!pass.equals(pass2)){

                    Toast.makeText(getApplicationContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }
                else{
                    long id = dbuser.insertarUsuario(u);
                    if( id >= 0 ){
                        Toast.makeText(Register.this,
                                nombre+" registrado, ya puedes iniciar sesión", Toast.LENGTH_LONG).show();
                        txtNombre.setText("");
                        txtApellido.setText("");
                        txtEmail.setText("");
                        txtPass.setText("");
                    }else{
                        Toast.makeText(Register.this,
                                "Error al registrarse, intente nuevamente", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });


    }


    private View.OnClickListener onClickIniciar() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,LoginActivity.class);
                Register.this.startActivity(intent);
            }
        };
    }
}