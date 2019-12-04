/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.resources;

import co.edu.uniandes.csw.gastronomia.dtos.TipoComidaDTO;
import co.edu.uniandes.csw.gastronomia.ejb.TipoComidaLogic;
import co.edu.uniandes.csw.gastronomia.entities.TipoComidaEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author af.benitez
 */

@Path("tipoComidas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TipoComidaResource 
{
    private static final Logger LOGGER = Logger.getLogger(TipoComidaResource.class.getName());
    
    private static final String RECURSO = "El recurso /tipoComidas/";
    private static final String NE = " no existe.";
      
    @Inject
    private TipoComidaLogic tipoComidaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    

    /**
     * Crea un nuevo tipoComida con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param tipo {@link TipoComidaDTO} - El tipo que se desea guardar.
     * @return JSON {@link TipoComidaDTO} - - El tipo guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el tipo o el id es
     * inválido.
     */
    @POST
    public TipoComidaDTO createTipo(TipoComidaDTO tipo) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "TipoComidaResource createTipo: input: {0}", tipo);
        TipoComidaDTO nuevaTipoDTO = new TipoComidaDTO(tipoComidaLogic.createTipoComida(tipo.toEntity()));
        LOGGER.log(Level.INFO, "TipoComidaResource createTipo: output: {0}", nuevaTipoDTO);
        return nuevaTipoDTO;
    }
    
     /**
     * Busca y devuelve todos los tipos comida que existen en la aplicacion.
     *
     * @return JSONArray {@link TipoComidaDTO} - Los tipos de comidas encontradas en la
     * aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<TipoComidaDTO> getTipos() 
    {
        LOGGER.info("TipoComidaResource getTipos: input: void");
        List<TipoComidaDTO> listaTipos = listEntity2DTO(tipoComidaLogic.getTipos());
        LOGGER.log(Level.INFO, "TipoComidaResource getTipos: output: {0}", listaTipos);
        return listaTipos;
    }

    /**
     * Busca el tipo comida con el id asociado recibido en la URL y lo devuelve.
     *
     * @param tipoId Identificador del tipo comida que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link TipoComidaDTO} - EL tipo comida buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el tipo comida.
     */
    @GET
    @Path("{tipoId: \\d+}")
    public TipoComidaDTO getTipo(@PathParam("tipoId") Long tipoId)
    {
        LOGGER.log(Level.INFO, "TipoComidaResource getTipo: input: {0}", tipoId);
        TipoComidaEntity tipoComidaEntity = tipoComidaLogic.getTipoComida(tipoId);
        if (tipoComidaEntity == null) 
        {
            throw new WebApplicationException("El recurso /tipoComidas/" + tipoId + NE, 404);
        }
        TipoComidaDTO tipoComidaDTO = new TipoComidaDTO(tipoComidaEntity);
        LOGGER.log(Level.INFO, "TipoComidaResource getTipo: output: {0}", tipoComidaDTO);
        return tipoComidaDTO;
    }

    /**
     * Actualiza el tipo comida con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param tipoId Identificador del tipo comida que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param tipoComida {@link TipoComidaDTO} - El tipo comida que se desea guardar.
     * @return JSON {@link TipoComidaDTO} - El tipo comida guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el tipo comida a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el tipo comida.
     */
    @PUT
    @Path("{tipoId: \\d+}")
    public TipoComidaDTO updateTipoComida(@PathParam("tipoId") Long tipoId, TipoComidaDTO tipo) throws BusinessLogicException 
    {
         LOGGER.log(Level.INFO, "TipoComidaResource updateTipoComida: input: id: {0} , book: {1}", new Object[]{tipoId, tipo});
        tipo.setId(tipoId);
        if (tipoComidaLogic.getTipoComida(tipoId) == null) 
        {
            throw new WebApplicationException("El recurso /tipoComidas/" + tipoId + NE, 404);
        }
        TipoComidaDTO tipoComidaDTO = new TipoComidaDTO(tipoComidaLogic.updateTipoComida(tipoId, tipo.toEntity()));
        LOGGER.log(Level.INFO, "TipoComidaResource updateTipoComida: output: {0}", tipoComidaDTO);
        return tipoComidaDTO;
    }

    /**
     * Borra el tipo comida con el id asociado recibido en la URL.
     *
     * @param tipoId Identificador del tipo comida que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * cuando el tipo comida no existe.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el tipo comida.
     */
    @DELETE
    @Path("{tipoId: \\d+}")
    public void deleteTipoComida(@PathParam("tipoId") Long tipoId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "TipoComidaResource deleteTipoComida: input: {0}", tipoId);
        TipoComidaEntity entity = tipoComidaLogic.getTipoComida(tipoId);
        if (entity == null) 
        {
            throw new WebApplicationException("El recurso /tipoComidas/" + tipoId + NE, 404);
        }
        tipoComidaLogic.deleteTipoComida(tipoId);
        LOGGER.info("TipoComidaResource deleteTipoComida: output: void");
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos TipoComidaEntity a una lista de
     * objetos TipoComidaEntity (json)
     *
     * @param entityList corresponde a la lista de tipoComida de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de libros en forma DTO (json)
     */
    private List<TipoComidaDTO> listEntity2DTO(List<TipoComidaEntity> entityList) {
        List<TipoComidaDTO> list = new ArrayList<>();
        for (TipoComidaEntity entity : entityList) {
            list.add(new TipoComidaDTO(entity));
        }
        return list;
    }

}
