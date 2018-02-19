/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dao;

import com.edutech.javaee.s03.e01.dto.CicloDto;
import com.edutech.javaee.s03.e01.model.Ciclo;
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
public class CicloDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    public Ciclo find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Ciclo u WHERE u.id = :param", Ciclo.class)
                    .setParameter("param", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Ciclo> findAll() {
        return this.em
                .createQuery("SELECT u FROM Ciclo u", Ciclo.class)
                .getResultList();
    }

    public Ciclo save(CicloDto dto) {
        Ciclo c = new Ciclo();

        c.setCodigo(dto.getCodigo());

        this.em.persist(c);
        return c;
    }

    public Ciclo edit(CicloDto dto) {
        Ciclo c = this.find(dto.getId());

        if (c != null) {
            c.setCodigo(dto.getCodigo());
            this.em.merge(c);
        }
        return c;
    }

    public Ciclo remove(Integer id) {
        Ciclo ap = this.find(id);
        this.em.remove(ap);
        return ap;
    }
}
