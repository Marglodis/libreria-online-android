package com.abpro_ae4.libreriaonlineapp.models;

import java.math.BigDecimal;

public class Libro {
    private String titulo;
    private String descripcion;
    private int imagenResourceId; //ID del recurso drawable
    private int cantidadEnCarrito;
    private BigDecimal precio;

    public Libro(String titulo, String descripcion, int imagenResourceId, BigDecimal precio) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagenResourceId = imagenResourceId;
        this.precio = precio;
        this.cantidadEnCarrito = 0;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getImagenResourceId() {
        return imagenResourceId;
    }
    public void setImagenResourceId(int imagenResourceId) { this.imagenResourceId = imagenResourceId; }

    public int getCantidadEnCarrito() {
        return cantidadEnCarrito;
    }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    /**
     * Calcula el subtotal de este libro en el carrito.
     * @return El precio total para la cantidad actual en el carrito.
     */
    public BigDecimal calcularSubtotal() {
        return this.precio.multiply(BigDecimal.valueOf(this.cantidadEnCarrito));
    }
    /**
     * Agrega una unidad de este libro al carrito.
     */
    public void agregarAlCarrito() {
        this.cantidadEnCarrito++;
    }

    /**
     * Elimina una unidad de este libro del carrito.
     * Si la cantidad es 0 o menor, no hace nada.
     */
    public void eliminarDelCarrito() {
        if (this.cantidadEnCarrito > 0) {
            this.cantidadEnCarrito--;
        }
    }

}
