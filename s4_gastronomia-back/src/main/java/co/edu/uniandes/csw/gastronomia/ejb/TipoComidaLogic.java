/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;

import co.edu.uniandes.csw.gastronomia.entities.TipoComidaEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.TipoComidaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author af.benitez
 */
@Stateless
public class TipoComidaLogic 
{
    @Inject
    private TipoComidaPersistence persistence;

    private static final Logger LOGGER = Logger.getLogger(TipoComidaLogic.class.getName());

    /**
     * Guardar un nuevo TipoComida
     *
     * @param tipoComidaEntity La entidad de tipo comida a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el nombre == "" es inválido o ya existe en la
     * persistencia.
     */
    public TipoComidaEntity createTipoComida(TipoComidaEntity tipoComidaEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de tipo de comida");

        if (tipoComidaEntity.getNombre().equals("")) 
        {
            throw new BusinessLogicException("El tipo comida no tiene nombre");
        }
        
        for (TipoComidaEntity tipoComidaEntity1 : persistence.findAll()) {
            if(tipoComidaEntity.getNombre().equals(tipoComidaEntity1.getNombre()))
            {
                throw new BusinessLogicException("El tipo de comida ya existe");
            }
        }
        
        persistence.create(tipoComidaEntity);
        
        LOGGER.log(Level.INFO, "Termina proceso de creación de tipo de comida");

        return tipoComidaEntity;
    }

    /**
     * Devuelve todos los tipos de comida que hay en la base de datos.
     *
     * @return Lista de entidades de tipo comida.
     */
    public List<TipoComidaEntity> getTipos() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los tipos de comida");
        List<TipoComidaEntity> tComida = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los tipos de comida");
        return tComida;
    }

    /**
     * Busca un tipoComida por ID
     *
     * @param tipoId El id del tipo comida a buscar
     * @return El tipo comida encontrado, null si no lo encuentra.
     */
    public TipoComidaEntity getTipoComida(Long tipoId) {
       
        TipoComidaEntity tipoComidaEntity = persistence.find(tipoId);
        if (tipoComidaEntity == null) 
        {
            LOGGER.log(Level.SEVERE, "El tipo comida con el id = {0} no existe", tipoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el tipo comida con id = {0}", tipoId);
        return tipoComidaEntity;
    }

    /**
     * Actualizar un tipo comida por ID
     *
     * @param tipoId El ID del tipo comida a actualizar
     * @param tipoComidaEntity La entidad de tipo comida con los cambios deseados
     * @return La entidad de tipo comida luego de actualizarla
     * @throws BusinessLogicException Si el id de la actualización es inválido
     */
    public TipoComidaEntity updateTipoComida(Long tipoId, TipoComidaEntity tipoComidaEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el tipo comida con id = {0}", tipoId);
        if (persistence.find(tipoComidaEntity.getId()) == null)
        {
            throw new BusinessLogicException("El id es inválido");
        }
        TipoComidaEntity newEntity = persistence.update(tipoComidaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el tipo comida con id = {0}", tipoComidaEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar un tipo comida por ID
     *
     * @param tipoId El ID del tipoComida a eliminar
     * @throws BusinessLogicException si el tipo no existe
     */
    public void deleteTipoComida(Long tipoId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un tipo de comida con id = {0}", tipoId);
        
        if (persistence.find(tipoId) == null)
        {
            throw new BusinessLogicException("No se puede borrar el tipo comina con id = " + tipoId + " porque no existe");
        }
        persistence.delete(tipoId);
        
        LOGGER.log(Level.INFO, "Termina proceso de borrar el tipo comida con id = {0}", tipoId);
    }
}
