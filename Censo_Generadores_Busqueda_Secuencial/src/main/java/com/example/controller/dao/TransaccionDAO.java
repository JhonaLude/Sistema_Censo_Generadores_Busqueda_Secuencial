/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao;

import com.example.models.Transaccion;
import com.example.tda.list.LinkedList;
import java.lang.reflect.Method;

/**
 *
 * @author Alexander Ludena
 */

public class TransaccionDAO {

    private LinkedList<Transaccion> transacciones;
    private int contador;

    public TransaccionDAO() {
        this.transacciones = new LinkedList<>();
        this.contador = 0;
    }

    // Métodos 
    public Transaccion crear(Transaccion transaccion) {
        transaccion.setId(++contador);
        transacciones.add(transaccion);
        return transaccion;
    }

    public Transaccion[] obtenerTodos() {
        return transacciones.toArray();
    }

    public Transaccion obtenerPorId(int id) {
        for (int i = 0; i < transacciones.getSize(); i++) {
            try {
                Transaccion transaccion = transacciones.get(i);
                if (transaccion.getId() == id) {
                    return transaccion;
                }
            } catch (Exception e) {
                // Manejo de excepciones
            }
        }
        return null;
    }

    public Transaccion[] obtenerPorFamiliaId(int familiaId) {
        LinkedList<Transaccion> resultado = new LinkedList<>();

        for (int i = 0; i < transacciones.getSize(); i++) {
            try {
                Transaccion transaccion = transacciones.get(i);
                if (transaccion.getFamiliaId() == familiaId) {
                    resultado.add(transaccion);
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return resultado.toArray();
    }

    public boolean actualizar(Transaccion transaccion) {
        for (int i = 0; i < transacciones.getSize(); i++) {
            try {
                if (transacciones.get(i).getId() == transaccion.getId()) {
                    transacciones.update(transaccion, i);
                    return true;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        for (int i = 0; i < transacciones.getSize(); i++) {
            try {
                if (transacciones.get(i).getId() == id) {
                    transacciones.delete(i);
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
            transacciones.quickSortByAttribute(atributo);
        } else {
            transacciones = transacciones.order(atributo, 0);
        }
    }

    public void ordenarPorMergeSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            transacciones.mergeSortByAttribute(atributo);
        } else {
            transacciones = transacciones.order(atributo, 0);
        }
    }

    public void ordenarPorShellSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            transacciones.shellSortByAttribute(atributo);
        } else {
            transacciones = transacciones.order(atributo, 0);
        }
    }

    // Método de búsqueda secuencial
    public Transaccion[] busquedaSecuencial(String atributo, Object valorBuscado) throws Exception {
        LinkedList<Transaccion> resultados = new LinkedList<>();

        for (int i = 0; i < transacciones.getSize(); i++) {
            try {
                Transaccion transaccion = transacciones.get(i);

                String metodoNombre = "get" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
                Method metodoGetter = Transaccion.class.getMethod(metodoNombre);

                // Obtener el valor del atributo
                Object valorAtributo = metodoGetter.invoke(transaccion);

                // Comparar el valor
                if (valorAtributo != null && valorAtributo.equals(valorBuscado)) {
                    resultados.add(transaccion);
                }
            } catch (Exception e) {

                System.err.println("Error en búsqueda secuencial: " + e.getMessage());
            }
        }

        return resultados.toArray();
    }

}
