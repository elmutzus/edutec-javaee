/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dto.SedeDto;
import com.edutech.javaee.s03.e01.model.Municipio;
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
@Path("/sedes")
public class SedeEndpoint {
    
    @PersistenceContext(unitName = "primary")
    EntityManager em;

    private Sede buscarSede(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Sede u JOIN FETCH u.municipio WHERE u.id = :parametro", Sede.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @GET
    @Produces({"application/json"})
    public List<Sede> findAll() {
        List<Sede> sd = this.em
                .createQuery("SELECT u FROM Sede u JOIN FETCH u.municipio", Sede.class)
                .getResultList();
        return sd;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Sede sd = this.buscarSede(id);

        if (sd == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(sd, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(SedeDto dto) {
        Sede sl = new Sede(
                dto.getCodigo(),
                dto.getNombre(),
                dto.getDireccion(),
                this.em.find(Municipio.class, dto.getMunicipio())
        );

        this.em.persist(sl);

        return Response.ok(sl).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(SedeDto dto) throws RollbackException {
        Sede sd = this.buscarSede(dto.getId());

        if (sd == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        sd.setCodigo(dto.getCodigo());
        sd.setDireccion(dto.getDireccion());
        sd.setNombre(dto.getNombre());
        sd.setMunicipio(this.em.find(Municipio.class, dto.getMunicipio()));

        this.em.merge(sd);
        return Response.ok(sd).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Sede sd = this.buscarSede(id);

        if (sd == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        this.em.remove(sd);
        return Response.ok(sd).build();
    }
}
