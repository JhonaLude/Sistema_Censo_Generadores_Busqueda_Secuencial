package com.example.controller.dao;

import com.example.models.Estadistica;
import com.example.tda.list.LinkedList;
import java.lang.reflect.Method;

/**
 *
 * @author Alexander Ludena
 */

public class EstadisticaDAO {

    private LinkedList<Estadistica> estadisticas;

    public EstadisticaDAO() {
        this.estadisticas = new LinkedList<>();
    }
    
    // Métodos 
    public boolean agregarEstadistica(Estadistica estadistica) {
        try {
            estadisticas.add(estadistica);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Estadistica[] obtenerTodas() {
        return estadisticas.toArray();
    }

    public Estadistica obtenerPorId(int id) {
        for (int i = 0; i < estadisticas.getSize(); i++) {
            try {
                Estadistica est = estadisticas.get(i);
                if (est.getId() == id) {
                    return est;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return null;
    }

    // Métodos de ordenamiento
    public void ordenarPorQuickSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            estadisticas.quickSortByAttribute(atributo);
        } else {
            estadisticas = estadisticas.order(atributo, 0); 
        }
    }

    public void ordenarPorMergeSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            estadisticas.mergeSortByAttribute(atributo);
        } else {
            estadisticas = estadisticas.order(atributo, 0); 
        }
    }

    public void ordenarPorShellSort(String atributo, boolean ascendente) throws Exception {
        if (ascendente) {
            estadisticas.shellSortByAttribute(atributo);
        } else {
            estadisticas = estadisticas.order(atributo, 0); 
        }
    }


    public boolean actualizarEstadistica(Estadistica estadistica) {
        for (int i = 0; i < estadisticas.getSize(); i++) {
            try {
                if (estadisticas.get(i).getId() == estadistica.getId()) {
                    estadisticas.update(estadistica, i);
                    return true;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return false;
    }

    public boolean eliminarEstadistica(int id) {
        for (int i = 0; i < estadisticas.getSize(); i++) {
            try {
                if (estadisticas.get(i).getId() == id) {
                    estadisticas.delete(i);
                    return true;
                }
            } catch (Exception e) {
                // Manejo de excepciones 
            }
        }
        return false;
    }
    
    //Método de Búsqueda secuencial
    public Estadistica[] busquedaSecuencial(String atributo, Object valorBuscado) throws Exception {
        LinkedList<Estadistica> resultados = new LinkedList<>();

        for (int i = 0; i < estadisticas.getSize(); i++) {
            try {
                Estadistica estadistica = estadisticas.get(i);

                String metodoNombre = "get" + atributo.substring(0, 1).toUpperCase() + atributo.substring(1);
                Method metodoGetter = Estadistica.class.getMethod(metodoNombre);

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
