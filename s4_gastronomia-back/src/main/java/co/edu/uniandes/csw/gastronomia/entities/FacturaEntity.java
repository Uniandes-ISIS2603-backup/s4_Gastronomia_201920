/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author af.benitez
 */
@Entity
public class FacturaEntity extends BaseEntity
{
    private int valorCompleto;
    
    private int valor;

    private Date fecha;
        
    private boolean sePago;

    /**
     * @return el valorCompleto
     */
    public int getValorCompleto() 
    {
        return valorCompleto;
    }

    /**
     * @param pValorCompleto el pValorCompleto to set
     */
    public void setValorCompleto(int pValorCompleto)
    {
        this.valorCompleto = pValorCompleto;
    }

    /**
     * @return el valor
     */
    public int getValor()
    {
        return valor;
    }

    /**
     * @param pValor el valor to set
     */
    public void setValor(int pValor) 
    {
        this.valor = pValor;
    }

    /**
     * @return sePago
     */
    public boolean getSePago() 
    {
        return sePago;
    }

    /**
     * @param sePago boolean a asignar a sePago
     */
    public void setSePago(boolean sePago) 
    {
        this.sePago = sePago;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (! super.equals(obj)) 
        {
          return false;
        }
        FacturaEntity reservaObj = (FacturaEntity) obj;
        return this.getId().equals(reservaObj.getId());
    }
    
    @Override
    public int hashCode()
    {
        if (this.getId() != null)
        {
            return this.getId().hashCode();
        }
        return super.hashCode();
    } 

    
  
}
