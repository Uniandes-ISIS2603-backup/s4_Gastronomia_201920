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
            if(cliente.getPreferencias() != null) {
                preferencias  = new ArrayList<TipoComidaDTO>();
                for(TipoComidaEntity tipoComidaEntity: cliente.getPreferencias()) {
                    preferencias.add(new TipoComidaDTO(tipoComidaEntity));
                }
            }
            
            if (cliente.getTarjetas() != null) {
                tarjetas = new ArrayList<TarjetaDeCreditoDTO>();
                for(TarjetaDeCreditoEntity tarjetaEntity: cliente.getTarjetas()) {
                    tarjetas.add(new TarjetaDeCreditoDTO(tarjetaEntity));
                }   
            }
            
            if (cliente.getReservas() != null) {
                reservas = new ArrayList<ReservaDTO>();
                for(ReservaEntity reservaEntity: cliente.getReservas()) {
                    reservas.add(new ReservaDTO(reservaEntity));
                }
            }
            
            if (cliente.getFacturas() != null) {
                facturas = new ArrayList<FacturaDTO>();
                for(FacturaEntity facturaEntity: cliente.getFacturas()) {
                    facturas.add(new FacturaDTO(facturaEntity));
                }
            }
            
            if (cliente.getFoodBlogs() != null) {
                foodBlogs = new ArrayList<FoodBlogDTO>();
                for (FoodBlogEntity foodBlogEntity: cliente.getFoodBlogs()) {
                    foodBlogs.add(new FoodBlogDTO(foodBlogEntity));   
                }
            }   
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
}
