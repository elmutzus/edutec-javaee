/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edutech.javaee.s03.e01.dao;

import com.edutech.javaee.s03.e01.dto.DepartamentoDto;
import com.edutech.javaee.s03.e01.model.Departamento;
import com.edutech.javaee.s03.e01.model.Municipio;
import java.util.ArrayList;
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
public class DepartamentoDao {

    @PersistenceContext(unitName = "primary")
    EntityManager em;

    final MunicipioDao mnDao;

    public DepartamentoDao() {
        this.mnDao = null;
    }

    @Inject
    public DepartamentoDao(MunicipioDao municipioDao) {
        this.mnDao = municipioDao;
    }

    private Municipio findMunicipio(Integer id) {
        Municipio m = this.mnDao.find(id);

        if (m == null) {
            throw new NullPointerException("No se puede encontrar el municipio requerido: " + id);
        }

        return m;
    }

    private List<Municipio> findAllMunicipios(List<Integer> ids) {
        List<Municipio> municipios = new ArrayList<>();

        ids.forEach((id) -> {
            municipios.add(findMunicipio(id));
        });

        return municipios;
    }

    public Departamento find(Integer id) {
        try {
            return this.em
                    .createQuery("SELECT u FROM Departamento u JOIN FETCH u.municipios WHERE u.id = :parametro", Departamento.class)
                    .setParameter("parametro", id)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public List<Departamento> findAll() {
        return this.em
                .createQuery("SELECT u FROM Departamento u JOIN FETCH u.municipios", Departamento.class)
                .getResultList();
    }

    public Departamento save(DepartamentoDto dto) {
        Departamento d = new Departamento();

        List<Municipio> municipio = findAllMunicipios(dto.getMunicipios());
        d.setMunicipios(municipio);

        d.setCodigo(dto.getCodigo());
        d.setNombre(dto.getNombre());

        this.em.persist(d);
        return d;
    }

    public Departamento edit(DepartamentoDto dto) {
        Departamento d = this.find(dto.getId());

        if (d != null) {
            List<Municipio> municipio = findAllMunicipios(dto.getMunicipios());
            d.setMunicipios(municipio);

            d.setCodigo(dto.getCodigo());
            d.setNombre(dto.getNombre());
            this.em.merge(d);
        }
        return d;
    }

    public Departamento remove(Integer id) {
        Departamento ap = this.find(id);
        this.em.remove(ap);
        return ap;
    }
}
