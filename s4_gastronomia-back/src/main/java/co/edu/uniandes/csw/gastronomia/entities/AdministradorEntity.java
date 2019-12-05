/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
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
   @PodamExclude
   @OneToMany(mappedBy = "administrador",fetch = javax.persistence.FetchType.LAZY )
   private List<RestauranteEntity> restaurantes = new ArrayList<>();
   
   
   private Long phone;

    public AdministradorEntity() {
        super();
    }
   
   
   
   public long getPhone()
   {
   return phone;
   }
   
   public void setPhone(long pViejo)
   {
   phone = pViejo ;
    }
   
     @Override
    public boolean equals(Object ob)
    {
       if(! super.equals(ob))
        {
            return false;
        }
        UsuarioEntity usuarioOb = ( UsuarioEntity) ob ;
        String usuario = usuarioOb.getUsername();
        return usuario.equals(usuarioOb.getUsername());
        
    }
    
    @Override
    public int hashCode()
    {
     
        if (this.getUsername() != null) {
            return this.getUsername().hashCode();
        }
        return super.hashCode();
    } 
   
    public void setRestaurantes(List<RestauranteEntity> restaurantes) {
        this.restaurantes = restaurantes;
    }

    public List<RestauranteEntity> getRestaurantes() {
        return this.restaurantes;
    }
}
