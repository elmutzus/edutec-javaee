/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dto.ProfesorDto;
import com.edutech.javaee.s03.e01.model.Profesor;
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
@Path("/profesores")
public class ProfesorEndpoint {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    private Profesor getSpecific(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Profesor WHERE u.id = :param", Profesor.class)
                    .setParameter("param", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @GET
    @Produces({"application/json"})
    public List<Profesor> findAll() {
        List<Profesor> profesores = this.em
                .createQuery("SELECT u FROM Profesor", Profesor.class)
                .getResultList();
        return profesores;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Profesor profesor = this.getSpecific(id);

        if (profesor == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(profesor, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(ProfesorDto dto) {
        Profesor profesor = new Profesor(
                dto.getCarnet(),
                dto.getNombre(),
                dto.getDireccion()
        );
        this.em.persist(profesor);
        return Response.ok(profesor).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(ProfesorDto dto) throws RollbackException {
        Profesor profesor = this.getSpecific(dto.getId());

        if (profesor == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        profesor.setCarnet(dto.getCarnet());
        profesor.setDireccion(dto.getDireccion());
        profesor.setNombre(dto.getNombre());

        this.em.merge(profesor);
        return Response.ok(profesor).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Profesor profesor = this.getSpecific(id);

        if (profesor == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        this.em.remove(profesor);
        return Response.ok(profesor).build();
    }
}
