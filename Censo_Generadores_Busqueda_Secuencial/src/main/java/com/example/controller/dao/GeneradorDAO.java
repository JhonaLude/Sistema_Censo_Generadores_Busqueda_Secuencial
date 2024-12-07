/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao;

import com.example.models.Generador;
import com.example.tda.list.LinkedList;
import java.lang.reflect.Method;

/**
 *
 * @author Alexander Ludena
 */

public class GeneradorDAO {

    private LinkedList<Generador> generadores;
    private int contador;

    public GeneradorDAO() {
        this.generadores = new LinkedList<>();
        this.contador = 0;
    }

    // Métodos
    public Generador crear(Generador generador) {
        generador.setId(++contador);
        generadores.add(generador);
        return generador;
    }

    public Generador[] obtenerTodos() {
        return generadores.toArray();
    }

    public Generador obtenerPorId(int id) {
        for (int i = 0; i < generadores.getSize(); i++) {
            try {
                Generador gen = generadores.get(i);
                if (gen.getId() == id) {
                    return gen;
                }
            } catch (Exception e) {
                // Manejo de excepciones
            }
        }
        return null;
    }

    public boolean actualizar(Generador generador) {
        for (int i = 0; i < generadores.getSize(); i++) {
            try {
                if (generadores.get(i).getId() == generador.getId()) {
                    generadores.update(generador, i);
                    return true;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        for (int i = 0; i < generadores.getSize(); i++) {
            try {
                if (generadores.get(i).getId() == id) {
                    generadores.delete(i);
                    return true;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return false;
    }

    // Métodos de ordenamiento
    public void ordenarPorQuickSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            generadores.quickSortByAttribute(atributo);
        } else {
            generadores = generadores.order(atributo, 0);
        }
    }

    public void ordenarPorMergeSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            generadores.mergeSortByAttribute(atributo);
        } else {
            generadores = generadores.order(atributo, 0);
        }
    }

    public void ordenarPorShellSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            generadores.shellSortByAttribute(atributo);
        } else {
            generadores = generadores.order(atributo, 0);
        }
    }

    // Método de búsqueda secuencial 
    public Generador[] busquedaSecuencial(String atributo, Object valorBuscado) throws Exception {
        LinkedList<Generador> resultados = new LinkedList<>();

        for (int i = 0; i < generadores.getSize(); i++) {
            try {
                Generador generador = generadores.get(i);

                String metodoNombre = "get" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
                Method metodoGetter = Generador.class.getMethod(metodoNombre);

                // Obtener el valor del atributo
                Object valorAtributo = metodoGetter.invoke(generador);

                // Comparar el valor
                if (valorAtributo != null && valorAtributo.equals(valorBuscado)) {
                    resultados.add(generador);
                }
            } catch (Exception e) {

                System.err.println("Error en búsqueda secuencial: " + e.getMessage());
            }
        }

        return resultados.toArray();
    }
}
