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
 * @author Cristina Isabel González Osorio
 */
public class ClienteDTO implements Serializable{
    
    private Long id;
    
    private String nombre;
    
    private String apellido;
    
    private String username;
    
    private String email;
    
    private String contrasena;
    
    private Date cumpleanos;
    
    private String numeroContacto;
    
    private int puntos;
    
    //private TarjetaDeCreditoDTO tarjetaCredito;
    

    public ClienteDTO() {
    }
    
    public ClienteDTO(ClienteEntity clienteEntity) {
        if (clienteEntity != null) {
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
        ClienteEntity clienteEntity = new ClienteEntity();
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
