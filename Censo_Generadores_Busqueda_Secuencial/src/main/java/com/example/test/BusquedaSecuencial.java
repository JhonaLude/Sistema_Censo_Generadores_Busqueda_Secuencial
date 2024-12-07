/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.test;

/**
 *
 * @author Alexander Ludena
 */

public class BusquedaSecuencial {

    // Búsqueda secuencial
    public static int busquedaSecuencial(int[] arr, int elemento) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == elemento) {
                return i; // Retorna el índice si encuentra el elemento
            }
        }
        return -1; // Retorna -1 si no encuentra el elemento
    }

    // Método para mostrar todos los elementos del arreglo
    public static void mostrarArreglo(int[] arr, String mensaje) {
        System.out.println(mensaje);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
            if ((i + 1) % 20 == 0) {
                System.out.println(); 
            }
        }
        System.out.println("\nTotal de elementos: " + arr.length);
    }
}
