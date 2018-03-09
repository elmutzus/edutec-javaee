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
import org.chilerobank.dao.TipoCuentaDao;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.dto.TipoCuentaDto;
import org.chilerobank.model.TipoCuenta;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/tiposCuentas")
public class TipoCuentaEndpoint {

    final TipoCuentaDao tpDao;

    public TipoCuentaEndpoint() {
        this.tpDao = null;
    }

    @Inject
    public TipoCuentaEndpoint(TipoCuentaDao tpDao) {
        this.tpDao = tpDao;
    }

    @GET
    @Produces({"application/json"})
    public List<TipoCuenta> findAll() {
        return this.tpDao.findAll();
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
        TipoCuenta tp = new TipoCuenta();

        tp.setDescripcion(dto.getDescripcion());
        tp.setNombre(dto.getNombre());
        tp.setTasaInteres(dto.getTasaInteres());

        this.tpDao.save(tp);

        return Response.ok(tp).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(TipoCuentaDto dto) throws RollbackException {
        TipoCuenta tp = new TipoCuenta();

        tp.setDescripcion(dto.getDescripcion());
        tp.setNombre(dto.getNombre());
        tp.setTasaInteres(dto.getTasaInteres());

        TipoCuenta updatedSl = this.tpDao.edit(tp);
        if (updatedSl == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(updatedSl).build();
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

        return Response.ok(tp).build();
    }
}
