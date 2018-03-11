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
import org.chilerobank.dao.DepartamentoDao;
import org.chilerobank.dao.MunicipioDao;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.dto.MunicipioDto;
import org.chilerobank.model.Cliente;
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

    /**
     * Creates a response object from an existing one
     *
     * @param current
     * @return
     */
    public Municipio createResponsObject(Municipio current) {
        List<Cliente> actualLst = new ArrayList<>();

        current.getClientes()
                .stream()
                .forEach((currentCl) -> actualLst.add(
                new Cliente(currentCl.getNombre(), currentCl.getDireccion(), currentCl.getNit(), currentCl.getFechaNacimiento(), currentCl.getMunicipio(), null))
                );

        return new Municipio(
                current.getCodigo(),
                current.getNombre(),
                current.getDepartamento(),
                actualLst
        );
    }

    /**
     * Creates a response object model from a DTO
     *
     * @param dto
     * @return
     */
    public Municipio createFromDto(MunicipioDto dto) {
        List<Cliente> actualLst = new ArrayList<>();

        if (dto.getClientes() != null && dto.getClientes().size() > 0) {
            dto.getClientes()
                    .stream()
                    .forEach((mnId)
                            -> actualLst.add(this.clDao.find(mnId))
                    );
        }

        return new Municipio(
                dto.getCodigo(),
                dto.getNombre(),
                this.dptDao.find(dto.getDepartamento()),
                actualLst
        );
    }

    @GET
    @Produces({"application/json"})
    public List<Municipio> findAll() {
        List<Municipio> actualLst = new ArrayList<>();

        this.mnDao.findAll()
                .stream()
                .forEach((currentObj)
                        -> actualLst.add(createResponsObject(currentObj))
                );

        return actualLst;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Municipio mn = this.mnDao.find(id);

        if (mn == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponsObject(mn), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(MunicipioDto dto) {
        String name = dto.getNombre();

        Municipio existent = this.mnDao.find(name);

        if (existent != null) {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity(new ErrorMessageDto(false, Response.Status.CONFLICT.getStatusCode(), "Recurso ya existe"))
                    .build();
        }

        Municipio mn = createFromDto(dto);
        
        this.mnDao.save(mn);
        return Response.ok(mn).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(MunicipioDto dto) throws RollbackException {
        Municipio mn = createFromDto(dto);

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
