/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.resources;

import co.edu.uniandes.csw.gastronomia.dtos.FacturaDTO;
import co.edu.uniandes.csw.gastronomia.ejb.FacturaLogic;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
     * @return JSON {@link FacturaDTO} - - La factura guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la facutra o el id es
     * inválido.
     */
    @POST
    public FacturaDTO createBook(FacturaDTO factura) throws BusinessLogicException 
    {
        return factura;
    }
}
