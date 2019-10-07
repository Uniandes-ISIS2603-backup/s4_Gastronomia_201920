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
    @Inject 
    private PlatoLogic logic;
    
    @POST
    public PlatoDTO createPlato(@PathParam("restaurantesId") Long restauranteId, PlatoDTO plato)throws BusinessLogicException
    {
        PlatoDTO platoDTO = new PlatoDTO(logic.createPlato(restauranteId, plato.toEntity()));
        return platoDTO;
    }
    
    @GET
    @Path("{platosId: \\d+}")
    public PlatoDTO getPlato(@PathParam("restaurantesId") Long restaurantesId, @PathParam("platosId") Long platosId) throws BusinessLogicException
    {
        PlatoEntity plato = logic.findPlato(restaurantesId, platosId);
        if(plato == null)
        {
            throw new WebApplicationException("El recurso /restaurantes/" + restaurantesId + "/platos/" + platosId + " no existe.", 404);
        }
        PlatoDTO platoDTO = new PlatoDTO(plato);
        return platoDTO;
    }
    @PUT
    @Path("{platosId: \\d+}")
    public PlatoDTO updatePlato(@PathParam("restaurantesId") Long restaurantesId, @PathParam("platosId") Long platosId, PlatoDTO plato )throws BusinessLogicException
    {
        if(platosId.equals(plato.getId()))
        {
            throw new BusinessLogicException("Los ids del plato no coinciden");
        }
        PlatoEntity entity = logic.findPlato(restaurantesId, platosId);
        if(entity == null)
        {
            throw new WebApplicationException("El recurso /restaurantes/" + restaurantesId + "/platos/" + platosId + " no existe.", 404);
        }
        PlatoDTO platoDTO = new PlatoDTO(logic.updatePlato(restaurantesId, plato.toEntity()));
        return platoDTO;
    }
    
    @DELETE
    @Path("{platosId: \\d+}")
    public void deletePlato(@PathParam("restaurantesId") Long restaurantesId, @PathParam("platosId") Long platosId)throws BusinessLogicException
    {
        PlatoEntity plato = logic.findPlato(restaurantesId, platosId);
        if(plato == null)
        {
            throw new WebApplicationException("El recurso /restaurantes/" + restaurantesId + "/platos/" + platosId + " no existe.", 404);
        }
        logic.deletePlato(restaurantesId, platosId);
    }
          
    
    
    
    
    
    
}
