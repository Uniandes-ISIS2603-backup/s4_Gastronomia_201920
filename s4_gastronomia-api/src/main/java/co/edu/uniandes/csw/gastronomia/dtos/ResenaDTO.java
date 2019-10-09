/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.ResenaEntity;
import java.io.Serializable;

/**
 *
 * @author Estudiante
 */
public class ResenaDTO implements Serializable {
    private Long id;
    
    private int calificacion;
    
    private String comentario;
    
    public ResenaDTO()
    {
        
    }
    
    public ResenaDTO(ResenaEntity r)
    {
        this.id=r.getId();
        this.comentario=r.getComentario();
        this.calificacion=r.getCalificacion();
    }
    
    public ResenaEntity toEntity()
    {
        ResenaEntity r= new ResenaEntity();
        r.setCalificacion(this.getCalificacion());
        r.setComentario(this.getComentario());
        r.setId(this.getId());
        return r;
    }
    
    public Long getId()
    {
        return this.getId();
    }
    public void setId(Long pId)
    {
        this.id=pId;
    }
    
    private int getCalificacion()
    {
        return this.calificacion;
    }
    
    public void setCalificacion(int pCalificacion)
    {
        this.calificacion=pCalificacion;
    }
    public String getComentario()
    {
        return this.comentario;
    }
    public void setComentario(String pComentario)
    {
        this.comentario=pComentario;
    }
}
