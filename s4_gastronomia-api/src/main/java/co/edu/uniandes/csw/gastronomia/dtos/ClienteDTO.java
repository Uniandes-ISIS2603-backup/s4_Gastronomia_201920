/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Cristina Isabel González Osorio
 */
public class ClienteDTO implements Serializable{
    
    private Date cumpleanos;
    
    private String numeroContacto;
    
    private int puntos;
    
    //private TarjetaDeCreditoDTO tarjetaCredito;
    

    public ClienteDTO() {
    }

    /**
     * @return the cumpleanos
     */
    public Date getCumpleanos() {
        return cumpleanos;
    }

    /**
     * @return the numeroContacto
     */
    public String getNumeroContacto() {
        return numeroContacto;
    }

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @param cumpleanos the cumpleanos to set
     */
    public void setCumpleanos(Date cumpleanos) {
        this.cumpleanos = cumpleanos;
    }

    /**
     * @param numeroContacto the numeroContacto to set
     */
    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * @return the tarjetaCredito
     */
    //public TarjetaDeCreditoDTO getTarjetaCredito() {
      //  return tarjetaCredito;
    //}

    /**
     * @param tarjetaCredito the tarjetaCredito to set
     */
    //public void setTarjetaCredito(TarjetaDeCreditoDTO tarjetaCredito) {
      //  this.tarjetaCredito = tarjetaCredito;
    //}
    
}
