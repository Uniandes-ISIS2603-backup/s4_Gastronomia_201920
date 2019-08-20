/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.persistence;

import co.edu.uniandes.csw.gastronomia.entities.TarjetaDeCreditoEntity;
import com.sun.istack.internal.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Estudiante
 */
@Stateless
public class TarjetaDeCreditoPersistence {
    @PersistenceContext(unitName = "gastronomiaPU")
    protected EntityManager em; 
    
    public TarjetaDeCreditoEntity find(Long tarjetaId)
    {
        return em.find(TarjetaDeCreditoEntity.class, tarjetaId);
    }
    public TarjetaDeCreditoEntity create(TarjetaDeCreditoEntity tarjetaDeCreditoEntity)
    {
        em.persist(tarjetaDeCreditoEntity);
        return tarjetaDeCreditoEntity;
    }
    
}
