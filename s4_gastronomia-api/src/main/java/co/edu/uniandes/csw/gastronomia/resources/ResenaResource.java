/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.resources;

import co.edu.uniandes.csw.gastronomia.dtos.ResenaDTO;
import co.edu.uniandes.csw.gastronomia.dtos.ResenaDetailDTO;
import co.edu.uniandes.csw.gastronomia.ejb.ResenaLogic;
import co.edu.uniandes.csw.gastronomia.entities.ResenaEntity;
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
@Path("resena")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped
public class ResenaResource {
     private static final Logger LOGGER = Logger.getLogger(ResenaResource.class.getName());
     
     @Inject
     private ResenaLogic logic;
     /**
      * crea un nuevo recurso de resena 
      * @param re
      * @return
      * @throws BusinessLogicException 
      */
      @POST
     public ResenaDTO createResena(ResenaDTO re) throws BusinessLogicException
     {
         LOGGER.log(Level.INFO, "ResenaResource createFoogBlog: input: {0}", re);
        ResenaDTO reDTO= new ResenaDTO(logic.createResena(re.toEntity()));     
        LOGGER.log(Level.INFO, "ResenaResource createResena: output: {0}", reDTO);
        return reDTO;
     }
     /**
      * 
      * @param resenasId
      * @throws BusinessLogicException 
      */
      @DELETE
    @Path("{resenasId: \\d+}")
    public void deleteResena(@PathParam("resenaId") Long resenaId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ResenaResource deleteResena: input: {0}", resenaId);
        logic.deleteResena(resenaId);
        LOGGER.log(Level.INFO, "ResenaResource createResena: output: void");
    }
    /**
     * Retorna un recurso de reseña dado un id que se le pase.
     * @param id
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{resenasId: \\d+}")
    public ResenaDetailDTO getResena(@PathParam("restaurantesId") Long id) throws BusinessLogicException
    {
        ResenaEntity re = logic.getResena(id);
        if(re==null)
        {
           throw new WebApplicationException("El food blog con id/" + id + " no existe.", 404); 
        }
        ResenaDetailDTO reN = new ResenaDetailDTO(re);
        return reN;
    }
    /**
     * Devueleve una lista con toda las reseñas
     * @return
     * @throws BusinessLogicException 
     */
     @GET
    public List<ResenaDetailDTO> getResenas() throws BusinessLogicException
    {
        List<ResenaDetailDTO> res = entity2DTO(logic.getResenas());
        return res;
    }
    /**
     * Update de una reseña con el id pasado por parametro y con una nueva reseña
     * @param id reseña actual
     * @param re informacion que se va a updatear.
     * @return
     * @throws BusinessLogicException 
     */
     @PUT
    @Path("{resenasId: \\d+}")
    public ResenaDetailDTO updateResena(@PathParam("resenasId") Long id, ResenaDetailDTO re) throws BusinessLogicException
    {
        re.setId(id);
        if(logic.getResena(id)==null)
        {
            throw new WebApplicationException("El resena con el id:" + id + " no existe.", 404);
        }
        ResenaDetailDTO reD = new ResenaDetailDTO(logic.updateResena(id,re.toEntity()));
        return reD;
    }
    
    /**
     * Cambia una entridad de reseña a un dto.
     * @param l
     * @return 
     */
     private List<ResenaDetailDTO> entity2DTO(List<ResenaEntity> l)
    {
        List<ResenaDetailDTO> ll = new ArrayList<>();
        for(ResenaEntity x : l)
        {
            ll.add(new ResenaDetailDTO(x));
        }
        return ll;
    }
}
