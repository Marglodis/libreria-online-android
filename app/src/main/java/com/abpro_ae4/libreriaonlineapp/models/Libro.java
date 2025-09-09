package com.abpro_ae4.libreriaonlineapp.models;

public class Libro {
    private String titulo;
    private String descripcion;
    private int imagenResorceId; //ID del recurso drawable

    public Libro(String titulo, String descripcion, int imagenResorceId) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenResorceId = imagenResorceId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImagenResorceId() {
        return imagenResorceId;
    }
}
