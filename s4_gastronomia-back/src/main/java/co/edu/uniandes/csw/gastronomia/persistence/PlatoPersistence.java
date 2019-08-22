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
import javax.persistence.Query;

/**
 *
 * @author Estudiante
 */
@Stateless
public class PlatoPersistence {
    @PersistenceContext(unitName = "gastronomiaPU")
    protected EntityManager em; 
    /**
     * Encuentra una plato asociado a un Id 
     * @param platoId. Id del plato el cual se quiere busca
     * @return  Retorna un plato en caso de que lo encuentre. En caso contrario retorna null
     */
    public PlatoEntity find(Long platoId)
    {
        return em.find(PlatoEntity.class, platoId);
    }
    /**
     * Crea un plato en la base de datos
     * @param plato. Plato el cual se quiere persisti
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
    /**
     * Busca todos los platos existentes
     * @return Una lista con todos los platos 
     */
    public List<PlatoEntity> findAll()
    {
        Query q = em.createQuery("select u from PlatoEntity u"); 
        return q.getResultList();
    }
    
    
}
