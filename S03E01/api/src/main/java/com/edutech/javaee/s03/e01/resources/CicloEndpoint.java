/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dao.CicloDao;
import com.edutech.javaee.s03.e01.dto.CicloDto;
import com.edutech.javaee.s03.e01.model.Ciclo;
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
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/ciclos")
public class CicloEndpoint {

    final CicloDao clDao;

    public CicloEndpoint() {
        this.clDao = null;
    }

    @Inject
    public CicloEndpoint(CicloDao clDao) {
        this.clDao = clDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Ciclo> findAll() {
        return this.clDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Ciclo ciclo = this.clDao.find(id);

        if (ciclo == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(ciclo, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(CicloDto dto) {
        Ciclo ciclo = this.clDao.save(dto);
        return Response.ok(ciclo).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(CicloDto dto) throws RollbackException {
        Ciclo ciclo = this.clDao.edit(dto);
        return Response.ok(ciclo).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Ciclo ciclo = this.clDao.remove(id);
        return Response.ok(ciclo).build();
    }
}
