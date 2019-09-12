/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;

import co.edu.uniandes.csw.gastronomia.entities.ResenaEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.ResenaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author David Martinez
 */
@Stateless
public class ResenaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ResenaLogic.class.getName());
    /**
     * Conecta la capade logica con la de persistencia para que haya una conexion a  la basae de datos 
     */
    @Inject
    private ResenaPersistence persistence;
    /**
     * Crea un food blog entra por parametro un resena.
     * @param resena que se quiere crear.
     * @return el resena que se creo.
     */
    public ResenaEntity createResena(ResenaEntity resena)
    {
       LOGGER.log(Level.INFO,"creacion de los resenas");
        ResenaEntity neResenaentity  = persistence.create(resena);
        LOGGER.log(Level.INFO, "Termina la creacion de resenas");
        return neResenaentity;
    }
    /**
     *Consulta la lista de todos los resenas en la base de datos.
     *
     * @return en una List todos los resenas creados.
     */
    public List<ResenaEntity> getResenas() 
    {
                LOGGER.log(Level.INFO, "Obtiene todos los resenas");
        List<ResenaEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina la consulta de los resenas");
        return lista;
    }
    /**
     * Obtiene los datos de una instancia de un resena.
     *
     * @param resenaId del resena que se esta buscando.
     * @return La informacion del resena buscado.
     */
    public ResenaEntity getResena(Long resenaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el food blog con id = {0}", resenaId);
        ResenaEntity resenaEntity = persistence.find(resenaId);
        if (resenaEntity == null) {
            LOGGER.log(Level.SEVERE, "el resena con el id = {0} no existe", resenaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el resena con id = {0}", resenaId);
        return resenaEntity;
    }
    
    /**
     * Actualiza la información de una instancia de resena.
     *
     * @param resenaId Identificador de la instancia a actualizar
     * @param resenaEntity Instancia de resenaEntity con los nuevos datos.
     * @return Instancia de resenaEntity con los datos actualizados.
     */
    public ResenaEntity updateResena(Long resenaId, ResenaEntity resenaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el resena con id = {0}", resenaId);
        ResenaEntity newfoodBlogEntity = persistence.update(resenaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el resena con id = {0}", resenaId);
        return newfoodBlogEntity;
    }
    /**
     * Elimina una instancia de Foodblog de la base de datos.
     *
     * @param authorsId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deleteResena(Long authorsId) throws BusinessLogicException
    {
        
    }
    
}
