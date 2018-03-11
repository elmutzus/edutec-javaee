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

    /**
     * Creates a response object from an existing one
     *
     * @param current
     * @return
     */
    public Operacion createResponseObject(Operacion current) {
        List<Transaccion> actualLst = new ArrayList<>();

        current.getTransacciones()
                .stream()
                .forEach((cur) -> actualLst.add(
                new Transaccion(
                        cur.getId(),
                        cur.getFechaMovimiento(),
                        cur.getMonto(),
                        null,
                        null
                ))
                );

        return new Operacion(
                current.getId(),
                current.getNombre(),
                current.getDescripcion(),
                actualLst
        );
    }

    /**
     * Creates a response object model from a DTO
     *
     * @param dto
     * @return
     */
    public Operacion createFromDto(OperacionDto dto) {
        List<Transaccion> actualLst = new ArrayList<>();

        if (dto.getTransacciones() != null && dto.getTransacciones().size() > 0) {
            dto.getTransacciones()
                    .stream()
                    .forEach((id)
                            -> actualLst.add(this.trDao.find(id))
                    );
        }

        return new Operacion(
                dto.getId(),
                dto.getNombre(),
                dto.getDescripcion(),
                actualLst
        );
    }

    @GET
    @Produces({"application/json"})
    public List<Operacion> findAll() {
        List<Operacion> actualLst = new ArrayList<>();

        this.opDao.findAll()
                .stream()
                .forEach((curObj)
                        -> actualLst.add(createResponseObject(curObj))
                );

        return actualLst;
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

        return Response.ok(createResponseObject(op), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(OperacionDto dto) {
        String name = dto.getNombre();

        Operacion existent = this.opDao.find(name);

        if (existent != null) {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity(new ErrorMessageDto(false, Response.Status.CONFLICT.getStatusCode(), "Recurso ya existe"))
                    .build();
        }

        Operacion op = createFromDto(dto);

        this.opDao.save(op);
        return Response.ok(createResponseObject(op)).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(OperacionDto dto) throws RollbackException {
        Operacion op = createFromDto(dto);

        Operacion updatedOp = this.opDao.edit(op);
        if (updatedOp == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(updatedOp)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Operacion op = this.opDao.remove(id);

        if (op == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(op)).build();
    }
}
