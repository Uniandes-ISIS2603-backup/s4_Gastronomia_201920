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
    private static Logger logger = Logger.getLogger(FoodBlogEntity.class.getName());
    @PersistenceContext (unitName = "gastronomiaPU")
    protected EntityManager em;
    public FoodBlogEntity create(FoodBlogEntity foodBlog)
    {
      logger.log(Level.INFO, "Se está creando el foodblog con el id={0}", foodBlog.getId());
        em.persist(foodBlog);
      logger.log(Level.INFO, "Se creo el foodblog con el  id={0}", foodBlog.getId());
        return foodBlog;
    }
    /**
     * Encuentra todas los foodblogs disponibles en la base de datos.
     * @return 
     */
    public List<FoodBlogEntity> findAll()            
    {
      logger.log(Level.INFO, "Se buscan todos los food blogs");
        TypedQuery q = em.createQuery("select u from FoodBlogEntity u", FoodBlogEntity.class);
        return (List<FoodBlogEntity>)q.getResultList();
    }
     /**
     * Busca si hay alguna foodBlog con el id pasado por parametro
     *
     * @param foodBlogsId: id correspondiente a la foodBlog buscado.
     * @return un foodBlog.
     */
    public FoodBlogEntity find(Long foodBlogsId) {
      logger.log(Level.INFO, "Consultando el foodblog con id={0}", foodBlogsId);
       
        return em.find(FoodBlogEntity.class, foodBlogsId);
    }

    /**
     * Actualiza una foodBlog con los datos pasadoos por parametro
     *
     * @param foodBlogEntity: la foodBlog que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. 
     * @return un foodBlog con los cambios aplicados.
     */
    public FoodBlogEntity update(FoodBlogEntity foodBlogEntity) {
      logger.log(Level.INFO, "Actualizando el foodBlog con id={0}", foodBlogEntity.getId());
       
        return em.merge(foodBlogEntity);
    }

    /**
     * Borra una foodBlog de la base de datos recibiendo pr parametro el id del foodblog que se quiere borrar
     * @param foodBlogsId: foodblog que se va a borrar.
     */
    public void delete(Long foodBlogsId) {

      logger.log(Level.INFO, "Borrando el foodBlog con id={0}", foodBlogsId);
        
        FoodBlogEntity foodBlogEntity = em.find(FoodBlogEntity.class, foodBlogsId);
        FoodBlogEntity fb=find(foodBlogsId);
        em.remove(foodBlogEntity);
        
        
    }
}
