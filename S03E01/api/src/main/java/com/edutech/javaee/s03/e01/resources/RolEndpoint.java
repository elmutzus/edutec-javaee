/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dao.RolDao;
import com.edutech.javaee.s03.e01.dto.RolDto;
import com.edutech.javaee.s03.e01.model.Rol;
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
@Path("/roles")
public class RolEndpoint {

    final RolDao rlDao;

    public RolEndpoint() {
        this.rlDao = null;
    }

    @Inject
    public RolEndpoint(RolDao rlDao) {
        this.rlDao = rlDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Rol> findAll() {
        return this.rlDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Rol rol = this.rlDao.find(id);

        if (rol == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(rol, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(RolDto dto) {
        Rol rol = this.rlDao.save(dto);
        return Response.ok(rol).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(RolDto dto) throws RollbackException {
        Rol rol = this.rlDao.edit(dto);
        return Response.ok(rol).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Rol rol = this.rlDao.remove(id);
        return Response.ok(rol).build();
    }

}
