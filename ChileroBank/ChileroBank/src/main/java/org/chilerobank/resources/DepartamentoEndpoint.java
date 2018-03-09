/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.resources;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.chilerobank.dao.DepartamentoDao;
import org.chilerobank.dto.ErrorMessageDto;
import org.chilerobank.model.Departamento;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/departamentos")
public class DepartamentoEndpoint {

    @Inject
    DepartamentoDao dao;

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Response findById(@PathParam("id") Integer id) {
        Departamento departamento = this.dao.find(id);
        if (departamento == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ErrorMessageDto(false, 404, "Recurso no encontrado"))
                    .build();
        }

        return Response.ok(departamento).build();
    }

    @GET
    @Produces({"application/json"})
    public List<Departamento> findAll() {
        return this.dao.findAll();
    }
}
