package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dao.ParametroSistemaDao;
import com.edutech.javaee.s03.e01.dto.ParametroDto;
import com.edutech.javaee.s03.e01.model.ParametroSistema;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nahum
 */
@Path("/parametros")
public class ParametroSistemaEndpoint {

    final ParametroSistemaDao psDao;

    public ParametroSistemaEndpoint() {
        this.psDao = null;
    }

    @Inject
    public ParametroSistemaEndpoint(ParametroSistemaDao psDao) {
        this.psDao = psDao;
    }

    @GET
    @Produces({"application/json"})
    public List<ParametroSistema> findAll() {
        return this.psDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        ParametroSistema ps = this.psDao.find(id);

        if (ps == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(ps, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Produces({"application/json"})
    public Response pruebaPOST(ParametroDto dto) {
        System.out.println("Prueba de POST");
        return Response.ok("Parametro creado").build();
    }

}
