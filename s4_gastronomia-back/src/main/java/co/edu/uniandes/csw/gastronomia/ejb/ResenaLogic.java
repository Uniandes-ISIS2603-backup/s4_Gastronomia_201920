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
     * @param r que se quiere crear.
     * @return el resena que se creo.
     */
    public ResenaEntity createResena(ResenaEntity r) throws BusinessLogicException
    {
       LOGGER.log(Level.INFO,"creacion de los resenas");
        if (r.getCalificacion()<=0 || r.getCalificacion()>5 ) {
            throw new BusinessLogicException("La calificacion debe ser un numero del 1 al 5");
        }
        if (r.getComentario().equals("")) {
             throw new BusinessLogicException("El comentario de la resenia no puede ser vacio");
        }
        if(r.getComentario()== null){
             throw new BusinessLogicException("El comentario de la resenia no puede ser null");
        }

        ResenaEntity neResenaentity  = persistence.create(r);
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
    public ResenaEntity updateResena(Long resenaId, ResenaEntity resenaEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el resena con id = {0}", resenaId);
        ResenaEntity res= persistence.find(resenaId);
        if (res==null) {
              throw new BusinessLogicException("No existe el id que se esta pasando por parametro");
        }
        if (resenaEntity==null) {
            throw new BusinessLogicException("La resena que porla cual se quiere actualizar es nula");
        }
        else if (resenaEntity.getCalificacion()==0 || resenaEntity.getCalificacion()>5 || resenaEntity.getCalificacion()<0) {
            throw new BusinessLogicException("La calificacion debe ser un numero del 1 al 5");
        }
        else  if (resenaEntity.getComentario().equals("") || resenaEntity.getComentario()==null) {
             throw new BusinessLogicException("El comentario de la resenia no puede ser vacio");
        }
        ResenaEntity newfoodBlogEntity = persistence.update(resenaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el resena con id = {0}", resenaId);
        return newfoodBlogEntity;
    }

    /**
     * Elimina una instancia de resena de la base de datos.
     *
     * @param foodblogId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException si el autor tiene libros asociados.
     */
    public void deleteResena(Long resenaId) throws BusinessLogicException
    {
         LOGGER.log(Level.INFO, "Inicia proceso de borrar la resena con id = {0}", resenaId);
         ResenaEntity resena=persistence.find(resenaId);
         if (resena==null) {
            throw new BusinessLogicException("EL id buscado es nulo");
        }

         persistence.delete(resenaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la resena con id = {0}", resenaId);
    }

}
