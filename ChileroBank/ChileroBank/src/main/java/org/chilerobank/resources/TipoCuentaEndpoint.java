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
import org.chilerobank.dao.TipoCuentaDao;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.dto.TipoCuentaDto;
import org.chilerobank.model.Cliente;
import org.chilerobank.model.Cuenta;
import org.chilerobank.model.TipoCuenta;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/tiposCuentas")
public class TipoCuentaEndpoint {

    final TipoCuentaDao tpDao;
    final CuentaDao ctDao;

    public TipoCuentaEndpoint() {
        this.tpDao = null;
        this.ctDao = null;
    }

    @Inject
    public TipoCuentaEndpoint(TipoCuentaDao tpDao, CuentaDao ctDao) {
        this.tpDao = tpDao;
        this.ctDao = ctDao;
    }

    /**
     * Creates a response object from an existing one
     *
     * @param current
     * @return
     */
    public TipoCuenta createResponseObject(TipoCuenta current) {
        List<Cuenta> actualLst = new ArrayList<>();

        current.getCuentas()
                .stream()
                .forEach((cur) -> actualLst.add(
                new Cuenta(
                        cur.getId(),
                        cur.getMoneda(),
                        cur.getFechaApertura(),
                        cur.getEstado(),
                        null,
                        null,
                        null,
                        null
                ))
                );

        return new TipoCuenta(
                current.getId(),
                current.getNombre(),
                current.getDescripcion(),
                current.getTasaInteres(),
                actualLst
        );
    }

    /**
     * Creates a response object model from a DTO
     *
     * @param dto
     * @return
     */
    public TipoCuenta createFromDto(TipoCuentaDto dto) {
        List<Cuenta> actualLst = new ArrayList<>();

        if (dto.getCuentas() != null && dto.getCuentas().size() > 0) {
            dto.getCuentas()
                    .stream()
                    .forEach((id)
                            -> actualLst.add(this.ctDao.find(id))
                    );
        }

        return new TipoCuenta(
                dto.getId(),
                dto.getNombre(),
                dto.getDescripcion(),
                dto.getTasaInteres(),
                actualLst
        );
    }

    @GET
    @Produces({"application/json"})
    public List<TipoCuenta> findAll() {
        List<TipoCuenta> actualLst = new ArrayList<>();

        this.tpDao.findAll()
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
        TipoCuenta tp = this.tpDao.find(id);

        if (tp == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(tp, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(TipoCuentaDto dto) {
        String name = dto.getNombre();

        TipoCuenta existent = this.tpDao.find(name);

        if (existent != null) {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity(new ErrorMessageDto(false, Response.Status.CONFLICT.getStatusCode(), "Recurso ya existe"))
                    .build();
        }

        TipoCuenta tp = createFromDto(dto);

        this.tpDao.save(tp);

        return Response.ok(tp).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(TipoCuentaDto dto) throws RollbackException {
        TipoCuenta tp = createFromDto(dto);

        TipoCuenta updatedTp = this.tpDao.edit(tp);
        if (updatedTp == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(updatedTp)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        TipoCuenta tp = this.tpDao.remove(id);

        if (tp == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(tp)).build();
    }
}
