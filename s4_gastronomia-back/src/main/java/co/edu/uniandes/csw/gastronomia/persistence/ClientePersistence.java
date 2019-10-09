/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.persistence;

import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Cristina González
 */
@Stateless
public class ClientePersistence {
    
    private static final Logger LOGGER = 
            Logger.getLogger(ReservaPersistence.class.getName());
    
    @PersistenceContext(unitName = "gastronomiaPU")
    protected EntityManager em;
    
     /**
     * Crea un cliente en la base de datos.
     *
     * @param clienteEntity entidad cliente que se creará en la base de datos.
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ClienteEntity create(ClienteEntity clienteEntity) {
        em.persist(clienteEntity);
        return clienteEntity;
    }
    
    /**
     * Busca si hay algún cliente con el id dado por parámetro.
     *
     * @param clienteId: id correspondiente al cliente buscado.
     * @return el cliente encontrado con el id dado por parámetro.
     */
    public ClienteEntity find (Long clienteId) {
        return em.find(ClienteEntity.class, clienteId);
    }
    
    /**
     * Busca si hay algún cliente con el username que se envía de argumento
     *
     * @param username: username del cliente que se está buscando
     * @return null si no existe ningún cliente con el username del argumento. 
     * Si existe alguno devuelve el primero.
     */
    public ClienteEntity findByUsername(String username) {
        LOGGER.log(Level.INFO, "Consultando clientes por username ", username);
        TypedQuery query = em.createQuery("Select e From ClienteEntity e where e.username = :username", ClienteEntity.class);
        query = query.setParameter("username", username);
        List<ClienteEntity> sameUsername = query.getResultList();
        ClienteEntity result;
        if (sameUsername == null) {
            result = null;
        } else if (sameUsername.isEmpty()) {
            result = null;
        } else {
            result = sameUsername.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar clientes por username ", username);
        return result;
    }
    
    /**
     * Busca si hay algún cliente con el email que se envía de argumento
     *
     * @param email: email del cliente que se está buscando
     * @return null si no existe ningún cliente con el email del argumento. 
     * Si existe alguno devuelve el primero.
     */
    public ClienteEntity findByEmail(String email) {
        LOGGER.log(Level.INFO, "Consultando clientes por email ", email);
        TypedQuery query = em.createQuery("Select e From ClienteEntity e where e.email = :email", ClienteEntity.class);
        query = query.setParameter("email", email);
        List<ClienteEntity> sameEmail = query.getResultList();
        ClienteEntity result;
        if (sameEmail == null) {
            result = null;
        } else if (sameEmail.isEmpty()) {
            result = null;
        } else {
            result = sameEmail.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar clientes por email ", email);
        return result;
    }
    
     /**
     * Devuelve todos los clientes de la base de datos.
     *
     * @return una lista con todos los clientes que encuentre en la base de 
     * datos.
     */
    public List<ClienteEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las clientes");
        Query q = em.createQuery("select u from ClienteEntity u");
        return q.getResultList();
    }
    
    /**
     * Actualiza un cliente.
     *
     * @param clienteEntity: el cliente que viene con los nuevos cambios.
     * @return un cliente con los cambios aplicados.
     */
    public ClienteEntity update(ClienteEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Actualizando el cliente con id={0}",
                clienteEntity.getId());
        return em.merge(clienteEntity);
    }
    
    /**
     *
     * Borra un cliente de la base de datos recibiendo como argumento el id del
     * cliente
     *
     * @param clienteId: id correspondiente al cliente a borrar.
     */
    public void delete(Long clienteId) {
        LOGGER.log(Level.INFO, "Borrando la cliente con id={0}", clienteId);
        ClienteEntity clienteEntity = em.find(ClienteEntity.class, clienteId);
        em.remove(clienteEntity);
    }
    
}
