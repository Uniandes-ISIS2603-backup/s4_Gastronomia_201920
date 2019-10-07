/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.resources;

import co.edu.uniandes.csw.gastronomia.dtos.AdministradorDTO;
import co.edu.uniandes.csw.gastronomia.dtos.AdministradorDetailDTO;
import co.edu.uniandes.csw.gastronomia.ejb.AdministradorLogic;
import co.edu.uniandes.csw.gastronomia.entities.AdministradorEntity;
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
 * @author Angela Maria Suarez
 */
@Path("administrador")
@Consumes("application/json")
@Produces("application/json")
@RequestScoped
public class AdministradorResource
{
   private static final Logger LOGGER = Logger.getLogger(AdministradorResource.class.getName());
   
   @Inject
   private AdministradorLogic administradorLogic;
   
   //--------------------------------------------------------------------------
    //Create method
    //--------------------------------------------------------------------------
   @POST
    public AdministradorDTO createAdministrador(AdministradorDTO admin) throws BusinessLogicException
    {
       LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: input: {0}", admin);
       AdministradorDTO rDTO= new AdministradorDTO(administradorLogic.createAdministrador(admin.toEntity()));     
        LOGGER.log(Level.INFO, "AdministradorResource createAdmin: output: {0}", rDTO);
        return rDTO;
    }
    
    //--------------------------------------------------------------------------
    //Delte method
    //--------------------------------------------------------------------------
    @DELETE
    @Path("{administradorId: \\d+}")
    public void deleteAdministrador(@PathParam("administradorId") Long administradorId) throws BusinessLogicException
    {
       LOGGER.log(Level.INFO, "AdministradorResource delete: input: {0}", administradorId);
       administradorLogic.deleteAdministrador(administradorId);
        LOGGER.log(Level.INFO, "AdministradorResource deleteAdmin: output: void");
    }
    
         //--------------------------------------------------------------------------
    //Get methods
    //--------------------------------------------------------------------------
    @GET
    @Path("{administradorId: \\d+}")
    public AdministradorDetailDTO getAdministrador(@PathParam("administradorId") Long id) throws BusinessLogicException
    {
     
     AdministradorEntity r = administradorLogic.getAdministrador(id);
        if(r==null)
        {
           throw new WebApplicationException("El recurso /administrador/" + id + " no existe.", 404); 
        }
        AdministradorDetailDTO admin2 = new AdministradorDetailDTO(r);
        return admin2;
    }
    
    @GET
    public List<AdministradorDetailDTO> getAdministradores() throws BusinessLogicException
    {
      List<AdministradorDetailDTO> admin = entity2DTO(administradorLogic.getAdministradores()  );
        return admin;
    }
    
    
    //--------------------------------------------------------------------------
    //Put method
    //--------------------------------------------------------------------------
    @PUT
    @Path("{administradorId: \\d+}")
    public AdministradorDetailDTO updateAdministrador(@PathParam("administradorId") Long id, AdministradorDetailDTO admin) throws BusinessLogicException
    {
      admin.setId(id);
      if( administradorLogic.getAdministrador(id) == null)
      {
          throw new WebApplicationException("El recurso /administrador/" + id + " no existe.", 404);
      }
      AdministradorDetailDTO nuevoADMIN = new AdministradorDetailDTO(administradorLogic.updateAdministrador(id, admin.toEntity()));
      return nuevoADMIN;
    }
    //--------------------------------------------------------------------------
    //private methods
    //--------------------------------------------------------------------------
    private List<AdministradorDetailDTO> entity2DTO(List<AdministradorEntity> lista)
    {
        List<AdministradorDetailDTO> listica = new ArrayList<>();
        for(AdministradorEntity x : lista)
        {
            listica.add(new AdministradorDetailDTO(x));
        }
        return listica;
    }
}
