/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import javax.persistence.Entity;

/**
 *
 * @author Estudiante Angela Maria Suarez P
 */

/**
 * Clase que modela un administrador.<br>
 */
// -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
    /**
     * Crea un administrador de la aplicacion de gastronomia. <br>
     * <b>post: </b> Se creó un administrador con los valores pasados por parámetro.<br>
     */
@Entity
public class AdministradorEntity  extends UsuarioEntity
{
   private long phone;

    public AdministradorEntity() {
        super();
    }
   
   public AdministradorEntity(String name , String apellido , String username , String contrasena , String email , long pPhone)
   {
       super(name, apellido, username, contrasena, email);
       this.phone = pPhone;
   }
   
   public long getPhone()
   {
   return phone;
   }
   
   public void setPhone(long pViejo)
   {
   phone = pViejo ;
    }
   
    
   
}
