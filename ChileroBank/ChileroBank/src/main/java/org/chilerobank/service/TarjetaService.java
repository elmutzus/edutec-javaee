/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.chilerobank.service;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.chilerobank.dto.PagoTarjetaDto;

/**
 *
 * @author Elder Mutzus <elmutzus@gmail.com>
 */
public class TarjetaService {

    public List<String> getCardAuthorization(String idCard) {
        Client client = ClientBuilder.newClient();
        List<String> informacion = new ArrayList<>();
        try {
            informacion = client.target("http://localhost:8081/tarjeta/api/consultas/validate/" + idCard)
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<String>>() {
                    });
        } catch (Exception e) {
            informacion.add("Error de comunicacion");
        }
        return informacion;
    }

    public PagoTarjetaDto pay(PagoTarjetaDto dto) {

        Client client = ClientBuilder.newClient();

        try {

            return client.target("http://localhost:8081/tarjeta/api/consultas/pagar")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(dto), PagoTarjetaDto.class);

        } catch (Exception ex) {
            throw ex;
        }
    }

    public PagoTarjetaDto consume(PagoTarjetaDto dto) {

        Client client = ClientBuilder.newClient();

        try {

            return client.target("http://localhost:8081/tarjeta/api/consultas/consumir")
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(dto), PagoTarjetaDto.class);

        } catch (Exception ex) {
            throw ex;
        }
    }

}
