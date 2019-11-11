/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.ReservaEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Cristina Isabel González Osorio
 */
public class ReservaDTO implements Serializable{
    
    private String motivo;
    
    private Date fecha;
    
    private int numPersonas;
    
    private String nombreCliente;
    
    private boolean cancelada;
    
    private String numeroContacto;
    
    private RestauranteDTO restaurante;
    
    private FacturaDTO factura;

    private ResenaDTO resena;

    public ReservaDTO() {
    }
    
    public ReservaDTO(ReservaEntity reservaEntity) {
        if (reservaEntity != null) {
            this.motivo = reservaEntity.getMotivo();
            this.fecha = reservaEntity.getFecha();
            this.numPersonas = reservaEntity.getNumPersonas();
            this.nombreCliente = reservaEntity.getNombreCliente();
            this.cancelada = reservaEntity.isCancelada();
            this.numeroContacto = reservaEntity.getNumeroContacto();
        }
    }
    
    public ReservaEntity toEntity() {
        ReservaEntity reservaEntity = new ReservaEntity();
        reservaEntity.setMotivo(this.motivo);
        reservaEntity.setFecha(this.fecha);
        reservaEntity.setNumPersonas(this.numPersonas);
        reservaEntity.setNombreCliente(this.nombreCliente);
        reservaEntity.setCancelada(this.cancelada);
        reservaEntity.setNumeroContacto(this.numeroContacto);
        return reservaEntity;
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
    public int getNumPersonas() {
        return numPersonas;
    }

    /**
     * @return the nombreCliente
     */
    public String getNombreCliente() {
        return nombreCliente;
    }

    /**
     * @return the cancelada
     */
    public boolean isCancelada() {
        return cancelada;
    }

    /**
     * @return the numeroContacto
     */
    public String getNumeroContacto() {
        return numeroContacto;
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
    public void setNumPersonas(int numPersonas) {
        this.numPersonas = numPersonas;
    }

    /**
     * @param nombreCliente the nombreCliente to set
     */
    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    /**
     * @param cancelada the cancelada to set
     */
    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    /**
     * @param numeroContacto the numeroContacto to set
     */
    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
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
