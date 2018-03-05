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
        return this.em
                .createQuery("SELECT r FROM Rol r ", Rol.class)
                .getResultList();
    }

    public Rol find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT r FROM Rol r WHERE r.id = :id", Rol.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
