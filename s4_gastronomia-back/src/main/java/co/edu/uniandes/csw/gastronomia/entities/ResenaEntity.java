/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author David Martinez
 */
@Entity
public class ResenaEntity extends BaseEntity implements Serializable {
    
    /**
     * La calificacion que rescibe un restaurante por parte de un usario
     */
    private int calificacion;
    /**
     * El comentqrio de la resena que hace un usuario
     */
    private String comentario;

    /**
     * @return the calificacion
     */
    public int getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
