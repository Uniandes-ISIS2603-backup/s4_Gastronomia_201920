/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;

import co.edu.uniandes.csw.gastronomia.entities.PlatoEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.PlatoPersistence;
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
        plato = persistence.create(plato);
        return plato; 
    }
}
