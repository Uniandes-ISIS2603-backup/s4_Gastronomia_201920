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
public class ResenaDetailDTO extends ResenaDTO implements Serializable {
    
    public ResenaDetailDTO(ResenaEntity r) {
        super(r);
    }
       
     public ResenaEntity toEntity()
    {
        ResenaEntity r= super.toEntity();
        return r;
    }
 public ResenaDetailDTO()
    {
        super();
      
    }
}