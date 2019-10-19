/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import co.edu.uniandes.csw.gastronomia.podam.DateStrategy;
import co.edu.uniandes.csw.gastronomia.podam.NumeroTarjetaDeCreditoStrategy;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamIntValue;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Estudiante
 */
@Entity
public class TarjetaDeCreditoEntity extends BaseEntity{
    @PodamStrategyValue(NumeroTarjetaDeCreditoStrategy.class)
    private Long numero; 
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaDeVencimiento; 
    
    @PodamIntValue(minValue = 100, maxValue = 999)
    private Integer cvv; 
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ClienteEntity cliente;
    /**
     * @return the numero
     */
    public long getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Long numero) {
        this.numero = numero;
    }

    /**
     * @return the fechaDeVencimiento
     */
    public Date getFechaDeVencimiento() {
        return fechaDeVencimiento;
    }

    /**
     * @param fechaDeVencimiento the fechaDeVencimiento to set
     */
    public void setFechaDeVencimiento(Date fechaDeVencimiento) {
        this.fechaDeVencimiento = fechaDeVencimiento;
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
    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    /**
     * 
     * @param o. Objeto con el que se va a realizar la comparacion
     * @return  retorna true si el objeto es igual al ingresado por parametros. False de lo contrario
     */
    @Override
    public boolean equals(Object o)
    {
      return super.equals(o);
    }
     @Override
    @Deprecated
    public int hashCode()
    {
        return super.hashCode();
    }

    /**
     * @return the cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
}
