/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dao.SalonDao;
import com.edutech.javaee.s03.e01.dto.SalonDto;
import com.edutech.javaee.s03.e01.model.Salon;
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
@Path("/salones")
public class SalonEndpoint {

    final SalonDao slDao;

    public SalonEndpoint() {
        this.slDao = null;
    }

    @Inject
    public SalonEndpoint(SalonDao slDao) {
        this.slDao = slDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Salon> findAll() {
        return this.slDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Salon sl = this.slDao.find(id);

        if (sl == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(sl, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(SalonDto dto) {
        Salon sl = this.slDao.save(dto);
        return Response.ok(sl).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(SalonDto dto) throws RollbackException {
        Salon sl = this.slDao.edit(dto);
        return Response.ok(sl).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Salon sl = this.slDao.remove(id);
        return Response.ok(sl).build();
    }
}
