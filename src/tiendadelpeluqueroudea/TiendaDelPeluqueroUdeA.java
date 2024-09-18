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
import java.util.*;

/**
 *
 * @author blandonm
 * @author sarair
 * @author penalosan
 */
public class TiendaDelPeluqueroUdeA {

    public static Scanner scanner = new Scanner(System.in);

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

    // Creo las colas a llenar
    public static Queue<Producto> colaShampoos = new LinkedList<>();
    public static Queue<Producto> colaCeras = new LinkedList<>();
    public static Queue<Producto> colaSecadores = new LinkedList<>();
    public static Queue<Producto> colaPlanchas = new LinkedList<>();
    public static Queue<Producto> colaCremas = new LinkedList<>();
    public static double precioPorVender = 0;
    public static double precioInicial = 0;

    public static int menu() {
        while (true) {
            int opcion1 = 0;
            System.out.println("Seleccione una opción:");
            System.out.println("1. Comprar producto");
            System.out.println("2. Finalizar el programa");

            try {
                opcion1 = scanner.nextInt();
                return opcion1;
            } catch (Exception e) {
                return opcion1;
            }
        }
    }

    public static int submenu() {
        int opcion2 = 0;
        System.out.println("Seleccione el producto:");
        System.out.println("1. Shampo para el cabello.");
        System.out.println("2. Cera para peinar.");
        System.out.println("3. Secador de cabello.");
        System.out.println("4. Plancha de cabello.");
        System.out.println("5. Crema humectante.");
        
        try {
            opcion2 = scanner.nextInt();
            return opcion2;
        } catch (Exception e) {
            return opcion2;
        }
        
    }

    // Método para comprar productos
    private static void comprarProductos(int opcion) {
        int cantidad = 0;
        System.out.println("Ingresa la cantidad del producto que deseas llevar");
        
        try {
            cantidad = scanner.nextInt();
        } catch (Exception e) {
            opcion = 0;
        }
        

        switch (opcion) {
            case 1:
                if (cantidad > colaShampoos.size()) {
                    System.out.println("No tenemos suficientes articulos");
                    return;
                } else {
                    for (int i = 1; i <= cantidad; i++) {
                        Producto shampoo = colaShampoos.poll();
                        precioPorVender -= shampoo.getPrecio();
                    }
                }
                break;
            case 2:
                if (cantidad > colaCeras.size()) {
                    System.out.println("No tenemos suficientes articulos");
                    return;
                } else {
                    for (int i = 1; i <= cantidad; i++) {

                        Producto cera = colaCeras.poll();
                        precioPorVender -= cera.getPrecio();
                    }
                }
                break;
            case 3:
                if (cantidad > colaSecadores.size()) {
                    System.out.println("No tenemos suficientes articulos");
                    return;
                } else {
                    for (int i = 1; i <= cantidad; i++) {
                        Producto secador = colaSecadores.poll();
                        precioPorVender -= secador.getPrecio();
                    }
                }
                break;
            case 4:
                if (cantidad > colaPlanchas.size()) {
                    System.out.println("No tenemos suficientes articulos");
                    return;
                } else {
                    for (int i = 1; i <= cantidad; i++) {
                        Producto plancha = colaPlanchas.poll();
                        precioPorVender -= plancha.getPrecio();
                    }
                }
                break;
            case 5:
                if (cantidad > colaCremas.size()) {
                    System.out.println("No tenemos suficientes articulos");
                    return;
                } else {
                    for (int i = 1; i <= cantidad; i++) {
                        Producto crema = colaCremas.poll();
                        precioPorVender -= crema.getPrecio();
                    }
                }
                break;
            default:
                System.out.println("Opción no válida. Intente nuevamente.");
        }
    }

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
            precioPorVender += producto.getPrecio();
        }

        precioInicial = precioPorVender;

        System.out.println(precioPorVender);

        // Llenado de colas desde el hashmap
        for (Producto producto : hashMapProductos.values()) {
            //System.out.println(producto);

            if (producto.getId().contains("SH")) {
                colaShampoos.add(producto);

            } else if (producto.getId().contains("CE")) {
                colaCeras.add(producto);

            } else if (producto.getId().contains("SE")) {
                colaSecadores.add(producto);

            } else if (producto.getId().contains("PL")) {
                colaPlanchas.add(producto);

            } else if (producto.getId().contains("CR")) {
                colaCremas.add(producto);

            } else {
                System.out.println(producto.toString() + " no se encuentra en las referencias");
            }
        }

        hashMapProductos.clear();

        while (true) {
            scanner = new Scanner(System.in);
            int opcion = menu();

            if (opcion == 1) {
                int opcion2 = submenu();
                if (opcion2 > 5 || opcion2 < 1) {
                    System.out.println("Opcion no valida, intente nuevamente");
                } else {
                    comprarProductos(opcion2);
                }
            } else if (opcion == 2) {
                int articulosPorVender = colaShampoos.size() + colaSecadores.size() + colaCeras.size() + colaPlanchas.size() + colaCremas.size();
                System.out.println("El precio total de los articulos pendientes por vender es: " + precioPorVender);
                System.out.println("La cantidad de articulos pendientes por vender es: " + articulosPorVender);
                System.out.println("Se vendieron " + (1000000 - articulosPorVender) + " articulos, por un valor total de: " + (precioInicial - precioPorVender) + " pesos");
                System.out.println("Muchas gracias por usar nuestros servicios!");
                break;
            } else {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}
