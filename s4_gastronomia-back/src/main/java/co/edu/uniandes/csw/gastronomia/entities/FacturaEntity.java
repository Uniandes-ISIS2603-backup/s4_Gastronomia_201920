/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import co.edu.uniandes.csw.gastronomia.podam.DateStrategy;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author af.benitez
 */
@Entity
public class FacturaEntity extends BaseEntity
{

    /**
    * Valor completo de la factura.
    */
    private int valorCompleto;
    
    /**
    * Valor del servicio prestado por el restaurante.
    */
    private int valor;
 
    /**
    * Dia que se realizo la factura.
    */
    @Temporal(TemporalType.DATE)
    private Date fecha;
        
    /**
    * Saber si se pago la factura.
    */
    private boolean sePago;

    /**
    * Constructor de la clase FacturaEntity.
    */
    public FacturaEntity()
    {
        //Constructor vacio para evitar falla.
    }
    
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
}
