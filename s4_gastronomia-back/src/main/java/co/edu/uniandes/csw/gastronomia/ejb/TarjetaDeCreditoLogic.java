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


/**
 *
 * @author Estudiante
 */
@Stateless
public class TarjetaDeCreditoLogic {
    @Inject
    TarjetaDeCreditoPersistence persistence; 
    public TarjetaDeCreditoEntity createTarjetaDeCredito(TarjetaDeCreditoEntity tarjeta)throws BusinessLogicException
    {
        
        if(tarjeta.getCvv() > 0)
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
        else if(tarjeta.getNumero()>0)
        {
            throw new BusinessLogicException("El numero de tarjeta no puede ser negativo");
        }
        else if(Integer.toString(tarjeta.getCvv()).length() == 10)
        {
            throw new BusinessLogicException("El numero de tarjeta no tiene 10 digitos");
        }
        tarjeta = persistence.create(tarjeta); 
        return tarjeta; 
    }
    
}
