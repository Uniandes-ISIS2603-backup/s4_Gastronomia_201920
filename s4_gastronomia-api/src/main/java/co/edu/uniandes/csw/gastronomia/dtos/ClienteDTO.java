/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.adapters.DateAdapter;
import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Cristina Isabel González Osorio, David Martinez
 */
public class ClienteDTO implements Serializable
{
    private Long id;
    
    private String nombre;
    
    private String apellido;

    private String username;
    
    private String email;
    
    private String contrasena;

    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date cumpleanos;
    
    private String numeroContacto;
    
    private Integer puntos;
   
    public ClienteDTO() {
        
    }
    
    public ClienteDTO(ClienteEntity clienteEntity) {
        if (clienteEntity != null) {
            this.id = clienteEntity.getId();
            this.nombre = clienteEntity.getNombre();
            this.apellido = clienteEntity.getApellido();
            this.username = clienteEntity.getUsername();
            this.email = clienteEntity.getEmail();
            this.contrasena = clienteEntity.getContrasena();
            this.cumpleanos = clienteEntity.getCumpleanos();
            this.numeroContacto = clienteEntity.getNumeroContacto();
            this.puntos = clienteEntity.getPuntos();
        }  
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
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
    public Integer getPuntos() {
        return puntos;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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
    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public ClienteEntity toEntity()
    {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setApellido(this.getApellido());
        clienteEntity.setContrasena(this.getContrasena());
        clienteEntity.setEmail(this.getEmail());
        clienteEntity.setId(this.getId());
        clienteEntity.setNombre(this.getNombre());
        clienteEntity.setUsername(this.getUsername());
        clienteEntity.setCumpleanos(this.getCumpleanos());
        clienteEntity.setNumeroContacto(this.getNumeroContacto());
        clienteEntity.setPuntos(this.getPuntos());
        return clienteEntity;
    }
}
