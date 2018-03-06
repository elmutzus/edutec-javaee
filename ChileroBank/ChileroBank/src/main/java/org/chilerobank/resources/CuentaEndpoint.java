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

    @GET
    @Produces({"application/json"})
    public List<Cuenta> findAll() {
        return this.cntDao.findAll();
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

        return Response.ok(cl, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(CuentaDto dto) {
        Cuenta cnt = new Cuenta();

        cnt.setCliente(this.clDao.find(dto.getCliente()));
        cnt.setEstado(dto.getEstado());
        cnt.setFechaApertura(dto.getFechaApertura());
        cnt.setMoneda(dto.getMoneda());
        cnt.setTipoCuenta(this.tpDao.find(dto.getTipoCuenta()));

        List<Transaccion> trxs = new ArrayList<>();

        for (Integer id : dto.getTransacciones()) {
            trxs.add(this.trxDao.find(id));
        }

        cnt.setTransacciones(trxs);

        List<Saldo> slds = new ArrayList<>();

        for (Integer id : dto.getSaldos()) {
            slds.add(this.slDao.find(id));
        }

        cnt.setSaldos(slds);

        this.cntDao.save(cnt);
        return Response.ok(cnt).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(CuentaDto dto) throws RollbackException {
        Cuenta cnt = new Cuenta();

        cnt.setCliente(this.clDao.find(dto.getCliente()));
        cnt.setEstado(dto.getEstado());
        cnt.setFechaApertura(dto.getFechaApertura());
        cnt.setMoneda(dto.getMoneda());
        cnt.setTipoCuenta(this.tpDao.find(dto.getTipoCuenta()));

        List<Transaccion> trxs = new ArrayList<>();

        for (Integer id : dto.getTransacciones()) {
            trxs.add(this.trxDao.find(id));
        }

        cnt.setTransacciones(trxs);

        List<Saldo> slds = new ArrayList<>();

        for (Integer id : dto.getSaldos()) {
            slds.add(this.slDao.find(id));
        }

        cnt.setSaldos(slds);

        Cuenta updatedCnt = this.cntDao.edit(cnt);
        if (updatedCnt == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(updatedCnt).build();
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

        return Response.ok(cnt).build();
    }
}
