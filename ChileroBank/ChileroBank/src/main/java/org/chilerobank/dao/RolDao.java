/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.chilerobank.model.Rol;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class RolDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public List<Rol> findAll() {
        return this.em.createNamedQuery("rol.findAll").getResultList();
    }

    public Rol find(Integer id) {
        try {
            return this.em
                    .createNamedQuery("rol.findById", Rol.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
    public Rol find(String name) {
        try {
            return this.em
                    .createNamedQuery("rol.findByName", Rol.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public Rol save(Rol entity) {
        this.em.persist(entity);
        return entity;
    }

    public Rol edit(Rol entity) {
        Rol sl = this.find(entity.getId());
        if (sl != null) {
            sl.setDescripcion(entity.getDescripcion());
            sl.setNombre(entity.getNombre());
            sl.setUsuarios(entity.getUsuarios());

            this.em.merge(sl);
        }
        return sl;
    }

    public Rol remove(Integer id) {
        Rol sl = this.find(id);
        this.em.remove(sl);
        return sl;
    }
}
