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
 * @author Angela Maria Suarez
 */
@Stateless
public class AdministradorLogic {
    
    @Inject
    private AdministradorPersistence persistence; 
    
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
            throw new BusinessLogicException("El apellido esta vacio");
        }
        else if(administradorEntity.getEmail() == null )
        {
             throw new BusinessLogicException("El email  esta vacio");
        }
         else if(administradorEntity.getContrasena() == null)
        {
             throw new BusinessLogicException("La contraseña esta vacio");
        }
        else if(administradorEntity.getName() == null)
        {
             throw new BusinessLogicException("El nombre esta vacio");
        }
        else if ( persistence.findByUserName(administradorEntity.getUsername()) != null)
        {
            throw new BusinessLogicException("El nombre esta vacio");
        }
        else if(administradorEntity.getUsername() == null)
        {
            throw new BusinessLogicException("El username es null");
        }
        administradorEntity = persistence.create(administradorEntity);
        return administradorEntity;
    }
    
    /**
     * Obtiene la lista de los administradores registrados 
     *
     * @return Una colección de objetos de administradorEntity 
     */
    public List<AdministradorEntity> getAdministradores()
    {
        List<AdministradorEntity> administradores = persistence.findAll();
        return administradores;
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
    
     /**
     * Actualiza la información de una instancia de Administrador
     * @param administradorEntity Instancia de AdministradorEntity con los nuevos datos y el 
     * id del administrador a actualizar
     * @return Instancia de AdministradorEntity con los datos actualizados
     */
    public AdministradorEntity updateAdministrador(Long administradorId , AdministradorEntity administradorEntity) throws BusinessLogicException
    {
        if(administradorEntity.getApellido() == null )
        {
            throw new BusinessLogicException("El apellido a cambiar esta vacio");
        }
        else if(administradorEntity.getEmail() == null)
        {
             throw new BusinessLogicException("El email a cambiar esta vacio");
        }
         else if(administradorEntity.getContrasena() == null )
        {
             throw new BusinessLogicException("El email a cambiar esta vacio");
        }
        else if(administradorEntity.getName() == null )
        {
             throw new BusinessLogicException("El nombre a cambiar esta vacio");
        }
         else if(administradorEntity.getUsername() == null)
        {
            throw new BusinessLogicException("El username es null");
        }
        AdministradorEntity newEntity = persistence.update(administradorEntity);
        return newEntity;
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
