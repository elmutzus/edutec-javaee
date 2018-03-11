/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.resources;

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
import javax.ws.rs.core.Response;
import org.chilerobank.dao.ParametroSistemaDao;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.dto.ParametroSistemaDto;
import org.chilerobank.model.ParametroSistema;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/parametros")
public class ParametroSistemaEndpoint {

    final ParametroSistemaDao psDao;

    public ParametroSistemaEndpoint() {
        this.psDao = null;
    }

    @Inject
    public ParametroSistemaEndpoint(ParametroSistemaDao psDao) {
        this.psDao = psDao;
    }
    
    /**
     * Creates a response object model from a DTO
     *
     * @param dto
     * @return
     */
    public ParametroSistema createFromDto(ParametroSistemaDto dto) {
        return new ParametroSistema(
                dto.getId(),
                dto.getNombre(),
                dto.getValor()
        );
    }

    @GET
    @Produces({"application/json"})
    public List<ParametroSistema> findAll() {
        return this.psDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        ParametroSistema ps = this.psDao.find(id);

        if (ps == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(ps).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(ParametroSistemaDto dto) {
        String name = dto.getNombre();

        ParametroSistema existent = this.psDao.find(name);

        if (existent != null) {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity(new ErrorMessageDto(false, Response.Status.CONFLICT.getStatusCode(), "Recurso ya existe"))
                    .build();
        }

        ParametroSistema ps = createFromDto(dto);

        this.psDao.save(ps);
        
        return Response.ok(ps).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(ParametroSistemaDto dto) throws RollbackException {
        ParametroSistema op = createFromDto(dto);

        ParametroSistema updatedPs = this.psDao.edit(op);
        if (updatedPs == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(updatedPs).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        ParametroSistema ps = this.psDao.remove(id);

        if (ps == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(ps).build();
    }
}
