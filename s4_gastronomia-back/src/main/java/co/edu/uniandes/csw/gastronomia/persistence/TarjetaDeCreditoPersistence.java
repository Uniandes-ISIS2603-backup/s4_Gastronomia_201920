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
import javax.persistence.Query;

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
     * @return Retorna la tarjeta de credito asociado al Id. En caso de que no 
     * exista se retorna null
     */
    public TarjetaDeCreditoEntity find(Long tarjetaId)
    {
        return em.find(TarjetaDeCreditoEntity.class, tarjetaId);
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
    /**
     * Encuentra todas las tarjetas de credito existentes en la base de datos
     * @return. Retorna una lista con todas las tarjetas de credito existentes. 
     */
    public List<TarjetaDeCreditoEntity> findAll()
    {
        Query q = em.createQuery("select u from TarjetaDeCreditoEntity u"); 
        return q.getResultList();
    }
    
}
