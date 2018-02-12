/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dto.AsignacionEstudianteDto;
import com.edutech.javaee.s03.e01.model.AsignacionEstudiante;
import com.edutech.javaee.s03.e01.model.Curso;
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
@Path("/asignacionEstudiantes")
public class AsignacionEstudianteEndpoint {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    private AsignacionEstudiante buscarAsignacionEstudiante(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM AsignacionEstudiante u JOIN FETCH u.estudiante JOIN FETCH u.curso WHERE u.id = :parametro", AsignacionEstudiante.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @GET
    @Produces({"application/json"})
    public List<AsignacionEstudiante> findAll() {
        List<AsignacionEstudiante> ae = this.em
                .createQuery("SELECT u FROM AsignacionEstudiante u JOIN FETCH u.estudiante JOIN FETCH u.curso", AsignacionEstudiante.class)
                .getResultList();
        return ae;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        AsignacionEstudiante ae = this.buscarAsignacionEstudiante(id);

        if (ae == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        return Response.ok(ae, MediaType.APPLICATION_JSON).build();
    }

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(AsignacionEstudianteDto dto) {
        AsignacionEstudiante ae = new AsignacionEstudiante(
                dto.getZona(),
                dto.getExamenFinal(),
                dto.getNotaFinal(),
                this.em.find(Estudiante.class, dto.getEstudiante()),
                this.em.find(Curso.class, dto.getCurso())
        );
        this.em.persist(ae);
        return Response.ok(ae).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(AsignacionEstudianteDto dto) throws RollbackException {
        AsignacionEstudiante ae = this.buscarAsignacionEstudiante(dto.getId());

        if (ae == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        ae.setCurso(this.em.find(Curso.class, dto.getCurso()));
        ae.setEstudiante(this.em.find(Estudiante.class, dto.getEstudiante()));
        ae.setExamenFinal(dto.getExamenFinal());
        ae.setNotaFinal(dto.getNotaFinal());
        ae.setZona(dto.getZona());

        this.em.merge(ae);
        return Response.ok(ae).build();
    }

    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        AsignacionEstudiante ae = this.buscarAsignacionEstudiante(id);

        if (ae == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")
                    .build();
        }

        this.em.remove(ae);
        return Response.ok(ae).build();
    }
}
