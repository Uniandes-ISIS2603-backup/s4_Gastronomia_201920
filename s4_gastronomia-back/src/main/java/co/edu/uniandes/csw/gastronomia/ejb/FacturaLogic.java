/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;

import co.edu.uniandes.csw.gastronomia.entities.FacturaEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.FacturaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author af.benitez
 */
@Stateless
public class FacturaLogic 
{
    @Inject
    private FacturaPersistence persistence;

    private static final Logger LOGGER = Logger.getLogger(FacturaLogic.class.getName());

    /**
     * Guardar un nuevo libro
     *
     * @param FacturaEntity La entidad de tipo factura a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el valor == 0 es inválido o ya existe en la
     * persistencia.
     */
    public FacturaEntity createFactura(FacturaEntity facturaEntity) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la factura");
        
        
        if (persistence.find(facturaEntity.getId()) != null) 
        {
            throw new BusinessLogicException("La factura ya existe");
        }
        if(facturaEntity.getValor() < 0)
        {
             throw new BusinessLogicException("El valor de la factura es menor a 0");
        }
        
        if(facturaEntity.getValorCompleto() < 0)
        {
            throw new BusinessLogicException("El valor completo de la factura es menor a 0");
        }
        if(facturaEntity.getValor() > facturaEntity.getValorCompleto())
        {
            throw new BusinessLogicException("El valor completo es menor al valor");
        }
 
        persistence.create(facturaEntity);
        
        LOGGER.log(Level.INFO, "Termina proceso de creación de la factura");

        return facturaEntity;
    }

    /**
     * Devuelve todos las facturas que hay en la base de datos.
     *
     * @return Lista de entidades de tipo factura.
     */
    public List<FacturaEntity> getFacturas() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos las facturas");
        List<FacturaEntity> factura = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos las facturas");
        return factura;
    }

    /**
     * Busca un factura por ID
     *
     * @param facturaId El id de la factura a buscar
     * @return La factura encontrada, null si no lo encuentra.
     */
    public FacturaEntity getFactura(Long facturaId) {
       
        FacturaEntity fEntity = persistence.find(facturaId);
        if (fEntity == null) 
        {
            LOGGER.log(Level.SEVERE, "La factura con el id = {0} no existe", facturaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultarla factura con id = {0}", facturaId);
        return fEntity;
    }

    /**
     * Actualizar una factura por ID
     *
     * @param facturaId El ID de la factura a actualizar
     * @param facturaEntity La entidad de factura con los cambios deseados
     * @return La entidad de factura luego de actualizarla
     * @throws BusinessLogicException Si el id de la actualización es inválido
     */
    public FacturaEntity updateFactura(Long facturaId, FacturaEntity facturaEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la factura con id = {0}", facturaId);
        if (persistence.find(facturaEntity.getId()) == null)
        {
            throw new BusinessLogicException("El id es inválido");
        }
        FacturaEntity newEntity = persistence.update(facturaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la factura con id = {0}", facturaEntity.getId());
        return newEntity;
    }

    /**
     * Eliminar una factura por ID
     *
     * @param facturaId El ID de la factura a eliminar
     * @throws BusinessLogicException si la factura no existe
     */
    public void deleteFactura(Long facturaId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la factura con id = {0}", facturaId);
        
        if (persistence.find(facturaId) == null)
        {
            throw new BusinessLogicException("No se puede borrar la factura con id = " + facturaId + " porque no existe");
        }
        persistence.delete(facturaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la factura con id = {0}", facturaId);
    }
    
}
