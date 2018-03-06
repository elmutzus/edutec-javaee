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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.chilerobank.dao.CuentaDao;
import org.chilerobank.dao.OperacionDao;
import org.chilerobank.dao.TransaccionDao;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.dto.TransaccionDto;
import org.chilerobank.model.Transaccion;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/transacciones")
public class TransaccionEndpoint {

    final TransaccionDao trDao;
    final CuentaDao cnDao;
    final OperacionDao opDao;

    public TransaccionEndpoint() {
        this.trDao = null;
        this.cnDao = null;
        this.opDao = null;
    }

    @Inject
    public TransaccionEndpoint(TransaccionDao trDao, CuentaDao cnDao, OperacionDao opDao) {
        this.trDao = trDao;
        this.cnDao = cnDao;
        this.opDao = opDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Transaccion> findAll() {
        return this.trDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Transaccion tr = this.trDao.find(id);

        if (tr == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(tr, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(TransaccionDto dto) {
        Transaccion tr = new Transaccion();

        tr.setCuenta(this.cnDao.find(dto.getCuenta()));
        tr.setFechaMovimiento(dto.getFechaMovimiento());
        tr.setMonto(dto.getMonto());
        tr.setOperacion(this.opDao.find(dto.getOperacion()));

        this.trDao.save(tr);

        return Response.ok(tr).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(TransaccionDto dto) throws RollbackException {
        Transaccion tr = new Transaccion();

        tr.setCuenta(this.cnDao.find(dto.getCuenta()));
        tr.setFechaMovimiento(dto.getFechaMovimiento());
        tr.setMonto(dto.getMonto());
        tr.setOperacion(this.opDao.find(dto.getOperacion()));

        Transaccion updatedTr = this.trDao.edit(tr);
        if (updatedTr == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(updatedTr).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Transaccion tp = this.trDao.remove(id);

        if (tp == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(tp).build();
    }
}
