/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.persistence;
import co.edu.uniandes.csw.gastronomia.entities.ResenaEntity;
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
public class ResenaPersistence {
     private static Logger LOGGER = Logger.getLogger(ResenaEntity.class.getName());
    @PersistenceContext (unitName = "gastronomiaPU")
    protected EntityManager em;
    public ResenaEntity create(ResenaEntity resenaBlog)
    {
        LOGGER.log(Level.INFO, "Se está creando el foodblog con el id={0}", resenaBlog.getId());
        em.persist(resenaBlog);
        LOGGER.log(Level.INFO, "Se creo el foodblog con el  id={0}", resenaBlog.getId());
        return resenaBlog;
    }
    public List<ResenaEntity> findAll()            
    {
        LOGGER.log(Level.INFO, "Se buscan las resenas");
        TypedQuery q = em.createQuery("select u from ResenaEntity u", ResenaEntity.class);
        return (List<ResenaEntity>)q.getResultList();
    }
     /**
     * Busca si hay alguna resenaBlog con el id que se envía de argumento
     *
     * @param resenaBlogsId: id correspondiente a la resenaBlog buscada.
     * @return un resenaBlog.
     */
    public ResenaEntity find(Long resenaBlogsId) {
        LOGGER.log(Level.INFO, "Consultando el foodblog con id={0}", resenaBlogsId);
       
        return em.find(ResenaEntity.class, resenaBlogsId);
    }

    /**
     * Actualiza una resenaBlog.
     *
     * @param resenaBlogEntity: la resenaBlog que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un resenaBlog con los cambios aplicados.
     */
    public ResenaEntity update(ResenaEntity resenaBlogEntity) {
        LOGGER.log(Level.INFO, "Actualizando el resenaBlog con id={0}", resenaBlogEntity.getId());
       
        return em.merge(resenaBlogEntity);
    }

    /**
     * Borra una resenaBlog de la base de datos recibiendo como argumento el id de
     * la resenaBlog
     *
     * @param resenaBlogsId: foodblog que se va a borrar.
     */
    public void delete(Long resenaBlogsId) {

        LOGGER.log(Level.INFO, "Borrando el resenaBlog con id={0}", resenaBlogsId);
        
        ResenaEntity resenaBlogEntity = em.find(ResenaEntity.class, resenaBlogsId);
       
        em.remove(resenaBlogEntity);
    }
}
