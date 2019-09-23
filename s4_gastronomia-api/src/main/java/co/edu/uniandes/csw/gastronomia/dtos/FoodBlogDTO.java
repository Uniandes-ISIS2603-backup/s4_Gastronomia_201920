/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.FoodBlogEntity;
import java.io.Serializable;

/**
 *
 * @author Estudiante
 */
public class FoodBlogDTO implements Serializable  
 {
    private Long id;
     /**
     * Representa el texto descriptivo del blog del usuario
     */
private String texto;    

/**
 * Numero de me gustas que tiene el blog del usuario
 */
private int numeroMeGusta;

/**
 * Numero de no me gusta que tiene el blog del ususario
 */
private int numeroNoMegusta;
/**
 * Ruta de imagen de la foto que tiene el usuario en su blog
 */
private String archivoMultimedia;
/**
 * COmentarios del blog
 */
private String comentarios;
 public FoodBlogDTO(FoodBlogEntity fb)
 {
     this.id=fb.getId();
     this.texto=fb.getTexto();
     this.numeroMeGusta=fb.getNumeroMeGusta();
     this.numeroNoMegusta=fb.getNumeroNoMegusta();
     this.comentarios=fb.getComentarios();
     this.archivoMultimedia=fb.getArchivoMultimedia();
      }
 public FoodBlogEntity toEntity()
 {
     FoodBlogEntity fb=new FoodBlogEntity();
     fb.setId(this.getId());
     fb.setTexto(this.getTexto());
     fb.setComentarios(this.getComentarios());
     fb.setNumeroMeGusta(this.getNumeroMeGusta());
     fb.setNumeroNoMegusta(this.getNumeroNoMegusta());
     fb.setArchivoMultimedia(this.getArchivoMultimedia());
     return fb;
 }
 public Long getId()
    {
        return this.getId();
    }
    public void setId(Long pId)
    {
        this.id=pId;
    }
/**
     * @return the texto
     */
    public String getTexto() {
        return texto;
    }

    /**
     * @param texto the texto to set
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * @return the numeroMeGusta
     */
    public int getNumeroMeGusta() {
        return numeroMeGusta;
    }

    /**
     * @param numeroMeGusta the numeroMeGusta to set
     */
    public void setNumeroMeGusta(int numeroMeGusta) {
        this.numeroMeGusta = numeroMeGusta;
    }

    /**
     * @return the numeroNoMegusta
     */
    public int getNumeroNoMegusta() {
        return numeroNoMegusta;
    }

    /**
     * @param numeroNoMegusta the numeroNoMegusta to set
     */
    public void setNumeroNoMegusta(int numeroNoMegusta) {
        this.numeroNoMegusta = numeroNoMegusta;
    }

    /**
     * @return the archivoMultimedia
     */
    public String getArchivoMultimedia() {
        return archivoMultimedia;
    }

    /**
     * @param archivoMultimedia the archivoMultimedia to set
     */
    public void setArchivoMultimedia(String archivoMultimedia) {
        this.archivoMultimedia = archivoMultimedia;
    }

    /**
     * @return the comentarios
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}