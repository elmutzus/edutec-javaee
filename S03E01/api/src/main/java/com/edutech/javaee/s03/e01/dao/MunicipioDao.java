/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dao;

import com.edutech.javaee.s03.e01.dto.MunicipioDto;
import com.edutech.javaee.s03.e01.model.Departamento;
import com.edutech.javaee.s03.e01.model.Municipio;
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
public class MunicipioDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    final DepartamentoDao dptDao;

    public MunicipioDao() {
        this.dptDao = null;
    }

    @Inject
    public MunicipioDao(DepartamentoDao departamentoDao) {
        this.dptDao = departamentoDao;
    }

    private Departamento findDepartamento(Integer id) {
        Departamento d = this.dptDao.find(id);

        if (d == null) {
            throw new NullPointerException("No se puede encontrar el departamento requerido: " + id);
        }

        return d;
    }

    public Municipio find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Municipio u JOIN FETCH u.departamento WHERE u.id = :parametro", Municipio.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Municipio> findAll() {
        return this.em
                .createQuery("SELECT u FROM Municipio u JOIN FETCH u.departamento", Municipio.class)
                .getResultList();
    }

    public Municipio save(MunicipioDto dto) {
        Municipio m = new Municipio();

        Departamento d = findDepartamento(dto.getDepartamento());
        m.setDepartamento(d);

        m.setCodigo(dto.getCodigo());
        m.setNombre(dto.getNombre());

        this.em.persist(m);
        return m;
    }

    public Municipio edit(MunicipioDto dto) {
        Municipio m = this.find(dto.getId());

        if (m != null) {
            Departamento d = findDepartamento(dto.getDepartamento());
            m.setDepartamento(d);

            m.setCodigo(dto.getCodigo());
            m.setNombre(dto.getNombre());
            this.em.merge(m);
        }
        return m;
    }

    public Municipio remove(Integer id) {
        Municipio ap = this.find(id);
        this.em.remove(ap);
        return ap;
    }
}
