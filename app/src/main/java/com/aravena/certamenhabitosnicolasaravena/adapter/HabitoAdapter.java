package com.aravena.certamenhabitosnicolasaravena.adapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aravena.certamenhabitosnicolasaravena.HabitoList;
import com.aravena.certamenhabitosnicolasaravena.R;
import com.aravena.certamenhabitosnicolasaravena.UpdateHabito;
import com.aravena.certamenhabitosnicolasaravena.models.Categoria;
import com.aravena.certamenhabitosnicolasaravena.models.Habito;
import com.aravena.certamenhabitosnicolasaravena.models.Prioridad;
import com.aravena.certamenhabitosnicolasaravena.sqlite.DbCategoria;
import com.aravena.certamenhabitosnicolasaravena.sqlite.DbHabito;
import com.aravena.certamenhabitosnicolasaravena.sqlite.DbPrioridad;

import org.w3c.dom.Text;

import java.io.DataInput;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class HabitoAdapter extends RecyclerView.Adapter<HabitoAdapter.ViewHolder>{
    ArrayList<Habito> listaHabitos;
    ArrayList<Habito> listaOriginal;


    public HabitoAdapter(ArrayList<Habito> lista){
        this.listaHabitos = lista;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaHabitos);
    }



    @NonNull
    @Override
    public HabitoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_habito,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HabitoAdapter.ViewHolder holder, int position) {
        holder.cargarHabito(listaHabitos.get(position));
        boolean isExpandible = listaHabitos.get(position).isExpandible();
        holder.extendiblelayout.setVisibility(isExpandible ? View.VISIBLE : View.GONE);
        holder.extendiblelayout.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(holder.extendiblelayout.getContext(), UpdateHabito.class);
                        intent.putExtra("Habito", listaHabitos.get(position));
                        holder.extendiblelayout.getContext().startActivity(intent);
                    }
                });
    }

    public void filtrado(String txtBuscar) {
        int longitud = txtBuscar.length();

        listaHabitos.clear();
        listaHabitos.addAll(listaOriginal);
        if (longitud != 0) {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Habito> coleccion =
                        listaHabitos.stream()
                                .filter(i -> i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                                .collect(Collectors.toList());
                List<Habito> coleccion1 =
                        listaHabitos.stream()
                                .filter(i -> i.getPrioridad().getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                                .collect(Collectors.toList());
                List<Habito> coleccion2 =
                        listaHabitos.stream()
                                .filter(i -> i.getCategoria().getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                                .collect(Collectors.toList());

                listaHabitos.clear();
                listaHabitos.addAll(coleccion);
                listaHabitos.addAll(coleccion1);
                listaHabitos.addAll(coleccion2);


            } else {
                for (Habito l : listaOriginal) {
                    if (l.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        listaHabitos.add(l);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaHabitos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNombre,txtDescripcion,txtCant,txtCategoria,txtPrioridad;
        LinearLayout linearLayout;
        RelativeLayout extendiblelayout;
        CheckBox cb;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombreH);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcionH);
            txtCant = itemView.findViewById(R.id.txtCantH);
            txtCategoria = itemView.findViewById(R.id.txtCategoriaH);
            txtPrioridad = itemView.findViewById(R.id.txtPrioridadH);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            extendiblelayout = itemView.findViewById(R.id.expandible_layout);
            cb = itemView.findViewById(R.id.cb);

            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /*Date date = new Date();
                    int clicks = 0;
                    clicks++;

                    if (clicks >1){
                        cb.setSelected(false);
                    }

                    SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", this.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("clicks", clicks);
                    editor.putString("date", date.toString());
                    editor.apply();
*/

                    Habito h = listaHabitos.get(getAdapterPosition());
                    DbHabito dbhabito = new DbHabito(itemView.getContext());
                    int id = h.getId();
                    String nombre = txtNombre.getText().toString();
                    String descripcion = txtDescripcion.getText().toString();
                    String fecha =txtCant.getText().toString();
                    Categoria categoria = h.getCategoria();

                    Prioridad prioridad = h.getPrioridad();
                    int hecho ;
                    if(cb.isChecked()){
                        hecho =1;
                    }else{
                        hecho = 0;
                    }
                    Habito habito = new Habito(id,nombre,descripcion,fecha,prioridad,categoria,hecho);
                    dbhabito.actualizarHabito(habito);

                }
            });

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Habito habito = listaHabitos.get(getAdapterPosition());
                    habito.setExpandible(!habito.isExpandible());
                    notifyItemChanged(getAdapterPosition());
                }
            });


        }

        public void cargarHabito(Habito h){
            int hecho = h.getHecho();
            if(hecho == 1){
                cb.setChecked(true);
            }else{
                cb.setChecked(false);
            }
            txtNombre.setText(h.getNombre());
            txtDescripcion.setText(h.getDescripcion());
            txtCant.setText(h.getCantidad());
            txtCategoria.setText(h.getCategoria().getNombre());
            txtPrioridad.setText(h.getPrioridad().getNombre());



        }
    }

}
