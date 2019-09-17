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
 * @author af.benitez
 */
public class FacturaDTO implements Serializable 
{
   private long id;
   
   private int valorCompleto;
    
   private int valor;

   private Date fecha;
        
   private boolean sePago;

    /**
     * Constructor por defecto
     */
   public FacturaDTO()
   {
       
   }
   
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the valorCompleto
     */
    public int getValorCompleto() {
        return valorCompleto;
    }

    /**
     * @param valorCompleto the valorCompleto to set
     */
    public void setValorCompleto(int valorCompleto) {
        this.valorCompleto = valorCompleto;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the sePago
     */
    public boolean isSePago() {
        return sePago;
    }

    /**
     * @param sePago the sePago to set
     */
    public void setSePago(boolean sePago) {
        this.sePago = sePago;
    }
}
