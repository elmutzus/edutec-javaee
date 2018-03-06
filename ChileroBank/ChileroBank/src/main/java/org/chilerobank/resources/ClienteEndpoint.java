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
import org.chilerobank.dao.MunicipioDao;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.dto.ClienteDto;
import org.chilerobank.model.Cliente;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/clientes")
public class ClienteEndpoint {

    final ClienteDao clDao;
    final MunicipioDao mnDao;

    public ClienteEndpoint() {
        this.clDao = null;
        this.mnDao = null;
    }

    @Inject
    public ClienteEndpoint(ClienteDao clDao, MunicipioDao mnDao) {
        this.clDao = clDao;
        this.mnDao = mnDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Cliente> findAll() {
        return this.clDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Cliente cl = this.clDao.find(id);

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
    public Response create(ClienteDto dto) {
        Cliente cl = new Cliente();

        cl.setDireccion(dto.getDireccion());
        cl.setFechaNacimiento(dto.getFechaNacimiento());
        cl.setMunicipio(this.mnDao.find(dto.getMunicipio()));
        cl.setNit(dto.getNit());
        cl.setNombre(dto.getNombre());

        this.clDao.save(cl);
        return Response.ok(cl).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(ClienteDto dto) throws RollbackException {
        Cliente cl = new Cliente();

        cl.setDireccion(dto.getDireccion());
        cl.setFechaNacimiento(dto.getFechaNacimiento());
        cl.setMunicipio(this.mnDao.find(dto.getMunicipio()));
        cl.setNit(dto.getNit());
        cl.setNombre(dto.getNombre());

        Cliente updatedCl = this.clDao.edit(cl);
        if (updatedCl == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(updatedCl).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Cliente cl = this.clDao.remove(id);

        if (cl == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(cl).build();
    }
}
