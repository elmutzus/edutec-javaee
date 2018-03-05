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
import org.chilerobank.model.Usuario;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class UsuarioDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public UsuarioDao() {
    }

    public UsuarioDao(EntityManager em) {
        this.em = em;
    }

    public Usuario find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Usuario u JOIN FETCH u.rol WHERE u.id = :parametro", Usuario.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Usuario> findAll() {
        return this.em
                .createQuery("SELECT u FROM Usuario u JOIN FETCH u.rol", Usuario.class)
                .getResultList();
    }

    public Usuario save(Usuario entity) {
        this.em.persist(entity);
        return entity;
    }

    public Usuario edit(Usuario entity) {
        Usuario usuario = this.find(entity.getId());
        if (usuario != null) {
            usuario.setCodigo(entity.getCodigo());
            usuario.setEmail(entity.getEmail());
            usuario.setNombre(entity.getNombre());
            usuario.setPassword(entity.getPassword());
            usuario.setTelefono(entity.getTelefono());
            this.em.merge(usuario);
        }
        return usuario;
    }

    public Usuario remove(Integer id) {
        Usuario usuario = this.find(id);
        this.em.remove(usuario);
        return usuario;
    }
}
