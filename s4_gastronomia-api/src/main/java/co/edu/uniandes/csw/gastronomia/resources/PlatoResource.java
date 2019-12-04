/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.resources;

import co.edu.uniandes.csw.gastronomia.dtos.PlatoDTO;
import co.edu.uniandes.csw.gastronomia.ejb.PlatoLogic;
import co.edu.uniandes.csw.gastronomia.entities.PlatoEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
 * @author je.canizarez
 */
@Produces("application/json")
@Consumes("application/json")
public class PlatoResource {
    

    private static final String RECURSO = "El recurso /restaurantes/";
    private static final String PLATOS = "/platos/";
    private static final String NO_EXISTE = " no existe.";
    
    @Inject 
    private PlatoLogic logic;
    /**
     * Metodo para crear un plato
     * @param restauranteId. Id del restaurante al cual pertenece el plaro
     * @param plato. plato que se quiere crear
     * @return plato creado
     * @throws BusinessLogicException 
     */
    @POST
    public PlatoDTO createPlato(@PathParam("restaurantesId") Long restauranteId, PlatoDTO plato)throws BusinessLogicException
    {
        return new PlatoDTO(logic.createPlato(restauranteId, plato.toEntity()));
    }
    /**
     * Metodo para consultar un plato de un restaurante
     * @param restaurantesId
     * @param platosId
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{platosId: \\d+}")
    public PlatoDTO getPlato(@PathParam("restaurantesId") Long restaurantesId, @PathParam("platosId") Long platosId) throws BusinessLogicException
    {
        PlatoEntity plato = logic.findPlato(restaurantesId, platosId);
        if(plato == null)
        {
            throw new WebApplicationException(RECURSO + restaurantesId + PLATOS + platosId + NO_EXISTE, 404);

        }
        return new PlatoDTO(plato);
    }
    @GET
    public List<PlatoDTO> getPlatos(@PathParam("restaurantesId") Long restaurantesId)
    {
        return listEntity2DTO(logic.getPlatos(restaurantesId));      
    }
    
    
    @PUT
    @Path("{platosId: \\d+}")
    public PlatoDTO updatePlato(@PathParam("restaurantesId") Long restaurantesId, @PathParam("platosId") Long platosId, PlatoDTO plato )throws BusinessLogicException
    {
        plato.setId(platosId);
        if(!platosId.equals(plato.getId()))
        {
            throw new BusinessLogicException("Los ids del plato no coinciden");
        }
        PlatoEntity entity = logic.findPlato(restaurantesId, platosId);
        if(entity == null)
        {
            throw new WebApplicationException(RECURSO + restaurantesId + PLATOS + platosId + NO_EXISTE, 404);

        }
        return new PlatoDTO(logic.updatePlato(restaurantesId, plato.toEntity()));
    }
    
    @DELETE
    @Path("{platosId: \\d+}")
    public void deletePlato(@PathParam("restaurantesId") Long restaurantesId, @PathParam("platosId") Long platosId)throws BusinessLogicException
    {
        PlatoEntity plato = logic.findPlato(restaurantesId, platosId);
        if(plato == null)
        {
            throw new WebApplicationException(RECURSO + restaurantesId + PLATOS + platosId + NO_EXISTE, 404);

        }
        logic.deletePlato(restaurantesId, platosId);
    }
          
    private List<PlatoDTO> listEntity2DTO(List<PlatoEntity> entityList)
    {
        List<PlatoDTO> list = new ArrayList<>();
        for(PlatoEntity entity: entityList)
        {
            list.add(new PlatoDTO(entity));
        }
        return list;
    }
    
    
    
    
    
    
}
