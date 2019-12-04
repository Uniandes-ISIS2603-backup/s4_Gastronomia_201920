/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.persistence;

import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Estudiante
 */
@Stateless
public class RestaurantePersistence 
{
    private static final Logger LOGGER = Logger.getLogger(RestaurantePersistence.class.getName());

    private static final String BUSCANDO="Buscando todos los restaurantes con nombre";
    @PersistenceContext(unitName = "gastronomiaPU")
    protected EntityManager em;
    /**
     * Perisite la entidad
     * @param rest la entidad a persisteir
     * @return la entidad persistida
     */
    public RestauranteEntity create(RestauranteEntity rest)
    {
        LOGGER.log(Level.INFO, "Creando un restaurante nuevo");
        em.persist(rest);
        LOGGER.log(Level.INFO, "Restaurante creado");
        return rest;
    }
    /**
     * devuelve todos los restaurantes
     * @return la lista de restaurantes
     */
    public List<RestauranteEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Buscando todos los restaurantes");
        TypedQuery q = em.createQuery("select u from RestauranteEntity u", RestauranteEntity.class);
        return q.getResultList();
    }
    /**
     * busca un restaurante con dicho id
     * @param id el identificador del restaurante a buscar
     * @return el restuarante que se busco o null
     */
    public RestauranteEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Busando restaurante con id={0}", id);
        if(id==null)
        {
            return null;
        }
        return em.find(RestauranteEntity.class, id);
    }
    /**
     * modifica una identidad
     * @param r la identidad modificada
     * @return la entidad modificada
     */
    public RestauranteEntity update(RestauranteEntity r)
    {
        LOGGER.log(Level.INFO, "Actualizando restaurante con id={0}", r.getId());
        return em.merge(r);
    }
    /**
     * borra una entidad con idcho id
     * @param id el id de la entidad a borrar
     */
    public void delete(Long id)
    {
        LOGGER.log(Level.INFO, "Borrando restaurante con id={0}", id);
        RestauranteEntity r = find(id);
        em.remove(r);
    }
    
    public List<RestauranteEntity> findNombre(String n)
    {
        LOGGER.log(Level.INFO,BUSCANDO+n);
        TypedQuery q = em.createQuery("select u from RestauranteEntity u where u.nombre = :nombre", RestauranteEntity.class);
        q.setParameter("nombre", n);
        return q.getResultList();
    }
    
    public List<RestauranteEntity> findPrecioPromedioRango(Double min, Double max)
    {
        LOGGER.log(Level.INFO, BUSCANDO);
        TypedQuery q = em.createQuery("select u from RestauranteEntity u where u.precioPorPersona >= :min and u.precioPorPersona <= :max", RestauranteEntity.class);
        q.setParameter("min", min);
        q.setParameter("max", max);
        return q.getResultList();
    }
    
    public List<RestauranteEntity> findPrecioReservaRango(Double min, Double max)
    {
        LOGGER.log(Level.INFO, BUSCANDO);
        TypedQuery q = em.createQuery("select u from RestauranteEntity u where u.costoReserva >= :min and u.costoReserva <= :max", RestauranteEntity.class);
        q.setParameter("min", min);
        q.setParameter("max", max);
        return q.getResultList();
    }
    
    public List<RestauranteEntity> findDescuentoCumpleanos(Boolean b)
    {
        LOGGER.log(Level.INFO, BUSCANDO);
        TypedQuery q = em.createQuery("select u from RestauranteEntity u where u.descuentaoCumpleanos = :bol", RestauranteEntity.class);
        q.setParameter("bol", b);
        return q.getResultList();
    }
    
    public List<RestauranteEntity> findServicioALaMesa(Boolean b)
    {
        LOGGER.log(Level.INFO, BUSCANDO);
        TypedQuery q = em.createQuery("select u from RestauranteEntity u where u.servicioALaMesa = :bol", RestauranteEntity.class);
        q.setParameter("bol", b);
        return q.getResultList();
    }
    
    public List<RestauranteEntity> findMusicaEnVivo(Boolean b)
    {
        LOGGER.log(Level.INFO, BUSCANDO);
        TypedQuery q = em.createQuery("select u from RestauranteEntity u where u.musicaEnVivo = :bol", RestauranteEntity.class);
        q.setParameter("bol", b);
        return q.getResultList();
    }
    
    public List<RestauranteEntity> findPetFriendly(Boolean b)
    {
        LOGGER.log(Level.INFO, BUSCANDO);
        TypedQuery q = em.createQuery("select u from RestauranteEntity u where u.petFriendly = :bol", RestauranteEntity.class);
        q.setParameter("bol", b);
        return q.getResultList();
    }
    
    
    
    public List<RestauranteEntity> findDireccion(String b)
    {
        LOGGER.log(Level.INFO, BUSCANDO);
        TypedQuery q = em.createQuery("select u from RestauranteEntity u where u.direccion like :bol", RestauranteEntity.class);
        q.setParameter("bol", b);
        return q.getResultList();
    }
    
    public RestauranteEntity findNombrePassword(String nombre, String pass)
    {
        LOGGER.log(Level.INFO, BUSCANDO);
        TypedQuery q = em.createQuery("select u from RestauranteEntity u where u.nombre = :nombre and u.contrasena = :pass", RestauranteEntity.class);
        q.setParameter("nombre", nombre);
        q.setParameter("pass", pass);
        try
        {
            return (RestauranteEntity) q.getSingleResult();
        }
        catch(Exception e )
        {
            //
        }
        return null;
    }
}
