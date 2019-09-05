/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;

import co.edu.uniandes.csw.gastronomia.entities.FoodBlogEntity;
import co.edu.uniandes.csw.gastronomia.persistence.FoodBlogPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author David Martinez
 */
@Stateless
public class FoodBlogLogic {
    /**
     * Conecta la capade logica con la de persistencia para que haya una conexion a  la basae de datos 
     */
    @Inject
    private FoodBlogPersistence persistence;
    
    public FoodBlogEntity createFoodBlog(FoodBlogEntity foodblog)
    {
        foodblog= persistence.create(foodblog);
        return foodblog;
    }
}
