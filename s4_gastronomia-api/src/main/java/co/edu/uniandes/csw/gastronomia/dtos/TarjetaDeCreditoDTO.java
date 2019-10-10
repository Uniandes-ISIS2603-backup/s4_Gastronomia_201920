/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.adapters.DateAdapter;
import co.edu.uniandes.csw.gastronomia.entities.TarjetaDeCreditoEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author je.canizarez
 */
public class TarjetaDeCreditoDTO implements Serializable {
    
    private Long id;
    private Long numero; 
    private Integer cvv; 
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaVencimiento; 
    
    public TarjetaDeCreditoDTO()
    {
        
    }
    public TarjetaDeCreditoDTO(TarjetaDeCreditoEntity tarjeta)
    {
        id = tarjeta.getId();
        numero = tarjeta.getNumero(); 
        cvv = tarjeta.getCvv(); 
        fechaVencimiento = tarjeta.getFechaDeVencimiento(); 
    }
    public TarjetaDeCreditoEntity toEntity()
    {
        TarjetaDeCreditoEntity retorno = new TarjetaDeCreditoEntity(); 
        retorno.setId(id);
        retorno.setCvv(cvv);
        retorno.setFechaDeVencimiento(fechaVencimiento);
        retorno.setNumero(numero);
        return retorno;
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
    public void setNumero(Long numero) {
        this.numero = numero;
    }

    /**
     * @return the cvv
     */
    public Integer getCvv() {
        return cvv;
    }

    /**
     * @param cvv the cvv to set
     */
    public void setCvv(Integer cvv) {
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
    

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    @Override 
    public String toString()
    {
          return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
