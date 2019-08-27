/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.persistence;

import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Estudiante
 */
@Stateless
public class RestaurantePersistence 
{
    private static final Logger LOGGER = Logger.getLogger(RestaurantePersistence.class.getName());

    @PersistenceContext(unitName = "gastronomiaPU")
    protected EntityManager em;

    public RestauranteEntity create(RestauranteEntity rest)
    {
        LOGGER.log(Level.INFO, "Creando un restaurante nuevo");
        em.persist(rest);
        LOGGER.log(Level.INFO, "Restaurante creado");
        return rest;
    }
    
    public List<RestauranteEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Buscando todos los restaurantes");
        TypedQuery q = em.createQuery("select u from RestauranteEntity u", RestauranteEntity.class);
        return q.getResultList();
    }
    
    public RestauranteEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Busando restaurante con id={0}", id);
        return em.find(RestauranteEntity.class, id);
    }
    
    public RestauranteEntity update(RestauranteEntity r)
    {
        LOGGER.log(Level.INFO, "Actualizando restaurante con id={0}", r.getId());
        return em.merge(r);
    }
    
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando restaurante con id={0}", id);
        RestauranteEntity r = find(id);
        em.remove(r);
    }
}
