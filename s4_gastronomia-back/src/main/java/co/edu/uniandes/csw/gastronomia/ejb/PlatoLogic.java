/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;

import co.edu.uniandes.csw.gastronomia.entities.PlatoEntity;
import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.PlatoPersistence;
import co.edu.uniandes.csw.gastronomia.persistence.RestaurantePersistence;
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
    @Inject
    private RestaurantePersistence restaurantePersistence;
    /**
     * Crea un plato y verifica las reglas de negocio
     * @param restauranteId
     * @param plato. Plato que se quiere crear. 
     * @return Retorna el plato creado
     * @throws BusinessLogicException Si se rompe alguna regla de negocio.
     */
    public PlatoEntity createPlato(Long restauranteId, PlatoEntity plato)throws BusinessLogicException
    {
        RestauranteEntity restaurante = restaurantePersistence.find(restauranteId);
        plato.setRestaurante(restaurante);
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
        else if(persistence.find( restauranteId, plato.getId()) != null)
        {
            throw new BusinessLogicException("El plato ya existe");
        }
        
        return persistence.create(plato);
    }
    /**
     * Metodo para actualizar un plato 
     * @param restauranteId
     * @param plato. Plato con la informacion a actualizar
     * @return plato con la informacion actualizada
     * @throws BusinessLogicException 
     */
    public PlatoEntity updatePlato(Long restauranteId, PlatoEntity plato  )throws BusinessLogicException
    {
        RestauranteEntity restaurante = restaurantePersistence.find(restauranteId); 
        plato.setRestaurante(restaurante);
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
     * @param restauranteId
     * @param platoId
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    public void deletePlato(Long restauranteId, Long platoId)throws BusinessLogicException
    {
        PlatoEntity viejo = findPlato(restauranteId, platoId);
        if(viejo == null)
        {
            throw new BusinessLogicException("El plato no está asociado al restaurante indicado");
        }
        persistence.delete(platoId);
    }
    
    /**
     * Metodo para encontrar un plato segun el id indicado
     * @param restauranteId
     * @param platoId. Id del plato que se quiere encontrar
     * @return Plato
     */
    public PlatoEntity findPlato(Long restauranteId, Long platoId)
    {
        
        return persistence.find(restauranteId, platoId); 
    }
    /**
     * Metodo para consultar todos los platos de un restaurante
     * @param restauranteId
     * @return 
     */
    public List<PlatoEntity> getPlatos(Long restauranteId)
    {
        RestauranteEntity restauranteEntity = restaurantePersistence.find(restauranteId);
        return restauranteEntity.getPlatos();
    }

    
    
}