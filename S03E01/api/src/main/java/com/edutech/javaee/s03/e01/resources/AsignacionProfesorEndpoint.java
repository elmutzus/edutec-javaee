/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dao.AsignacionProfesorDao;
import com.edutech.javaee.s03.e01.dto.AsignacionProfesorDto;
import com.edutech.javaee.s03.e01.model.AsignacionProfesor;
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
@Path("/asignacionProfesores")
public class AsignacionProfesorEndpoint {

    final AsignacionProfesorDao apDao;

    public AsignacionProfesorEndpoint() {
        this.apDao = null;
    }

    @Inject
    public AsignacionProfesorEndpoint(AsignacionProfesorDao apDao) {
        this.apDao = apDao;
    }

    @GET
    @Produces({"application/json"})
    public List<AsignacionProfesor> findAll() {
        return this.apDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        AsignacionProfesor ap = this.apDao.find(id);

        if (ap == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(ap, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(AsignacionProfesorDto dto) {
        AsignacionProfesor ap = apDao.save(dto);
        return Response.ok(ap).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(AsignacionProfesorDto dto) throws RollbackException {
        AsignacionProfesor ap = this.apDao.edit(dto);
        return Response.ok(ap).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        AsignacionProfesor ap = this.apDao.remove(id);
        return Response.ok(ap).build();
    }
}
