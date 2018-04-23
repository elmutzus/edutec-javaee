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

    public Departamento findByCode(String id) {
        try {
            return this.em
                    .createNamedQuery("Departamento.findByCode", Departamento.class)
                    .setParameter("codeDepartamento", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public Departamento find(String name) {
        try {
            return this.em
                    .createNamedQuery("Departamento.findByName", Departamento.class)
                    .setParameter("nameDepartamento", name)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Departamento> findAll() {
        return this.em.createNamedQuery("Departamento.findAll").getResultList();
    }

    public Departamento save(Departamento entity) {
        this.em.persist(entity);
        return entity;
    }

    public Departamento edit(Departamento entity) {
        Departamento dep = this.find(entity.getId());
        if (dep != null) {
            dep.setCodigo(entity.getCodigo());
            dep.setNombre(entity.getNombre());

            if (entity.getMunicipios() != null && entity.getMunicipios().size() > 0) {
                dep.setMunicipios(entity.getMunicipios());
            }
            
            this.em.merge(dep);
        }
        return dep;
    }

    public Departamento remove(Integer id) {
        Departamento dep = this.find(id);
        this.em.remove(dep);
        return dep;
    }
}
