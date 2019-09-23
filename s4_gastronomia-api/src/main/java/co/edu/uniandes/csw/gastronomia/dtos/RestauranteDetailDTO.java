/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
import java.io.Serializable;

/**
 *
 * @author Estudiante
 */
public class RestauranteDetailDTO extends RestauranteDTO implements Serializable 
{

    public RestauranteDetailDTO(RestauranteEntity r) 
    {
        super(r);
    }
    public RestauranteDetailDTO() 
    {
        super();
    }
    public RestauranteEntity toEntity()
    {
        RestauranteEntity r = super.toEntity();
        return r;
    }
}
