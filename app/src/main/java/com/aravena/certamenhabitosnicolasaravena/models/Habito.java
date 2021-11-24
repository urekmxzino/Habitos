package com.aravena.certamenhabitosnicolasaravena.models;

import java.io.Serializable;

public class Habito implements Serializable {
    private int id;
    private String nombre;
    private String descripcion;
    private String cantidad;
    private Categoria categoria;
    private Prioridad prioridad;
    private boolean expandible;
    private int hecho;

    public Habito() {
    }

    public Habito(int id, String nombre, String descripcion, String cantidad,Prioridad prioridad, Categoria categoria, int hecho) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.prioridad = prioridad;
        this.expandible = false;
        this.hecho = hecho;
    }

    public Habito(String nombre, String descripcion, String cantidad,Prioridad prioridad, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.prioridad = prioridad;
        this.hecho = 0;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public boolean isExpandible() {
        return expandible;
    }

    public int getHecho() {
        return hecho;
    }

    public void setHecho(int hecho) {
        this.hecho = hecho;
    }

    public void setExpandible(boolean expandible) {
        this.expandible = expandible;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
