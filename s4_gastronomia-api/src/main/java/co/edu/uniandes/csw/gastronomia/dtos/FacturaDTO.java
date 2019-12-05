/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.adapters.DateAdapter;
import co.edu.uniandes.csw.gastronomia.entities.FacturaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author af.benitez
 */
public class FacturaDTO implements Serializable 
{
   private Long id;
   
   private Integer valorCompleto;
    
   private Integer valor;

   @XmlJavaTypeAdapter(DateAdapter.class)
   private Date fecha;
        
   private Boolean sePago;

    /**
     * Constructor por defecto
     */
   public FacturaDTO()
   {
       //
   }
  
    /**
     * Constructor a partir de la entidad
     *
     * @param fEntity La entidad de factura
     */
    public FacturaDTO(FacturaEntity fEntity) 
    {
        if (fEntity != null)
        {
            this.id = fEntity.getId();
            this.valorCompleto = fEntity.getValorCompleto();
            this.valor = fEntity.getValor();
            this.sePago = fEntity.getSePago();
            this.fecha = fEntity.getFecha();
            
        }
    }

    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad de factura asociado.
     */
    public FacturaEntity toEntity() {
        FacturaEntity facturaEntity = new FacturaEntity();
        facturaEntity.setId(this.id);
        facturaEntity.setValor(this.valor);
        facturaEntity.setValorCompleto(this.valorCompleto);
        facturaEntity.setSePago(this.sePago);
        facturaEntity.setFecha(this.fecha);
        
       
        return facturaEntity;
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
