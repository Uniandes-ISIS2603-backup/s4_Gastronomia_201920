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
public class FoodBlogDetailDTO extends FoodBlogDTO implements Serializable
{

    public FoodBlogDetailDTO(FoodBlogEntity fb) {
        super(fb);
    }
    
    public FoodBlogDetailDTO()
    {
        super();
      
    }
     public FoodBlogEntity toEntity()
    {
        FoodBlogEntity fb= super.toEntity();
        return fb;
    }
}
