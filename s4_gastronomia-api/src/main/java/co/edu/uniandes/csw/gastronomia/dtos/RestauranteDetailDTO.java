/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.dtos;

import co.edu.uniandes.csw.gastronomia.entities.PlatoEntity;
import co.edu.uniandes.csw.gastronomia.entities.ReservaEntity;
import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiante
 */
public class RestauranteDetailDTO extends RestauranteDTO implements Serializable {

    private AdministradorDTO administrador;
    private List<PlatoDTO> platos = new ArrayList<>();
    private List<ReservaDTO> reservas = new ArrayList<>();

    public RestauranteDetailDTO(RestauranteEntity r) {
        super(r);
        if (r != null) {
            administrador = new AdministradorDTO(r.getAdministrador());
            List<PlatoEntity> lista = r.getPlatos();
            for (PlatoEntity p : lista) {
                platos.add(new PlatoDTO(p));
            }
            List<ReservaEntity> lista1 = r.getReservas();
            for (ReservaEntity p : lista1) {
                reservas.add(new ReservaDTO(p));
            }
        }

    }

    public RestauranteDetailDTO() {
        super();
    }

    @Override
    public RestauranteEntity toEntity() {
        RestauranteEntity r = super.toEntity();
        r.setAdministrador(administrador.toEntity());
        List<PlatoEntity> lista = new ArrayList<>();
        for (PlatoDTO pp : platos) {
            lista.add(pp.toEntity());
        }
        r.setPlatos(lista);
        List<ReservaEntity> lista1 = new ArrayList<>();
        for (ReservaDTO pp : reservas) {
            lista1.add(pp.toEntity());
        }
        r.setReservas(lista1);
        return r;
    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    public AdministradorDTO getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorDTO admi) {
        administrador = admi;
    }

    public List<PlatoDTO> getPlatos() {
        return platos;
    }

    public void setPlatos(List<PlatoDTO> platos) {
        this.platos = platos;
    }

}
