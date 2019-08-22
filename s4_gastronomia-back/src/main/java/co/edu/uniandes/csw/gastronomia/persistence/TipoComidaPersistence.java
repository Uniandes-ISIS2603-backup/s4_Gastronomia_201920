/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.persistence;

import co.edu.uniandes.csw.gastronomia.entities.TipoComidaEntity;

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
public class TipoComidaPersistence 
{
    @PersistenceContext(unitName = "gastronomiaPU")
    
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param tipoComidaEntity objeto TipoComida que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public TipoComidaEntity create(TipoComidaEntity tipoComidaEntity) 
    {
        em.persist(tipoComidaEntity);
        
        return tipoComidaEntity;
    }
    
    /**
     * Devuelve todos los tipoComida de la base de datos.
     *
     * @return una lista con todos los tiposComida que encuentre en la base de datos,
     * "select u from TipoComidaEntity u" es como un "select * from TipoComidaEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<TipoComidaEntity> findAll()
    {
        Query q = em.createQuery("select u from BookEntity u");
        return q.getResultList();
    }
    
      /**
     * Busca si hay algun tipoComida con el id que se envía de argumento
     *
     * @param tipoComidaId: id correspondiente al tipo buscado.
     * @return un tipoComida.
     */
    public TipoComidaEntity find(Long tipoComidaId) 
    {
        return em.find(TipoComidaEntity.class, tipoComidaId);
    }
    
    /**
     * Actualiza un tipo.
     *
     * @param tipoComidaEntity: el tipo que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return un tipoComida con los cambios aplicados.
     */
    public TipoComidaEntity update(TipoComidaEntity tipoComidaEntity) 
    {
        return em.merge(tipoComidaEntity);
    }

    /**
     *
     * Borra un TipoComida de la base de datos recibiendo como argumento el nombre
     *
     * @param tipoId: id correspondiente al libro a borrar.
     */
    public void delete(Long tipoId)
    {
        TipoComidaEntity tipoComidaEntity = em.find(TipoComidaEntity.class, tipoId);
        em.remove(tipoComidaEntity);
    }
}
