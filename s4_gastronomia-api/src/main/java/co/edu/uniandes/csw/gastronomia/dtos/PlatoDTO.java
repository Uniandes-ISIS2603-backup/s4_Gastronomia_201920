/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.PlatoEntity;
import java.io.Serializable;

/**
 *
 * @author je.canizarez
 */
public class PlatoDTO implements Serializable {
    
    private Double precio; 
    private String rutaImagen;
    private String descripcion; 
    private String nombre;
    
    public PlatoDTO(){
        
    }
    public PlatoDTO(PlatoEntity plato)
    {
        precio = plato.getPrecio(); 
        rutaImagen  = plato.getRutaImagen();
        descripcion = plato.getDescripcion(); 
        nombre = plato.getNombreComida();
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
    
    
    
}
