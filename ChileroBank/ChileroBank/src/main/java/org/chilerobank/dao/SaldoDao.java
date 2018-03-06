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
import org.chilerobank.model.Saldo;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class SaldoDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public Saldo find(Integer id) {
        try {
            return this.em
                    .createNamedQuery("saldo.findById", Saldo.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Saldo> findAll() {
        return this.em.createNamedQuery("saldo.findAll").getResultList();
    }

    public Saldo save(Saldo entity) {
        this.em.persist(entity);
        return entity;
    }

    public Saldo edit(Saldo entity) {
        Saldo sl = this.find(entity.getId());
        if (sl != null) {
            sl.setCuenta(entity.getCuenta());
            sl.setFecha(entity.getFecha());
            this.em.merge(sl);
        }
        return sl;
    }

    public Saldo remove(Integer id) {
        Saldo sl = this.find(id);
        this.em.remove(sl);
        return sl;
    }
}
