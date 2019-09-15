/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.persistence;

import co.edu.uniandes.csw.gastronomia.entities.FacturaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author af.benitez
 */
@Stateless
public class FacturaPersistence 
{
    @PersistenceContext(unitName = "gastronomiaPU")
    
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param facturaEntity objeto facturas que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public FacturaEntity create(FacturaEntity facturaEntity) 
    {
       
        em.persist(facturaEntity);
        
        return facturaEntity;
    }
    
    /**
     * Devuelve todas las facturas de la base de datos.
     *
     * @return una lista con todos las facturas  que encuentre en la base de datos,
     * "select u from FacturaEntity u" es como un "select * from FacturaEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<FacturaEntity> findAll()
    {
        Query q = em.createQuery("select u from FacturaEntity u");
        return q.getResultList();
    }
    
      /**
     * Busca si hay alguna factura con el id que se envía de argumento
     *
     * @param facturaId: id correspondiente a la factura buscado.
     * @return un facturaId.
     */
    public FacturaEntity find(Long facturaId) 
    {
        return em.find(FacturaEntity.class, facturaId);
    }
    
    /**
     * Actualiza una factura.
     *
     * @param facturaEntity: la factura que viene con los nuevos cambios. Por ejemplo
     * el valor pudo cambiar. En ese caso, se haria uso del método update.
     * @return un resenaEntity con los cambios aplicados.
     */
    public FacturaEntity update(FacturaEntity facturaEntity) 
    {
        return em.merge(facturaEntity);
    }

    /**
     *
     * Borra una factura de la base de datos recibiendo como argumento el id
     *
     * @param facturaId: id correspondiente al libro a borrar.
     */
    public void delete(Long facturaId)
    {
        FacturaEntity facturaEntity = em.find(FacturaEntity.class, facturaId);
        em.remove(facturaEntity);
    }
}
