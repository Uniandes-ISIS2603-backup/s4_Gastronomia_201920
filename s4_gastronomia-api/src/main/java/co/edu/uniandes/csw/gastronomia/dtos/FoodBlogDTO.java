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
private Integer numeroMeGusta;

/**
 * Numero de no me gusta que tiene el blog del ususario
 */
private Integer numeroNoMegusta;
/**
 * Ruta de imagen de la foto que tiene el usuario en su blog
 */
private String archivoMultimedia;
/**
 * COmentarios del blog
 */
private String comentarios;

//private ClienteDTO clienteDto;
public FoodBlogDTO()
{
  //
}
 
    public FoodBlogDTO(FoodBlogEntity fb)
    {
     if(fb!=null)
     {
         this.id=fb.getId();
     this.texto=fb.getTexto();
     this.numeroMeGusta=fb.getNumeroMeGusta();
     this.numeroNoMegusta=fb.getNumeroNoMegusta();
     this.archivoMultimedia=fb.getArchivoMultimedia();
     this.comentarios=fb.getComentarios();
     }
    }
 
 
 public FoodBlogEntity toEntity()
 {
     FoodBlogEntity fb=new FoodBlogEntity();
     fb.setId(this.getId());
     fb.setTexto(this.getTexto());
     fb.setNumeroMeGusta(this.getNumeroMeGusta());
     fb.setNumeroNoMegusta(this.getNumeroNoMegusta());
     fb.setArchivoMultimedia(this.getArchivoMultimedia());
     fb.setComentarios(this.getComentarios());
     return fb;
 }
 public Long getId()
    {
        return this.id;
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
    public Integer getNumeroMeGusta() {
        return numeroMeGusta;
    }

    /**
     * @param numeroMeGusta the numeroMeGusta to set
     */
    public void setNumeroMeGusta(Integer numeroMeGusta) {
        this.numeroMeGusta = numeroMeGusta;
    }

    /**
     * @return the numeroNoMegusta
     */
    public Integer getNumeroNoMegusta() {
        return numeroNoMegusta;
    }

    /**
     * @param numeroNoMegusta the numeroNoMegusta to set
     */
    public void setNumeroNoMegusta(Integer numeroNoMegusta) {
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