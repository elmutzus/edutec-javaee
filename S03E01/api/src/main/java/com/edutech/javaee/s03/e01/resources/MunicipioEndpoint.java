/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dao.MunicipioDao;
import com.edutech.javaee.s03.e01.dto.MunicipioDto;
import com.edutech.javaee.s03.e01.model.Municipio;
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
@Path("/municipios")
public class MunicipioEndpoint {

    final MunicipioDao mnDao;

    public MunicipioEndpoint() {
        this.mnDao = null;
    }

    @Inject
    public MunicipioEndpoint(MunicipioDao mnDao) {
        this.mnDao = mnDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Municipio> findAll() {
        return this.mnDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Municipio mn = this.mnDao.find(id);

        if (mn == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(mn, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(MunicipioDto dto) {
        Municipio mn = this.mnDao.save(dto);
        return Response.ok(mn).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(MunicipioDto dto) throws RollbackException {
        Municipio mn = this.mnDao.edit(dto);
        return Response.ok(mn).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Municipio mn = this.mnDao.remove(id);
        return Response.ok(mn).build();
    }

}
