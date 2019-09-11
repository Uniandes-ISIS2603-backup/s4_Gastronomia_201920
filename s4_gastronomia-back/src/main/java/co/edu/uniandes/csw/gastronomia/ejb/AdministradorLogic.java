/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;

import co.edu.uniandes.csw.gastronomia.entities.AdministradorEntity;
import co.edu.uniandes.csw.gastronomia.persistence.AdministradorPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Estudiante
 */
@Stateless
public class AdministradorLogic {
    
    private static final Logger LOGGER = Logger.getLogger(AdministradorLogic.class.getName()); 
    
    @Inject
    private AdministradorPersistence persistence; 
    
    /**
     * Se encarga de crear un Administrador en la base de datos.
     *
     * @param administradorEntity Objeto de administradorEntity con los datos nuevos
     * @return Objeto de administradorEntity con los datos nuevos y su ID.
     */
    public AdministradorEntity createAdministrador( AdministradorEntity administradorEntity)
    {
        LOGGER.log( Level.INFO , " Inicia proceso de creación del administrador");
        AdministradorEntity newAdministradorEntity = persistence.create(administradorEntity);
        LOGGER.log(Level.INFO , "Termino proceso de creación del administrador ");
        return newAdministradorEntity;
    }
    
}
