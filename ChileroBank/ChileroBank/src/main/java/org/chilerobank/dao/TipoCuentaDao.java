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
import org.chilerobank.model.TipoCuenta;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class TipoCuentaDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public TipoCuenta find(Integer id) {
        try {
            return this.em
                    .createNamedQuery("tipoCuenta.findById", TipoCuenta.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<TipoCuenta> findAll() {
        return this.em.createNamedQuery("tipoCuenta.findAll").getResultList();
    }

    public TipoCuenta save(TipoCuenta entity) {
        this.em.persist(entity);
        return entity;
    }

    public TipoCuenta edit(TipoCuenta entity) {
        TipoCuenta tc = this.find(entity.getId());
        if (tc != null) {
            tc.setDescripcion(entity.getDescripcion());
            tc.setNombre(entity.getNombre());
            tc.setTasaInteres(entity.getTasaInteres());
            this.em.merge(tc);
        }
        return tc;
    }

    public TipoCuenta remove(Integer id) {
        TipoCuenta tc = this.find(id);
        this.em.remove(tc);
        return tc;
    }
}
