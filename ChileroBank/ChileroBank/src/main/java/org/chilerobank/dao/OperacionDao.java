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
import org.chilerobank.model.Operacion;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class OperacionDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public Operacion find(Integer id) {
        try {
            return this.em
                    .createNamedQuery("operacion.findById", Operacion.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
    public Operacion find(String name) {
        try {
            return this.em
                    .createNamedQuery("operacion.findByName", Operacion.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Operacion> findAll() {
        return this.em.createNamedQuery("operacion.findAll").getResultList();
    }

    public Operacion save(Operacion entity) {
        this.em.persist(entity);
        return entity;
    }

    public Operacion edit(Operacion entity) {
        Operacion opr = this.find(entity.getId());
        if (opr != null) {
            opr.setDescripcion(entity.getDescripcion());
            opr.setNombre(entity.getNombre());
            this.em.merge(opr);
        }
        return opr;
    }

    public Operacion remove(Integer id) {
        Operacion opr = this.find(id);
        this.em.remove(opr);
        return opr;
    }
}
