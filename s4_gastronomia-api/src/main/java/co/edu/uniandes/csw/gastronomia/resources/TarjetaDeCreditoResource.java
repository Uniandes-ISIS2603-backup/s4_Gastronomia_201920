/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.resources;


import co.edu.uniandes.csw.gastronomia.dtos.TarjetaDeCreditoDTO;
import co.edu.uniandes.csw.gastronomia.ejb.TarjetaDeCreditoLogic;
import co.edu.uniandes.csw.gastronomia.entities.TarjetaDeCreditoEntity;
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
public class TarjetaDeCreditoResource {
    @Inject 
    private TarjetaDeCreditoLogic logic;
    
    private static final String NE = " no existe.";
    
    @POST
    public TarjetaDeCreditoDTO createTarjetaDeCredito(@PathParam("clientesId") Long clienteId, TarjetaDeCreditoDTO tarjeta)throws BusinessLogicException
    {
        TarjetaDeCreditoDTO platoDTO = new TarjetaDeCreditoDTO(logic.createTarjetaDeCredito(clienteId, tarjeta.toEntity()));
        return platoDTO;
    }
    
    @GET
    @Path("{tarjetasId: \\d+}")
    public TarjetaDeCreditoDTO getTarjetaDeCredito(@PathParam("clientesId") Long clienteId,  @PathParam("tarjetasId") Long tarjetasId) throws BusinessLogicException
    {
        TarjetaDeCreditoEntity tarjeta = logic.findTarjetaDeCredito(clienteId, tarjetasId);
        if(tarjeta == null)
        {
            throw new WebApplicationException("El recurso  /tarjetas/" + tarjetasId + NE, 404);
        }
        return new TarjetaDeCreditoDTO(tarjeta);
    }
    @GET
    public List<TarjetaDeCreditoDTO> getTarjetas(@PathParam("clientesId") Long clienteId)
    {
        return listEntity2DTO(logic.getTarjetas(clienteId));
    }
    
        @DELETE
    @Path("{tarjetasId: \\d+}")
    public void deleteTarjetaDeCredito(@PathParam("clientesId") Long clienteId, @PathParam("tarjetasId") Long tarjetasId)throws BusinessLogicException
    {
        TarjetaDeCreditoEntity tarjeta = logic.findTarjetaDeCredito(clienteId ,tarjetasId);
        if(tarjeta == null)
        {
            throw new WebApplicationException("El recurso  /tarjetas/" + tarjetasId + NE, 404);
        }
        logic.deleteTarjetaDeCredito(clienteId, tarjetasId);
    }
    @PUT
    @Path("{tarjetasId: \\d+}")
    public TarjetaDeCreditoDTO updateTarjetaDeCredito(@PathParam("clientesId") Long clienteId, @PathParam("tarjetasId") Long tarjetasId, TarjetaDeCreditoDTO tarjeta )throws BusinessLogicException
    {
        tarjeta.setId(tarjetasId);
        if(!tarjetasId.equals(tarjeta.getId()))
        {
            throw new BusinessLogicException("Los ids del plato no coinciden");
        }
        TarjetaDeCreditoEntity entity = logic.findTarjetaDeCredito(clienteId, tarjetasId);
        if(entity == null)
        {
            throw new WebApplicationException("El recurso /tarjetas/" + tarjetasId + NE, 404);
        }
        return new TarjetaDeCreditoDTO(logic.updatetarjetaDeCredito(clienteId, tarjeta.toEntity()));
    }
      
  private List<TarjetaDeCreditoDTO> listEntity2DTO(List<TarjetaDeCreditoEntity> entityList)
    {
        List<TarjetaDeCreditoDTO> list = new ArrayList<>();
        for(TarjetaDeCreditoEntity entity: entityList)
        {
            list.add(new TarjetaDeCreditoDTO(entity));
        }
        return list;
    }    
    
    
    
    
    
    
}
