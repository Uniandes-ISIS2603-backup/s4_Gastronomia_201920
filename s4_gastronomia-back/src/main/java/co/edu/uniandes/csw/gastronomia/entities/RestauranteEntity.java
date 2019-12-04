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

    public RestauranteEntity(String imagen, String nombre, String contrasena, String direccion, String tipoRestaurante, Double precioPorPersona, Boolean descuentaoCumpleanos, Boolean zonaDeFumadores, Boolean petFriendly, Boolean servicioALaMesa, Boolean musicaEnVivo, Double costoReserva, Date horario) {
        super();
        this.imagen = imagen;//ya
        this.nombre = nombre;//ya
        this.contrasena = contrasena;//ya
        this.direccion = direccion;//ya
        this.tipoRestaurante = tipoRestaurante;//ya
        this.precioPorPersona = precioPorPersona;//ya
        this.descuentaoCumpleanos = descuentaoCumpleanos;//ya
        this.zonaDefumadores = zonaDeFumadores;//ya
        this.petFriendly = petFriendly;//ya
        this.servicioALaMesa = servicioALaMesa;//ya
        this.musicaEnVivo = musicaEnVivo;//ya
        this.costoReserva = costoReserva;//ya
        this.horario = horario;//ya
    }
    
    
    
    public RestauranteEntity(String imagen, String nombre, String contrasena, String direccion, String tipoRestaurante, Double precioPorPersona, Boolean descuentaoCumpleanos, Boolean zonaDeFumadores, Boolean petFriendly, Boolean servicioALaMesa, Boolean musicaEnVivo, Double costoReserva, Date horario, AdministradorEntity administrador, List<PlatoEntity> platos, List<ReservaEntity> reservas) {
        super();
        this.imagen = imagen;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.tipoRestaurante = tipoRestaurante;
        this.precioPorPersona = precioPorPersona;
        this.descuentaoCumpleanos = descuentaoCumpleanos;
        this.zonaDefumadores = zonaDeFumadores;
        this.petFriendly = petFriendly;
        this.servicioALaMesa = servicioALaMesa;
        this.musicaEnVivo = musicaEnVivo;
        this.costoReserva = costoReserva;
        this.horario = horario;
        this.administrador = administrador;
        this.platos = platos;
        this.reservas = reservas;
    }

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setPlatos(List<PlatoEntity> platos) {
        this.platos = platos;
    }
    
    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }
    
    public String getNombre() {
        return nombre;
    }
    

    public void setAdministrador(AdministradorEntity administrador) {
        this.administrador = administrador;
    }

    public List<PlatoEntity> getPlatos() {
        return platos;
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

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

   

    public void setTipoRestaurante(String tipoRestaurante) {
        this.tipoRestaurante = tipoRestaurante;
    }

    public Double getPrecioPorPersona() {
        return precioPorPersona;
    }
    
public Boolean getServicioALaMesa() {
        return servicioALaMesa;
    }
    
    public Boolean getDescuentaoCumpleanos() {
        return descuentaoCumpleanos;
    }

    public void setDescuentaoCumpleaños(Boolean descuentaoCumpleaños) {
        this.descuentaoCumpleanos = descuentaoCumpleaños;
    }
    
    
    public void setServicioALaMesa(Boolean servicioALaMesa) {
        this.servicioALaMesa = servicioALaMesa;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getZonaDeFumadores() {
        return zonaDefumadores;
    }

    

    
    public void setPetFriendly(Boolean petFriendly) {
        this.petFriendly = petFriendly;
    }

    


    public Boolean getMusicaEnVivo() {
        return musicaEnVivo;
    }
    
     public String getTipoRestaurante() {
        return tipoRestaurante;
    }

    public void setMusicaEnVivo(Boolean musicaEnVivo) {
        this.musicaEnVivo = musicaEnVivo;
    }
    
public Date getHorario() {
        return horario;
    }
    public Double getCostoReserva() {
        return costoReserva;
    }

    public void setCostoReserva(Double costoReserva) {
        this.costoReserva = costoReserva;
    }

    public Boolean getPetFriendly() {
        return petFriendly;
    }


    public void setHorario(Date horario) {
        this.horario = horario;
    } 
    
    public void setZonaDeFumadores(Boolean zonaDefumadores) {
        this.zonaDefumadores = zonaDefumadores;
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
