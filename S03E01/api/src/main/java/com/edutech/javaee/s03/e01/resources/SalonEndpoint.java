/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dto.SalonDto;
import com.edutech.javaee.s03.e01.model.Salon;
import com.edutech.javaee.s03.e01.model.Sede;
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
@Path("/salones")
public class SalonEndpoint {
    
    @PersistenceContext(unitName = "primary")
    EntityManager em;

    private Salon buscarSalon(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Salon u JOIN FETCH u.sede WHERE u.id = :parametro", Salon.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @GET
    @Produces({"application/json"})
    public List<Salon> findAll() {
        List<Salon> cr = this.em
                .createQuery("SELECT u FROM Salon u JOIN FETCH u.sede", Salon.class)
                .getResultList();
        return cr;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Salon sl = this.buscarSalon(id);

        if (sl == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(sl, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(SalonDto dto) {
        Salon sl = new Salon(
                dto.getCodigo(),
                this.em.find(Sede.class, dto.getSede())
        );

        this.em.persist(sl);

        return Response.ok(sl).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(SalonDto dto) throws RollbackException {
        Salon sl = this.buscarSalon(dto.getId());

        if (sl == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        sl.setCodigo(dto.getCodigo());
        sl.setSede(this.em.find(Sede.class, dto.getSede()));

        this.em.merge(sl);
        return Response.ok(sl).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Salon sl = this.buscarSalon(id);

        if (sl == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        this.em.remove(sl);
        return Response.ok(sl).build();
    }
}
