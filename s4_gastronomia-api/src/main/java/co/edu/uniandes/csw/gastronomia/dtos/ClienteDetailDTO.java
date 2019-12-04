/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
import co.edu.uniandes.csw.gastronomia.entities.FacturaEntity;
import co.edu.uniandes.csw.gastronomia.entities.FoodBlogEntity;
import co.edu.uniandes.csw.gastronomia.entities.ReservaEntity;
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
    private List<TipoComidaDTO> preferencias;
    
    private List<TarjetaDeCreditoDTO> tarjetas;
    
    private List<ReservaDTO> reservas;
    
    private List<FacturaDTO> facturas;
    
    private List<FoodBlogDTO> foodBlogs;

    public ClienteDetailDTO() 
    {
        super();
    }
    
    public ClienteDetailDTO(ClienteEntity  cliente)
    {
        super(cliente);
        if (cliente != null) 
        {
            preferencias = listTipoComidaEntity2DTO(cliente.getPreferencias());
            tarjetas = listTarjetaEntity2DTO(cliente.getTarjetas());
            reservas = listReservaEntity2DTO(cliente.getReservas());
            facturas = listFacturaEntity2DTO(cliente.getFacturas());
            foodBlogs = listFoodBlogEntity2DTO(cliente.getFoodBlogs());
        }
    }
    
    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el cliente.
     */
    @Override
    public ClienteEntity toEntity()
    {
        ClienteEntity cliente = super.toEntity();
        if (getPreferencias() != null) {
            List<TipoComidaEntity> tiposEntity = new ArrayList<>();
            for (TipoComidaDTO dtoTipoComida : getPreferencias()) {
                tiposEntity.add(dtoTipoComida.toEntity());
            }
            cliente.setPreferencias(tiposEntity);
        }
        
        if(getTarjetas() != null) {
            List<TarjetaDeCreditoEntity> tarjetasEntity = new ArrayList<>();
            for (TarjetaDeCreditoDTO dtoTarjeta: getTarjetas()) {
                tarjetasEntity.add(dtoTarjeta.toEntity());
            }
            cliente.setTarjetas(tarjetasEntity);
        }
        
        if(getReservas() != null) {
            List<ReservaEntity> reservasEntity = new ArrayList<>();
            for (ReservaDTO dtoReserva : getReservas()) {
                reservasEntity.add(dtoReserva.toEntity());
            }
            cliente.setReservas(reservasEntity);
        }
        
        if (getFacturas() != null) {
        List<FacturaEntity> facturasEntity =new ArrayList<>();
            for (FacturaDTO dtoFactura : getFacturas()) {
                facturasEntity.add(dtoFactura.toEntity());
            }
            cliente.setFacturas(facturasEntity);
        
        }
        
        if (getFoodBlogs()!= null) {
            List<FoodBlogEntity> foodBlogsEntity = new ArrayList<>();
            for (FoodBlogDTO dtoFoodBlog : getFoodBlogs()) {
                foodBlogsEntity.add(dtoFoodBlog.toEntity());
            }
            cliente.setFoodBlogs(foodBlogsEntity);
        }
        return cliente;
    }

    /**
     * @return the preferencias
     */
    public List<TipoComidaDTO> getPreferencias() {
        return preferencias;
    }

    /**
     * @return the tarjetas
     */
    public List<TarjetaDeCreditoDTO> getTarjetas() {
        return tarjetas;
    }

    /**
     * @return the reservas
     */
    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    /**
     * @return the facturas
     */
    public List<FacturaDTO> getFacturas() {
        return facturas;
    }

    /**
     * @return the foodBlogs
     */
    public List<FoodBlogDTO> getFoodBlogs() {
        return foodBlogs;
    }

    /**
     * @param preferencias the preferencias to set
     */
    public void setPreferencias(List<TipoComidaDTO> preferencias) {
        this.preferencias = preferencias;
    }

    /**
     * @param tarjetas the tarjetas to set
     */
    public void setTarjetas(List<TarjetaDeCreditoDTO> tarjetas) {
        this.tarjetas = tarjetas;
    }

    /**
     * @param reservas the reservas to set
     */
    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    /**
     * @param facturas the facturas to set
     */
    public void setFacturas(List<FacturaDTO> facturas) {
        this.facturas = facturas;
    }

    /**
     * @param foodBlogs the foodBlogs to set
     */
    public void setFoodBlogs(List<FoodBlogDTO> foodBlogs) {
        this.foodBlogs = foodBlogs;
    }
    
    private List<TipoComidaDTO> listTipoComidaEntity2DTO(List<TipoComidaEntity> entityList) {
        List<TipoComidaDTO> list = new ArrayList<>();
        if (entityList != null ) {
            for (TipoComidaEntity entity : entityList) {
                list.add(new TipoComidaDTO(entity));
            } 
        }
        return list; 
    }
    
    private List<TarjetaDeCreditoDTO> listTarjetaEntity2DTO(List<TarjetaDeCreditoEntity> entityList) {
        List<TarjetaDeCreditoDTO> list = new ArrayList<>();
        if (entityList != null ) {
            for (TarjetaDeCreditoEntity entity : entityList) {
                list.add(new TarjetaDeCreditoDTO(entity));
            } 
        }
        return list; 
    }
    
    private List<ReservaDTO> listReservaEntity2DTO(List<ReservaEntity> entityList) {
        List<ReservaDTO> list = new ArrayList<>();
        if (entityList != null ) {
            for (ReservaEntity entity : entityList) {
                list.add(new ReservaDTO(entity));
            } 
        }
        return list; 
    }
    
    private List<FacturaDTO> listFacturaEntity2DTO(List<FacturaEntity> entityList) {
        List<FacturaDTO> list = new ArrayList<>();
        if (entityList != null ) {
            for (FacturaEntity entity : entityList) {
                list.add(new FacturaDTO(entity));
            } 
        }
        return list; 
    }
    
    private List<FoodBlogDTO> listFoodBlogEntity2DTO(List<FoodBlogEntity> entityList) {
        List<FoodBlogDTO> list = new ArrayList<>();
        if (entityList != null ) {
            for (FoodBlogEntity entity : entityList) {
                list.add(new FoodBlogDTO(entity));
            } 
        }
        return list; 
    }
    
    
}
