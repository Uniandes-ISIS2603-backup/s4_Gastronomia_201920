/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author David Martinez
 */
@Entity
public class ResenaEntity extends BaseEntity implements Serializable {
    
    /**
     * La calificacion que rescibe un restaurante por parte de un usario
     */
    private Integer calificacion;
    /**
     * El comentqrio de la resena que hace un usuario
     */
    private String comentario;

    
  //  @PodamExclude 
   // @OneToOne
   // private ReservaEntity reserva;
    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
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
     @Override
    public boolean equals(Object obj)
    {
        if(! super.equals(obj))
        {
            return false;
        } 
        ResenaEntity resenaObj=(ResenaEntity) obj;
        return comentario.equals(resenaObj.getComentario());
    }
   @Override
    public int hashCode()
   {
       
       
       if(this.getComentario()!=null)
       {
           return this.getComentario().hashCode();
       }
       return super.hashCode();
   }

    /**
     * @return the reserva
     */
    //public ReservaEntity getReserva() {
    //    return reserva;
   // }

    /**
     * @param reserva the reserva to set
     */
    //public void setReserva(ReservaEntity reserva) {
    //    this.reserva = reserva;
   // }
   
    
    

    
}
