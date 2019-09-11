/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;

import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.ClientePersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Cristina Isabel González Osorio
 */
@Stateless
public class ClienteLogic {
    
    @Inject
    private ClientePersistence persistence;
    
    public ClienteEntity createCliente (ClienteEntity cliente) throws BusinessLogicException {
        
        if (cliente.getNombre() == null) {
           throw new BusinessLogicException("El nombre del cliente no puede estar vacío."); 
        }
        cliente = persistence.create(cliente);
        return cliente;
    }
    
    
    
}
