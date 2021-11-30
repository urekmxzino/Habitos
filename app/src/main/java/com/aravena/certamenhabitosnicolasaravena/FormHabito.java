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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class FormHabito extends AppCompatActivity {
    EditText txtNombre,txtDescripcion,txtCant,txtCategoria,txtPrioridad;
    Button addBtn;
    Spinner spcat, sppriori;

    public void cargarSpinner(){
        DbCategoria dbcat = new DbCategoria(this);
        ArrayList<Categoria> categorias = dbcat.getCategorias();
        if(categorias != null){
            ArrayAdapter<Categoria> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,categorias);
            spcat.setAdapter(adapter);

        }


        DbPrioridad dbpriori = new DbPrioridad(this);
        ArrayList<Prioridad> prioridades = dbpriori.getPrioridades();
        if(prioridades != null){
            ArrayAdapter<Prioridad> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,prioridades);
            sppriori.setAdapter(adapter);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_habito);




        txtNombre = findViewById(R.id.txtNombreHabito);
        txtDescripcion = findViewById(R.id.txtDescripcionHabito);
       // txtCant = findViewById(R.id.txtCantHabito);
        spcat = findViewById(R.id.spCat);
        sppriori = findViewById(R.id.spPriori);


        addBtn = findViewById(R.id.addHabito_button);


        cargarSpinner();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();
                String descripcion = txtDescripcion.getText().toString();
                //String cant = txtCant.getText().toString();
                String txtCant;
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                Categoria categoria = (Categoria) spcat.getSelectedItem();
                Prioridad prioridad = (Prioridad) sppriori.getSelectedItem();



                Habito h = new Habito(nombre,descripcion,currentDate,prioridad,categoria);

                DbHabito db = new DbHabito(getApplicationContext());





                if (nombre.equals("")){
                    Toast.makeText(getApplicationContext(), "No ingresaste un nombre", Toast.LENGTH_SHORT).show();
                }else if(descripcion.equals("")){
                    Toast.makeText(getApplicationContext(), "No ingresaste una descripción", Toast.LENGTH_SHORT).show();
                }else{
                    long id = db.insertarHabito(h);
                    if(id>=0){
                        Toast.makeText(FormHabito.this,
                                "Habito insertado", Toast.LENGTH_LONG).show();
                        txtNombre.setText("");
                        txtDescripcion.setText("");
                        //txtCant.setText("");
                    }else {
                        Toast.makeText(FormHabito.this,
                                "Error al insertar", Toast.LENGTH_LONG).show();
                    }
                }



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