package com.aravena.certamenhabitosnicolasaravena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.SearchView;

import com.aravena.certamenhabitosnicolasaravena.adapter.HabitoAdapter;
import com.aravena.certamenhabitosnicolasaravena.models.Habito;
import com.aravena.certamenhabitosnicolasaravena.sqlite.DbHabito;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HabitoList extends AppCompatActivity implements SearchView.OnQueryTextListener{
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    SearchView txtBuscar;
    HabitoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habito_list);
        add_button = findViewById(R.id.add_buttonHabito);
        recyclerView = findViewById(R.id.recyclerView);
        txtBuscar = findViewById(R.id.txtBuscar);

        ArrayList<Habito> array = new DbHabito(getApplicationContext()).getHabitos();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        adapter = new HabitoAdapter(array);
        recyclerView.setAdapter(adapter);



        txtBuscar.setOnQueryTextListener(this);








        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HabitoList.this, FormHabito.class);
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
    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapter.filtrado(s);
        return false;
    }
}