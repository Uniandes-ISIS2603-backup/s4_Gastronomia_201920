/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import javax.persistence.MappedSuperclass;

/**
 *
 * @author Angela Maria Suarez P
 */

/**
 * Clase que modela un usuario.<br>
 */

@MappedSuperclass
public abstract class UsuarioEntity extends BaseEntity {
     // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

   
   
    /**
     * Nombre del usuario.
     */
    private String nombre;
    
    /**
     * Apellido del usuario.
     */
    private String apellido;
    
    /**
     * UserName del usuario.
     */
    private String username;
    
    /**
     * Correo electronico del usuario.
     */
    private String email;
    
    /**
     * Contrasena del usuario.
     */
    private String contrasena;

    public UsuarioEntity() {
    }
    
    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
    
     
    /**
     * Crea un usuario de la aplicacion de gastronomia. <br>
     * <b>post: </b> Se creó un usuario con los valores pasados por parámetro.<br>
     * @param name Corresponde a la nombre del usuario nuevo. nombre != null && nombre != "".
     * @param apellido Corresponde al apellido del usuario nuevo. apellido != null && apellido != "".
     * @param username Corresponde al user name  del usuario nuevo. username != null && username != "".
     * @param email Corresponde al correo electronico del usuaio.
     * @param contrasena Corresponde a la contraseña del usuario
     */
    
    public UsuarioEntity( String nombre , String apellido , String username , String contrasena , String email)
    {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.contrasena = contrasena;
        this.email = email;
    }

   
            

// -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el nombre del usuario. <br>
     * @return El nombre del usario.
     */
    public String getNombre()
    {
        return nombre;
    }
    
    
    /**
     * Retorna el apellido del usuario. <br>
     * @return El apellido del usario.
     */
    public String getApellido()
    {
        return apellido;
    }
    
    /**
     * Retorna el correo electronico del usuario. <br>
     * @return El correo electronico del usario.
     */
    public String getEmail()
    {
        return email;
    }
    
     /**
     * Retorna el username del usuario. <br>
     * @return El username del usario.
     */
    public String getUsername()
    {
        return username;
    }
    
     /**
     * Retorna el contrasena del usuario. <br>
     * @return El contrasena del usario.
     */
    public String getContrasena()
    {
        return contrasena;
    }
    
    /**
     * Modifica el nombre del usuario. <br>
     * @param pNombre  nuevo nombre del usario.
     */
    public void setNombre( String pNombre)
    {
        this.nombre = pNombre;
    }
   
    /**
     * Modifica el apellido del usuario. <br>
     * @param pApellido nuevo nombre del usario.
     */
    public void setApellido( String pApellido)
    {
        this.apellido = pApellido;
    }
    
    /**
     * Modifica el username del usuario. <br>
     * @param pUsername nuevo username del usario.
     */
    public void setUsername( String pUsername)
    {
        this.username = pUsername;
    }
    
    /**
     * Modifica el correo electronico del usuario. <br>
     * @param pEmail nuevo correo electronico del usario.
     */
    public void setEmail( String pEmail)
    {
        this.email = pEmail;
    }
    
    /**
     * Modifica el nombre del usuario. <br>
     * @param pContrasena el nuevo nombre del usario.
     */
    public void setContrasena( String pContrasena)
    {
        this.contrasena = pContrasena;
    
    }
   

    @Override
    public boolean equals(Object ob)
    {
        if(! super.equals(ob))
        {
            return false;
        }
        UsuarioEntity usuarioOb = ( UsuarioEntity) ob ;
        return username.equals(usuarioOb.getUsername());
        
        
    }

}
