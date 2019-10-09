/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.persistence;

import co.edu.uniandes.csw.gastronomia.entities.ReservaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Cristina González
 */
@Stateless
public class ReservaPersistence {
    
    private static final Logger LOGGER = 
            Logger.getLogger(ReservaPersistence.class.getName());
    
    @PersistenceContext(unitName = "gastronomiaPU")
    protected EntityManager em;
    
    /**
     * Crea una reserva en la base de datos.
     *
     * @param reservaEntity entidad reserva que se creará en la base de datos.
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ReservaEntity create(ReservaEntity reservaEntity) {
        em.persist(reservaEntity);
        return reservaEntity;
    }
    
    /**
     * Busca si hay alguna reserva con el id dado por parámetro.
     *
     * @param reservaId: id correspondiente a la reserva buscada.
     * @return la reserva encontrada con el id dado por parámetro.
     */
    public ReservaEntity find (Long reservaId) {
        return em.find(ReservaEntity.class, reservaId);
    }
    
    /**
     * Devuelve todos las reservas de la base de datos.
     *
     * @return una lista con todos las reservas que encuentre en la base de 
     * datos.
     */
    public List<ReservaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las reservas");
        Query q = em.createQuery("select u from ReservaEntity u");
        return q.getResultList();
    }
    
    /**
     * Actualiza una reserva.
     *
     * @param reservaEntity: la reserva que viene con los nuevos cambios.
     * @return una reserva con los cambios aplicados.
     */
    public ReservaEntity update(ReservaEntity reservaEntity) {
        LOGGER.log(Level.INFO, "Actualizando la reserva con id={0}",
                reservaEntity.getId());
        return em.merge(reservaEntity);
    }
    
    /**
     *
     * Borra una reserva de la base de datos recibiendo como argumento el id de
     * la reserva
     *
     * @param reservaId: id correspondiente a la reserva a borrar.
     */
    public void delete(Long reservaId) {
        LOGGER.log(Level.INFO, "Borrando la reserva con id={0}", reservaId);
        ReservaEntity reservaEntity = em.find(ReservaEntity.class, reservaId);
        em.remove(reservaEntity);
    }
    
}
