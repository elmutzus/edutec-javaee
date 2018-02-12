package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dto.ParametroDto;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author nahum
 */
@Path("/parametros")
public class ParametroEndpoint {
    
    @GET
    @Produces({"application/json"})
    public List<ParametroDto> findAll() {
        List<ParametroDto> parametros = new ArrayList<>();
        parametros.add( new ParametroDto(1, "Debug", "0"));
        parametros.add( new ParametroDto(2, "Titulo", "Curso Java EE con Angular 5"));
        parametros.add( new ParametroDto(3, "# Decimales", "2"));
        parametros.add( new ParametroDto(4, "Otro parametro", "10"));
        return parametros;
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Long id) {
        List<ParametroDto> parametros = new ArrayList<>();
        parametros.add( new ParametroDto(1, "Debug", "0"));
        parametros.add( new ParametroDto(2, "Titulo", "Curso Java EE con Angular 5"));
        parametros.add( new ParametroDto(3, "# Decimales", "2"));
        parametros.add( new ParametroDto(4, "Otro parametro", "10"));
        
        List<ParametroDto> results = parametros.stream()
                .filter(item -> item.getId().equals(id))
                .collect(Collectors.toList());        
        
        if (results.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();
        
        return Response.ok(results.get(0)).build();
    }
    
    @POST
    @Produces({"application/json"})
    public Response pruebaPOST(ParametroDto dto) {
        System.out.println("Prueba de POST");
        return Response.ok("Parametro creado").build();
    }

}
