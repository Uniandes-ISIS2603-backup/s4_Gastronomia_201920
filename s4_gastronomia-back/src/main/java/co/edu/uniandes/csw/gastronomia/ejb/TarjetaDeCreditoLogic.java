/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;
import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
import co.edu.uniandes.csw.gastronomia.entities.TarjetaDeCreditoEntity;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.ClientePersistence;
import co.edu.uniandes.csw.gastronomia.persistence.TarjetaDeCreditoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Estudiante
 */
@Stateless
public class TarjetaDeCreditoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(TarjetaDeCreditoLogic.class.getName());
    
    @Inject
    TarjetaDeCreditoPersistence persistence; 
    
    @Inject
    private ClientePersistence clientePersistence;
    /**
     * Metodo para crear una tarjeta de credito
     * @param clienteId
     * @param tarjeta tarjeta que se quiere crear
     * @return retorna el id de la tarjeta creada
     * @throws BusinessLogicException 
     */
    public TarjetaDeCreditoEntity createTarjetaDeCredito(Long clienteId,TarjetaDeCreditoEntity tarjeta)throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de creación de tarjeta");
        ClienteEntity cliente = clientePersistence.find(clienteId);
        tarjeta.setCliente(cliente);
        
        if(tarjeta.getCvv() < 0)
        {
            throw new BusinessLogicException("El cvv no es negativo");
        }
        else if(Integer.toString(tarjeta.getCvv()).length() != 3)
        {
            throw new BusinessLogicException("El cvv no es de 3 digitos");
        }
        else if(tarjeta.getFechaVencimiento() == null)
        {
            throw new BusinessLogicException("La fecha es vacia");
        }
        else if(tarjeta.getNumero()< 0)
        {
            throw new BusinessLogicException("El numero de tarjeta no puede ser negativo");
        }
        else if((Long.toString(tarjeta.getNumero()).length() != 16))
        {
            throw new BusinessLogicException("El numero de tarjeta no tiene 16 digitos");
        }
        else if (!Long.toString(tarjeta.getNumero()).startsWith("4") && !Long.toString(tarjeta.getNumero()).startsWith("5") )
        {
            throw new BusinessLogicException("La tarjeta no es visa ni mastercard");
        }
         LOGGER.info("Termina proceso de creación de tarjeta");
        return persistence.create(tarjeta);   
    }
    /**
     * Metodo
     * @param tarjetaId. Id de la tarjeta que se quiere actualizar  
     * @param tarjetaEntity Tarjeta con la que se quiere atualizar
     * @return Retorna la tarjeta actualizada.
     * @throws BusinessLogicException 
     */
    public TarjetaDeCreditoEntity updatetarjetaDeCredito(Long clienteId, TarjetaDeCreditoEntity tarjetaEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar tarjeta con id = {0}", tarjetaEntity.getId());
        ClienteEntity cliente = clientePersistence.find(clienteId);
        tarjetaEntity.setCliente(cliente);
         if(tarjetaEntity.getCvv() < 0)
        {
            throw new BusinessLogicException("El cvv no es negativo");
        }
        else if(Integer.toString(tarjetaEntity.getCvv()).length() != 3)
        {
            throw new BusinessLogicException("El cvv no es de 3 digitos");
        }
        else if(tarjetaEntity.getFechaVencimiento() == null)
        {
            throw new BusinessLogicException("La fecha es vacia");
        }
        else if(tarjetaEntity.getNumero()< 0)
        {
            throw new BusinessLogicException("El numero de tarjeta no puede ser negativo");
        }
        else if((Long.toString(tarjetaEntity.getNumero()).length() != 16))
        {
            throw new BusinessLogicException("El numero de tarjeta no tiene 16 digitos");
        }
         else if (!Long.toString(tarjetaEntity.getNumero()).startsWith("4") && !Long.toString(tarjetaEntity.getNumero()).startsWith("5") )
        {
            throw new BusinessLogicException("La tarjeta no es visa ni mastercard");
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar tarjeta con id = {0}", tarjetaEntity.getId());
        return persistence.update(tarjetaEntity);
    }
    /**
     * Metodo para eliminar una tarjeta de credito
     * @param tarjetaId 
     */
    public void deleteTarjetaDeCredito(Long clienteId, Long tarjetaId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar tarjeta con id = {0}", tarjetaId);
        TarjetaDeCreditoEntity vieja = findTarjetaDeCredito(clienteId, tarjetaId);
        if(vieja == null)
        {
            throw new BusinessLogicException("La tarjeta no esta asociada al cliente indicado");
        }
        persistence.delete(tarjetaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar tarjeta con id = {0}", tarjetaId);
    }

    /**
     * Metodo para consultar una tarjeta de credito segun el id dado.
     * @param tarjetaId. Id de la tarjeta que se quiere consultar
     * @return Tarjeta con el id indicado. Null en caso de que no exista ninguna tarjeta.
     */
    public TarjetaDeCreditoEntity findTarjetaDeCredito(Long clienteId, Long tarjetaId)
    {   
        return persistence.find(clienteId,tarjetaId);
    }
    
     /**
     * Metodo para consultar todos las tarjetas de un cliente.
     * @param clienteId
     * @return 
     */
    public List<TarjetaDeCreditoEntity> getTarjetas(Long clienteId)
    {
        ClienteEntity clienteEntity = clientePersistence.find(clienteId);
        return clienteEntity.getTarjetas();
    }

       
    
    
    
    
    
    
    
}
