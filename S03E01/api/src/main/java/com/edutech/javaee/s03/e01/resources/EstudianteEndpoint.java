/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dto.EstudianteDto;
import com.edutech.javaee.s03.e01.model.Estudiante;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
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
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/estudiantes")
public class EstudianteEndpoint {
    @PersistenceContext(unitName = "primary")
    EntityManager em;

    private Estudiante getSpecific(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Estudiante WHERE u.id = :param", Estudiante.class)
                    .setParameter("param", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @GET
    @Produces({"application/json"})
    public List<Estudiante> findAll() {
        List<Estudiante> ciclos = this.em
                .createQuery("SELECT u FROM Estudiante", Estudiante.class)
                .getResultList();
        return ciclos;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Estudiante estudiante = this.getSpecific(id);

        if (estudiante == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(estudiante, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(EstudianteDto dto) {
        Estudiante estudiante = new Estudiante(
                dto.getCarnet(),
                dto.getNombre(),
                dto.getDireccion()
        );
        this.em.persist(estudiante);
        return Response.ok(estudiante).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(EstudianteDto dto) throws RollbackException {
        Estudiante estudiante = this.getSpecific(dto.getId());

        if (estudiante == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        estudiante.setCarnet(dto.getCarnet());
        estudiante.setDireccion(dto.getDireccion());
        estudiante.setNombre(dto.getNombre());
        
        this.em.merge(estudiante);
        return Response.ok(estudiante).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Estudiante estudiante = this.getSpecific(id);

        if (estudiante == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        this.em.remove(estudiante);
        return Response.ok(estudiante).build();
    }
}
