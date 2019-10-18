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
public class RestauranteLogic 
{
    
    private static final String R = "El restaurante";
    private static final String B = "Bad password sheme";
    private static final String BOO = "El booleano de ";
    private static final Logger LOGGER = Logger.getLogger(RestauranteLogic.class.getName());

    @Inject
    private RestaurantePersistence persistence;
    
    public RestauranteEntity createRestaurante(RestauranteEntity r)throws BusinessLogicException            
    {
        LOGGER.log(Level.INFO,"Se está creando el restaurante con id={0}",r.getId());
        if(persistence.find(r.getId())!=null)
        {
            throw new BusinessLogicException(R + " ya existe");
        }
        if(r.getNombre()==null)
        {
            throw new BusinessLogicException( R + " no tiene nombre");
        }
        if(r.getNombre().isEmpty())
        {
            throw new BusinessLogicException(R + " no tiene nombre");
        }
        if(r.getContrasena()==null )
        {
            throw new BusinessLogicException(B);
        }
        if(r.getContrasena().isEmpty() )
        {
           throw new BusinessLogicException(B); 
        }
        if( r.getContrasena().length() < 8)
        {
            throw new BusinessLogicException(B); 
        }
        if(r.getZonaDeFumadores()==null)
        {
            throw new BusinessLogicException(BOO + " zona de fuamadores no puede ser nulo");
        }
        if(r.getDescuentaoCumpleanos()==null)
        {
            throw new BusinessLogicException(BOO + " descuentos de cumpleaños no puede ser nulo");
        }
        if(r.getPetFriendly()==null)
        {
            throw new BusinessLogicException(BOO + " pet friendly no puede ser nulo");
        }
        if(r.getServicioALaMesa()==null)
        {
            throw new BusinessLogicException(BOO + " servicio a la mesa no puede ser nulo");
        }
        if(r.getImagen()==null )
        {
            throw new BusinessLogicException("La imagen está vaci o no existe");
        }
        if( r.getImagen().isEmpty())
        {
            throw new BusinessLogicException("La imagen está vaci o no existe");
        }
        if(r.getDireccion()==null)
        {
            throw new BusinessLogicException("La direcion es nula o no tiene");
        }               
        if(r.getDireccion().isEmpty())
        {
            throw new BusinessLogicException("La direcion es nula o no tiene");
        }
        if(r.getHorario()==null)
        {
            throw new BusinessLogicException("No tiene horario");
        }
        if(r.getCostoReserva()== null)
        {
            throw new BusinessLogicException("El costo de reserva es nulo");
        }
        if(r.getCostoReserva()<=0.0)
        {
            throw new BusinessLogicException("El costo de reserva es negativo");
        }
        if(r.getTipoRestaurante()==null)
        {
            throw new BusinessLogicException("El tipo de restaurante no puede ser nulo o vacio");
        }
        if(r.getTipoRestaurante().isEmpty())
        {
            throw new BusinessLogicException("El tipo de restaurante no puede ser nulo o vacio");
        }
        if(r.getMusicaEnVivo()==null)
        {
            throw new BusinessLogicException(BOO + " musica en vivo no puede ser nulo");
        }
        if(r.getPrecioPorPersona() == null)
        {
            throw new BusinessLogicException("el costo promedio de persona no puede ser nulo");
        }
        if(r.getPrecioPorPersona()<=0.0)
        {
            throw new BusinessLogicException("el cosoto promedio de persona no puede ser menor a 0");
        }
        LOGGER.log(Level.INFO,"Se creó el restaurante con id={0}",r.getId());
        return persistence.create(r);
    }
    
    public List<RestauranteEntity> getRestaurantes() throws BusinessLogicException
    {
        LOGGER.log(Level.INFO,"Se está haciendo una busqueda general de todos los restaurantes");
        List<RestauranteEntity> result = persistence.findAll();
        LOGGER.log(Level.INFO,"Se termino la busqueda de los restaurantes");
        return result;
    }
    
    public RestauranteEntity getRestauranteContrasenaNombre(String nombre , String contrasena) throws BusinessLogicException
    {
        if(nombre==null)
        {
            throw new BusinessLogicException("Bad name format, null string");
        }
        if(nombre.isEmpty())
        {
            throw new BusinessLogicException("Bad name format, empty string");
        }
        if(contrasena==null)
        {
            throw new BusinessLogicException("Bad password format, null string");
        }
        if(contrasena.isEmpty())
        {
            throw new BusinessLogicException("Bad password format, empty string");
        }
        RestauranteEntity r = persistence.findNombrePassword(nombre, contrasena);
        if(r==null)
        {
            throw new BusinessLogicException("Wrong, it doesn´t exist");
        }
        return r;
    }
    
    public List<RestauranteEntity> getRestaurantesPetFriendly(Boolean b)throws  BusinessLogicException
    {
        if(b==null)
        {
            throw new BusinessLogicException("No puede mandar un booleano nulo");
        }
        return persistence.findPetFriendly(b);
    }
    
    public List<RestauranteEntity> getRestaurantesMusicaEnVivo(Boolean b)throws  BusinessLogicException
    {
        if(b==null)
        {
            throw new BusinessLogicException("No puede mandar un booleano nulo");
        }
        return persistence.findMusicaEnVivo(b);
    }
    
    public List<RestauranteEntity> getRestaurantesServicioALaMesa(Boolean b)throws  BusinessLogicException
    {
        if(b==null)
        {
            throw new BusinessLogicException("No puede mandar un booleano nulo");
        }
        return persistence.findServicioALaMesa(b);
    }
    
    public List<RestauranteEntity> getRestaurantesDescuentoCumpleanos(Boolean b)throws  BusinessLogicException
    {
        if(b==null)
        {
            throw new BusinessLogicException("No puede mandar un booleano nulo");
        }
        return persistence.findDescuentoCumpleanos(b);
    }
    
    public List<RestauranteEntity> getRestaurantesPrecioReservaRango(Double max, Double min)throws  BusinessLogicException
    {
       if(max==null)
       {
           throw new BusinessLogicException("El max no puede ser nulo");
       }
       if(min==null)
       {
           throw new BusinessLogicException("El min no puede ser nulo");
       }
       if(max<min)
       {
           throw new BusinessLogicException("El max no puede ser menor que el min");
       }
       return persistence.findPrecioReservaRango(min, max);
    }
    
    public List<RestauranteEntity> getRestaurantesPrecioPromedioRango(Double max, Double min)throws  BusinessLogicException
    {
       if(max==null)
       {
           throw new BusinessLogicException("El max no puede ser nulo");
       }
       if(min==null)
       {
           throw new BusinessLogicException("El min no puede ser nulo");
       }
       if(max<min)
       {
           throw new BusinessLogicException("El max no puede ser menor que el min");
       }
       return persistence.findPrecioPromedioRango(min, max);
    }
    
    public List<RestauranteEntity> getRestaurantesDireccion(String dir)throws BusinessLogicException
    {
        if(dir==null){
            throw new BusinessLogicException("Bad String format");
        }
        if(dir.isEmpty())
        {
            throw new BusinessLogicException("Bad String format");
        }
        return persistence.findDireccion(dir);
    }
    
    public List<RestauranteEntity> getRestauranteNombre(String nombre)throws BusinessLogicException
    {
        if(nombre==null){
            throw new BusinessLogicException("Bad name format");
        }
        if(nombre.isEmpty())
        {
            throw new BusinessLogicException("Bad name format");
        }
        return persistence.findNombre(nombre);
    }
    
    public RestauranteEntity getRestaurante(Long id) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO,"Se está haciendo una busqueda del restaurante con id={0}",id);
        RestauranteEntity r = persistence.find(id);
        if(r==null)
        {
            throw new BusinessLogicException("No existe el restaurante con id "+id);
        }
        LOGGER.log(Level.INFO,"Se encontro el restaurante con id={0}",id);
        return r;
    }
    
    public RestauranteEntity updateRestaurante(RestauranteEntity r , Long id) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO,"Se está haciendo una modificación del restaurante con id={0}",id);
        RestauranteEntity e = persistence.find(id);
        if(e==null)
        {
            throw new BusinessLogicException("no existe el restaurante que se quiere modificar");
        }
        if(r.getNombre()==null)
        {
            throw new BusinessLogicException("El restaurante no tiene nombre");
        }
        if(r.getNombre().isEmpty())
        {
            throw new BusinessLogicException("El restaurante no tiene nombre");
        }
        if(r.getContrasena()==null )
        {
            throw new BusinessLogicException("Bad password sheme ");
        }
        if(r.getContrasena().isEmpty() )
        {
           throw new BusinessLogicException("Bad password sheme "); 
        }
        if( r.getContrasena().length() < 8)
        {
            throw new BusinessLogicException("Bad password sheme "); 
        }
        if(r.getZonaDeFumadores()==null)
        {
            throw new BusinessLogicException("El booleano de zona de fuamadores no puede ser nulo");
        }
        if(r.getDescuentaoCumpleanos()==null)
        {
            throw new BusinessLogicException("El booleano de descuentos de cumpleaños no puede ser nulo");
        }
        if(r.getPetFriendly()==null)
        {
            throw new BusinessLogicException("El booleano de pet friendly no puede ser nulo");
        }
        if(r.getServicioALaMesa()==null)
        {
            throw new BusinessLogicException("El booleano de servicio a la mesa no puede ser nulo");
        }
        if(r.getImagen()==null )
        {
            throw new BusinessLogicException("La imagen está vaci o no existe");
        }
        if( r.getImagen().isEmpty())
        {
            throw new BusinessLogicException("La imagen está vaci o no existe");
        }
        if(r.getDireccion()==null)
        {
            throw new BusinessLogicException("La direcion es nula o no tiene");
        }               
        if(r.getDireccion().isEmpty())
        {
            throw new BusinessLogicException("La direcion es nula o no tiene");
        }
        if(r.getHorario()==null)
        {
            throw new BusinessLogicException("No tiene horario");
        }
        if(r.getCostoReserva()== null)
        {
            throw new BusinessLogicException("El costo de reserva es nulo");
        }
        if(r.getCostoReserva()<=0.0)
        {
            throw new BusinessLogicException("El costo de reserva es negativo");
        }
        if(r.getTipoRestaurante()==null)
        {
            throw new BusinessLogicException("El tipo de restaurante no puede ser nulo o vacio");
        }
        if(r.getTipoRestaurante().isEmpty())
        {
            throw new BusinessLogicException("El tipo de restaurante no puede ser nulo o vacio");
        }
        if(r.getMusicaEnVivo()==null)
        {
            throw new BusinessLogicException("el booleanno de musica en vivo no puede ser nulo");
        }
        if(r.getPrecioPorPersona() == null)
        {
            throw new BusinessLogicException("el costo promedio de persona no puede ser nulo");
        }
        if(r.getPrecioPorPersona()<=0.0)
        {
            throw new BusinessLogicException("el cosoto promedio de persona no puede ser menor a 0");
        }
        e = persistence.update(r);
        LOGGER.log(Level.INFO,"Se está modifico el restaurante con id={0}",id);
        return e;
    }
    
    public void deleteRestaurante(Long id) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO,"Se está borrando el restaurante con id={0}",id);
        RestauranteEntity r = persistence.find(id);
        if(r==null)
        {
            throw new BusinessLogicException("El restaurante que se quiere borrar no existe"); 
        }
        persistence.delete(id);
    }
    
}
