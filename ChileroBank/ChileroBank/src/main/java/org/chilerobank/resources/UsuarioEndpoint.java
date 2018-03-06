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
import org.chilerobank.dao.RolDao;
import org.chilerobank.dao.UsuarioDao;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.dto.UsuarioDto;
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

    @GET
    @Produces({"application/json"})
    public List<Usuario> findAll() {
        return this.usuarioDao.findAll();
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

        return Response.ok(usuario, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(UsuarioDto dto) {
        Usuario usr = new Usuario();

        usr.setCodigo(dto.getCodigo());
        usr.setEmail(dto.getEmail());
        usr.setFechaNacimiento(dto.getFechaNacimiento());
        usr.setNombre(dto.getNombre());
        usr.setPassword(dto.getPassword());
        usr.setTelefono(dto.getTelefono());
        usr.setRol(this.rolDao.find(dto.getRol()));

        this.usuarioDao.save(usr);
        return Response.ok(usr).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(UsuarioDto dto) throws RollbackException {
        Usuario usr = new Usuario();

        usr.setCodigo(dto.getCodigo());
        usr.setEmail(dto.getEmail());
        usr.setFechaNacimiento(dto.getFechaNacimiento());
        usr.setNombre(dto.getNombre());
        usr.setPassword(dto.getPassword());
        usr.setTelefono(dto.getTelefono());
        usr.setRol(this.rolDao.find(dto.getRol()));

        Usuario updatedUsr = this.usuarioDao.edit(usr);
        if (updatedUsr == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(updatedUsr).build();
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

        return Response.ok(usr).build();
    }

}
