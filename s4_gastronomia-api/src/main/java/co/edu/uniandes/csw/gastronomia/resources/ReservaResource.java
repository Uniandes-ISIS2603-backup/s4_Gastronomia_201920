/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.resources;

import co.edu.uniandes.csw.gastronomia.dtos.ReservaDTO;
import co.edu.uniandes.csw.gastronomia.ejb.ReservaLogic;
import co.edu.uniandes.csw.gastronomia.entities.ReservaEntity;
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
 * @author Cristina Isabel González Osorio
 */
@Path("reservas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ReservaResource {
    
    private static final Logger LOGGER = Logger.getLogger(ReservaResource.class.getName());
    
    @Inject
    private ReservaLogic reservaLogic;
     
    @POST
    public ReservaDTO createReserva(ReservaDTO reserva) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ReservaResource createReserva: input: {0}", reserva);
        ReservaDTO nuevaReservaDTO = new ReservaDTO(reservaLogic.createReserva(reserva.toEntity()));
        LOGGER.log(Level.INFO, "ReservaResource createReserva: output: {0}", nuevaReservaDTO);
        return nuevaReservaDTO;
    }
    
    @GET
    public List<ReservaDTO> getReservas() {
        LOGGER.info("ReservaResource getReservas: input: void");
        List<ReservaDTO> listaReservas = new ArrayList<ReservaDTO>();
        LOGGER.log(Level.INFO, "ReservaResource getReservas: output: {0}", listaReservas);
        return listaReservas;
    }
    
    @GET
    @Path("{reservaId: \\d+}")
    public ReservaDTO getReserva(@PathParam("reservaId") Long reservaId) {
        LOGGER.log(Level.INFO, "ReservaResource getReserva: input: {0}", reservaId);
        ReservaEntity reservaEntity = reservaLogic.getReserva(reservaId);
        if (reservaEntity == null) {
            throw new WebApplicationException("El recurso /reservas/" + reservaId + " no existe.", 404);
        }
        ReservaDTO reservaDetailDTO = new ReservaDTO(reservaEntity);
        LOGGER.log(Level.INFO, "ReservaResource getReserva: output: {0}", reservaDetailDTO);
        return reservaDetailDTO;
    }
    
    @PUT
    @Path("{reservaId: \\d+}")
    public ReservaDTO updateReserva(@PathParam("reservaId") Long reservaId, ReservaDTO reserva) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ReservaResource updateReserva: input: id: {0} , reserva: {1}", new Object[]{reservaId, reserva});
        //reserva.setId(reservaId);
        if (reservaLogic.getReserva(reservaId) == null) {
            throw new WebApplicationException("El recurso /reservas/" + reservaId + " no existe.", 404);
        }
        ReservaDTO reservaDTO = new ReservaDTO(reservaLogic.updateReserva(reservaId, reserva.toEntity()));
        LOGGER.log(Level.INFO, "ReservaResource updateReserva: output: {0}", reservaDTO);
        return reservaDTO;
    }
    
    @DELETE
    @Path("{reservaId: \\d+}")
    public void deleteReserva(@PathParam("reservaId") Long reservaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ReservaResource deleteReserva: input: {0}", reservaId);
        ReservaEntity entity = reservaLogic.getReserva(reservaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /reservas/" + reservaId + " no existe.", 404);
        }
        reservaLogic.deleteReserva(reservaId);
        LOGGER.info("ReservaResource deleteReserva: output: void");
    }
    
}
