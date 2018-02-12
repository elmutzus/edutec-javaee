/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.resources;

import com.edutech.javaee.s03.e01.dto.CursoDto;
import com.edutech.javaee.s03.e01.model.Ciclo;
import com.edutech.javaee.s03.e01.model.Curso;
import com.edutech.javaee.s03.e01.model.Salon;
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
@Path("/cursos")
public class CursoEndpoint {
    @PersistenceContext(unitName = "primary")
    EntityManager em;

    private Curso buscarCurso(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Curso u JOIN FETCH u.ciclo JOIN FETCH u.salon WHERE u.id = :parametro", Curso.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch(NoResultException nre) {
            return null;
        }
    }
    
    @GET
    @Produces({"application/json"})
    public List<Curso> findAll() {
        List<Curso> cr = this.em
                .createQuery("SELECT u FROM Curso u JOIN FETCH u.ciclo JOIN FETCH u.salon", Curso.class)
                .getResultList();        
        return cr;
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {      
        Curso cr = this.buscarCurso(id);
        
        if (cr == null)
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")                    
                    .build();
        
        return Response.ok(cr, MediaType.APPLICATION_JSON).build();
    }
    
    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response create(CursoDto dto) {
        Curso ap = new Curso(
                dto.getCodigo(),
                dto.getDireccion(),
                this.em.find(Ciclo.class, dto.getCiclo()),
                this.em.find(Salon.class, dto.getSalon())             
            );
        this.em.persist(ap);
        return Response.ok(ap).build();
    }

    @PUT
    @Produces({"application/json"})
    public Response update(CursoDto dto) throws RollbackException {
        Curso cr = this.buscarCurso(dto.getId());

        if (cr == null)
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")                    
                    .build();
        
        cr.setCodigo(dto.getCodigo());
        cr.setDireccion(dto.getDireccion());
        cr.setCiclo(this.em.find(Ciclo.class, dto.getCiclo()));
        cr.setSalon(this.em.find(Salon.class, dto.getSalon()));
        
        this.em.merge(cr);
        return Response.ok(cr).build();
    }
    
    @DELETE
    @Path("{id}")
    @Produces({"application/json"})
    public Response delete(@PathParam("id") Integer id) {
        Curso cr = this.buscarCurso(id);

        if (cr == null)
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")                    
                    .build();
            
        this.em.remove(cr);
        return Response.ok(cr).build();
    }
}
