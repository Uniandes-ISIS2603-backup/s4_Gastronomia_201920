/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
import co.edu.uniandes.csw.gastronomia.entities.TipoComidaEntity;
import java.io.Serializable;

/**
 *
 * @author af.benitez
 */
public class TipoComidaDTO implements Serializable 
{
    private long id;
    
    private String nombre;
    
    private ClienteEntity cliente;
    
    /**
     * Constructor por defecto
     */
    public TipoComidaDTO()
    {
        //Constructor vacio por defecto
    }
    
    /**
     * Constructor a partir de la entidad
     *
     * @param tEntity La entidad de tipo comida
     */
    public TipoComidaDTO(TipoComidaEntity tEntity) 
    {
        if (tEntity != null) 
        {
            this.id = tEntity.getId();
            this.nombre = tEntity.getNombre(); 
            this.cliente = tEntity.getCliente();
        }
    }

    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad de tipo comida asociado.
     */
    public TipoComidaEntity toEntity() 
    {
        TipoComidaEntity tipoEntity = new TipoComidaEntity();
        tipoEntity.setId(this.id);
        tipoEntity.setNombre(this.nombre);
        tipoEntity.setCliente(this.getCliente());
        
        return tipoEntity;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
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
