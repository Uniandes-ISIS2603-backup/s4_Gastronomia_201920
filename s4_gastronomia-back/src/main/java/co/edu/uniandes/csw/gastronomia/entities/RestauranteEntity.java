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
    private Boolean zonaDeFumadores;
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
    @OneToMany
    private List<PlatoEntity> platos ;
    


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
        this.zonaDeFumadores = zonaDeFumadores;//ya
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
        this.zonaDeFumadores = zonaDeFumadores;
        this.petFriendly = petFriendly;
        this.servicioALaMesa = servicioALaMesa;
        this.musicaEnVivo = musicaEnVivo;
        this.costoReserva = costoReserva;
        this.horario = horario;
        this.administrador = administrador;
        this.platos = platos;
      
    }

    public AdministradorEntity getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorEntity administrador) {
        this.administrador = administrador;
    }

    public List<PlatoEntity> getPlatos() {
        return platos;
    }

    public void setPlatos(List<PlatoEntity> platos) {
        this.platos = platos;
    }

    
        
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipoRestaurante() {
        return tipoRestaurante;
    }

    public void setTipoRestaurante(String tipoRestaurante) {
        this.tipoRestaurante = tipoRestaurante;
    }

    public Double getPrecioPorPersona() {
        return precioPorPersona;
    }

    public void setPrecioPorPersona(Double precioPorPersona) {
        this.precioPorPersona = precioPorPersona;
    }

    public Boolean getDescuentaoCumpleanos() {
        return descuentaoCumpleanos;
    }

    public void setDescuentaoCumpleaños(Boolean descuentaoCumpleaños) {
        this.descuentaoCumpleanos = descuentaoCumpleaños;
    }

    public Boolean getZonaDeFumadores() {
        return zonaDeFumadores;
    }

    public void setZonaDeFumadores(Boolean zonaDeFumadores) {
        this.zonaDeFumadores = zonaDeFumadores;
    }

    public Boolean getPetFriendly() {
        return petFriendly;
    }

    public void setPetFriendly(Boolean petFriendly) {
        this.petFriendly = petFriendly;
    }

    public Boolean getServicioALaMesa() {
        return servicioALaMesa;
    }

    public void setServicioALaMesa(Boolean servicioALaMesa) {
        this.servicioALaMesa = servicioALaMesa;
    }

    public Boolean getMusicaEnVivo() {
        return musicaEnVivo;
    }

    public void setMusicaEnVivo(Boolean musicaEnVivo) {
        this.musicaEnVivo = musicaEnVivo;
    }

    public Double getCostoReserva() {
        return costoReserva;
    }

    public void setCostoReserva(Double costoReserva) {
        this.costoReserva = costoReserva;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    } 
    @Override
    public boolean equals(Object other)
    {
        return super.equals(other);
    }
}
