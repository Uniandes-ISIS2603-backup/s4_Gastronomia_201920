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
 * @author Cristina González
 */
@Entity
public class ReservaEntity extends BaseEntity{
    
    private String motivo;
    
    private Date fecha;
    
    private int numPersonas;
    
    private String nombreCliente;
    
    private boolean cancelada;
    
    private String numeroContacto;

    public ReservaEntity() {
    }

    public ReservaEntity(String motivo, Date fecha, int numPersonas, String nombreCliente, boolean cancelada, String numeroContacto) {
        this.motivo = motivo;
        this.fecha = fecha;
        this.numPersonas = numPersonas;
        this.nombreCliente = nombreCliente;
        this.cancelada = cancelada;
        this.numeroContacto = numeroContacto;
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
    
   @Override
    public boolean equals(Object obj) {
        if (! super.equals(obj)) {
          return false;
        }
        ReservaEntity reservaObj = (ReservaEntity) obj;
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
