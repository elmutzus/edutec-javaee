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
import org.chilerobank.dao.MunicipioDao;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.dto.ClienteDto;
import org.chilerobank.model.Cliente;
import org.chilerobank.model.Cuenta;
import org.chilerobank.model.Municipio;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/clientes")
public class ClienteEndpoint {

    final ClienteDao clDao;
    final MunicipioDao mnDao;
    final CuentaDao ctDao;

    public ClienteEndpoint() {
        this.clDao = null;
        this.mnDao = null;
        this.ctDao = null;
    }

    @Inject
    public ClienteEndpoint(ClienteDao clDao, MunicipioDao mnDao, CuentaDao ctDao) {
        this.clDao = clDao;
        this.mnDao = mnDao;
        this.ctDao = ctDao;
    }

    /**
     * Creates a response object from an existing one
     *
     * @param current
     * @return
     */
    public Cliente createResponseObject(Cliente current) {
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
        
        Municipio currentMn = current.getMunicipio();
        
        Municipio actualMn = new Municipio(
                currentMn.getId(),
                currentMn.getCodigo(),
                currentMn.getNombre(),
                null,
                null
        );

        return new Cliente(
                current.getId(),
                current.getNombre(),
                current.getDireccion(),
                current.getNit(),
                current.getFechaNacimiento(),
                actualMn,
                actualLst
        );
    }

    /**
     * Creates a response object model from a DTO
     *
     * @param dto
     * @return
     */
    public Cliente createFromDto(ClienteDto dto) {
        List<Cuenta> actualLst = new ArrayList<>();

        if (dto.getCuentas() != null && dto.getCuentas().size() > 0) {
            dto.getCuentas()
                    .stream()
                    .forEach((id)
                            -> actualLst.add(this.ctDao.find(id))
                    );
        }

        return new Cliente(
                dto.getId(),
                dto.getNombre(),
                dto.getDireccion(),
                dto.getNit(),
                dto.getFechaNacimiento(),
                this.mnDao.find(dto.getMunicipio()),
                actualLst
        );
    }

    @GET
    @Produces({"application/json"})
    public List<Cliente> findAll() {
        List<Cliente> actualLst = new ArrayList<>();

        this.clDao.findAll()
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
        Cliente mn = this.clDao.find(id);

        if (mn == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(mn), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(ClienteDto dto) {
        String name = dto.getNombre();

        Cliente existent = this.clDao.find(name);

        if (existent != null) {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity(new ErrorMessageDto(false, Response.Status.CONFLICT.getStatusCode(), "Recurso ya existe"))
                    .build();
        }

        Cliente cl = createFromDto(dto);

        this.clDao.save(cl);
        return Response.ok(createResponseObject(cl)).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(ClienteDto dto) throws RollbackException {
        Cliente cl = createFromDto(dto);

        Cliente updatedCl = this.clDao.edit(cl);
        if (updatedCl == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(updatedCl)).build();
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

        return Response.ok(createResponseObject(cl)).build();
    }
}
