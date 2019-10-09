/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.PlatoEntity;
import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiante
 */
public class RestauranteDetailDTO extends RestauranteDTO implements Serializable 
{
    private AdministradorDTO administrador;
    private List<PlatoDTO> platos = new ArrayList<>();
    
    public RestauranteDetailDTO(RestauranteEntity r) 
    {
        super(r);
        administrador= new AdministradorDTO(r.getAdministrador());
        List<PlatoEntity> lista= r.getPlatos();
        for(PlatoEntity p: lista)
        {
            platos.add(new PlatoDTO(p));
        }
    }
    public RestauranteDetailDTO() 
    {
        super();
    }
    public RestauranteEntity toEntity()
    {
        RestauranteEntity r = super.toEntity();
        r.setAdministrador(administrador.toEntity());
        List<PlatoEntity> lista= new ArrayList<>();
        for(PlatoDTO pp : platos)
        {
            lista.add(pp.toEntity());
        }
        r.setPlatos(lista);
        return r;
    }
    
    public AdministradorDTO getAdministrador()
    {
        return administrador;
    }
    
    public void setAdministrador(AdministradorDTO admi)
    {
        administrador=admi;
    }

    public List<PlatoDTO> getPlatos() {
        return platos;
    }

    public void setPlatos(List<PlatoDTO> platos) {
        this.platos = platos;
    }
    
}
