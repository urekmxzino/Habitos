package com.aravena.certamenhabitosnicolasaravena;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aravena.certamenhabitosnicolasaravena.models.Categoria;
import com.aravena.certamenhabitosnicolasaravena.models.Prioridad;
import com.aravena.certamenhabitosnicolasaravena.sqlite.DbCategoria;
import com.aravena.certamenhabitosnicolasaravena.sqlite.DbPrioridad;

public class FormPrioridad extends AppCompatActivity {

    EditText txtNombre;
    Button addBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_prioridad);





        txtNombre = findViewById(R.id.txtNombrePriori);
        addBtn = findViewById(R.id.addPrioridad_Button1);







        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();

                Prioridad p = new Prioridad(nombre);
                DbPrioridad dbpriori = new DbPrioridad(getApplicationContext());




                if (nombre.equals("")){
                    Toast.makeText(getApplicationContext(), "No ingresaste un nombre de Prioridad", Toast.LENGTH_SHORT).show();
                }
                else{
                    long id = dbpriori.insertarPrioridad(p);
                    if (id >= 0) {
                        Toast.makeText(FormPrioridad.this, nombre + " insertada", Toast.LENGTH_SHORT).show();
                        txtNombre.setText("");
                    } else {
                        Toast.makeText(FormPrioridad.this, "Error al insertar", Toast.LENGTH_SHORT).show();
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