/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;
import co.edu.uniandes.csw.gastronomia.entities.TarjetaDeCreditoEntity;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
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
    /**
     * Metodo para crear una tarjeta de credito
     * @param tarjeta tarjeta que se quiere crear
     * @return retorna el id de la tarjeta creada
     * @throws BusinessLogicException 
     */
    public TarjetaDeCreditoEntity createTarjetaDeCredito(TarjetaDeCreditoEntity tarjeta)throws BusinessLogicException
    {
        LOGGER.info("Inicia proceso de creación de tarjeta");
        if(tarjeta.getCvv() < 0)
        {
            throw new BusinessLogicException("El cvv no es negativo");
        }
        else if(Integer.toString(tarjeta.getCvv()).length() != 3)
        {
            throw new BusinessLogicException("El cvv no es de 3 digitos");
        }
        else if(tarjeta.getFechaDeVencimiento() == null)
        {
            throw new BusinessLogicException("La fecha es vacia");
        }
        else if(tarjeta.getNumero()< 0)
        {
            throw new BusinessLogicException("El numero de tarjeta no puede ser negativo");
        }
        else if(!(Long.toString(tarjeta.getNumero()).length() == 16))
        {
            throw new BusinessLogicException("El numero de tarjeta no tiene 16 digitos");
        }
        else if (!Long.toString(tarjeta.getNumero()).startsWith("4") && !Long.toString(tarjeta.getNumero()).startsWith("5") )
        {
            throw new BusinessLogicException("La tarjeta no es visa ni mastercard");
        }
        tarjeta = persistence.create(tarjeta); 
        LOGGER.info("Termina proceso de creación de tarjeta");
        return tarjeta; 
    }
    /**
     * Metodo
     * @param tarjetaId. Id de la tarjeta que se quiere actualizar  
     * @param tarjetaEntity Tarjeta con la que se quiere atualizar
     * @return Retorna la tarjeta actualizada.
     * @throws BusinessLogicException 
     */
    public TarjetaDeCreditoEntity updatetarjetaDeCredito(Long tarjetaId, TarjetaDeCreditoEntity tarjetaEntity) throws BusinessLogicException
    {
         if(tarjetaEntity.getCvv() < 0)
        {
            throw new BusinessLogicException("El cvv no es negativo");
        }
        else if(Integer.toString(tarjetaEntity.getCvv()).length() != 3)
        {
            throw new BusinessLogicException("El cvv no es de 3 digitos");
        }
        else if(tarjetaEntity.getFechaDeVencimiento() == null)
        {
            throw new BusinessLogicException("La fecha es vacia");
        }
        else if(tarjetaEntity.getNumero()< 0)
        {
            throw new BusinessLogicException("El numero de tarjeta no puede ser negativo");
        }
        else if(!(Long.toString(tarjetaEntity.getNumero()).length() == 16))
        {
            throw new BusinessLogicException("El numero de tarjeta no tiene 16 digitos");
        }
         else if (!Long.toString(tarjetaEntity.getNumero()).startsWith("4") && !Long.toString(tarjetaEntity.getNumero()).startsWith("5") )
        {
            throw new BusinessLogicException("La tarjeta no es visa ni mastercard");
        }
        TarjetaDeCreditoEntity tarjeta = persistence.update(tarjetaEntity);
        return tarjeta;
        
    }
    /**
     * Metodo para eliminar una tarjeta de credito
     * @param tarjetaId 
     */
    public void deleteTarjetaDeCredito(Long tarjetaId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar premio con id = {0}", tarjetaId);
        persistence.delete(tarjetaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar premio con id = {0}", tarjetaId);
    }
    /**
     * Metodo para obtener todas las tarjetas de credtio
     * @return 
     */
    public List<TarjetaDeCreditoEntity> getTarjetas()
    {
        LOGGER.info("Inicia proceso de consultar todos las tarjetas");
        List<TarjetaDeCreditoEntity> tarjetas = persistence.findAll();
         LOGGER.info("Termina proceso de consultar todas las tarjetas");
        return tarjetas;
    }
    
    public TarjetaDeCreditoEntity getTarjetaDeCredito(Long tarjetaId)
    {   
        LOGGER.log(Level.INFO, "Inicia proceso de consultar premio con id = {0}", tarjetaId);
        TarjetaDeCreditoEntity tarjeta = persistence.find(tarjetaId);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar premio con id = {0}", tarjeta.getId());
        return tarjeta;
    }
    
    
    
    
    
    
}
