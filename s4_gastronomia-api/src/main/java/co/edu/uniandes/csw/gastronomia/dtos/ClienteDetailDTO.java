/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
import co.edu.uniandes.csw.gastronomia.entities.FacturaEntity;
import co.edu.uniandes.csw.gastronomia.entities.FoodBlogEntity;
import co.edu.uniandes.csw.gastronomia.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.gastronomia.entities.TipoComidaEntity;
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
    
    private List<TipoComidaDTO> preferencias = new ArrayList<TipoComidaDTO>();
    
    private List<FacturaDTO> facturas = new ArrayList<FacturaDTO>();
    
    private List<ReservaDTO> reservas = new ArrayList<ReservaDTO>();
    
    private List<TarjetaDeCreditoDTO> tarjetas = new ArrayList<TarjetaDeCreditoDTO>();

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
        List<TarjetaDeCreditoEntity> listaTarjetas = cliente.getTarjetas();
        for(TarjetaDeCreditoEntity e: listaTarjetas)
        {
            tarjetas.add(new TarjetaDeCreditoDTO(e));
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
            cliente.setFoodBlogs(foodBlogentity);
        
        }
        List<TarjetaDeCreditoEntity> lista = new ArrayList<TarjetaDeCreditoEntity>();
        for(TarjetaDeCreditoDTO e: getTarjetas())
        {
            lista.add(e.toEntity());
        }
        cliente.setTarjetas(lista);
        
        if (facturas!=null)
        {
        List<FacturaEntity> facturasEntity =new ArrayList<FacturaEntity>();
            for (FacturaDTO facturaDTO : facturas)
            {
            facturasEntity.add(facturaDTO.toEntity());
            }
            cliente.setFacturas(facturasEntity);
        
        }
          
        if (preferencias!=null)
        {
        List<TipoComidaEntity> tipoComidaEntity=new ArrayList<>();
            for (TipoComidaDTO tipoDTO : preferencias)
            {
            tipoComidaEntity.add(tipoDTO.toEntity());
            }
            cliente.setPreferencias(tipoComidaEntity);
        
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

    /**
     * @return the tarjetas
     */
    public List<TarjetaDeCreditoDTO> getTarjetas() {
        return tarjetas;
    }

    /**
     * @param tarjetas the tarjetas to set
     */
    public void setTarjetas(List<TarjetaDeCreditoDTO> tarjetas) {
        this.tarjetas = tarjetas;
    }
    
      /**
     * @return the facturas
     */
    public List<FacturaDTO> getFacturas() {
        return facturas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(List<FacturaDTO> facturas) {
        this.facturas = facturas;
    } 
    
      /**
     * @return the preferencias
     */
    public List<TipoComidaDTO> getPreferencias() {
        return preferencias;
    }

    /**
     * @param preferencias the preferencias to set
     */
    public void setPreferencias(List<TipoComidaDTO> preferencias) {
        this.preferencias = preferencias;
    } 
    
}
