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
import org.chilerobank.model.Transaccion;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class TransaccionDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public Transaccion find(Integer id) {
        try {
            return this.em
                    .createNamedQuery("transaccion.findById", Transaccion.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Transaccion> findAll() {
        return this.em.createNamedQuery("transaccion.findAll").getResultList();
    }

    public Transaccion save(Transaccion entity) {
        this.em.persist(entity);
        return entity;
    }

    public Transaccion edit(Transaccion entity) {
        Transaccion trx = this.find(entity.getId());
        if (trx != null) {
            trx.setCuenta(entity.getCuenta());
            trx.setFechaMovimiento(entity.getFechaMovimiento());
            trx.setMonto(entity.getMonto());
            trx.setOperacion(entity.getOperacion());
            this.em.merge(trx);
        }
        return trx;
    }

    public Transaccion remove(Integer id) {
        Transaccion trx = this.find(id);
        this.em.remove(trx);
        return trx;
    }
}
