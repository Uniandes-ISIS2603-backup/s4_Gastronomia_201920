/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.adapters.DateAdapter;
import co.edu.uniandes.csw.gastronomia.entities.ReservaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Cristina Isabel González Osorio
 */
public class ReservaDTO implements Serializable{
    
    private Long id;
    
    private String motivo;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fecha;
    
    private Integer numPersonas;
    
    private Boolean cancelada;
    
    private RestauranteDTO restaurante;
    
    private FacturaDTO factura;

    private ResenaDTO resena;

    public ReservaDTO() {
    }
    
    public ReservaDTO(ReservaEntity reservaEntity) {
        if (reservaEntity != null) {
            this.id = reservaEntity.getId();
            this.motivo = reservaEntity.getMotivo();
            this.fecha = reservaEntity.getFecha();
            this.numPersonas = reservaEntity.getNumPersonas();
            this.cancelada = reservaEntity.getCancelada();
            this.restaurante = new RestauranteDTO(reservaEntity.getRestaurante());
            this.factura = new FacturaDTO(reservaEntity.getFactura());
            this.resena = new ResenaDTO(reservaEntity.getResena());
        }
    }
    
    public ReservaEntity toEntity() {
        ReservaEntity reservaEntity = new ReservaEntity();
        reservaEntity.setMotivo(this.motivo);
        reservaEntity.setFecha(this.fecha);
        reservaEntity.setNumPersonas(this.numPersonas);
        reservaEntity.setCancelada(this.cancelada);
        if (getRestaurante() != null) {
            reservaEntity.setRestaurante(this.restaurante.toEntity());
        }
        if (getFactura() != null) {
            reservaEntity.setFactura(this.factura.toEntity());
        }
        if(getResena() != null) {
            reservaEntity.setResena(this.resena.toEntity());
        }
        return reservaEntity;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @return the numPersonas
     */
    public Integer getNumPersonas() {
        return numPersonas;
    }

    /**
     * @return the cancelada
     */
    public Boolean getCancelada() {
        return cancelada;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @param numPersonas the numPersonas to set
     */
    public void setNumPersonas(Integer numPersonas) {
        this.numPersonas = numPersonas;
    }

    /**
     * @param cancelada the cancelada to set
     */
    public void setCancelada(Boolean cancelada) {
        this.cancelada = cancelada;
    }

    /**
     * @return the restaurante
     */
    public RestauranteDTO getRestaurante() {
        return restaurante;
    }

    /**
     * @param restaurante the restaurante to set
     */
    public void setRestaurante(RestauranteDTO restaurante) {
        this.restaurante = restaurante;
    }

    /**
     * @return the factura
     */
    public FacturaDTO getFactura() {
        return factura;
    }

    /**
     * @param factura the factura to set
     */
    public void setFactura(FacturaDTO factura) {
        this.factura = factura;
    }

    /**
     * @return the resena
     */
    public ResenaDTO getResena() {
        return resena;
    }

    /**
     * @param resena the resena to set
     */
    public void setResena(ResenaDTO resena) {
        this.resena = resena;
    }  
}
