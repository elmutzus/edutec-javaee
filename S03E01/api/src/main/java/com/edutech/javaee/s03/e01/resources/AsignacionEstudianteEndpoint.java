/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dao.AsignacionEstudianteDao;
import com.edutech.javaee.s03.e01.dto.AsignacionEstudianteDto;
import com.edutech.javaee.s03.e01.model.AsignacionEstudiante;
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
@Path("/asignacionEstudiantes")
public class AsignacionEstudianteEndpoint {

    final AsignacionEstudianteDao aeDao;

    public AsignacionEstudianteEndpoint() {
        this.aeDao = null;
    }

    @Inject
    public AsignacionEstudianteEndpoint(AsignacionEstudianteDao aeDao) {
        this.aeDao = aeDao;
    }

    @GET
    @Produces({"application/json"})
    public List<AsignacionEstudiante> findAll() {
        return this.aeDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        AsignacionEstudiante ae = this.aeDao.find(id);

        if (ae == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(ae, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(AsignacionEstudianteDto dto) {
        AsignacionEstudiante ae = this.aeDao.save(dto);
        return Response.ok(ae).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(AsignacionEstudianteDto dto) throws RollbackException {
        AsignacionEstudiante ae = this.aeDao.edit(dto);
        return Response.ok(ae).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        AsignacionEstudiante ae = this.aeDao.remove(id);
        return Response.ok(ae).build();
    }
}
