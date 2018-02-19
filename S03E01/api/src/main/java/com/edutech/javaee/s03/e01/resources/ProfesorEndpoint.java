/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dao.ProfesorDao;
import com.edutech.javaee.s03.e01.dto.ProfesorDto;
import com.edutech.javaee.s03.e01.model.Profesor;
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
@Path("/profesores")
public class ProfesorEndpoint {

    final ProfesorDao prfDao;

    public ProfesorEndpoint() {
        this.prfDao = null;
    }

    @Inject
    public ProfesorEndpoint(ProfesorDao prfDao) {
        this.prfDao = prfDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Profesor> findAll() {
        return this.prfDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Profesor profesor = this.prfDao.find(id);

        if (profesor == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(profesor, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(ProfesorDto dto) {
        Profesor profesor = this.prfDao.save(dto);
        return Response.ok(profesor).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(ProfesorDto dto) throws RollbackException {
        Profesor profesor = this.prfDao.edit(dto);
        return Response.ok(profesor).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Profesor profesor = this.prfDao.remove(id);
        return Response.ok(profesor).build();
    }
}
