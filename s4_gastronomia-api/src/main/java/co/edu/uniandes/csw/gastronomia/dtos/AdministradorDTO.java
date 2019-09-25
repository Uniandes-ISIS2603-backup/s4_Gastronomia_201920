/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.AdministradorEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * AdministradorDTO Objeto de transferencia de datos de Administradores. Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name": string
 *   }
 * </pre> Por ejemplo una editorial se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 1,
 *      "name": "Norma"
 *   }
 *
 * </pre>
 *
 * @author Angela Maria Suarez Paeea
 */
public class AdministradorDTO implements  Serializable 
{
    private Long id;
    private String apellido;
    private String nombre;
    private String username;
    private String email;
    private String contrasena;
    private Long phone;
    
    public AdministradorDTO()
    {
    }
    
    
    public AdministradorDTO(AdministradorEntity administradorEntity)
    {
        if( administradorEntity != null)
        {
            this.id = administradorEntity.getId();
            this.apellido = administradorEntity.getApellido();
            this.contrasena = administradorEntity.getContrasena();
            this.email = administradorEntity.getEmail();
            this.phone = administradorEntity.getPhone();
            this.nombre = administradorEntity.getNombre();
            this.username = administradorEntity.getUsername();
        }
    }
    
    public Long getId()
    {
        return id;
    }
    
    public Long getPhone()
    {
        return phone;
    }
    
    public String getApellido()
    {
        return apellido;
    }
    
    public String getNombre()
    {
        return nombre;
    }
  
     public String getUsername()
    {
        return username;
    }
     
      public String getEmail()
    {
        return email;
    }
    
     public String getContrasena()
    {
        return contrasena;
    }
    
     public void setPhone(Long pPhone)
    {
        this.phone = pPhone;
    }
     
    public void setId(Long pId)
    {
        this.id = pId;
    }
    
    public void setContrasena(String pContrasena)
    {
        this.contrasena = pContrasena;
    }
    
    public void setUsername(String pUsername)
    {
        this.username = pUsername;
    }
    
    public void setEmail(String pEmail)
    {
        this.email = pEmail;
    }
    
    public void setApellido(String pApellido)
    {
        this.apellido = pApellido;
    }
    
    public void setNombre(String pNombre)
    {
        this.nombre = pNombre;
    }
    
    public AdministradorEntity toEntity() {
        AdministradorEntity administradorEntity = new AdministradorEntity();
        administradorEntity.setApellido(this.apellido);
        administradorEntity.setContrasena(this.contrasena);
        administradorEntity.setEmail(this.email);
        administradorEntity.setId(this.id);
        administradorEntity.setNombre(this.nombre);
        administradorEntity.setUsername(this.username);
        administradorEntity.setPhone(this.phone);
        return administradorEntity;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
