/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import javax.persistence.Entity;

/**
 *
 * @author af.benitez
 */
@Entity
public class TipoComidaEntity extends BaseEntity 
{
    private String nombre;

    
    public TipoComidaEntity()
    {
        //Constructor vacio para evitar falla.
    }
    
    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }
    
     @Override
    public boolean equals(Object obj)
    {
        if (! super.equals(obj)) 
        {
          return false;
        }
        TipoComidaEntity reservaObj = (TipoComidaEntity) obj;
        return this.getId().equals(reservaObj.getId());
    }
    
    @Override
    public int hashCode()
    {
        if (this.getId() != null)
        {
            return this.getId().hashCode();
        }
        return super.hashCode();
    } 
}
