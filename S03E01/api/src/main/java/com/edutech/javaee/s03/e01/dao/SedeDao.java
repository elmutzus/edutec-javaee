/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dao;

import com.edutech.javaee.s03.e01.dto.SedeDto;
import com.edutech.javaee.s03.e01.model.Municipio;
import com.edutech.javaee.s03.e01.model.Sede;
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
public class SedeDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    final MunicipioDao mnDao;

    public SedeDao() {
        this.mnDao = null;
    }

    @Inject
    public SedeDao(MunicipioDao municipioDao) {
        this.mnDao = municipioDao;
    }

    private Municipio findMunicipio(Integer id) {
        Municipio m = this.mnDao.find(id);

        if (m == null) {
            throw new NullPointerException("No se puede encontrar el municipio requerido: " + id);
        }

        return m;
    }

    public Sede find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Sede u JOIN FETCH u.municipio WHERE u.id = :parametro", Sede.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Sede> findAll() {
        return this.em
                .createQuery("SELECT u FROM Sede u JOIN FETCH u.municipio", Sede.class)
                .getResultList();
    }

    public Sede save(SedeDto dto) {
        Sede s = new Sede();
        Municipio m = findMunicipio(dto.getMunicipio());

        s.setMunicipio(m);
        s.setCodigo(dto.getCodigo());
        s.setDireccion(dto.getDireccion());
        s.setNombre(dto.getNombre());

        this.em.persist(s);
        return s;
    }

    public Sede edit(SedeDto dto) {
        Sede s = this.find(dto.getId());

        if (s != null) {
            Municipio m = findMunicipio(dto.getMunicipio());

            s.setMunicipio(m);
            s.setCodigo(dto.getCodigo());
            s.setDireccion(dto.getDireccion());
            s.setNombre(dto.getNombre());
            this.em.merge(s);
        }
        return s;
    }

    public Sede remove(Integer id) {
        Sede ap = this.find(id);
        this.em.remove(ap);
        return ap;
    }
}
