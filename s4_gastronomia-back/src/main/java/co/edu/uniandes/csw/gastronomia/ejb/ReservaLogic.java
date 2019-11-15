/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;

import co.edu.uniandes.csw.gastronomia.entities.ReservaEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.ReservaPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Cristina Isabel González Osorio
 */
@Stateless
public class ReservaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ReservaLogic.class.getName());
    
    @Inject
    private ReservaPersistence persistence;
    
    /**
     * Crea una nueva reserva
     *
     * @param reservaEntity La entidad de tipo reserva de la nueva reserva a persistir.
     * @return La entidad luego de persistirla.
     * @throws BusinessLogicException Si la fecha es inválida.
     */
    public ReservaEntity createReserva (ReservaEntity reservaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del reserva");
        checkBusinessLogic(reservaEntity);
        if (reservaEntity.isCancelada()) {
            throw new BusinessLogicException("No es posible crear una reserva cancelada.");
        }
        reservaEntity = persistence.create(reservaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del reserva");
        return reservaEntity;
    }
    
    /**
     * Devuelve todas las reservas que hay en la base de datos.
     *
     * @return Lista de entidades de tipo reserva.
     */
    public List<ReservaEntity> getReservas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las reservas");
        List<ReservaEntity> reservas = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las reservas");
        return reservas;
    }
    
    /**
     * Busca una reserva por ID
     *
     * @param reservasId El id de la reserva a buscar
     * @return La reserva encontrada, null si no la encuentra.
     */
    public ReservaEntity getReserva(Long reservaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la reserva con id = {0}", reservaId);
        ReservaEntity reservaEntity = persistence.find(reservaId);
        if (reservaEntity == null) {
            LOGGER.log(Level.SEVERE, "La reserva con el id = {0} no existe", reservaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la reserva con id = {0}", reservaId);
        return reservaEntity;
    }
    
    /**
     * Actualizar una reserva por ID
     *
     * @param reservaId El ID de la reserva a actualizar
     * @param reservaEntity La entidad de la reserva con los cambios deseados
     * @return La entidad de la reserva luego de actualizarla
     * @throws BusinessLogicException Si la fecha de la actualización es inválida
     */
    public ReservaEntity updateReserva(Long reservaId, ReservaEntity reservaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la reserva con id = {0}", reservaId);
        checkBusinessLogic(reservaEntity);
        ReservaEntity newEntity = persistence.update(reservaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la reserva con id = {0}", reservaEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar una reserva por ID
     *
     * @param reservaId El ID de la reserva a eliminar
     * @throws BusinessLogicException si la reserva tiene autores asociados
     */
    public void deleteReserva(Long reservaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la reserva con id = {0}", reservaId);
        persistence.delete(reservaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la reserva con id = {0}", reservaId);
    }
    
    private void checkBusinessLogic(ReservaEntity reservaEntity) throws BusinessLogicException{
        if (reservaEntity.getFecha() == null) {
           throw new BusinessLogicException("La fecha de la reserva es inválida"); 
        }
        if (reservaEntity.getNumPersonas() <= 0) {
            throw new BusinessLogicException("El número de personas de la reserva no puede ser menor a 1.");
        }
    }
    
}
