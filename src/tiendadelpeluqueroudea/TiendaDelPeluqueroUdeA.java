/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tiendadelpeluqueroudea;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author blandonm
 * @author sarair
 * @author penalosan
 */
public class TiendaDelPeluqueroUdeA {

    // TODO code application logic here
    // Menú
    // Método para ejcutar comprar.
    // Método para calular artículos.
    // Método para generar los articulos
    private static final String[][] nombresProductos = {
        {"Shampoo Savital", "Shampoo Pantene", "Shampoo H&S"},// SH  
        {"Cera Ego", "Cera Revlon", "Cera Got2b"},// CE
        {"Secador Remington", "Secador Dyson", "Secador Conair"}, // SE
        {"Plancha GHD", "Plancha Babyliss", "Plancha Remington"}, //PL
        {"Crema Nivea", "Crema Dove", "Crema Eucerin"} //CR
    };

    // Array de precios de los productos
    private static final double[][] preciosProductos = {
        {15.99, 18.49, 17.29}, // Precios para Shampoo
        {9.99, 10.99, 11.49}, // Precios para Cera
        {59.99, 399.99, 29.99}, // Precios para Secador
        {99.99, 79.99, 49.99}, // Precios para Plancha
        {6.99, 7.49, 8.99} // Precios para Crema
    };

// Método para generar ID's
    public static String randomID(String codigo, int numero) {
        return String.format(codigo + "-%07d", numero);
    }

    public static boolean validarRandomID(String id) {
        String regex = "^[A-Z]{2}-\\d{7}$";
        return Pattern.matches(regex, id);
    }

    public static void main(String[] args) {
        Map<String, Producto> hashMapProductos = new HashMap<>();
        Random random = new Random();
        String[] codigo = {"SH", "CE", "SE", "PL", "CR"};
        
        // Creo las colas a llenar
        Queue<Producto> colaShampoos = new LinkedList<>();
        Queue<Producto> colaCeras = new LinkedList<>();
        Queue<Producto> colaSecadores = new LinkedList<>();
        Queue<Producto> colaPlanchas = new LinkedList<>();
        Queue<Producto> colaCremas = new LinkedList<>();

        // Generar 1,000,000 artículos
        for (int i = 0; i < 1000000; i++) {
            int randomIndex = random.nextInt(codigo.length);
            String randomCodigo = codigo[randomIndex];
            String id = randomID(randomCodigo, i + 1); // Genera un ID aleatorio

            if (validarRandomID(id)) {
                int posicionRandom = random.nextInt(nombresProductos[randomIndex].length);
                String nombreAleatorio = nombresProductos[randomIndex][posicionRandom];
                double precio = preciosProductos[randomIndex][posicionRandom];

                // Crear el producto y agregarlo al HashMap
                Producto producto = new Producto(id, nombreAleatorio, precio);
                hashMapProductos.put(id, producto);
            }
        }

        for (Producto producto : hashMapProductos.values()) {
            //System.out.println(producto);
            
            if(producto.getId().contains("SH")){
                colaShampoos.add(producto);
            } 
            else if(producto.getId().contains("CE")){
            colaCeras.add(producto);
            }
            else if(producto.getId().contains("SE")){
            colaSecadores.add(producto);
            }
            else if(producto.getId().contains("PL")){
            colaPlanchas.add(producto);
            }
            else if(producto.getId().contains("CR")){
            colaCremas.add(producto);
            }
            else{
            System.out.println(producto.toString() + " no se encuentra en las referencias");
            }
        }
        
        System.out.println("The size of the map is " + hashMapProductos.size()); 
    }
}
    


