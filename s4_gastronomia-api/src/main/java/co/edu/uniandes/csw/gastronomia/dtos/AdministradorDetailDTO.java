/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.AdministradorEntity;
import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase que extiende de {@link AdministradirDTO} para manejar las relaciones entre los
 * AdministradirDTO y otros DTOs. Para conocer el contenido de un AdministradirDTO vaya a la
 * documentacion de {@link AdministradirDTO}
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      
 *      "id": number,
 *      "nombre": string,
 *      "apellido": string,
 *      "username": string,
 *      "email": string,
 *      "contrasena": string,
 *      "phone": number
 *      "restaurantes": [{@link RestaurantedDTO}]
 *   }
 * </pre> Por ejemplo un autor se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *     "id": 1,
 *      "nombre": Angela Maria,
 *      "apellido": Suarez Parra,
 *      "username": amsuarezp18,
 *      "email": amsuarezp@gmail.com,
 *      "contrasena": pepitoPerez,
 *      "phone": 3125671890, 
 *      "restaurantes" : [
 *          "id"=,
 *          "imagen":,
 *          "nombre":,
 *          "contrasena":,
 *          "direccion":,
 *          "tipoRestaurante":,
 *          "precioPorPersona":,
 *          "descuentoCumpleanos":,
 *          "zonaDeFumadores":,
 *          "petFriendly":,
 *          "servicioALaMesa":,
 *          "musicaEnVivo":,
 *          "costoReserva":,
 *           "horario":
 *              
 *      ]
 *   }
 *
 * </pre>
 *
 * @author Angela 
 */
public class AdministradorDetailDTO extends AdministradorDTO implements Serializable
{
    private List<RestauranteDTO> restaurantes;
    
    public AdministradorDetailDTO()
    {
        super();
    }
    
    public AdministradorDetailDTO(AdministradorEntity admin)
    {
        super(admin);
        if( admin != null)
        {
            restaurantes = new ArrayList<>();
            for ( RestauranteEntity entityrestaurante : admin.getRestaurantes())
            {
                restaurantes.add(new RestauranteDTO(entityrestaurante));
            }
            {
                
            }
        }
    }
    
    
    @Override
    public AdministradorEntity toEntity()
    {
        AdministradorEntity admin = super.toEntity();
        if( restaurantes != null)
        {
            List<RestauranteEntity> restauranteEntity = new ArrayList<>();
            for ( RestauranteDTO dtoRestaurantes : restaurantes)
            {
                restauranteEntity.add(dtoRestaurantes.toEntity());
            }
            admin.setRestaurantes(restauranteEntity);
        }
        return admin;
    }
    
    public List<RestauranteDTO> getRestaurantes()
    {
        return restaurantes;
    }
    
    public void setRestaurantes(List<RestauranteDTO> restaurantes)
    {
        this.restaurantes = restaurantes;
    }
    

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
