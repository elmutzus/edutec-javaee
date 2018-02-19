/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dao;

import com.edutech.javaee.s03.e01.dto.ParametroSistemaDto;
import com.edutech.javaee.s03.e01.model.ParametroSistema;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
@Stateless
public class ParametroSistemaDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public ParametroSistema find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM ParametroSistema WHERE u.id = :parametro", ParametroSistema.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<ParametroSistema> findAll() {
        return this.em
                .createQuery("SELECT u FROM ParametroSistema u JOIN FETCH u.departamento", ParametroSistema.class)
                .getResultList();
    }

    public ParametroSistema save(ParametroSistemaDto dto) {
        ParametroSistema ps = new ParametroSistema();

        ps.setNombre(dto.getNombre());
        ps.setValor(dto.getValor());

        this.em.persist(ps);
        return ps;
    }

    public ParametroSistema edit(ParametroSistemaDto dto) {
        ParametroSistema ps = this.find(dto.getId());

        if (ps != null) {
            ps.setNombre(dto.getNombre());
            ps.setValor(dto.getValor());
            this.em.merge(ps);
        }
        return ps;
    }

    public ParametroSistema remove(Integer id) {
        ParametroSistema ap = this.find(id);
        this.em.remove(ap);
        return ap;
    }
}
