/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.entities;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
/**
 *
 * @author Estudiante
 */
@Entity
public class PlatoEntity extends BaseEntity {
    private String rutaImagen; 
    
    private double precio; 
    
    private String descripcion; 
    
    private String nombreComida; 
    
    @ManyToOne
    private RestauranteEntity restaurante;
    
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
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
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
     * @return the nombreComida
     */
    public String getNombreComida() {
        return nombreComida;
    }

    /**
     * @param nombreComida the nombreComida to set
     */
    public void setNombreComida(String nombreComida) {
        this.nombreComida = nombreComida;
    }
       /**
     * 
     * @param restaurante 
     */
    public void setRestaurante(RestauranteEntity restaurante)
    {
        this.restaurante = restaurante;
    }
    public RestauranteEntity getRestaurante()
    {
        return restaurante;
    }
    @Override
    public boolean equals(Object o)
    {
       return super.equals(o);
    }
    @Override
    @Deprecated
    public int hashCode()
    {
        return super.hashCode();
    }
    
}
