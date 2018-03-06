/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.resources;

import java.util.ArrayList;
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
import org.chilerobank.dao.OperacionDao;
import org.chilerobank.dao.TransaccionDao;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.dto.OperacionDto;
import org.chilerobank.model.Operacion;
import org.chilerobank.model.Transaccion;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/operaciones")
public class OperacionEndpoint {

    final OperacionDao opDao;
    final TransaccionDao trDao;

    public OperacionEndpoint() {
        this.opDao = null;
        this.trDao = null;
    }

    @Inject
    public OperacionEndpoint(OperacionDao opDao, TransaccionDao trDao) {
        this.opDao = opDao;
        this.trDao = trDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Operacion> findAll() {
        return this.opDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Operacion op = this.opDao.find(id);

        if (op == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(op, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(OperacionDto dto) {
        Operacion op = new Operacion();

        op.setDescripcion(dto.getDescripcion());
        op.setNombre(dto.getNombre());

        List<Transaccion> trxs = new ArrayList<>();

        for (Integer id : dto.getTransacciones()) {
            trxs.add(this.trDao.find(id));
        }

        op.setTransacciones(trxs);

        this.opDao.save(op);
        return Response.ok(op).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(OperacionDto dto) throws RollbackException {
        Operacion op = new Operacion();

        op.setDescripcion(dto.getDescripcion());
        op.setNombre(dto.getNombre());

        List<Transaccion> trxs = new ArrayList<>();

        for (Integer id : dto.getTransacciones()) {
            trxs.add(this.trDao.find(id));
        }

        op.setTransacciones(trxs);

        Operacion updatedMn = this.opDao.edit(op);
        if (updatedMn == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(updatedMn).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Operacion mn = this.opDao.remove(id);

        if (mn == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(mn).build();
    }
}
