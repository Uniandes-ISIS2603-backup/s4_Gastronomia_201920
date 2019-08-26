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
    public ResenaEntity create(ResenaEntity resena)
    {
        LOGGER.log(Level.INFO, "Se está creando la resena con el id={0}", resena.getId());
        em.persist(resena);
        LOGGER.log(Level.INFO, "Se creo la resena con el  id={0}", resena.getId());
        return resena;
    }
    /**
     * Retorna una lista con todoas la resenas en la base de datos.
     * @return 
     */
    public List<ResenaEntity> findAll()            
    {
        LOGGER.log(Level.INFO, "Se buscan las resenas");
        TypedQuery q = em.createQuery("select u from ResenaEntity u", ResenaEntity.class);
        return (List<ResenaEntity>)q.getResultList();
    }
     /**
     * Busca si hay alguna resena con el id pasado por parametro
     *
     * @param resenasId: id correspondiente a la resena buscada.
     * @return un resena.
     */
    public ResenaEntity find(Long resenasId) {
        LOGGER.log(Level.INFO, "Consultando la resena con id={0}", resenasId);
       
        return em.find(ResenaEntity.class, resenasId);
    }

    /**
     * Actualiza una resena con la informacion nueva pasada por parametro.
     *
     * @param resenaEntity: la resena nueva 
     * @return un resena con los cambios aplicados.
     */
    public ResenaEntity update(ResenaEntity resenaEntity) {
        LOGGER.log(Level.INFO, "Actualizando la resena con id={0}", resenaEntity.getId());
       
        return em.merge(resenaEntity);
    }

    /**
     *Borra una resena con el d pasado por parametro
     *
     * @param resenasId: food que se va a borrar.
     */
    public void delete(Long resenasId) {

        LOGGER.log(Level.INFO, "Borrando la resena con id={0}", resenasId);
        
        ResenaEntity resenaEntity = em.find(ResenaEntity.class, resenasId);
       
        em.remove(resenaEntity);
    }
}
