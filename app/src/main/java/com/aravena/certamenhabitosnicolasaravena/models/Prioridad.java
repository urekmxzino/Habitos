package com.aravena.certamenhabitosnicolasaravena.models;

import java.io.Serializable;

public class Prioridad implements Serializable {
    private int id;
    private String nombre;

    public Prioridad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Prioridad(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String toString() {
        return this.nombre;
    }
}
