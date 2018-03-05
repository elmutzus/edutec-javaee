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
import org.chilerobank.model.Departamento;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class DepartamentoDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public Departamento find(Integer id) {
        try {
            return this.em
                    .createNamedQuery("Departamento.findById", Departamento.class)
                    .setParameter("idDepartamento", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Departamento> findAll() {
        return this.em.createNamedQuery("Departamento.findAll").getResultList();
    }
}
