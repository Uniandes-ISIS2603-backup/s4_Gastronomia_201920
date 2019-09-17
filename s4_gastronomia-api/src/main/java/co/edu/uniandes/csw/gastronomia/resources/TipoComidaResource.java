/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.resources;

import co.edu.uniandes.csw.gastronomia.dtos.TipoComidaDTO;
import co.edu.uniandes.csw.gastronomia.ejb.TipoComidaLogic;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
    public TipoComidaDTO createBook(TipoComidaDTO tipo) throws BusinessLogicException 
    {
        return tipo;
    }
}
