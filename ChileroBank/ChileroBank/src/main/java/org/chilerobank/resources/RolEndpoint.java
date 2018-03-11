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
import org.chilerobank.dao.RolDao;
import org.chilerobank.dao.UsuarioDao;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.dto.RolDto;
import org.chilerobank.model.Rol;
import org.chilerobank.model.Usuario;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/roles")
public class RolEndpoint {

    final RolDao rolDao;
    final UsuarioDao usDao;

    public RolEndpoint() {
        this.rolDao = null;
        this.usDao = null;
    }

    @Inject
    public RolEndpoint(RolDao rolDao, UsuarioDao usDao) {
        this.rolDao = rolDao;
        this.usDao = usDao;
    }

    /**
     * Creates a response object from an existing one
     *
     * @param current
     * @return
     */
    public Rol createResponseObject(Rol current) {
        List<Usuario> actualLst = new ArrayList<>();

        current.getUsuarios()
                .stream()
                .forEach((cur) -> actualLst.add(
                new Usuario(
                        cur.getId(),
                        cur.getCodigo(),
                        cur.getEmail(),
                        cur.getFechaNacimiento(),
                        cur.getNombre(),
                        cur.getPassword(),
                        cur.getTelefono(),
                        null
                ))
                );

        return new Rol(
                current.getId(),
                current.getNombre(),
                current.getDescripcion(),
                actualLst
        );
    }

    /**
     * Creates a response object model from a DTO
     *
     * @param dto
     * @return
     */
    public Rol createFromDto(RolDto dto) {
        List<Usuario> actualLst = new ArrayList<>();

        if (dto.getUsuarios() != null && dto.getUsuarios().size() > 0) {
            dto.getUsuarios()
                    .stream()
                    .forEach((id)
                            -> actualLst.add(this.usDao.find(id))
                    );
        }

        return new Rol(
                dto.getId(),
                dto.getNombre(),
                dto.getDescripcion(),
                actualLst
        );
    }

    @GET
    @Produces({"application/json"})
    public List<Rol> findAll() {
        List<Rol> actualLst = new ArrayList<>();

        this.rolDao.findAll()
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
        Rol rl = this.rolDao.find(id);

        if (rl == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(rl), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(RolDto dto) {
        String name = dto.getNombre();

        Rol existent = this.rolDao.find(name);

        if (existent != null) {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity(new ErrorMessageDto(false, Response.Status.CONFLICT.getStatusCode(), "Recurso ya existe"))
                    .build();
        }

        Rol rl = createFromDto(dto);

        this.rolDao.save(rl);
        return Response.ok(createResponseObject(rl)).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(RolDto dto) throws RollbackException {
        Rol rl = createFromDto(dto);

        Rol updatedRl = this.rolDao.edit(rl);
        if (updatedRl == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(updatedRl)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Rol rl = this.rolDao.remove(id);

        if (rl == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(rl)).build();
    }
}
