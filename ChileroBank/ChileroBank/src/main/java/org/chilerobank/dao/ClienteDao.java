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
import org.chilerobank.model.Cliente;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class ClienteDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public Cliente find(Integer id) {
        try {
            return this.em
                    .createNamedQuery("cliente.findById", Cliente.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public Cliente find(String nombre) {
        try {
            return this.em
                    .createNamedQuery("cliente.findByName", Cliente.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Cliente> findAll() {
        return this.em.createNamedQuery("cliente.findAll").getResultList();
    }

    public Cliente save(Cliente entity) {
        this.em.persist(entity);
        return entity;
    }

    public Cliente edit(Cliente entity) {
        Cliente cl = this.find(entity.getId());
        if (cl != null) {
            cl.setDireccion(entity.getDireccion());
            cl.setFechaNacimiento(entity.getFechaNacimiento());
            cl.setMunicipio(entity.getMunicipio());
            cl.setNit(entity.getNit());
            cl.setNombre(entity.getNombre());
            this.em.merge(cl);
        }
        return cl;
    }

    public Cliente remove(Integer id) {
        Cliente cl = this.find(id);
        this.em.remove(cl);
        return cl;
    }
}
