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
import org.chilerobank.dao.OperacionDao;
import org.chilerobank.dao.SaldoDao;
import org.chilerobank.dao.TransaccionDao;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.dto.TransaccionDto;
import org.chilerobank.model.Cuenta;
import org.chilerobank.model.Operacion;
import org.chilerobank.model.Saldo;
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

    final static int DEBIT = 1;
    final static int CREDIT = 2;

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

    /**
     * Creates a response object from an existing one
     *
     * @param current
     * @return
     */
    public Transaccion createResponseObject(Transaccion current) {
        Cuenta curCuenta = current.getCuenta();

        Operacion currentOp = current.getOperacion();

        Operacion actualOp = new Operacion(
                currentOp.getId(),
                currentOp.getNombre(),
                currentOp.getDescripcion(),
                null
        );

        return new Transaccion(
                current.getId(),
                current.getFechaMovimiento(),
                current.getMonto(),
                new Cuenta(
                        curCuenta.getId(),
                        curCuenta.getMoneda(),
                        curCuenta.getFechaApertura(),
                        curCuenta.getEstado(),
                        null,
                        null,
                        null,
                        null,
                        curCuenta.getMonto()
                ),
                actualOp
        );
    }

    /**
     * Creates a response object model from a DTO
     *
     * @param dto
     * @return
     */
    public Transaccion createFromDto(TransaccionDto dto) {
        return new Transaccion(
                dto.getId(),
                dto.getFechaMovimiento(),
                dto.getMonto(),
                this.cnDao.find(dto.getCuenta()),
                this.opDao.find(dto.getOperacion())
        );
    }

    
    public float getTotalAmount(Cuenta cnt, float amount, int type) {
        float total = cnt.getMonto();

        if (type == CREDIT) {
            return total + amount;
        }

        if (type == DEBIT) {
            return total - amount;
        }

        throw new InternalError("Debe elegir una opción válida. 1=Débito, 2=CRÉDITO");
    }

    @GET
    @Produces({"application/json"})
    public List<Transaccion> findAll() {
        List<Transaccion> actualLst = new ArrayList<>();

        this.trDao.findAll()
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
        Transaccion tr = this.trDao.find(id);

        if (tr == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(tr), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(TransaccionDto dto) {
        Cuenta cnt = this.cnDao.find(dto.getCuenta());
        
        if(cnt == null){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "No se encontró la cuenta"))
                    .build();
        }

        float saldo = getTotalAmount(cnt, dto.getMonto(), dto.getOperacion());

        if (saldo > 0) {
            Transaccion tr = createFromDto(dto);

            this.trDao.save(tr);
            
            cnt.setMonto(saldo);
            this.cnDao.save(cnt);

            return Response.ok(createResponseObject(tr)).build();
        } else {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity(new ErrorMessageDto(false, 404, "El monto no es suficiente para la transacción"))
                    .build();
        }
    }

    @PUT
    @Produces({"application/json"})
    public Response update(TransaccionDto dto) throws RollbackException {
        Transaccion tr = createFromDto(dto);

        Transaccion updatedTr = this.trDao.edit(tr);
        if (updatedTr == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(updatedTr)).build();
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

        return Response.ok(createResponseObject(tp)).build();
    }
}
