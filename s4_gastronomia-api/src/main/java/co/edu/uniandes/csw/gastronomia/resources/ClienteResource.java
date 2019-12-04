/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.resources;

import co.edu.uniandes.csw.gastronomia.dtos.ClienteDTO;
import co.edu.uniandes.csw.gastronomia.dtos.ClienteDetailDTO;
import co.edu.uniandes.csw.gastronomia.ejb.ClienteLogic;
import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
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
@Path("clientes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ClienteResource {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteResource.class.getName());
    private static final String RECURSO = "El recurso /clientes/";
    private static final String NO_EXISTE = " no existe.";
    
    @Inject
    private ClienteLogic clienteLogic;
    
    @POST
    public ClienteDTO createCliente(ClienteDTO cliente) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteResource createCliente: input: {0}", cliente);
        ClienteDTO nuevoClienteDTO = new ClienteDTO(clienteLogic.createCliente(cliente.toEntity()));
        LOGGER.log(Level.INFO, "ClienteResource createCliente: output: {0}", nuevoClienteDTO);
        return nuevoClienteDTO;
    }
    
    @GET
    public List<ClienteDetailDTO> getClientes() {
        LOGGER.info("ClienteResource getClientes: input: void");
        List<ClienteDetailDTO> listaClientes = listEntity2DetailDTO(clienteLogic.getClientes());
        LOGGER.log(Level.INFO, "ClienteResource getClientes: output: {0}", listaClientes);
        return listaClientes;
    }
    
    /**
     * Busca el cliente con el id asociado recibido en la URL y lo devuelve.
     *
     * @param clientesId Identificador del cliente que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link ClienteDetailDTO} - El cliente buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @GET
    @Path("{clienteId: \\d+}")
    public ClienteDetailDTO getCliente(@PathParam("clienteId") Long clienteId) {
        LOGGER.log(Level.INFO, "ClienteResource getCliente: input: {0}", clienteId);
        ClienteEntity clienteEntity = clienteLogic.getCliente(clienteId);
        if (clienteEntity == null) {
            throw new WebApplicationException(RECURSO + clienteId + NO_EXISTE, 404);
        }
        ClienteDetailDTO clienteDetailDTO = new ClienteDetailDTO(clienteEntity);
        LOGGER.log(Level.INFO, "ClienteResource getCliente: output: {0}", clienteDetailDTO);
        return clienteDetailDTO;
    }
    

    /**
     * Busca el cliente con el id asociado recibido en la URL y lo devuelve.
     *
     * @param clientesId Identificador del cliente que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link ClienteDetailDTO} - El cliente buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @GET
    @Path("{clienteUsername: \\S+}")
    public ClienteDetailDTO getCliente(@PathParam("clienteUsername") String clienteUsername) {
        LOGGER.log(Level.INFO, "ClienteResource getCliente: input: {0}", clienteUsername);
        ClienteEntity clienteEntity = clienteLogic.getCliente(clienteUsername);
        if (clienteEntity == null) {
            throw new WebApplicationException(RECURSO + clienteUsername + NO_EXISTE, 404);
        }
        ClienteDetailDTO clienteDetailDTO = new ClienteDetailDTO(clienteEntity);
        LOGGER.log(Level.INFO, "ClienteResource getCliente: output: {0}", clienteDetailDTO);
        return clienteDetailDTO;
    }
    
    @PUT
    @Path("{clienteId: \\d+}")
    public ClienteDTO updateCliente(@PathParam("clienteId") Long clienteId, ClienteDTO cliente) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteResource updateCliente: input: id: {0} , cliente: {1}", new Object[]{clienteId, cliente});
        cliente.setId(clienteId);
        if (clienteLogic.getCliente(clienteId) == null) {
            throw new WebApplicationException(RECURSO + clienteId + NO_EXISTE, 404);
        }
        ClienteDTO clienteDTO = new ClienteDTO(clienteLogic.updateCliente(clienteId, cliente.toEntity()));
        LOGGER.log(Level.INFO, "ClienteResource updateCliente: output: {0}", clienteDTO);
        return clienteDTO;
    }
    
    @DELETE
    @Path("{clienteId: \\d+}")
    public void deleteCliente(@PathParam("clienteId") Long clienteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteResource deleteCliente: input: {0}", clienteId);
        ClienteEntity entity = clienteLogic.getCliente(clienteId);
        if (entity == null) {
            throw new WebApplicationException(RECURSO + clienteId + NO_EXISTE, 404);
        }
        clienteLogic.deleteCliente(clienteId);
        LOGGER.info("ClienteResource deleteCliente: output: void");
    }
    
    @Path("{clientesId: \\d+}/tarjetas")
    public Class<TarjetaDeCreditoResource> getTarjetaResource(@PathParam("clientesId") Long id) throws BusinessLogicException
    {
        if (clienteLogic.getCliente(id) == null) {
            throw new WebApplicationException(RECURSO + id + "/tarjetas" + NO_EXISTE, 404);
        }
        return TarjetaDeCreditoResource.class;
    }
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ClienteEntity a una lista de
     * objetos ClienteDetailDTO (json)
     *
     * @param entityList corresponde a la lista de clientes de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de clientes en forma DTO (json)
     */
    private List<ClienteDetailDTO> listEntity2DetailDTO(List<ClienteEntity> entityList) {
        List<ClienteDetailDTO> list = new ArrayList<>();
        for (ClienteEntity entity : entityList) {
            list.add(new ClienteDetailDTO(entity));
        }
        return list;
    }
    
    
    
    
}
