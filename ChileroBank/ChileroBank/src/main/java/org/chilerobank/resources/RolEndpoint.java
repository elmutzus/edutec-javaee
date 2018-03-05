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
import javax.ws.rs.Produces;
import org.chilerobank.dao.RolDao;
import org.chilerobank.model.Rol;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
@Path("/roles")
public class RolEndpoint {

    final RolDao rolDao;

    public RolEndpoint() {
        this.rolDao = null;
    }

    @Inject
    public RolEndpoint(RolDao rolDao) {
        this.rolDao = rolDao;
    }

    @GET
    @Produces({"application/json"})
    public List<Rol> findAll() {
        return this.rolDao.findAll();
    }
}
