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
import org.chilerobank.model.Cuenta;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class CuentaDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public Cuenta find(Integer id) {
        try {
            return this.em
                    .createNamedQuery("cuenta.findById", Cuenta.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Cuenta> findAll() {
        return this.em.createNamedQuery("cuenta.findAll").getResultList();
    }

    public Cuenta save(Cuenta entity) {
        this.em.persist(entity);
        return entity;
    }

    public Cuenta edit(Cuenta entity) {
        Cuenta ct = this.find(entity.getId());
        if (ct != null) {
            ct.setCliente(entity.getCliente());
            ct.setEstado(entity.getEstado());
            ct.setFechaApertura(entity.getFechaApertura());
            ct.setMoneda(entity.getMoneda());
            ct.setTipoCuenta(entity.getTipoCuenta());
            ct.setTransacciones(entity.getTransacciones());
            this.em.merge(ct);
        }
        return ct;
    }

    public Cuenta remove(Integer id) {
        Cuenta ct = this.find(id);
        this.em.remove(ct);
        return ct;
    }
}
