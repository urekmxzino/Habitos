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
import com.aravena.certamenhabitosnicolasaravena.sqlite.DbCategoria;

public class FormCategoria extends AppCompatActivity {
    EditText txtNombre;
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_categoria);





        txtNombre = findViewById(R.id.txtNombreCat);
        addBtn = findViewById(R.id.addCategoria_button);







        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombre.getText().toString();

                Categoria c = new Categoria(nombre);
                DbCategoria dbcat = new DbCategoria(getApplicationContext());
                long id = dbcat.insertarCategoria(c);
                if(id>=0){
                    Toast.makeText(FormCategoria.this,nombre+" insertada",Toast.LENGTH_SHORT).show();
                    txtNombre.setText("");
                }else{
                    Toast.makeText(FormCategoria.this,"Error al insertar",Toast.LENGTH_SHORT).show();
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