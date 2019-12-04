/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.persistence;

import co.edu.uniandes.csw.gastronomia.entities.TarjetaDeCreditoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Estudiante
 */
@Stateless
public class TarjetaDeCreditoPersistence {
   
    
    @PersistenceContext(unitName = "gastronomiaPU")
    protected EntityManager em; 
    /**
     * Metodo que busca una tarjeta de credito asociada con el id dado
     * @param tarjetaId. Id de la tarjeta que se quiere buscar.
     * @param clienteId. Id del cliente. 
     * @return Retorna la tarjeta de credito asociado al Id. En caso de que no 
     * exista se retorna null
     */
    public TarjetaDeCreditoEntity find(Long clienteId,Long tarjetaId)
    {
       TypedQuery<TarjetaDeCreditoEntity> q = em.createQuery("select p from TarjetaDeCreditoEntity p where (p.cliente.id = :clienteid) and (p.id = :tarjetaId)", TarjetaDeCreditoEntity.class);
        q.setParameter("clienteid", clienteId);
        q.setParameter("tarjetaId", tarjetaId);
        List<TarjetaDeCreditoEntity> results = q.getResultList();
        TarjetaDeCreditoEntity tarjeta = null;
        if (results.size() >= 1) {
            tarjeta = results.get(0);
        }
        return tarjeta;
    }
    /**
     * Crea una tarjeta de credito
     * @param tarjetaDeCreditoEntity. Tarjeta con los datos, la cual se quiere persistir
     * @return Retorna la tarjeta creada con el Id 
     */
    public TarjetaDeCreditoEntity create(TarjetaDeCreditoEntity tarjetaDeCreditoEntity)
    {
        em.persist(tarjetaDeCreditoEntity);
        return tarjetaDeCreditoEntity;
    }
    /**
     * Actualiza la información de una tarjeta de credito
     * @param tarjeta. Tarjeta de credito con la información que se quiere actualizar.
     * @return Retorna la tarjeta de credito actualizada.
     */
    public TarjetaDeCreditoEntity update(TarjetaDeCreditoEntity tarjeta)
    {
        return em.merge(tarjeta);
    }
    /**
     * Elimina una tarjeta de credito asociada al Id dado
     * @param tarjetaId. Id de la tarjeta la cual se quiere eliminar
     */
    public  void delete(Long tarjetaId)
    {
        TarjetaDeCreditoEntity tarjeta = em.find(TarjetaDeCreditoEntity.class,tarjetaId);
        em.remove(tarjeta); 
    }
  
    
}
