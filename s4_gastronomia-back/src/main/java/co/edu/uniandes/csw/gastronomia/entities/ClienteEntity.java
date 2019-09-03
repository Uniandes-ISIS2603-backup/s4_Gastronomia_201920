/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author Cristina González
 */
@Entity
public class ClienteEntity extends UsuarioEntity{
    
    private Date cumpleanos;
    
    private String numeroContacto;
    
    private int puntos;

    public ClienteEntity() {
        super();
    }

    public ClienteEntity(String name , String apellido , String username , String contrasena , String email, Date cumpleanos, String numeroContacto, int puntos) {
        super(name, apellido, username, contrasena, email);
        this.cumpleanos = cumpleanos;
        this.numeroContacto = numeroContacto;
        this.puntos = puntos;
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
    
    @Override
    public boolean equals(Object obj) {
        if (! super.equals(obj)) {
          return false;
        }
        ClienteEntity clienteObj = (ClienteEntity) obj;
        if (this.getId().equals(clienteObj.getId())) {
          return true;
        }
        return false;
    }
}
