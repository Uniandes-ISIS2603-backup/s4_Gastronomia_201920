/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;

/**
 *
 * @author Estudiante
 */
@Entity
public class TarjetaDeCreditoEntity extends BaseEntity{
    
    private int numero; 
    private Date fechaDeVencimiento; 
    private int cvv; 
    private String banco; 
    
    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
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
    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    /**
     * @return the banco
     */
    public String getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(String banco) {
        this.banco = banco;
    }
    /**
     * 
     * @param o. Objeto con el que se va a realizar la comparacion
     * @return  retorna true si el objeto es igual al ingresado por parametros. False de lo contrario
     */
    @Override
    public boolean equals(Object o)
    {
      if(!super.equals(o))
      {     
          return false;
      }
      TarjetaDeCreditoEntity tarjeta = (TarjetaDeCreditoEntity) o; 
      return this.banco.equals(tarjeta.getBanco()) &&
             this.cvv == tarjeta.getCvv() && 
             this.fechaDeVencimiento.equals(tarjeta.getFechaDeVencimiento()) &&
             this.numero == tarjeta.getNumero() &&
             Objects.equals(this.getId(), tarjeta.getId());
      
      
    }
    


    
    
   
    
}
