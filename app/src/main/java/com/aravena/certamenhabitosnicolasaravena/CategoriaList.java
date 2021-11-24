package com.aravena.certamenhabitosnicolasaravena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoriaList extends AppCompatActivity {
    FloatingActionButton add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_list);

        add_button = findViewById(R.id.add_buttonCat);






        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(CategoriaList.this, FormCategoria.class);
                startActivity(intent1);

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