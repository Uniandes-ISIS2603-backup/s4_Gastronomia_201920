/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.resources;

import co.edu.uniandes.csw.gastronomia.dtos.FacturaDTO;
import co.edu.uniandes.csw.gastronomia.ejb.FacturaLogic;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author af.benitez
 */

@Path("facturas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FacturaResource 
{
    private static final Logger LOGGER = Logger.getLogger(FacturaResource.class.getName());
    
    
    @Inject
    private FacturaLogic facturaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    

    /**
     * Crea una nueva factura con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param factura {@link FacturaDTO} - La factura que se desea guardar.
     * @return JSON {@link FacturaDTO} - - La factura guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la facutra o el id es
     * inválido.
     */
    @POST
    public FacturaDTO createFactura(FacturaDTO factura) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "FacturaResource createFactura: input: {0}", factura);
        FacturaDTO nuevaFacturaDTO = new FacturaDTO(facturaLogic.createFactura(factura.toEntity()));
        LOGGER.log(Level.INFO, "FacturaResource createFactura: output: {0}", nuevaFacturaDTO);
        return nuevaFacturaDTO;
    }
    
    /**
     * Busca y devuelve todos las facturas que existen en la aplicacion.
     *
     * @return JSONArray {@link FacturaDTO} - Las facturas encontradas en la
     * aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<FacturaDTO> getFacturas() 
    {
        return null;
    }

    /**
     * Busca ña factura con el id asociado recibido en la URL y lo devuelve.
     *
     * @param facturaId Identificador de la factura que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link FacturaDTO} - La factura buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la factura.
     */
    @GET
    @Path("{facturaId: \\d+}")
    public FacturaDTO getFactura(@PathParam("facturaId") Long facturaId)
    {
        return null;
    }

    /**
     * Actualiza la factura con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param facturaId Identificador de la factura que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param factura {@link FacturaDTO} - La factura que se desea guardar.
     * @return JSON {@link FacturaDTO} - La factura guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la factura a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar la factura.
     */
    @PUT
    @Path("{facturaId: \\d+}")
    public FacturaDTO updateFactura(@PathParam("facturaId") Long facturaId, FacturaDTO factrua) throws BusinessLogicException 
    {
        return null;
    }

    /**
     * Borra la factura con el id asociado recibido en la URL.
     *
     * @param facturaId Identificador de la factura que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     * cuando la factura no existe o no esta paga.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la factura.
     */
    @DELETE
    @Path("{facturaId: \\d+}")
    public void deleteBook(@PathParam("facturaId") Long facturaId) throws BusinessLogicException
    {
        
    }


}
