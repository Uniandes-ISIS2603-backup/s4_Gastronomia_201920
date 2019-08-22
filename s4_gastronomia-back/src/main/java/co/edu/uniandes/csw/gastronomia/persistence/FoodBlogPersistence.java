/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.persistence;

import co.edu.uniandes.csw.gastronomia.entities.FoodBlogEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author David Martinez
 */
@Stateless
public class FoodBlogPersistence {
    private static Logger LOGGER = Logger.getLogger(FoodBlogEntity.class.getName());
    @PersistenceContext (unitName = "gastronomiaPU")
    protected EntityManager em;
    public FoodBlogEntity create(FoodBlogEntity foodBlog)
    {
        LOGGER.log(Level.INFO, "Se está creando el foodblog con el id={0}", foodBlog.getId());
        em.persist(foodBlog);
        LOGGER.log(Level.INFO, "Se creo el foodblog con el  id={0}", foodBlog.getId());
        return foodBlog;
    }
    public List<FoodBlogEntity> findAll()            
    {
        LOGGER.log(Level.INFO, "Se buscan todos los food blogs");
        TypedQuery q = em.createQuery("select u from FoodBlogEntity u", FoodBlogEntity.class);
        return (List<FoodBlogEntity>)q.getResultList();
    }
     /**
     * Busca si hay alguna foodBlog con el id que se envía de argumento
     *
     * @param foodBlogsId: id correspondiente a la foodBlog buscada.
     * @return un foodBlog.
     */
    public FoodBlogEntity find(Long foodBlogsId) {
        LOGGER.log(Level.INFO, "Consultando el foodblog con id={0}", foodBlogsId);
       
        return em.find(FoodBlogEntity.class, foodBlogsId);
    }

    /**
     * Actualiza una foodBlog.
     *
     * @param foodBlogEntity: la foodBlog que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un foodBlog con los cambios aplicados.
     */
    public FoodBlogEntity update(FoodBlogEntity foodBlogEntity) {
        LOGGER.log(Level.INFO, "Actualizando el foodBlog con id={0}", foodBlogEntity.getId());
       
        return em.merge(foodBlogEntity);
    }

    /**
     * Borra una foodBlog de la base de datos recibiendo como argumento el id de
     * la foodBlog
     *
     * @param foodBlogsId: foodblog que se va a borrar.
     */
    public void delete(Long foodBlogsId) {

        LOGGER.log(Level.INFO, "Borrando el foodBlog con id={0}", foodBlogsId);
        
        FoodBlogEntity foodBlogEntity = em.find(FoodBlogEntity.class, foodBlogsId);
       
        em.remove(foodBlogEntity);
    }
}
