/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.persistence;

import co.edu.uniandes.csw.gastronomia.entities.PlatoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Estudiante
 */
@Stateless
public class PlatoPersistence {
    
    @PersistenceContext(unitName = "gastronomiaPU")
    private EntityManager em; 
    
   
    
    /**
     * Encuentra una plato asociado a un Id 
     * @param restauranteId
     * @param platoId. Id del plato el cual se quiere busca
     * @return  Retorna un plato en caso de que lo encuentre. En caso contrario retorna null
     */
    public PlatoEntity find(Long restauranteId, Long platoId)
    {
        TypedQuery<PlatoEntity> q = em.createQuery("select p from PlatoEntity p where (p.restaurante.id = :restauranteid) and (p.id = :platoId)", PlatoEntity.class);
        q.setParameter("restauranteid", restauranteId);
        q.setParameter("platoId", platoId);
        List<PlatoEntity> results = q.getResultList();
        PlatoEntity plato = null;
        if (results.size() >= 1) {
            plato = results.get(0);
        }
        return plato;
    }
    /**
     * Crea un plato en la base de datos
     * @param platoEntity
     * @return Retorna plato con el id que fue guardado en la case de datos. 
     */
    public PlatoEntity create(PlatoEntity platoEntity)
    {
     em.persist(platoEntity);
     return platoEntity; 
    }
    /**
     * Actualiza la informacion de un plato 
     * @param platoEntity. Plato con al informacion actualizada
     * @return Plato con la informacion actualizada
     */
    public PlatoEntity update(PlatoEntity platoEntity)
    {
        return em.merge(platoEntity);
    }
    /**
     * Elimina un pato de la base de batos
     * @param platoId . ID del plato el cual se quiere eliminar
     */
    public void delete(Long platoId)
    {
        PlatoEntity plato = em.find(PlatoEntity.class, platoId);
        em.remove(plato);
    }

    
    
}