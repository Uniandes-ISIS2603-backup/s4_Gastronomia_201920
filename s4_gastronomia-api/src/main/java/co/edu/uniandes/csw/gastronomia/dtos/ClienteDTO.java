/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Cristina Isabel González Osorio, David Martinez
 */
public class ClienteDTO implements Serializable
{

    
    private Long id;
    
    private String apellido;
    
    private String nombre;
    
    private String username;
    
    private String email;
    
    private String contrasena;
    
    private Date cumpleanos;
    
    private String numeroContacto;
    
    private int puntos;
   
    public ClienteDTO() {
    }
    public ClienteDTO(ClienteEntity clienteEntity)
    {
        if(clienteEntity!=null)
        {
            this.id = clienteEntity.getId();
            this.apellido = clienteEntity.getApellido();
            this.contrasena = clienteEntity.getContrasena();
            this.email = clienteEntity.getEmail();
            this.nombre = clienteEntity.getNombre();
            this.username = clienteEntity.getUsername();
            this.cumpleanos=clienteEntity.getCumpleanos();
            this.numeroContacto=clienteEntity.getNumeroContacto();
            this.puntos=clienteEntity.getPuntos();
        }
    }
/**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public ClienteEntity toEntity()
    {
        ClienteEntity clienteEntity=new ClienteEntity();
         clienteEntity.setApellido(this.apellido);
        clienteEntity.setContrasena(this.contrasena);
        clienteEntity.setEmail(this.email);
        clienteEntity.setId(this.id);
        clienteEntity.setNombre(this.nombre);
        clienteEntity.setUsername(this.username);
        clienteEntity.setCumpleanos(this.cumpleanos);
        clienteEntity.setNumeroContacto(this.numeroContacto);
        clienteEntity.setPuntos(this.puntos);
                return clienteEntity;
    }


}
