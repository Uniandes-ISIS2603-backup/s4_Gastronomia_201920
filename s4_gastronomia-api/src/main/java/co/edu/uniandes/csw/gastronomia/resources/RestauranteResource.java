/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.resources;

import co.edu.uniandes.csw.gastronomia.dtos.RestauranteDTO;
import co.edu.uniandes.csw.gastronomia.dtos.RestauranteDetailDTO;
import co.edu.uniandes.csw.gastronomia.ejb.RestauranteLogic;
import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;


/**
 *
 * @author Estudiante
 */
@Path("restaurantes")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped
public class RestauranteResource
{
    private static final Logger LOGGER = Logger.getLogger(RestauranteResource.class.getName());
    
    private static final String RECURSO="El recurso /restaurantes/";
    @Inject
    private RestauranteLogic logic;
    
    @POST
    public RestauranteDTO createRestaurante(RestauranteDTO r) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "RestauranteResource createRestaurante: input: {0}", r);
        RestauranteDTO rDTO= new RestauranteDTO(logic.createRestaurante(r.toEntity()));     
        LOGGER.log(Level.INFO, "RestauranteResource createRestaurante: output: {0}", rDTO);
        return rDTO;
    }
    //--------------------------------------------------------------------------
    //Delte method
    //--------------------------------------------------------------------------
    @DELETE
    @Path("{restaurantesId: \\d+}")
    public void deleteRestaurante(@PathParam("restaurantesId") Long restaurantesId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "RestauranteResource deleteRestaurante: input: {0}", restaurantesId);
        logic.deleteRestaurante(restaurantesId);
        LOGGER.log(Level.INFO, "RestauranteResource createRestaurante: output: void");
    }
    //--------------------------------------------------------------------------
    //Get methods
    //--------------------------------------------------------------------------
    @GET
    @Path("{restaurantesId: \\d+}")
    public RestauranteDetailDTO getRestaurante(@PathParam("restaurantesId") Long id) throws BusinessLogicException
    {
        RestauranteEntity r = logic.getRestaurante(id);
        if(r==null)
        {
           throw new WebApplicationException(RECURSO + id + " no existe.", 404); 
        }
        RestauranteDetailDTO rr = new RestauranteDetailDTO(r);
        return rr;
    }
    
    @GET
    public List<RestauranteDetailDTO> getRestaurantes() throws BusinessLogicException
    {
        List<RestauranteDetailDTO> r = entity2DTO(logic.getRestaurantes());
        return r;
    }
    

    
    //--------------------------------------------------------------------------
    //Put method
    //--------------------------------------------------------------------------
    @PUT
    @Path("{restaurantesId: \\d+}")
    public RestauranteDetailDTO updateRestaurante(@PathParam("restaurantesId") Long id, RestauranteDetailDTO r) throws BusinessLogicException
    {
        r.setId(id);
        if(logic.getRestaurante(id)==null)
        {
            throw new WebApplicationException(RECURSO + id + " no existe.", 404);
        }
        RestauranteDetailDTO rr = new RestauranteDetailDTO(logic.updateRestaurante(r.toEntity(), id));
        return rr;
    }
     @Path("{restaurantesId: \\d+}/platos")
    public Class<PlatoResource> getReviewResource(@PathParam("restaurantesId") Long id) throws BusinessLogicException
    {
        if (logic.getRestaurante(id) == null) {
            throw new WebApplicationException(RECURSO + id + "/platos no existe.", 404);
        }
        return PlatoResource.class;
    }
    //--------------------------------------------------------------------------
    //private methods
    //--------------------------------------------------------------------------
    private List<RestauranteDetailDTO> entity2DTO(List<RestauranteEntity> l)
    {
        List<RestauranteDetailDTO> ll = new ArrayList<>();
        for(RestauranteEntity x : l)
        {
            ll.add(new RestauranteDetailDTO(x));
        }
        return ll;
    }
}
