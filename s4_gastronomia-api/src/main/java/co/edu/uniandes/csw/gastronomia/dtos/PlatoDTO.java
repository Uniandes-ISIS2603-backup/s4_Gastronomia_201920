/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.PlatoEntity;
import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
import java.io.Serializable;
import javax.persistence.ManyToOne;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author je.canizarez
 */
public class PlatoDTO implements Serializable {
    
    private Long id;
    private Double precio; 
    private String rutaImagen;
    private String descripcion; 
    private String nombre;

    
    public PlatoDTO(){
        
    }
    public PlatoDTO(PlatoEntity plato)
    {
        id = plato.getId();
        precio = plato.getPrecio(); 
        rutaImagen  = plato.getRutaImagen();
        descripcion = plato.getDescripcion(); 
        nombre = plato.getNombreComida();
    }
    public PlatoEntity toEntity()
    {
     PlatoEntity retorno = new PlatoEntity(); 
     retorno.setId(id);
     retorno.setDescripcion(descripcion);
     retorno.setNombreComida(nombre);
     retorno.setPrecio(precio);
     retorno.setRutaImagen(rutaImagen);
     return retorno;
    }

    /**
     * @return the precio
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return the rutaImagen
     */
    public String getRutaImagen() {
        return rutaImagen;
    }

    /**
     * @param rutaImagen the rutaImagen to set
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @Override
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
    
}
