/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristina Isabel González Osorio
 */
public class ClienteDetailDTO extends ClienteDTO{
    
    //private List<FoodBlogDTO> foodBlogs = new ArrayList<FoodBlogDTO>();
    
    //private List<TipoComidaDTO> preferencias = new ArrayList<TipoComidaDTO>();
    
    //private List<FacturaDTO> facturas = new ArrayList<FacturaDTO>();
    
    private List<ReservaDTO> reservas = new ArrayList<ReservaDTO>();

    public ClienteDetailDTO() {
    }

    /**
     * @return the reservas
     */
    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    } 
    
}
