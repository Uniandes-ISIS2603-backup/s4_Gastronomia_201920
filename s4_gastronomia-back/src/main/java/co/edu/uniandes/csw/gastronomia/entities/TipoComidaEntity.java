/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author af.benitez
 */
@Entity
public class TipoComidaEntity extends BaseEntity 
{
 
    /**
    * Nombre del tipo comida.
    */
    private String nombre;

    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ClienteEntity cliente;
    
    
    /**
    * Constructor de la clase TipoComidaEntity.
    */
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
    
    /**
     * @return the cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
   
}
