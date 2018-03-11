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
import org.chilerobank.dao.ClienteDao;
import org.chilerobank.dao.CuentaDao;
import org.chilerobank.dao.SaldoDao;
import org.chilerobank.dao.TipoCuentaDao;
import org.chilerobank.dao.TransaccionDao;
import org.chilerobank.dto.CuentaDto;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.model.Cuenta;
import org.chilerobank.model.Operacion;
import org.chilerobank.model.Saldo;
import org.chilerobank.model.Transaccion;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/cuentas")
public class CuentaEndpoint {

    final CuentaDao cntDao;
    final TipoCuentaDao tpDao;
    final SaldoDao slDao;
    final TransaccionDao trxDao;
    final ClienteDao clDao;

    public CuentaEndpoint() {
        this.cntDao = null;
        this.tpDao = null;
        this.slDao = null;
        this.trxDao = null;
        this.clDao = null;
    }

    @Inject
    public CuentaEndpoint(CuentaDao cntDao, TipoCuentaDao tpDao, SaldoDao slDao, TransaccionDao trxDao, ClienteDao clDao) {
        this.cntDao = cntDao;
        this.tpDao = tpDao;
        this.slDao = slDao;
        this.trxDao = trxDao;
        this.clDao = clDao;
    }

    /**
     * Creates a response object from an existing one
     *
     * @param current
     * @return
     */
    public Cuenta createResponseObject(Cuenta current) {
        List<Saldo> actualSls = new ArrayList<>();

        if (current.getSaldos() != null && current.getSaldos().size() > 0) {
            current.getSaldos()
                    .stream()
                    .forEach((curSl) -> actualSls.add(
                    new Saldo(
                            curSl.getId(),
                            null,
                            curSl.getFecha()
                    )
            ));
        }

        List<Transaccion> actualTrxs = new ArrayList<>();

        if (current.getTransacciones() != null && current.getTransacciones().size() > 0) {
            current.getTransacciones()
                    .stream()
                    .forEach((curTrx) -> {
                        Operacion op = curTrx.getOperacion();

                        actualTrxs.add(
                                new Transaccion(
                                        curTrx.getId(),
                                        curTrx.getFechaMovimiento(),
                                        curTrx.getMonto(),
                                        null,
                                        new Operacion(
                                                op.getId(),
                                                op.getNombre(),
                                                op.getDescripcion(),
                                                null
                                        )
                                )
                        );
                    }
                    );
        }

        return new Cuenta(
                current.getId(),
                current.getMoneda(),
                current.getFechaApertura(),
                current.getEstado(),
                current.getTipoCuenta(),
                current.getCliente(),
                actualSls,
                actualTrxs
        );
    }

    /**
     * Creates a response object model from a DTO
     *
     * @param dto
     * @return
     */
    public Cuenta createFromDto(CuentaDto dto) {
        List<Saldo> actualSls = new ArrayList<>();

        if (dto.getSaldos() != null && dto.getSaldos().size() > 0) {
            dto.getSaldos()
                    .stream()
                    .forEach((id)
                            -> actualSls.add(this.slDao.find(id))
                    );
        }

        List<Transaccion> actualTrxs = new ArrayList<>();

        if (dto.getTransacciones() != null && dto.getTransacciones().size() > 0) {
            dto.getTransacciones()
                    .stream()
                    .forEach((id)
                            -> actualTrxs.add(this.trxDao.find(id))
                    );
        }

        return new Cuenta(
                dto.getId(),
                dto.getMoneda(),
                dto.getFechaApertura(),
                dto.getEstado(),
                this.tpDao.find(dto.getTipoCuenta()),
                this.clDao.find(dto.getCliente()),
                actualSls,
                actualTrxs
        );
    }

    @GET
    @Produces({"application/json"})
    public List<Cuenta> findAll() {
        List<Cuenta> actualLst = new ArrayList<>();

        this.cntDao.findAll()
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
        Cuenta cl = this.cntDao.find(id);

        if (cl == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(cl), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(CuentaDto dto) {
        Integer id = dto.getId();

        Cuenta existent = this.cntDao.find(id);

        if (existent != null) {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity(new ErrorMessageDto(false, Response.Status.CONFLICT.getStatusCode(), "Recurso ya existe"))
                    .build();
        }

        Cuenta cnt = createFromDto(dto);

        this.cntDao.save(cnt);
        return Response.ok(createResponseObject(cnt)).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(CuentaDto dto) throws RollbackException {
        Cuenta cnt = createFromDto(dto);

        Cuenta updatedCnt = this.cntDao.edit(cnt);
        if (updatedCnt == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(updatedCnt)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Cuenta cnt = this.cntDao.remove(id);

        if (cnt == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(cnt)).build();
    }
}
