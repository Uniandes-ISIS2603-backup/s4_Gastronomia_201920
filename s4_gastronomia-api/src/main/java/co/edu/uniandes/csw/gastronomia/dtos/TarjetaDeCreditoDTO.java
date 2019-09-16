/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.TarjetaDeCreditoEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author je.canizarez
 */
public class TarjetaDeCreditoDTO implements Serializable {
    
    private long numero; 
    private int cvv; 
    private Date fechaVencimiento; 
    
    public TarjetaDeCreditoDTO(TarjetaDeCreditoEntity tarjeta)
    {
        numero = tarjeta.getNumero(); 
        cvv = tarjeta.getCvv(); 
        fechaVencimiento = tarjeta.getFechaDeVencimiento(); 
    }

    /**
     * @return the numero
     */
    public long getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(long numero) {
        this.numero = numero;
    }

    /**
     * @return the cvv
     */
    public int getCvv() {
        return cvv;
    }

    /**
     * @param cvv the cvv to set
     */
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    /**
     * @return the fechaVencimiento
     */
    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * @param fechaVencimiento the fechaVencimiento to set
     */
    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
