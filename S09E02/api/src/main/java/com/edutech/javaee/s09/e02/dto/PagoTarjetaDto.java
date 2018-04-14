package com.edutech.javaee.s09.e02.dto;

/**
 *
 * @author nahum
 */
public class PagoTarjetaDto {
    // Input
    private String numeroTarjeta;
    private Float montoPago;
    
    // Output
    private Float saldo;
    private Float disponible;

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public Float getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(Float montoPago) {
        this.montoPago = montoPago;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Float getDisponible() {
        return disponible;
    }

    public void setDisponible(Float disponible) {
        this.disponible = disponible;
    }    
}
