/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
import co.edu.uniandes.csw.gastronomia.entities.FoodBlogEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Cristina Isabel González Osorio,David Martinez
 */
public class ClienteDetailDTO extends ClienteDTO implements Serializable
{
    
    private List<FoodBlogDTO> foodBlogs = new ArrayList<FoodBlogDTO>();
    
    //private List<TipoComidaDTO> preferencias = new ArrayList<TipoComidaDTO>();
    
    //private List<FacturaDTO> facturas = new ArrayList<FacturaDTO>();
    
    private List<ReservaDTO> reservas = new ArrayList<ReservaDTO>();

    public ClienteDetailDTO() 
    {
        super();
    }
    
    public ClienteDetailDTO(ClienteEntity  cliente)
    {
        super(cliente);
        if (cliente!=null) 
        {
            foodBlogs=new ArrayList<>();
            for (FoodBlogEntity foodBlogEntity: cliente.getFoodBlogs()) {
              foodBlogs.add(new FoodBlogDTO(foodBlogEntity));
                
            }
        }
    }
    public ClienteEntity toEntity()
    {
        ClienteEntity cliente= super.toEntity();
        if (foodBlogs!=null)
        {
        List<FoodBlogEntity> foodBlogentity=new ArrayList<>();
            for (FoodBlogDTO fbDTO : foodBlogs)
            {
            foodBlogentity.add(fbDTO.toEntity());
            }
        
        }
        return cliente;
    }
    /**
     * 
     * @return  los foodblogs
     */
        public List<FoodBlogDTO> getfoodBlogs()
        {
            return foodBlogs;
        }

        public void setFoodBlogs(List<FoodBlogDTO> pFoodBlogs)
        {
            this.foodBlogs=pFoodBlogs;
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
