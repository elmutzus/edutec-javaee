package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dao.DepartamentoDao;
import com.edutech.javaee.s03.e01.dto.DepartamentoDto;
import com.edutech.javaee.s03.e01.model.Departamento;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.RollbackException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author nahum
 */
@Stateless
@Path("/departamentos")
public class DepartamentoEndpoint {

    final DepartamentoDao dptDao;

    public DepartamentoEndpoint() {
        this.dptDao = null;
    }

    @Inject
    public DepartamentoEndpoint(DepartamentoDao dptDao) {
        this.dptDao = dptDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Departamento> findAll() {
        return this.dptDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Departamento dp = this.dptDao.find(id);

        if (dp == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(dp, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(DepartamentoDto dto) {
        Departamento ap = this.dptDao.save(dto);
        return Response.ok(ap).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(DepartamentoDto dto) throws RollbackException {
        Departamento dp = this.dptDao.edit(dto);
        return Response.ok(dp).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Departamento dp = this.dptDao.remove(id);
        return Response.ok(dp).build();
    }
}
