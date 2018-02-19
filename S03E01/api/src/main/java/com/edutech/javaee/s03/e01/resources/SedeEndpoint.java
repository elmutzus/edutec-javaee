/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dao.SedeDao;
import com.edutech.javaee.s03.e01.dto.SedeDto;
import com.edutech.javaee.s03.e01.model.Sede;
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
@Path("/sedes")
public class SedeEndpoint {

    final SedeDao sdDao;

    public SedeEndpoint() {
        this.sdDao = null;
    }

    @Inject
    public SedeEndpoint(SedeDao sdDao) {
        this.sdDao = sdDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Sede> findAll() {
        return this.sdDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Sede sd = this.sdDao.find(id);

        if (sd == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(sd, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(SedeDto dto) {
        Sede sl = this.sdDao.save(dto);
        return Response.ok(sl).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(SedeDto dto) throws RollbackException {
        Sede sd = this.sdDao.edit(dto);
        return Response.ok(sd).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Sede sd = this.sdDao.remove(id);
        return Response.ok(sd).build();
    }
}
