/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author David Martinez
 */
@Entity
public class FoodBlogEntity extends BaseEntity implements Serializable{
    
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

 //@PodamExclude
// @ManyToOne
 //private ClienteEntity cliente;
//-------------------------------------------------------
//metodos Get Set
//-------------------------------------------------------
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
    @Override
    public boolean equals(Object obj)
    {
        if(! super.equals(obj))
        {
            return false;
        } 
        FoodBlogEntity foodblogObj=(FoodBlogEntity) obj;
        return texto.equals(foodblogObj.getTexto());
    }
   @Override
    public int hashCode()
   {
       
       
       if(this.getTexto()!=null)
       {
           return this.getTexto().hashCode();
       }
       return super.hashCode();
   }

    /**
     * @return the cliente
     */
    //public ClienteEntity getCliente() {
   //     return cliente;
   // }

    /**
     * @param cliente the cliente to set
     */
   // public void setCliente(ClienteEntity cliente) {
    //    this.cliente = cliente;
   // }
   
    
    
    
}
