/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dao.EstudianteDao;
import com.edutech.javaee.s03.e01.dto.EstudianteDto;
import com.edutech.javaee.s03.e01.model.Estudiante;
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
@Path("/estudiantes")
public class EstudianteEndpoint {

    final EstudianteDao estDao;

    public EstudianteEndpoint() {
        this.estDao = null;
    }

    @Inject
    public EstudianteEndpoint(EstudianteDao estDao) {
        this.estDao = estDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Estudiante> findAll() {
        return this.estDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Estudiante estudiante = this.estDao.find(id);

        if (estudiante == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(estudiante, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(EstudianteDto dto) {
        Estudiante estudiante = this.estDao.save(dto);
        return Response.ok(estudiante).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(EstudianteDto dto) throws RollbackException {
        Estudiante estudiante = this.estDao.edit(dto);
        return Response.ok(estudiante).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Estudiante estudiante = this.estDao.remove(id);
        return Response.ok(estudiante).build();
    }
}
