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
import org.chilerobank.dao.CuentaDao;
import org.chilerobank.dao.SaldoDao;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.dto.SaldoDto;
import org.chilerobank.model.Saldo;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/saldos")
public class SaldoEndpoint {

    final SaldoDao slDao;
    final CuentaDao cnDao;

    public SaldoEndpoint() {
        this.slDao = null;
        this.cnDao = null;
    }

    @Inject
    public SaldoEndpoint(SaldoDao slDao, CuentaDao cnDao) {
        this.slDao = slDao;
        this.cnDao = cnDao;
    }

    /**
     * Creates a response object from an existing one
     *
     * @param current
     * @return
     */
    public Saldo createResponseObject(Saldo current) {
        return new Saldo(
                current.getId(),
                current.getCuenta(),
                current.getFecha(),
                current.getMonto()
        );
    }

    /**
     * Creates a response object model from a DTO
     *
     * @param dto
     * @return
     */
    public Saldo createFromDto(SaldoDto dto) {
        return new Saldo(
                dto.getId(),
                this.cnDao.find(dto.getCuenta()),
                dto.getFecha(),
                dto.getMonto()
        );
    }

    @GET
    @Produces({"application/json"})
    public List<Saldo> findAll() {
        List<Saldo> actualLst = new ArrayList<>();

        this.slDao.findAll()
                .stream()
                .forEach((currentObj)
                        -> actualLst.add(createResponseObject(currentObj))
                );

        return actualLst;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Saldo sl = this.slDao.find(id);

        if (sl == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(sl), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(SaldoDto dto) {
        Saldo sl = createFromDto(dto);

        this.slDao.save(sl);
        return Response.ok(createResponseObject(sl)).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(SaldoDto dto) throws RollbackException {
        Saldo sl = createFromDto(dto);

        Saldo updatedSl = this.slDao.edit(sl);
        if (updatedSl == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(updatedSl)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Saldo mn = this.slDao.remove(id);

        if (mn == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(mn)).build();
    }

}
