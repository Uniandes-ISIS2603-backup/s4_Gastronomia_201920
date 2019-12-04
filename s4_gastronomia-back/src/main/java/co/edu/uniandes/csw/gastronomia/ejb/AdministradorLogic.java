/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;

import co.edu.uniandes.csw.gastronomia.entities.AdministradorEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.AdministradorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Angela Maria Suarez Parra
 */
@Stateless
public class AdministradorLogic {
    
    @Inject
    private AdministradorPersistence persistence; 
    
    private static final String UPDATE = " a actualizar esta en null o vacio";
    private static final String CREATE = " a crear  esta en null o vacio";
    private static final String NOMBRE = " El nombre ";
    
    /**
     * Se encarga de crear un Administrador en la base de datos.
     *
     * @param administradorEntity Objeto de administradorEntity con los datos nuevos
     * @return Objeto de administradorEntity con los datos nuevos y su ID.
     */
    public AdministradorEntity createAdministrador( AdministradorEntity administradorEntity) throws BusinessLogicException
    {
        if(administradorEntity.getApellido() == null )
        {
            throw new BusinessLogicException("El apellido" + CREATE);
        }
        else if(administradorEntity.getEmail() == null )
        {
             throw new BusinessLogicException("El email" + CREATE);
        }
         else if(administradorEntity.getContrasena() == null)
        {
             throw new BusinessLogicException("La contraseña" + CREATE);
        }
        else if(administradorEntity.getNombre() == null)
        {
             throw new BusinessLogicException(NOMBRE +CREATE );
        }
        else if(administradorEntity.getUsername() == null)
        {
             throw new BusinessLogicException(NOMBRE + CREATE);
        }
        
        else if ( persistence.findByUserName(administradorEntity.getUsername()) != null)
        {
            throw new BusinessLogicException("EL usernme ya existe");
        }
         persistence.create(administradorEntity);
        return administradorEntity;
    }
    
    /**
     * Obtiene la lista de los administradores registrados 
     *
     * @return Una colección de objetos de administradorEntity 
     */
    public List<AdministradorEntity> getAdministradores()
    {
       // List<AdministradorEntity> administradores = persistence.findAll();
        return  persistence.findAll();
    } 
    
    /**
     * Obtiene los datos de una instancia de Administrador a partir de su ID 
     * @param Instancia de Administrador con los datos del autor consultado
     * @return Una colección de objetos de administradorEntity 
     */
    public AdministradorEntity getAdministrador(Long administradorId)
    {
        AdministradorEntity administradorEntity = persistence.find(administradorId);
       
        return administradorEntity;
    }
    
     public AdministradorEntity getAdministrador(String adminUsername) {
        AdministradorEntity adminEntity = persistence.findByUserName(adminUsername);
        return adminEntity;
    }
     /**
     * Actualiza la información de una instancia de Administrador
     * @param administradorEntity Instancia de AdministradorEntity con los nuevos datos y el 
     * id del administrador a actualizar
     * @return Instancia de AdministradorEntity con los datos actualizados
     */
    public AdministradorEntity updateAdministrador( Long administradorId , AdministradorEntity administradorEntity) throws BusinessLogicException
    {
        if(administradorEntity.getApellido() == null )
        {
            throw new BusinessLogicException("El apellido" + UPDATE);
        }
        else if(administradorEntity.getEmail() == null)
        {
             throw new BusinessLogicException("El email" + UPDATE);
        }
         else if(administradorEntity.getContrasena() == null )
        {
             throw new BusinessLogicException("La contrasena" + UPDATE );
        }
        else if(administradorEntity.getNombre() == null )
        {
             throw new BusinessLogicException(NOMBRE + UPDATE);
        }
        
        else if(administradorEntity.getUsername() == null )
        {
             throw new BusinessLogicException("El Username" + UPDATE);
        }
        
        
         persistence.update(administradorEntity);
        return administradorEntity;
    }
    
    /**
     * Elimina una instancia de Administrador de la base de datos
     * @param administradorEntity instancia de AdministradorEntity con los nuevos datos
     * @throws BusinessLogicException si el administrador tiene restuarantes asociados 
     */
    public void deleteAdministrador(Long administradorId) throws BusinessLogicException
    {
    persistence.delete(administradorId);
        
    }
    
}
