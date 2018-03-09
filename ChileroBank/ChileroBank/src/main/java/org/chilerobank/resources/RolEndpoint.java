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

    @GET
    @Produces({"application/json"})
    public List<Rol> findAll() {
        return this.rolDao.findAll();
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

        return Response.ok(rl, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(RolDto dto) {
        Rol rl = new Rol();

        rl.setDescripcion(dto.getDescripcion());
        rl.setNombre(dto.getNombre());

        List<Usuario> usrs = new ArrayList<>();

        for (Integer id : dto.getUsuarios()) {
            Usuario usr = this.usDao.find(id);

            usrs.add(usr);
        }

        rl.setUsuarios(usrs);

        this.rolDao.save(rl);
        return Response.ok(rl).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(RolDto dto) throws RollbackException {
        Rol rl = new Rol();

        rl.setDescripcion(dto.getDescripcion());
        rl.setNombre(dto.getNombre());

        List<Usuario> usrs = new ArrayList<>();

        for (Integer id : dto.getUsuarios()) {
            Usuario usr = this.usDao.find(id);

            usrs.add(usr);
        }

        rl.setUsuarios(usrs);

        Rol updatedUsr = this.rolDao.edit(rl);
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
        Rol usr = this.rolDao.remove(id);

        if (usr == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(usr).build();
    }
}
