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
           throw new WebApplicationException("El recurso /restaurantes/" + id + " no existe.", 404); 
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
    
//    @GET
//    public List<RestauranteDetailDTO> getRestaurantes2(@QueryParam("contrasena") String contrasena, @QueryParam("nombre") String nombre, @QueryParam("precioPorPersonaMax") Double precioPorPersonaMax,@QueryParam("precioPorPersonaMin") Double precioPorPersonaMin,@QueryParam("precioPorReservaMax") Double precioPorReservaMax,@QueryParam("precioPorReservaMin") Double precioPorReservaMin, @QueryParam("cumpleanos") Boolean cumpleanos,@QueryParam("petFriendly") Boolean pet,@QueryParam("musicaEnVivo") Boolean musica, @QueryParam("servicioALaMesa") Boolean sam, @QueryParam("zonaFumadores") Boolean fumadores,@QueryParam("direccion") String dir) throws BusinessLogicException
//    {
//        List<RestauranteDetailDTO> t = entity2DTO(logic.getRestaurantes());
//        if(nombre!=null && contrasena!=null)
//        {
//            t.clear();
//            t.add(new RestauranteDetailDTO(logic.getRestauranteContrasenaNombre(nombre, contrasena)));
//        }
//        else
//        {
//            List<RestauranteDetailDTO> x = new ArrayList<RestauranteDetailDTO>();
//            if(nombre!=null)
//            {
//                x=entity2DTO(logic.getRestauranteNombre(nombre));
//                for(RestauranteDetailDTO r : t)
//                {
//                    if(!x.contains(r))
//                    {
//                        t.remove(r);
//                    }
//                }
//            }
//            if(precioPorPersonaMax!=null && precioPorPersonaMin!=null)
//            {
//                x=entity2DTO(logic.getRestaurantesPrecioPromedioRango(precioPorReservaMax, precioPorReservaMin));
//                for(RestauranteDetailDTO r : t)
//                {
//                    if(!x.contains(r))
//                    {
//                        t.remove(r);
//                    }
//                }
//            }
//            if(precioPorReservaMax!=null && precioPorReservaMin!=null)
//            {
//                x=entity2DTO(logic.getRestaurantesPrecioReservaRango(precioPorReservaMax, precioPorReservaMin));
//                for(RestauranteDetailDTO r : t)
//                {
//                    if(!x.contains(r))
//                    {
//                        t.remove(r);
//                    }
//                }
//            }
//            if(cumpleanos!=null)
//            {
//                x=entity2DTO(logic.getRestaurantesDescuentoCumpleanos(new Boolean(cumpleanos)));
//                for(RestauranteDetailDTO r : t)
//                {
//                    if(!x.contains(r))
//                    {
//                        t.remove(r);
//                    }
//                }
//            }
//            if(pet!=null)
//            {
//                x=entity2DTO(logic.getRestaurantesPetFriendly(new Boolean(pet)));
//                for(RestauranteDetailDTO r : t)
//                {
//                    if(!x.contains(r))
//                    {
//                        t.remove(r);
//                    }
//                }
//            }
//            if(musica!=null)
//            {
//                x=entity2DTO(logic.getRestaurantesMusicaEnVivo(new Boolean(musica)));
//                for(RestauranteDetailDTO r : t)
//                {
//                    if(!x.contains(r))
//                    {
//                        t.remove(r);
//                    }
//                }
//            }
//            if(sam!=null)
//            {
//                x=entity2DTO(logic.getRestaurantesServicioALaMesa(new Boolean(sam)));
//                for(RestauranteDetailDTO r : t)
//                {
//                    if(!x.contains(r))
//                    {
//                        t.remove(r);
//                    }
//                }
//            }
////            if(fumadores!=null)
////            {
////                x=entity2DTO(logic.getRestaurante(fumadores));
////                for(RestauranteDetailDTO r : t)
////                {
////                    if(!x.contains(r))
////                    {
////                        t.remove(r);
////                    }
////                }
////            }
//            if(dir!=null)
//            {
//                x=entity2DTO(logic.getRestaurantesDireccion(dir));
//                for(RestauranteDetailDTO r : t)
//                {
//                    if(!x.contains(r))
//                    {
//                        t.remove(r);
//                    }
//                }
//            }
//        }
//        return t;
//    }
    
    
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
            throw new WebApplicationException("El recurso /restaurantes/" + id + " no existe.", 404);
        }
        RestauranteDetailDTO rr = new RestauranteDetailDTO(logic.updateRestaurante(r.toEntity(), id));
        return rr;
    }
     @Path("{restaurantesId: \\d+}/platos")
    public Class<PlatoResource> getReviewResource(@PathParam("restaurantesId") Long id) throws BusinessLogicException
    {
        if (logic.getRestaurante(id) == null) {
            throw new WebApplicationException("El recurso /restaurantes/" + id + "/platos no existe.", 404);
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
