package com.example;

import java.util.Scanner;

public class MaquinaExpendedora {
    
    String[] productos = {"Coca Cola", "Agua", "Snacks", "Chips"};
    double[] precios = {6000, 3000, 4000, 3500}; 
    int[] cantidades = {10, 5, 8, 6};

    public static void main(String[] args) {
        MaquinaExpendedora maquina = new MaquinaExpendedora();
        maquina.iniciar();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            System.out.print("Ingrese el nombre del producto que desea comprar (o 'salir' para terminar): ");
            String productoSeleccionado = scanner.nextLine();

            if (productoSeleccionado.equalsIgnoreCase("salir")) {
                continuar = false;
                System.out.println("Gracias por usar la máquina expendedora. ¡Hasta luego!");
                continue;
            }

            int indiceProducto = seleccionarProducto(productoSeleccionado);
            if (indiceProducto != -1) {
                System.out.print("Ingrese la cantidad de dinero: ");
                double dineroIngresado = scanner.nextDouble();
                scanner.nextLine(); 
                if (verificarPago(indiceProducto, dineroIngresado)) {
                    entregarProducto(indiceProducto);
                    double cambio = calcularCambio(indiceProducto, dineroIngresado);
                    System.out.println("Su cambio es: $" + cambio + " COP");
                } else {
                    System.out.println("Pago insuficiente. El producto cuesta $" + precios[indiceProducto] + " COP");
                }
            } else {
                System.out.println("Producto no disponible. Intente de nuevo.");
            }
        }

        scanner.close();
    }

    public void mostrarMenu() {
        System.out.println("---- Menú de la Máquina Expendedora ----");
        for (int i = 0; i < productos.length; i++) {
            System.out.println(productos[i] + " - $" + precios[i] + " COP (Disponibles: " + cantidades[i] + ")");
        }
        System.out.println("---------------------------------------");
    }

    public int seleccionarProducto(String nombreProducto) {
        for (int i = 0; i < productos.length; i++) {
            if (productos[i].equalsIgnoreCase(nombreProducto)) {
                return i;
            }
        }
        return -1; 
    }

    public boolean verificarPago(int indiceProducto, double dineroIngresado) {
        return dineroIngresado >= precios[indiceProducto];
    }

    public double calcularCambio(int indiceProducto, double dineroIngresado) {
        return dineroIngresado - precios[indiceProducto];
    }

    public void entregarProducto(int indiceProducto) {
        if (cantidades[indiceProducto] > 0) {
            cantidades[indiceProducto]--; 
            System.out.println("Producto entregado: " + productos[indiceProducto]);
        } else {
            System.out.println("Error: No hay stock de " + productos[indiceProducto]);
        }
    }
}
