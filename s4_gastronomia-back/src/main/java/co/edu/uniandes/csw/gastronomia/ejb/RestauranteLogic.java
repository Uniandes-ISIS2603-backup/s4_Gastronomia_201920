/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.ejb;

import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.RestaurantePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Estudiante
 */
@Stateless
public class RestauranteLogic {

    private static final Logger LOGGER = Logger.getLogger(RestauranteLogic.class.getName());

    private static final String COND_RESTAURANTE1 = "El restaurante no tiene nombre";

    private static final String COND_RESTAURANTE2 = "El tipo de restaurante no puede ser nulo o vacio";

    private static final String B_PASS = "Bad password sheme ";

    private static final String IMAGEN = "La imagen está vaci o no existe";

    private static final String DIRECCION = "La direcion es nula o no tiene";

    private static final String BOOLEAN = "No puede mandar un booleano nulo";

    @Inject
    private RestaurantePersistence persistence;

    public RestauranteEntity createRestaurante(RestauranteEntity r) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Se está creando el restaurante con id={0}", r.getId());
        if (persistence.find(r.getId()) != null) {
            throw new BusinessLogicException("El restaurante ya existe");
        }
        checkBusinessLogic(r);
        LOGGER.log(Level.INFO, "Se creó el restaurante con id={0}", r.getId());
        return persistence.create(r);
    }

    public List<RestauranteEntity> getRestaurantes() {
        LOGGER.log(Level.INFO, "Se está haciendo una busqueda general de todos los restaurantes");
        List<RestauranteEntity> result = persistence.findAll();
        LOGGER.log(Level.INFO, "Se termino la busqueda de los restaurantes");
        return result;
    }

    public RestauranteEntity getRestauranteContrasenaNombre(String nombre, String contrasena) throws BusinessLogicException {
        if (nombre == null) {
            throw new BusinessLogicException("Bad name format, null string");
        }
        if (nombre.isEmpty()) {
            throw new BusinessLogicException("Bad name format, empty string");
        }
        if (contrasena == null) {
            throw new BusinessLogicException("Bad password format, null string");
        }
        if (contrasena.isEmpty()) {
            throw new BusinessLogicException("Bad password format, empty string");
        }
        RestauranteEntity r = persistence.findNombrePassword(nombre, contrasena);
        if (r == null) {
            throw new BusinessLogicException("Wrong, it doesn´t exist");
        }
        return r;
    }

    public List<RestauranteEntity> getRestaurantesPetFriendly(Boolean b) throws BusinessLogicException {
        if (b == null) {
            throw new BusinessLogicException(BOOLEAN);
        }
        return persistence.findPetFriendly(b);
    }

    public List<RestauranteEntity> getRestaurantesMusicaEnVivo(Boolean b) throws BusinessLogicException {
        if (b == null) {
            throw new BusinessLogicException(BOOLEAN);
        }
        return persistence.findMusicaEnVivo(b);
    }

    public List<RestauranteEntity> getRestaurantesServicioALaMesa(Boolean b) throws BusinessLogicException {
        if (b == null) {
            throw new BusinessLogicException(BOOLEAN);
        }
        return persistence.findServicioALaMesa(b);
    }

    public List<RestauranteEntity> getRestaurantesDescuentoCumpleanos(Boolean b) throws BusinessLogicException {
        if (b == null) {
            throw new BusinessLogicException(BOOLEAN);
        }
        return persistence.findDescuentoCumpleanos(b);
    }

    public List<RestauranteEntity> getRestaurantesPrecioReservaRango(Double max, Double min) throws BusinessLogicException {
        if (max == null) {
            throw new BusinessLogicException("El max no puede ser nulo");
        }
        if (min == null) {
            throw new BusinessLogicException("El min no puede ser nulo");
        }
        if (max < min) {
            throw new BusinessLogicException("El max no puede ser menor que el min");
        }
        return persistence.findPrecioReservaRango(min, max);
    }

    public List<RestauranteEntity> getRestaurantesPrecioPromedioRango(Double max, Double min) throws BusinessLogicException {
        if (max == null) {
            throw new BusinessLogicException("El max no puede ser nulo");
        }
        if (min == null) {
            throw new BusinessLogicException("El min no puede ser nulo");
        }
        if (max < min) {
            throw new BusinessLogicException("El max no puede ser menor que el min");
        }
        return persistence.findPrecioPromedioRango(min, max);
    }

    public List<RestauranteEntity> getRestaurantesDireccion(String dir) throws BusinessLogicException {
        if (dir == null) {
            throw new BusinessLogicException("Bad String format");
        }
        if (dir.isEmpty()) {
            throw new BusinessLogicException("Bad String format");
        }
        return persistence.findDireccion(dir);
    }

    public List<RestauranteEntity> getRestauranteNombre(String nombre) throws BusinessLogicException {
        if (nombre == null) {
            throw new BusinessLogicException("Bad name format");
        }
        if (nombre.isEmpty()) {
            throw new BusinessLogicException("Bad name format");
        }
        return persistence.findNombre(nombre);
    }

    public RestauranteEntity getRestaurante(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Se está haciendo una busqueda del restaurante con id={0}", id);
        RestauranteEntity r = persistence.find(id);
        if (r == null) {
            throw new BusinessLogicException("No existe el restaurante con id " + id);
        }
        LOGGER.log(Level.INFO, "Se encontro el restaurante con id={0}", id);
        return r;
    }

    public RestauranteEntity updateRestaurante(RestauranteEntity r, Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Se está haciendo una modificación del restaurante con id={0}", id);
        RestauranteEntity e = persistence.find(id);
        if (e == null) {
            throw new BusinessLogicException("no existe el restaurante que se quiere modificar");
        }
        checkBusinessLogic(r);
        e = persistence.update(r);
        LOGGER.log(Level.INFO, "Se está modifico el restaurante con id={0}", id);
        return e;
    }

    public void deleteRestaurante(Long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Se está borrando el restaurante con id={0}", id);
        RestauranteEntity r = persistence.find(id);
        if (r == null) {
            throw new BusinessLogicException("El restaurante que se quiere borrar no existe");
        }
        persistence.delete(id);
    }
    
    public void checkBusinessLogic(RestauranteEntity r) throws BusinessLogicException {
        if (r.getNombre() == null || r.getNombre().isEmpty()) {
            throw new BusinessLogicException(COND_RESTAURANTE1);
        }
        if (r.getContrasena() == null || r.getContrasena().isEmpty() || r.getContrasena().length() < 8) {
            throw new BusinessLogicException(B_PASS);
        }
        if (r.getZonaDeFumadores() == null) {
            throw new BusinessLogicException("El booleano de zona de fuamadores no puede ser nulo");
        }
        if (r.getDescuentaoCumpleanos() == null) {
            throw new BusinessLogicException("El booleano de descuentos de cumpleaños no puede ser nulo");
        }
        if (r.getPetFriendly() == null) {
            throw new BusinessLogicException("El booleano de pet friendly no puede ser nulo");
        }
        if (r.getServicioALaMesa() == null) {
            throw new BusinessLogicException("El booleano de servicio a la mesa no puede ser nulo");
        }
        if (r.getImagen() == null || r.getImagen().isEmpty()) {
            throw new BusinessLogicException(IMAGEN);
        }
        if (r.getDireccion() == null || r.getDireccion().isEmpty()) {
            throw new BusinessLogicException(DIRECCION);
        }
        
        if (r.getHorario() == null) {
            throw new BusinessLogicException("No tiene horario");
        }
        switch
        if (r.getCostoReserva() == null) {
            throw new BusinessLogicException("El costo de reserva es nulo");
        }
        if (r.getCostoReserva() <= 0.0) {
            throw new BusinessLogicException("El costo de reserva es negativo");
        }
        if (r.getTipoRestaurante() == null || r.getTipoRestaurante().isEmpty()) {
            throw new BusinessLogicException(COND_RESTAURANTE2);
        }
        if (r.getMusicaEnVivo() == null) {
            throw new BusinessLogicException("el booleanno de musica en vivo no puede ser nulo");
        }
        if (r.getPrecioPorPersona() == null) {
            throw new BusinessLogicException("el costo promedio de persona no puede ser nulo");
        }
        if (r.getPrecioPorPersona() <= 0.0) {
            throw new BusinessLogicException("el cosoto promedio de persona no puede ser menor a 0");
        }
    }
}
