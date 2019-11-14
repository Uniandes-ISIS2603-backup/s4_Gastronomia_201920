/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import co.edu.uniandes.csw.gastronomia.podam.DateStrategy;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Cristina González
 */
@Entity
public class ClienteEntity extends UsuarioEntity{
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date cumpleanos;
    
    private String numeroContacto;
    
    private int puntos;
       
    @PodamExclude
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<TarjetaDeCreditoEntity> tarjetas = new ArrayList<TarjetaDeCreditoEntity>();

    @PodamExclude
    @OneToMany(mappedBy = "cliente", fetch = javax.persistence.FetchType.LAZY )
    private List<FoodBlogEntity> foodBlogs = new ArrayList<FoodBlogEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente", fetch = javax.persistence.FetchType.LAZY )
    private List<TipoComidaEntity> preferencias = new ArrayList<TipoComidaEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente", fetch = javax.persistence.FetchType.LAZY )
    private List<FacturaEntity> facturas = new ArrayList<FacturaEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "cliente", fetch = javax.persistence.FetchType.LAZY )
    private List<ReservaEntity> reservas = new ArrayList<ReservaEntity>();

    public ClienteEntity() {
        super();
    }

    /**
     * @return the cumpleanos
     */
    public Date getCumpleanos() {
        return cumpleanos;
    }

    /**
     * @return the numeroContacto
     */
    public String getNumeroContacto() {
        return numeroContacto;
    }

    /**
     * @return the puntos
     */
    public int getPuntos() {
        return puntos;
    }

    /**
     * @return the tarjetas
     */
    public List<TarjetaDeCreditoEntity> getTarjetas() {
        return tarjetas;
    }

    /**
     * @return the foodBlogs
     */
    public List<FoodBlogEntity> getFoodBlogs() {
        return foodBlogs;
    }

    /**
     * @return the preferencias
     */
    public List<TipoComidaEntity> getPreferencias() {
        return preferencias;
    }

    /**
     * @return the facturas
     */
    public List<FacturaEntity> getFacturas() {
        return facturas;
    }

    /**
     * @return the reservas
     */
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    /**
     * @param cumpleanos the cumpleanos to set
     */
    public void setCumpleanos(Date cumpleanos) {
        this.cumpleanos = cumpleanos;
    }

    /**
     * @param numeroContacto the numeroContacto to set
     */
    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     * @param tarjetas the tarjetas to set
     */
    public void setTarjetas(List<TarjetaDeCreditoEntity> tarjetas) {
        this.tarjetas = tarjetas;
    }

    /**
     * @param foodBlogs the foodBlogs to set
     */
    public void setFoodBlogs(List<FoodBlogEntity> foodBlogs) {
        this.foodBlogs = foodBlogs;
    }

    /**
     * @param preferencias the preferencias to set
     */
    public void setPreferencias(List<TipoComidaEntity> preferencias) {
        this.preferencias = preferencias;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(List<FacturaEntity> facturas) {
        this.facturas = facturas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (! super.equals(obj)) {
          return false;
        }
        ClienteEntity clienteObj = (ClienteEntity) obj;
        return this.getUsername().equals(clienteObj.getUsername());
    }
    
    @Override
    public int hashCode()
    {
        if (this.getUsername() != null)
        {
            return this.getUsername().hashCode();
        }
        return super.hashCode();
    }
}
