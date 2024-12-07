/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller.dao;

import com.example.models.Familia;
import com.example.tda.list.LinkedList;
import java.lang.reflect.Method;

/**
 *
 * @author Alexander Ludena
 */

public class FamiliaDAO {

    private LinkedList<Familia> familias;
    private int contador;

    public FamiliaDAO() {
        this.familias = new LinkedList<>();
        this.contador = 0;
    }

    // Métodos 
    public Familia crear(Familia familia) {
        familia.setId(++contador);
        familias.add(familia);
        return familia;
    }

    public Familia[] obtenerTodos() {
        return familias.toArray();
    }

    public Familia obtenerPorId(int id) {
        for (int i = 0; i < familias.getSize(); i++) {
            try {
                Familia fam = familias.get(i);
                if (fam.getId() == id) {
                    return fam;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return null;
    }

    public boolean actualizar(Familia familia) {
        for (int i = 0; i < familias.getSize(); i++) {
            try {
                if (familias.get(i).getId() == familia.getId()) {
                    familias.update(familia, i);
                    return true;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return false;
    }

    public boolean eliminar(int id) {
        for (int i = 0; i < familias.getSize(); i++) {
            try {
                if (familias.get(i).getId() == id) {
                    familias.delete(i);
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
            familias.quickSortByAttribute(atributo);
        } else {
            familias = familias.order(atributo, 0); 
        }
    }

    public void ordenarPorMergeSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            familias.mergeSortByAttribute(atributo);
        } else {
            familias = familias.order(atributo, 0); 
        }
    }

    public void ordenarPorShellSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            familias.shellSortByAttribute(atributo);
        } else {
            familias = familias.order(atributo, 0); 
        }
    }

    // Método de Búsqueda secuencial
    public Familia[] busquedaSecuencial(String atributo, Object valorBuscado) throws Exception {
        LinkedList<Familia> resultados = new LinkedList<>();

        for (int i = 0; i < familias.getSize(); i++) {
            try {
                Familia estadistica = familias.get(i);

                
                String metodoNombre = "get" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
                Method metodoGetter = Familia.class.getMethod(metodoNombre);

                // Obtener el valor del atributo
                Object valorAtributo = metodoGetter.invoke(estadistica);

                // Comparar el valor
                if (valorAtributo != null && valorAtributo.equals(valorBuscado)) {
                    resultados.add(estadistica);
                }
            } catch (Exception e) {
                
                System.err.println("Error en búsqueda secuencial: " + e.getMessage());
            }
        }

        return resultados.toArray();
    }

}
