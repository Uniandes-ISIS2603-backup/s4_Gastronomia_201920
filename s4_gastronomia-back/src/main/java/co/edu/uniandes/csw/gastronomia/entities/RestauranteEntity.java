/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import co.edu.uniandes.csw.gastronomia.podam.DateStrategy;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Estudiante
 */
@Entity
public class RestauranteEntity extends BaseEntity implements Serializable
{
    /**
     * La imagen del restaurante
     */
    private String imagen;
    /**
     * el nombre del restaurante
     */
    private String nombre;
    /**
     * la contraseña del restaurante
     */
    private String contrasena;
    /**
     * la direccion del restaurante
     */
    private String direccion;
    /**
     * el tipo de restaurnate
     */
    private String tipoRestaurante;
    /**
     * el precio promedio por persona
     */
    private Double precioPorPersona;
    /**
     * si hay descuento por cumpleaños
     */
    private Boolean descuentaoCumpleanos;
    /**
     * si tiene ona de fumadores
     */
    private Boolean zonaDefumadores;
    /**
     * si es petFriendly
     */
    private Boolean petFriendly;
    /**
     * si tienenservicio a la mesa
     */
    private Boolean servicioALaMesa;
    /**
     * si tiene música en vivo
     */
    private Boolean musicaEnVivo;
    /**
     * el costo de la reserva del restaurante
     */
    private Double costoReserva;
    /**
     * el horario
     */
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date horario;
    
    @PodamExclude
    @ManyToOne
    private AdministradorEntity administrador;
    
    @PodamExclude
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<PlatoEntity> platos ;
    
    @PodamExclude
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ReservaEntity> reservas;

    public RestauranteEntity()
    {
        super();
        //Empty constructor
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    public void setPlatos(List<PlatoEntity> platos) {
        this.platos = platos;
    }
    
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

   
    

    /**
     * @return the tipoRestaurante
     */
    public String getTipoRestaurante() {
        return tipoRestaurante;
    }

    /**
     * @return the precioPorPersona
     */
    public Double getPrecioPorPersona() {
        return precioPorPersona;
    }



    public AdministradorEntity getAdministrador() {
        return administrador;
    }
        
    public String getImagen() {
        return imagen;
    }

    public String getContrasena() {
        return contrasena;
    }
    
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
public String getDireccion() {
        return direccion;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    


    
public void setPrecioPorPersona(Double precioPorPersona) {
        this.precioPorPersona = precioPorPersona;
    }

    /**
     * @return the zonaDeFumadores
     */
    public Boolean getZonaDeFumadores() {
        return zonaDefumadores;
    }

    /**
     * @return the petFriendly
     */
    public Boolean getPetFriendly() {
        return petFriendly;
    }

    /**
     * @return the servicioALaMesa
     */
    public Boolean getServicioALaMesa() {
        return servicioALaMesa;
    }

    /**
     * @return the musicaEnVivo
     */
    public Boolean getMusicaEnVivo() {
        return musicaEnVivo;
    }

    /**
     * @return the costoReserva
     */
    public Double getCostoReserva() {
        return costoReserva;
    }

    /**
     * @return the horario
     */
    public Date getHorario() {
        return horario;
    }

    /**
     * @return the platos
     */
    public List<PlatoEntity> getPlatos() {
        return platos;
    }


    /**
     * @return the reservas
     */
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

 
    
    

    
    public Boolean getDescuentaoCumpleanos() {
        return descuentaoCumpleanos;
}

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @param tipoRestaurante the tipoRestaurante to set
     */
    public void setTipoRestaurante(String tipoRestaurante) {
        this.tipoRestaurante = tipoRestaurante;
    }
    
  
 

    /**
     * @param descuentaoCumpleanos the descuentaoCumpleanos to set
     */
    public void setDescuentaoCumpleanos(Boolean descuentaoCumpleanos) {
        this.descuentaoCumpleanos = descuentaoCumpleanos;
    }

    /**
     * @param zonaDeFumadores the zonaDeFumadores to set
     */
    public void setZonaDeFumadores(Boolean zonaDeFumadores) {
        this.zonaDefumadores = zonaDeFumadores;
    }

    /**
     * @param petFriendly the petFriendly to set
     */
    public void setPetFriendly(Boolean petFriendly) {
        this.petFriendly = petFriendly;
    }

    public void setMusicaEnVivo(Boolean musicaEnVivo) {
        this.musicaEnVivo = musicaEnVivo;
    }
 
      
    public void setServicioALaMesa(Boolean servicioALaMesa) {
        this.servicioALaMesa = servicioALaMesa;
    }
    /**
     * @param costoReserva the costoReserva to set
     */
    public void setCostoReserva(Double costoReserva) {
        this.costoReserva = costoReserva;
    }


    /**
     * @param horario the horario to set
     */
    public void setHorario(Date horario) {
        this.horario = horario;
    }

    /**
     * @param administrador the administrador to set
     */
    public void setAdministrador(AdministradorEntity administrador) {
        this.administrador = administrador;
    }

    
    
    
    @Override
    public boolean equals(Object ob)
    {
       if(! super.equals(ob))
        {
            return false;
        }
        RestauranteEntity usuarioOb = ( RestauranteEntity) ob ;
        String usuario = usuarioOb.getNombre();
        return usuario.equals(usuarioOb.getNombre());
        
    }
    
    @Override
    public int hashCode()
    {
     
        if (this.getNombre() != null) {
            return this.getNombre().hashCode();
        }
        return super.hashCode();
    } 
   
}
