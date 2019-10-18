/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import co.edu.uniandes.csw.gastronomia.podam.DateStrategy;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Cristina González
 */
@Entity
public class ReservaEntity extends BaseEntity{
    
    private String motivo;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecha;
    
    private int numPersonas;
    
    private String nombreCliente;
    
    private boolean cancelada;
    
    private String numeroContacto;
//    
//    @PodamExclude
//    @ManyToOne
//    private ClienteEntity cliente;
    
//    @PodamExclude
//    @ManyToOne
//    private RestauranteEntity restaurante;
    
    //@PodamExclude
    //@OneToOne
    //private FacturaEntity factura;
    
    //@PodamExclude
    //@OneToOne
    //private ResenaEntity resena;
    
    

    public ReservaEntity() {
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

//    /**
//     * @return the restaurante
//     */
//    public RestauranteEntity getRestaurante() {
//        return restaurante;
//    }
//
//    /**
//     * @return the factura
//     */
//    public FacturaEntity getFactura() {
//        return factura;
//    }
//
//    /**
//     * @return the resena
//     */
//    public ResenaEntity getResena() {
//        return resena;
//    }

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

//    /**
//     * @param restaurante the restaurante to set
//     */
//    public void setRestaurante(RestauranteEntity restaurante) {
//        this.restaurante = restaurante;
//    }
//
//    /**
//     * @param factura the factura to set
//     */
//    public void setFactura(FacturaEntity factura) {
//        this.factura = factura;
//    }
//
//    /**
//     * @param resena the resena to set
//     */
//    public void setResena(ResenaEntity resena) {
//        this.resena = resena;
//    }
    
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
