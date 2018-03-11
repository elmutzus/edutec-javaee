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
import org.chilerobank.dao.ClienteDao;
import org.chilerobank.dao.DepartamentoDao;
import org.chilerobank.dao.MunicipioDao;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.dto.MunicipioDto;
import org.chilerobank.model.Municipio;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/municipios")
public class MunicipioEndpoint {

    final DepartamentoDao dptDao;
    final MunicipioDao mnDao;
    final ClienteDao clDao;

    public MunicipioEndpoint() {
        this.dptDao = null;
        this.mnDao = null;
        this.clDao = null;
    }

    @Inject
    public MunicipioEndpoint(DepartamentoDao dpDao, MunicipioDao mnDao, ClienteDao clDao) {
        this.dptDao = dpDao;
        this.mnDao = mnDao;
        this.clDao = clDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Municipio> findAll() {
        return this.mnDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Municipio cl = this.mnDao.find(id);

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
    public Response create(MunicipioDto dto) {
        Municipio mn = new Municipio();

        mn.setCodigo(dto.getCodigo());
        mn.setDepartamento(this.dptDao.find(dto.getDepartamento()));
        mn.setNombre(dto.getNombre());

        this.mnDao.save(mn);
        return Response.ok(mn).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(MunicipioDto dto) throws RollbackException {
        Municipio mn = new Municipio();

        mn.setCodigo(dto.getCodigo());
        mn.setDepartamento(this.dptDao.find(dto.getDepartamento()));
        mn.setNombre(dto.getNombre());

        Municipio updatedMn = this.mnDao.edit(mn);
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
        Municipio mn = this.mnDao.remove(id);

        if (mn == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(mn).build();
    }
}
