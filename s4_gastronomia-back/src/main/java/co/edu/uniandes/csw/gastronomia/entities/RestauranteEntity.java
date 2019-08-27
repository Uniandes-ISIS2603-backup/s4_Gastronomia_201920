/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

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
    private String RUTA_IMAGEN_RESTAURANTE;
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
    private Boolean descuentaoCumpleaños;
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
    private Date horario;

    public RestauranteEntity()
    {
        //Empty constructor
    }

    public RestauranteEntity(String RUTA_IMAGEN_RESTAURANTE, String nombre, String contrasena, String direccion, String tipoRestaurante, Double precioPorPersona, Boolean descuentaoCumpleaños, Boolean zonaDeFumadores, Boolean petFriendly, Boolean servicioALaMesa, Boolean musicaEnVivo, Double costoReserva, Date horario) {
        this.RUTA_IMAGEN_RESTAURANTE = RUTA_IMAGEN_RESTAURANTE;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.tipoRestaurante = tipoRestaurante;
        this.precioPorPersona = precioPorPersona;
        this.descuentaoCumpleaños = descuentaoCumpleaños;
        this.zonaDeFumadores = zonaDeFumadores;
        this.petFriendly = petFriendly;
        this.servicioALaMesa = servicioALaMesa;
        this.musicaEnVivo = musicaEnVivo;
        this.costoReserva = costoReserva;
        this.horario = horario;
    }

    public String getRUTA_IMAGEN_RESTAURANTE() {
        return RUTA_IMAGEN_RESTAURANTE;
    }

    public void setRUTA_IMAGEN_RESTAURANTE(String RUTA_IMAGEN_RESTAURANTE) {
        this.RUTA_IMAGEN_RESTAURANTE = RUTA_IMAGEN_RESTAURANTE;
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

    public Boolean getDescuentaoCumpleaños() {
        return descuentaoCumpleaños;
    }

    public void setDescuentaoCumpleaños(Boolean descuentaoCumpleaños) {
        this.descuentaoCumpleaños = descuentaoCumpleaños;
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
}
