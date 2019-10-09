/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;

import co.edu.uniandes.csw.gastronomia.entities.PlatoEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.PlatoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
/**
 *
 * @author jecanizarez
 */
@Stateless
public class PlatoLogic {
    @Inject
    private PlatoPersistence persistence;
    /**
     * Crea un plato y verifica las reglas de negocio
     * @param plato. Plato que se quiere crear. 
     * @return Retorna el plato creado
     * @throws BusinessLogicException Si se rompe alguna regla de negocio.
     */
    public PlatoEntity createPlato(PlatoEntity plato)throws BusinessLogicException
    {
        if(plato.getPrecio() < 0)
        {
            throw new BusinessLogicException("El precio del plato es  negativo");
        }
        else if(plato.getNombreComida() == null)
        {
            throw new BusinessLogicException("El plato no tiene un nombre");
        }
        else if(plato.getDescripcion() == null)
        {
            throw new BusinessLogicException("El plato no tiene una descripcion");
        }
        else if(persistence.find(plato.getId()) != null)
        {
            throw new BusinessLogicException("El plato ya existe");
        }
        
        return persistence.create(plato);
    }
    /**
     * Metodo para actualizar un plato 
     * @param platoId. Id del plato que se quiere actualizar
     * @param plato. Plato con la informacion a actualizar
     * @return plato con la informacion actualizada
     * @throws BusinessLogicException 
     */
    public PlatoEntity updatePlato(Long platoId, PlatoEntity plato  )throws BusinessLogicException
    {
         if(plato.getPrecio() < 0)
        {
            throw new BusinessLogicException("El precio del plato es  negativo");
        }
        else if(plato.getNombreComida() == null)
        {
            throw new BusinessLogicException("El plato no tiene un nombre");
        }
        else if(plato.getDescripcion() == null)
        {
            throw new BusinessLogicException("El plato no tiene una descripcion");
        }
         return persistence.update(plato);
         
    }
    /**
     * Metodo para eliminar un plato segun el id especificado
     * @param tarjetaId 
     */
    public void deletePlato(Long platoId)
    {
        persistence.delete(platoId);
    }
    
    /**
     * Metodo para encontrar un plato segun el id indicado
     * @param platoId. Id del plato que se quiere encontrar
     * @return Plato
     */
    public PlatoEntity findPlato(Long platoId)
    {
        return persistence.find(platoId); 
    }
    /**
     * Metodo para encontrar todos los platos.
     * @return 
     */
    public List<PlatoEntity> findAllPlato()
    {
        return persistence.findAll(); 
    }
    
    
}
