package com.aravena.certamenhabitosnicolasaravena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.aravena.certamenhabitosnicolasaravena.models.Usuario;

public class MainActivity extends AppCompatActivity {
    TextView nombrePerfil;
    ConstraintLayout verHabitos, addHabitos,verUbicacion,logout;
    Usuario user = LoginActivity.user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verHabitos = findViewById(R.id.verHabitos);
        addHabitos = findViewById(R.id.addHabitos);
        verUbicacion = findViewById(R.id.verUbicacion);
        logout = findViewById(R.id.logout);
        nombrePerfil = findViewById(R.id.nombrePerfil);





        nombrePerfil.setText(user.getNombres());

        verHabitos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HabitoList.class);
                startActivity(intent);
            }
        });
        addHabitos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FormHabito.class);
                startActivity(intent);
            }
        });
        verUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // condicional - switch

        switch (item.getItemId()) { // tomamos el id del item seleccionado
            case R.id.menu_inicio:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;


            case R.id.menu_habito:
                Intent intent1 = new Intent(this, HabitoList.class);
                startActivity(intent1);
                return true;

            case R.id.menu_categoria:
                Intent intent2 = new Intent(this, CategoriaList.class);
                startActivity(intent2);
                return true;
            case R.id.menu_prioridad:
                Intent intent3 = new Intent(this, PrioridadList.class);
                startActivity(intent3);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}