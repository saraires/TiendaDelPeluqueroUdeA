/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tiendadelpeluqueroudea;

/**
 *
 * @author sarai
 */
public class Producto {

    private String id;
    private String nombre;
    private Double precio;

    public Producto(String id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    
     // Implementación del método toString()
    @Override
    public String toString() {
        return "ID: " + id + ", Nombre: " + nombre + ", Precio: " + precio;
    }

    public Double getPrecio() {
        return precio;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

}
