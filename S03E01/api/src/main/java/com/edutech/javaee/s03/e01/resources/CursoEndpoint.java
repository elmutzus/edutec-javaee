/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dao.CursoDao;
import com.edutech.javaee.s03.e01.dto.CursoDto;
import com.edutech.javaee.s03.e01.model.Curso;
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
@Path("/cursos")
public class CursoEndpoint {

    final CursoDao crDao;

    public CursoEndpoint() {
        this.crDao = null;
    }

    @Inject
    public CursoEndpoint(CursoDao crDao) {
        this.crDao = crDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Curso> findAll() {
        return this.crDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Curso cr = this.crDao.find(id);

        if (cr == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(cr, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(CursoDto dto) {
        Curso ap = this.crDao.save(dto);
        return Response.ok(ap).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(CursoDto dto) throws RollbackException {
        Curso cr = this.crDao.edit(dto);
        return Response.ok(cr).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Curso cr = this.crDao.remove(id);
        return Response.ok(cr).build();
    }
}
