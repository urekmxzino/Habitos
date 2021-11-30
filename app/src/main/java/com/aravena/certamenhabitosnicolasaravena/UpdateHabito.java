package com.aravena.certamenhabitosnicolasaravena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aravena.certamenhabitosnicolasaravena.models.Categoria;
import com.aravena.certamenhabitosnicolasaravena.models.Habito;
import com.aravena.certamenhabitosnicolasaravena.models.Prioridad;
import com.aravena.certamenhabitosnicolasaravena.sqlite.DbCategoria;
import com.aravena.certamenhabitosnicolasaravena.sqlite.DbHabito;
import com.aravena.certamenhabitosnicolasaravena.sqlite.DbPrioridad;

import java.util.ArrayList;

public class UpdateHabito extends AppCompatActivity {
    EditText txtNombre,txtDescripcion,txtCant,txtCategoria,txtPrioridad;
    Button update , delete;
    Spinner spcat, sppriori;
    CheckBox cb;
    ArrayList<Categoria> categorias;
    ArrayList<Prioridad> prioridades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_habito);

        txtNombre = findViewById(R.id.txtNombreHabitoU);
        txtDescripcion = findViewById(R.id.txtDescripcionHabitoU);
        txtCant = findViewById(R.id.txtCantHabitoU);
        spcat = findViewById(R.id.spCatU);
        sppriori = findViewById(R.id.spPrioriU);
        update = findViewById(R.id.addHabito_buttonU);
        delete = findViewById(R.id.deleteHabito_button);
        cb = findViewById(R.id.cb);




        cargarSpinner();




        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Habito habito = (Habito) bundle.get("Habito");
        txtNombre.setText(habito.getNombre());
        txtDescripcion.setText(habito.getDescripcion());
        txtCant.setText(habito.getCantidad());



        int index = indexCategoria(habito);
        spcat.setSelection(index);
        int indexPriori = indexPrioridad(habito);
        sppriori.setSelection(indexPriori);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHabito dbhabito = new DbHabito(getApplicationContext());
                int id = habito.getId();
                String nombre = txtNombre.getText().toString();
                String descripcion = txtDescripcion.getText().toString();
                String cant = txtCant.getText().toString();
                //int hecho = Integer.parseInt(cb.getText().toString());
               // int hecho = Integer.parseInt(cb.getText().toString());
                Categoria categoria = (Categoria) spcat.getSelectedItem();
                Prioridad prioridad = (Prioridad) sppriori.getSelectedItem();
                Habito habitoEdit = new Habito(id,nombre,descripcion,cant,prioridad,categoria,habito.getHecho());

                dbhabito.actualizarHabito(habitoEdit);
                Toast.makeText(getApplicationContext(), "Habito modificado", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHabito dbHabito = new DbHabito(getApplicationContext());
                dbHabito.eliminarHabito(habito.getId());
                Toast.makeText(getApplicationContext(), "Habito eliminado", Toast.LENGTH_SHORT).show();
            }
        });













    }
    public int indexCategoria(Habito habito) {
        int index = 0;
        for (Categoria categoria : categorias) {

            if (categoria.toString().equals(habito.getCategoria().toString())) {
                return index;
            } else {
                index += 1;
            }
        }
        return -1;
    }

    public int indexPrioridad(Habito habito) {
        int index = 0;
        for (Prioridad prioridad : prioridades) {

            if (prioridad.toString().equals(habito.getPrioridad().toString())) {
                return index;
            } else {
                index += 1;
            }
        }
        return -1;
    }

    public void cargarSpinner(){
        DbCategoria dbcat = new DbCategoria(this);
        categorias = dbcat.getCategorias();
        if(categorias != null){
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,categorias);
            spcat.setAdapter(adapter);

        }


        DbPrioridad dbpriori = new DbPrioridad(this);
        prioridades = dbpriori.getPrioridades();
        if(prioridades != null){
            ArrayAdapter<Prioridad> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,prioridades);
            sppriori.setAdapter(adapter);

        }
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