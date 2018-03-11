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
import org.chilerobank.dto.UsuarioDto;
import org.chilerobank.model.Rol;
import org.chilerobank.model.Usuario;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/usuarios")
public class UsuarioEndpoint {

    //@Inject
    final UsuarioDao usuarioDao;

    //@Inject
    final RolDao rolDao;

    public UsuarioEndpoint() {
        this.usuarioDao = null;
        this.rolDao = null;
    }

    @Inject
    public UsuarioEndpoint(UsuarioDao usuarioDao, RolDao rolDao) {
        this.usuarioDao = usuarioDao;
        this.rolDao = rolDao;
    }

    /**
     * Creates a response object from an existing one
     *
     * @param current
     * @return
     */
    public Usuario createResponseObject(Usuario current) {
        Rol rl = current.getRol();

        return new Usuario(
                current.getId(),
                current.getCodigo(),
                current.getEmail(),
                current.getFechaNacimiento(),
                current.getNombre(),
                current.getPassword(),
                current.getTelefono(),
                new Rol(
                        rl.getId(),
                        rl.getNombre(),
                        rl.getDescripcion(),
                        null
                )
        );
    }

    /**
     * Creates a response object model from a DTO
     *
     * @param dto
     * @return
     */
    public Usuario createFromDto(UsuarioDto dto) {

        return new Usuario(
                dto.getId(),
                dto.getCodigo(),
                dto.getEmail(),
                dto.getFechaNacimiento(),
                dto.getNombre(),
                dto.getPassword(),
                dto.getTelefono(),
                this.rolDao.find(dto.getRol())
        );
    }

    @GET
    @Produces({"application/json"})
    public List<Usuario> findAll() {
        List<Usuario> actualLst = new ArrayList<>();

        this.usuarioDao.findAll()
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
        Usuario usuario = this.usuarioDao.find(id);

        if (usuario == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(usuario), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(UsuarioDto dto) {
        String code = dto.getCodigo();

        Usuario existent = this.usuarioDao.find(code);

        if (existent != null) {
            return Response
                    .status(Response.Status.CONFLICT)
                    .entity(new ErrorMessageDto(false, Response.Status.CONFLICT.getStatusCode(), "Recurso ya existe"))
                    .build();
        }

        Usuario usr = createFromDto(dto);

        this.usuarioDao.save(usr);
        return Response.ok(createResponseObject(usr)).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(UsuarioDto dto) throws RollbackException {
        Usuario usr = createFromDto(dto);

        Usuario updatedUsr = this.usuarioDao.edit(usr);
        if (updatedUsr == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(updatedUsr)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Usuario usr = this.usuarioDao.remove(id);

        if (usr == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(createResponseObject(usr)).build();
    }

}
