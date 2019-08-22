/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.persistence;

import co.edu.uniandes.csw.gastronomia.entities.AdministradorEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Estudiante Angela Maria Suarez P
 */
@Stateless
public class AdministradorPersistence {
    
    @PersistenceContext(unitName = "gastronomiaPU")
    protected EntityManager em;
    
    
    public AdministradorEntity create( AdministradorEntity administradorEntity )
    {
       em.persist(administradorEntity);
        return administradorEntity;
              
    }
    
    public AdministradorEntity find(Long administradorId)
    {
        return em.find(AdministradorEntity.class, administradorId);
    }
    
    public List<AdministradorEntity> findAll()
    {
        TypedQuery<AdministradorEntity> query = em.createQuery("select u from AdministradorEntity u", AdministradorEntity.class);
        return query.getResultList();
    }
    
}
