/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.resources;

import co.edu.uniandes.csw.gastronomia.dtos.FoodBlogDTO;
import co.edu.uniandes.csw.gastronomia.dtos.FoodBlogDTO;
import co.edu.uniandes.csw.gastronomia.ejb.FoodBlogLogic;
import co.edu.uniandes.csw.gastronomia.entities.FoodBlogEntity;
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
 * @author d.martinezg2
 */
@Path("foodBlogs")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped
public class FoodBlogResource
{
     private static final Logger LOGGER = Logger.getLogger(FoodBlogResource.class.getName());
     
     @Inject
     private FoodBlogLogic logic;
     
     /**
      * Crea un recurso de  Foodblog. 
      * @param fb el recurso que se va a crear.
      * @return
      * @throws BusinessLogicException 
      */
     @POST
     public FoodBlogDTO createFoogBlog(FoodBlogDTO fb) throws BusinessLogicException
     {
         LOGGER.log(Level.INFO, "FoodBlogResource createFoogBlog: input: {0}", fb);
         
        FoodBlogDTO fbDTO= new FoodBlogDTO(logic.createFoodBlog(fb.toEntity()));     
        
        LOGGER.log(Level.INFO, "FoodBlogResource createFoodBlog: output: {0}", fbDTO);
        return fbDTO;
     }
     /**
      * borra un recurso de foodblog dado el id de busqueda.
      * @param foodBlogsId
      * @throws BusinessLogicException 
      */
      @DELETE
    @Path("{foodBlogsId: \\d+}")
    public void deleteFoodBlog(@PathParam("foodBlogsId") Long foodBlogsId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "FoodBlogResource deleteFoodBlog: input: {0}", foodBlogsId);
        if (logic.getFoodBlog(foodBlogsId)==null) 
        {
            throw new WebApplicationException("El recurso de foodblog no existe, id de la peticion:"+foodBlogsId,404);
        }
        logic.deleteFoodBlog(foodBlogsId);
        LOGGER.log(Level.INFO, "FoodBlogResource createFoodBlog: output: void");
    }
    //--------------------------------------------------------------------------
    //Get methods
    //--------------------------------------------------------------------------
    /**
     * obtien un recurso de fooblog dado un id.
     * @param id
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{foodBlogsId: \\d+}")
    public FoodBlogDTO getFoodBlog(@PathParam("foodBlogsId") Long id) throws BusinessLogicException
    {
        FoodBlogEntity fb = logic.getFoodBlog(id);
        if(fb==null)
        {
           throw new WebApplicationException("El food blog con id/" + id + " no existe.", 404); 
        }
        FoodBlogDTO fbN = new FoodBlogDTO(fb);
        return fbN;
    }
    /**
     * Retornar los foodblogs
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    public List<FoodBlogDTO> getFoodBlogs() throws BusinessLogicException
    {
        List<FoodBlogDTO> fbs = entity2DTO(logic.getFoodBlogs());
        return fbs;
    }
    
    
    //--------------------------------------------------------------------------
    //Put method
    //--------------------------------------------------------------------------
    /**
     * Update de un recucurso foodblog dado.
     * @param id
     * @param fb
     * @return
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{foodBlogsId: \\d+}")
    public FoodBlogDTO updateFoodBlog(@PathParam("foodBlogsId") Long id, FoodBlogDTO fb) throws BusinessLogicException
    {
        fb.setId(id);   
        if(logic.getFoodBlog(id)==null)
        {
            throw new WebApplicationException("El foodblog con el id:" + id + " no existe.", 404);
        }
        FoodBlogDTO fBD = new FoodBlogDTO(logic.updateFoodBlog(id,fb.toEntity()));
        return fBD;
    }
    //--------------------------------------------------------------------------
    //private methods
    //--------------------------------------------------------------------------
    /**
     * Concvierte una entidad de foodblog a un DTO.
     * @param l
     * @return 
     */
    private List<FoodBlogDTO> entity2DTO(List<FoodBlogEntity> l)
    {
        List<FoodBlogDTO> ll = new ArrayList<>();
        for(FoodBlogEntity x : l)
        {
            ll.add(new FoodBlogDTO(x));
        }
        return ll;
    }
}
