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
 * @author Estudiante
 */
@Entity
public class PlatoEntity extends BaseEntity {
    private String rutaImagen; 
    
    private Double precio; 
    
    private String descripcion; 
    
    private String nombreComida; 
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private RestauranteEntity restaurante;
    
    public PlatoEntity()
    {
        super();
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
