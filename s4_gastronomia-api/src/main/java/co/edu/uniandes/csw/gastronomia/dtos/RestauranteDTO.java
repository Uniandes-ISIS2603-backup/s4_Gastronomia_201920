/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.adapters.DateAdapter;
import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Estudiante
 */
/**
 * {
   "id",
    "imagen":,
    "nombre":,
    "contrasena":,
    "direccion":,
    "tipoRestaurante":,
    "precioPorPersona":,
    "descuentoCumpleanos":,
    "zonaDeFumadores":,
    "petFriendly":,
    "servicioALaMesa":,
    "musicaEnVivo":,
    "costoReserva":,
    "horario":
  }
 */
public class RestauranteDTO implements Serializable
{
    private Long id;
    private String imagen;
    private String nombre;
    private String contrasena;
    private String direccion;
    private String tipoRestaurante;
    private Double precioPorPersona;
    private Boolean descuentoCumpleanos;
    private Boolean zonaDeFumadores;
    private Boolean petFriendly;
    private Boolean servicioALaMesa;
    private Boolean musicaEnVivo;
    private Double costoReserva;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date horario;
//------------------------------------------------------------------------------
//Constructores
//------------------------------------------------------------------------------
    public RestauranteDTO() {
        //Constructor vacio
    }
    
    public RestauranteDTO(RestauranteEntity r)
    {
        if(r!=null)
        {
            id=r.getId();
            imagen=r.getImagen();
            nombre=r.getNombre();
            contrasena=r.getContrasena();
            direccion=r.getDireccion();
            tipoRestaurante=r.getTipoRestaurante();
            precioPorPersona=r.getPrecioPorPersona();
            descuentoCumpleanos=r.getDescuentaoCumpleanos();
            zonaDeFumadores=r.getZonaDeFumadores();
            petFriendly=r.getPetFriendly();
            servicioALaMesa=r.getServicioALaMesa();
            musicaEnVivo=r.getMusicaEnVivo();
            costoReserva=r.getCostoReserva();
            horario=r.getHorario();
        }
    }
    
    public RestauranteEntity toEntity()
    {
        RestauranteEntity r = new RestauranteEntity();
        r.setId(this.getId());
        r.setImagen(this.getImagen());
        r.setNombre(this.getNombre());
        r.setContrasena(this.getContrasena());
        r.setDireccion(this.getDireccion());
        r.setTipoRestaurante(this.getTipoRestaurante());
        r.setPrecioPorPersona(this.getPrecioPorPersona());
        r.setDescuentaoCumpleaños(this.getDescuentoCumpleanos());
        r.setZonaDeFumadores(this.getZonaDeFumadores());
        r.setPetFriendly(this.getPetFriendly());
        r.setServicioALaMesa(this.getServicioALaMesa());
        r.setMusicaEnVivo(this.getMusicaEnVivo());
        r.setCostoReserva(this.getCostoReserva());
        r.setHorario(this.getHorario());
        return r;
    }
//------------------------------------------------------------------------------
//Getters & Setters
//------------------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getDescuentoCumpleanos() {
        return descuentoCumpleanos;
    }

    public void setDescuentoCumpleanos(Boolean descuentoCumpleanos) {
        this.descuentoCumpleanos = descuentoCumpleanos;
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
