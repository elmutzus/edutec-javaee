package com.edutech.javaee.s09.e02.resources;

import com.edutech.javaee.s09.e02.model.Tarjeta;
import com.edutech.javaee.s09.e02.dto.PagoTarjetaDto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
@Path("/consultas")
public class TarjetaEndpoint {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    @GET
    @Produces({"application/json"})
    public List<Tarjeta> findAll() {
        List<Tarjeta> tarjetas = em.createQuery("SELECT u FROM Tarjeta u ", Tarjeta.class)
                .getResultList();        
        return tarjetas;
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Integer id) {
        Tarjeta tarjeta = this.em.find(Tarjeta.class, id);
        
        if (tarjeta == null)
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")                    
                    .build();
        
        return Response.ok(tarjeta, MediaType.APPLICATION_JSON).build();
    }
        
    @GET
    @Path("validate/{number}")
    @Produces({"application/json"})
    public Response validate(@PathParam("number") String numTarjeta) {
        List<Tarjeta> tarjetas = em.createQuery("SELECT t FROM Tarjeta t WHERE t.numero = :numero", Tarjeta.class)
               .setParameter("numero", numTarjeta)
               .getResultList();
        
        if (tarjetas.isEmpty())
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Recurso no encontrado")                    
                    .build();
        
        if (tarjetas.get(0).getDisponible() <= 0)
            return Response
                    .status(Response.Status.NOT_ACCEPTABLE)
                    .type(MediaType.TEXT_HTML)
                    .entity("Imposible realizar operacion")                    
                    .build();
        
        List<String> informacion = new ArrayList<>();
        informacion.add(tarjetas.get(0).getDescripcion());
        informacion.add(tarjetas.get(0).getDisponible().toString());
        informacion.add(tarjetas.get(0).getSaldo().toString());
        return Response.ok(informacion).build();
    }
    
    @POST
    @Path("pagar")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response pagar(PagoTarjetaDto dto) {
        if(dto.getMontoPago() == 0)
            return Response
                    .status(Response.Status.CONFLICT)
                    .type(MediaType.TEXT_HTML)
                    .entity("El monto de pago debe ser mayor a cero")                    
                    .build();
        
        if("".equals(dto.getNumeroTarjeta()))
            return Response
                    .status(Response.Status.CONFLICT)
                    .type(MediaType.TEXT_HTML)
                    .entity("Debe ingresar el número de tarjeta a pagar")                    
                    .build();
        
        List<Tarjeta> tarjetas = em.createQuery("SELECT t FROM Tarjeta t WHERE t.numero = :numero", Tarjeta.class)
               .setParameter("numero", dto.getNumeroTarjeta())
               .getResultList();
        
        if(tarjetas == null || tarjetas.isEmpty())
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Tarjeta no encontrada")                    
                    .build();
        
        Tarjeta tarjeta = tarjetas.get(0);
        
        tarjeta.setDisponible(tarjeta.getDisponible() + dto.getMontoPago());
        tarjeta.setSaldo(tarjeta.getSaldo() - dto.getMontoPago());
        
        em.persist(tarjeta);
        
        dto.setDisponible(tarjeta.getDisponible());
        dto.setSaldo(tarjeta.getSaldo());
        
        return Response.ok().entity(dto).build();
    }
    
    @POST
    @Path("consumir")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response consumir(PagoTarjetaDto dto) {
        if(dto.getMontoPago() == 0)
            return Response
                    .status(Response.Status.CONFLICT)
                    .type(MediaType.TEXT_HTML)
                    .entity("El monto de consumo debe ser mayor a cero")                    
                    .build();
        
        if("".equals(dto.getNumeroTarjeta()))
            return Response
                    .status(Response.Status.CONFLICT)
                    .type(MediaType.TEXT_HTML)
                    .entity("Debe ingresar el número de tarjeta a pagar")                    
                    .build();
        
        List<Tarjeta> tarjetas = em.createQuery("SELECT t FROM Tarjeta t WHERE t.numero = :numero", Tarjeta.class)
               .setParameter("numero", dto.getNumeroTarjeta())
               .getResultList();
        
        if(tarjetas == null || tarjetas.isEmpty())
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_HTML)
                    .entity("Tarjeta no encontrada")                    
                    .build();
        
        Tarjeta tarjeta = tarjetas.get(0);
        
        if(dto.getMontoPago() > tarjeta.getDisponible())
            return Response
                    .status(Response.Status.CONFLICT)
                    .type(MediaType.TEXT_HTML)
                    .entity("El monto de consumo supera el monto disponible en la tarjeta")                    
                    .build();
        
        tarjeta.setDisponible(tarjeta.getDisponible() - dto.getMontoPago());
        tarjeta.setSaldo(tarjeta.getSaldo() + dto.getMontoPago());
        
        em.persist(tarjeta);
        
        dto.setDisponible(tarjeta.getDisponible());
        dto.setSaldo(tarjeta.getSaldo());
        
        return Response.ok().entity(dto).build();
    }    
}
