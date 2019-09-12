/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;

import co.edu.uniandes.csw.gastronomia.entities.FoodBlogEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.FoodBlogPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author David Martinez
 */
@Stateless
public class FoodBlogLogic {
    
    private static final Logger LOGGER = Logger.getLogger(FoodBlogLogic.class.getName());
    /**
     * Conecta la capade logica con la de persistencia para que haya una conexion a  la basae de datos 
     */
    @Inject
    private FoodBlogPersistence persistence;
    /**
     * Crea un food blog entra por parametro un foodblog.
     * @param foodblog que se quiere crear.
     * @return el food blog que se creo.
     */
    public FoodBlogEntity createFoodBlog(FoodBlogEntity foodblog)
    {
       LOGGER.log(Level.INFO,"creacion de los foodblogs");
        FoodBlogEntity neFoodBlogentity  = persistence.create(foodblog);
        LOGGER.log(Level.INFO, "Termina la creacion de foodblogs");
        return neFoodBlogentity;
    }
    /**
     *Consulta la lista de todos los foodblogs en la base de datos.
     *
     * @return en una List todos los foodblogs creados.
     */
    public List<FoodBlogEntity> getFoodblogs() 
    {
                LOGGER.log(Level.INFO, "Obtiene todos los foodblogs");
        List<FoodBlogEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina la consulta de los foodblogs");
        return lista;
    }
    /**
     * Obtiene los datos de una instancia de un foodblog.
     *
     * @param fbId del foodblog que se esta buscando.
     * @return La informacion del foodblog buscado.
     */
    public FoodBlogEntity getfoodBlog(Long fbId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el food blog con id = {0}", fbId);
        FoodBlogEntity fbEntity = persistence.find(fbId);
        if (fbEntity == null) {
            LOGGER.log(Level.SEVERE, "el foodblog con el id = {0} no existe", fbId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el foodblog con id = {0}", fbId);
        return fbEntity;
    }
    
    /**
     * Actualiza la información de una instancia de foodblog.
     *
     * @param fbId Identificador de la instancia a actualizar
     * @param fbEntity Instancia de fbEntity con los nuevos datos.
     * @return Instancia de fbEntity con los datos actualizados.
     */
    public FoodBlogEntity updatefoodBlog(Long fbId, FoodBlogEntity fbEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el foodblog con id = {0}", fbId);
        FoodBlogEntity newfoodBlogEntity = persistence.update(fbEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el foodblog con id = {0}", fbId);
        return newfoodBlogEntity;
    }
    /**
     * Elimina una instancia de Foodblog de la base de datos.
     *
     * @param authorsId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deleteFoodBlog(Long authorsId) throws BusinessLogicException
    {
        
    }
    
}
