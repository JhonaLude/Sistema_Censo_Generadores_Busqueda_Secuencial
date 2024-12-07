/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.rest;

import com.example.controller.dao.services.GeneradorService;
import com.example.models.Generador;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Alexander Ludena
 */

@Path("/generadores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeneradorREST {

    private final GeneradorService generadorService = new GeneradorService();

    // Métodos CRUD 
    @GET
    public Response obtenerGeneradores() {
        return Response.ok(generadorService.obtenerTodosGeneradores()).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerGenerador(@PathParam("id") int id) {
        Generador generador = generadorService.obtenerGenerador(id);
        if (generador != null) {
            return Response.ok(generador).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response crearGenerador(Generador generador) {
        Generador nuevo = generadorService.registrarGenerador(
                generador.getMarca(),
                generador.getModelo(),
                generador.getCosto(),
                generador.getConsumoPorHora(),
                generador.getGeneracion(),
                generador.getUso()
        );
        return Response.status(Response.Status.CREATED).entity(nuevo).build();
    }

    // Métodos de ordenamiento
    @GET
    @Path("/ordenar/quicksort")
    public Response ordenarPorQuickSort(
            @QueryParam("atributo") String atributo,
            @QueryParam("ascendente") @DefaultValue("true") boolean ascendente) {
        try {
            generadorService.ordenarPorQuickSort(atributo, ascendente);
            return Response.ok(generadorService.obtenerTodosGeneradores()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/ordenar/mergesort")
    public Response ordenarPorMergeSort(
            @QueryParam("atributo") String atributo,
            @QueryParam("ascendente") @DefaultValue("true") boolean ascendente) {
        try {
            generadorService.ordenarPorMergeSort(atributo, ascendente);
            return Response.ok(generadorService.obtenerTodosGeneradores()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/ordenar/shellsort")
    public Response ordenarPorShellSort(
            @QueryParam("atributo") String atributo,
            @QueryParam("ascendente") @DefaultValue("true") boolean ascendente) {
        try {
            generadorService.ordenarPorShellSort(atributo, ascendente);
            return Response.ok(generadorService.obtenerTodosGeneradores()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // Método de búsqueda secuencial
    @GET
    @Path("/buscar")
    public Response busquedaSecuencial(
            @QueryParam("atributo") String atributo,
            @QueryParam("valor") String valorBuscado) {
        try {
            // Convertir el valor a su tipo apropiado en caso de ser necesario
            Object valor = convertirValor(atributo, valorBuscado);
            Generador[] resultados = generadorService.busquedaSecuencial(atributo, valor);
            return Response.ok(resultados).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    // Método de conversión de tipos para la búsqueda
    private Object convertirValor(String atributo, String valorBuscado) {
        try {
            switch (atributo.toLowerCase()) {
                case "id":
                case "consumoporhora":
                case "costo":
                    return Double.parseDouble(valorBuscado);
                case "generacion":
                    return Integer.parseInt(valorBuscado);
                default:
                    return valorBuscado;
            }
        } catch (NumberFormatException e) {
            return valorBuscado;
        }
    }
}
