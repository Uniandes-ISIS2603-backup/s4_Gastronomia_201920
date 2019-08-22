/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.persistence;

import co.edu.uniandes.csw.gastronomia.entities.ResenaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author af.benitez
 */
@Stateless
public class ResenaPersistence 
{
    @PersistenceContext(unitName = "gastronomiaPU")
    
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param resenaEntity objeto resena que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ResenaEntity create(ResenaEntity resenaEntity) 
    {
        em.persist(resenaEntity);
        
        return resenaEntity;
    }
    
    /**
     * Devuelve todos los tipoComida de la base de datos.
     *
     * @return una lista con todos las resenas  que encuentre en la base de datos,
     * "select u from ResenaEntity u" es como un "select * from ResenaEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<ResenaEntity> findAll()
    {
        Query q = em.createQuery("select u from BookEntity u");
        return q.getResultList();
    }
    
      /**
     * Busca si hay alguna resna con el id que se envía de argumento
     *
     * @param resenaId: id correspondiente al tipo buscado.
     * @return un tipoComida.
     */
    public ResenaEntity find(Long resenaId) 
    {
        return em.find(ResenaEntity.class, resenaId);
    }
    
    /**
     * Actualiza un tipo.
     *
     * @param resenaEntity: la resena que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un resenaEntity con los cambios aplicados.
     */
    public ResenaEntity update(ResenaEntity resenaEntity) 
    {
        return em.merge(resenaEntity);
    }

    /**
     *
     * Borra un TipoComida de la base de datos recibiendo como argumento el nombre
     *
     * @param resenaId: id correspondiente al libro a borrar.
     */
    public void delete(Long resenaId)
    {
        ResenaEntity resenaEntity = em.find(ResenaEntity.class, resenaId);
        em.remove(resenaEntity);
    }
}
