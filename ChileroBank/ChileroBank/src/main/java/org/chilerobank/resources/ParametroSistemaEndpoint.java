/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.chilerobank.dto.ParametroSistemaDto;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/parametros")
public class ParametroSistemaEndpoint {

    @GET
    @Produces({"application/json"})
    public List<ParametroSistemaDto> findAll() {
        List<ParametroSistemaDto> parametros = new ArrayList<>();
        parametros.add(new ParametroSistemaDto(1, "Debug", "0"));
        parametros.add(new ParametroSistemaDto(2, "Titulo", "Curso Java EE con Angular 5"));
        parametros.add(new ParametroSistemaDto(3, "# Decimales", "2"));
        parametros.add(new ParametroSistemaDto(4, "Otro parametro", "10"));
        return parametros;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Long id) {
        List<ParametroSistemaDto> parametros = new ArrayList<>();
        parametros.add(new ParametroSistemaDto(1, "Debug", "0"));
        parametros.add(new ParametroSistemaDto(2, "Titulo", "Curso Java EE con Angular 5"));
        parametros.add(new ParametroSistemaDto(3, "# Decimales", "2"));
        parametros.add(new ParametroSistemaDto(4, "Otro parametro", "10"));

        List<ParametroSistemaDto> results = parametros.stream()
                .filter(item -> item.getId().equals(id))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(results.get(0)).build();
    }

    @POST
    @Produces({"application/json"})
    public Response pruebaPOST(ParametroSistemaDto dto) {
        System.out.println("Prueba de POST");
        return Response.ok("Parametro creado").build();
    }
}
