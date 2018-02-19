package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dao.UsuarioDao;
import com.edutech.javaee.s03.e01.dto.UsuarioDto;
import com.edutech.javaee.s03.e01.model.Usuario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

/**
 *
 * @author nahum
 */
@Stateless
@Path("/usuarios")
public class UsuarioEndpoint {

    final UsuarioDao usrDao;

    public UsuarioEndpoint() {
        this.usrDao = null;
    }

    @Inject
    public UsuarioEndpoint(UsuarioDao usrDao) {
        this.usrDao = usrDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Usuario> findAll() {
        return this.usrDao.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Usuario usuario = this.usrDao.find(id);

        if (usuario == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(usuario, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(UsuarioDto dto) {
        Usuario usuario = this.usrDao.save(dto);
        return Response.ok(usuario).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(UsuarioDto dto) throws RollbackException {
        Usuario usuario = this.usrDao.edit(dto);
        return Response.ok(usuario).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Usuario usuario = this.usrDao.remove(id);
        return Response.ok(usuario).build();
    }

    @GET
    @Path("/partial")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, Object>> getMessage() {
        //public Response getMessage() {
        List<Map<String, Object>> usuarios = new ArrayList<>();

        Map<String, Object> usuario = new HashMap<>();
        usuario.put("Nombre", "Nahum Alarcon");
        usuario.put("Email", "nahum.rahim@gmail.com");
        usuario.put("Telefono", "123456");
        usuario.put("Activo", 1);
        usuarios.add(usuario);

        usuario = new HashMap<>();
        usuario.put("Nombre", "nalarcon");
        usuario.put("Email", "nahum@verynicetech.com");
        //usuario.put("Telefono", "99998888");
        usuarios.add(usuario);

        return usuarios;
        //return Response.ok(usuario).build();

    }

}
