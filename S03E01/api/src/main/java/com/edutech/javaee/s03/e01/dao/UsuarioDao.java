/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dao;

import com.edutech.javaee.s03.e01.dto.UsuarioDto;
import com.edutech.javaee.s03.e01.model.Rol;
import com.edutech.javaee.s03.e01.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
public class UsuarioDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    final RolDao rlDao;

    public UsuarioDao() {
        this.rlDao = null;
    }

    @Inject
    public UsuarioDao(RolDao rolDao) {
        this.rlDao = rolDao;
    }

    private Rol findRol(Integer id) {
        Rol r = this.rlDao.find(id);

        if (r == null) {
            throw new NullPointerException("No se puede encontrar el rol requerido: " + id);
        }

        return r;
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

    public Usuario save(UsuarioDto dto) {
        Usuario u = new Usuario();
        Rol r = findRol(dto.getIdRol());

        u.setRol(r);
        u.setCodigo(dto.getCodigo());
        u.setEmail(dto.getEmail());
        u.setNombre(dto.getNombre());
        u.setPassword(dto.getPassword());
        u.setTelefono(dto.getTelefono());

        this.em.persist(u);
        return u;
    }

    public Usuario edit(UsuarioDto dto) {
        Usuario u = this.find(dto.getId());

        if (u != null) {
            Rol r = findRol(dto.getIdRol());

            u.setRol(r);
            u.setCodigo(dto.getCodigo());
            u.setEmail(dto.getEmail());
            u.setNombre(dto.getNombre());
            u.setPassword(dto.getPassword());
            u.setTelefono(dto.getTelefono());
            this.em.merge(u);
        }
        return u;
    }

    public Usuario remove(Integer id) {
        Usuario ap = this.find(id);
        this.em.remove(ap);
        return ap;
    }
}
