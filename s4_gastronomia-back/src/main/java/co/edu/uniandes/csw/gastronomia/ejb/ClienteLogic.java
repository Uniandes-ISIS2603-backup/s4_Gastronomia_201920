/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;

import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.ClientePersistence;
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
public class ClienteLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());
    
    @Inject
    private ClientePersistence persistence;
    
    /**
     * Crea un nuevo cliente
     *
     * @param clienteEntity La entidad de tipo cliente del nuevo cliente a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el username o el email son inválidos o ya existe en la
     * persistencia.
     */
    public ClienteEntity createCliente (ClienteEntity clienteEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del cliente");
        checkBusinessLogic(clienteEntity);
        if(persistence.findByUsername(clienteEntity.getUsername()) != null){
            throw new BusinessLogicException("El username ya existe");   
        }
        if(persistence.findByEmail(clienteEntity.getEmail()) != null){
            throw new BusinessLogicException("El email ya existe");   
        }
        if(clienteEntity.getPuntos() != 0) {
             throw new BusinessLogicException("El número de puntos del cliente debe ser 0"); 
        }
        clienteEntity = persistence.create(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del cliente");
        return clienteEntity;
    }
    
    /**
     * Devuelve todos los clientes que hay en la base de datos.
     *
     * @return Lista de entidades de tipo cliente.
     */
    public List<ClienteEntity> getClientes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los clientes");
        List<ClienteEntity> clientes = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los clientes");
        return clientes;
    }
    
    /**
     * Busca un cliente por ID
     *
     * @param clientesId El id del cliente a buscar
     * @return El cliente encontrado, null si no lo encuentra.
     */
    public ClienteEntity getCliente(Long clienteId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el cliente con id = {0}", clienteId);
        ClienteEntity clienteEntity = persistence.find(clienteId);
        if (clienteEntity == null) {
            LOGGER.log(Level.SEVERE, "El cliente con el id = {0} no existe", clienteId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el cliente con id = {0}", clienteId);
        return clienteEntity;
    }
    
    /**
     * Actualizar un cliente por ID
     *
     * @param clienteId El ID del cliente a actualizar
     * @param clienteEntity La entidad del cliente con los cambios deseados
     * @return La entidad del cliente luego de actualizarla
     * @throws BusinessLogicException Si el IBN de la actualización es inválido
     */
    public ClienteEntity updateCliente(Long clienteId, ClienteEntity clienteEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el cliente con id = {0}", clienteId);
        checkBusinessLogic(clienteEntity);
        if(clienteEntity.getPuntos() < 0) {
             throw new BusinessLogicException("El número de puntos del cliente debe ser mayor a 0"); 
        }
        ClienteEntity newEntity = persistence.update(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el cliente con id = {0}", clienteEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un cliente por ID
     *
     * @param clienteId El ID del cliente a eliminar
     * @throws BusinessLogicException si el cliente tiene autores asociados
     */
    public void deleteCliente(Long clienteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el cliente con id = {0}", clienteId);
        persistence.delete(clienteId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el cliente con id = {0}", clienteId);
    }  
    
    private void checkBusinessLogic(ClienteEntity clienteEntity) throws BusinessLogicException{
        if (clienteEntity.getNombre() == null || clienteEntity.getNombre().isEmpty()) {
           throw new BusinessLogicException("El nombre del cliente es inválido"); 
        }
        if(clienteEntity.getApellido() == null || clienteEntity.getApellido().isEmpty()) {
            throw new BusinessLogicException("El apellido del cliente es inválido"); 
        }
        if(clienteEntity.getUsername() == null || clienteEntity.getUsername().isEmpty()) {
            throw new BusinessLogicException("El username del cliente es inválido"); 
        }
        if(clienteEntity.getEmail() == null || clienteEntity.getEmail().isEmpty()) {
            throw new BusinessLogicException("El email del cliente es inválido"); 
        }
        if(clienteEntity.getContrasena() == null || clienteEntity.getContrasena().isEmpty()) {
             throw new BusinessLogicException("La contraseña del cliente es inválido"); 
        }
        if(clienteEntity.getNumeroContacto() == null || clienteEntity.getNumeroContacto().isEmpty()) {
             throw new BusinessLogicException("El número de contacto del cliente es inválido"); 
        }
    }
}
