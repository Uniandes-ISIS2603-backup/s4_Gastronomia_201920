/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.logic;

import co.edu.uniandes.csw.gastronomia.ejb.RestauranteLogic;
import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.RestaurantePersistence;
import com.gs.collections.impl.list.fixed.ArrayAdapter;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @restaurante Estudiante
 */
@RunWith(Arquillian.class)
public class RestauranteLogicTest
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private RestauranteLogic restauranteLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<RestauranteEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RestauranteEntity.class.getPackage())
                .addPackage(RestauranteLogic.class.getPackage())
                .addPackage(RestaurantePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from RestauranteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            RestauranteEntity entity = factory.manufacturePojo(RestauranteEntity.class);
            em.persist(entity);            
            data.add(entity);
        }        
    }
    
    @Test
    public void createRestaurante()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.assertNotNull(r1);
            Assert.assertTrue(r1.equals(r));
        } 
        catch (BusinessLogicException ex) 
        {
            Assert.fail(ex.getMessage());
        }        
    }
    @Test
    public void createRestauraneSinDescuentoCumpleanosTest()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;        
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        try 
        {
            r.setDescuentaoCumpleanos(null);
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail("No se puede crear sin descuento de cumpleaños");
        } 
        catch (BusinessLogicException ex) 
        {
            //pasa
        }
    }
    @Test
    public void createRestauranteSinTestZonaFumadores()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        try 
        {
            r.setZonaDeFumadores(null);
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail("No se pude crear");
        } 
        catch (BusinessLogicException ex) 
        {
            //pasa
        }
    }
    @Test
    public void createRestauranteSinPetFriendly()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }                
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        try 
        {
            r.setPetFriendly(null);
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail("No se puede crear");
        } 
        catch (BusinessLogicException ex) 
        {
            //pasa
        }
    }
    @Test
    public void createRestauranteSinMusicaEnVivo()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        
        try 
        {
            r.setMusicaEnVivo(null);
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail("No se puede crear");
        } 
        catch (BusinessLogicException ex) 
        {
            //pasa
        }
    }
    @Test 
    public void createRestauranteYaExiste()
    {
        try {
            RestauranteEntity r = restauranteLogic.createRestaurante(data.get(0));
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    @Test
    public void createRestauranteImagenNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setImagen(null);
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestauranteImagenEmpty()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setImagen("");
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestauranteNombreNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setNombre(null);
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestauranteNombreEmpty()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setNombre("");
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestauranteContrasenaNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setContrasena(null);
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestauranteContrasenaEmpty()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setContrasena("");
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestauranteContrasenaLess8()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setContrasena("123456");
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestauranteDireccionNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setDireccion(null);
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestauranteDireccionEmpty()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setDireccion("");
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestauranteTipoNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setTipoRestaurante(null);
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestauranteTipoEmpty()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setTipoRestaurante("");
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestaurantePrecioPorPersonaNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setPrecioPorPersona(null);
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestaurantePrecioPorPersona00()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setPrecioPorPersona(0.0);
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestauranteServicioALaMesaNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setServicioALaMesa(null);
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestauranteCostoReservaNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setCostoReserva(null);
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestauranteCostoReserva00()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setCostoReserva(0.0);
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void createRestauranteHorarioNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setHorario(null);
        try 
        {
            r1 = restauranteLogic.createRestaurante(r);
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    //__________________________________________________________________________
    //tests de get
    //__________________________________________________________________________
    @Test
    public void getRestaurantesTest()
    {
        //try {
            List<RestauranteEntity> l = restauranteLogic.getRestaurantes();
            int  c = 0;
            for(RestauranteEntity x : l)
            {
                for(RestauranteEntity y: data)
                {
                    if(x.equals(y))
                    {
                        c++;
                    }
                }
            }
            Assert.assertTrue(c==data.size());
        //} catch (BusinessLogicException ex) {
            //Assert.fail("Nos epudo hacer la busqueda");
        //}
    }
    @Test
    public void getRestauranteTest()
    {
        try {
            RestauranteEntity r = data.get(0);
            RestauranteEntity r1 = restauranteLogic.getRestaurante(r.getId());
            Assert.assertTrue(r.equals(r1));
        } catch (BusinessLogicException ex) {
            Assert.fail();
        }
    }
    @Test
    public void getRestauranteTestNull()
    {
        try
        {
            RestauranteEntity r = restauranteLogic.getRestaurante(Long.MIN_VALUE);
            Assert.fail();
        }
        catch(BusinessLogicException e)
        {
            //pasa
        }
    }
    @Test
    public void getRestauranteContrasenaNombreTest()
    {
        try {
            RestauranteEntity r = data.get(0);
            RestauranteEntity r1 = restauranteLogic.getRestauranteContrasenaNombre(r.getNombre(), r.getContrasena());
            Assert.assertNotNull(r1);
            Assert.assertTrue(r1.equals(r1));
        } catch (BusinessLogicException ex) {
            Assert.fail();
        }
    }
    @Test
    public void getRestauranteNombreContrasenaNoExTest()
    {
        try
        {
            RestauranteEntity r = restauranteLogic.getRestauranteContrasenaNombre("No", "existe");
            Assert.fail();
        }
        catch(BusinessLogicException e)
        {
            //pasa
        }
    }
    @Test
    public void getRestauranteNombreContrasenaBadNameFormatEmptyStringTest()
    {
        try
        {
            RestauranteEntity r = restauranteLogic.getRestauranteContrasenaNombre("", "existe");
            Assert.fail("Empty String");
        }
        catch(BusinessLogicException e)
        {
            //No pasa nada
        }        
    }
    @Test
    public void getRestauranteNombreContrasenaBadNameFormatNullTest()
    {
        try
        {
            RestauranteEntity r = restauranteLogic.getRestauranteContrasenaNombre(null, "existe");
            Assert.fail("Null String");
        }
        catch(BusinessLogicException e)
        {
            //No pasa nada
        }        
    }
    @Test
    public void getRestauranteNombreContrasenaBadPassFormatEmptyStringTest()
    {
        try
        {
            RestauranteEntity r = restauranteLogic.getRestauranteContrasenaNombre("No", "");
            Assert.fail("Empty String");
        }
        catch(BusinessLogicException e)
        {
            //No pasa nada
        }        
    }
    @Test
    public void getRestauranteNombreContrasenaBadPassFormatNullTest()
    {
        try
        {
            RestauranteEntity r = restauranteLogic.getRestauranteContrasenaNombre("existe",null);
            Assert.fail("Null String");
        }
        catch(BusinessLogicException e)
        {
            //No pasa nada
        }        
    }
    @Test
    public void getRestaurantePetFriendlyTest()
    {
        try {
            List<RestauranteEntity> rT = new ArrayList<>();
            List<RestauranteEntity> rF = new ArrayList<>();
            for(RestauranteEntity x : data)
            {
                if(x.getPetFriendly()!=null)
                {
                    if(x.getPetFriendly())
                    {
                        rT.add(x);
                    }
                    else
                    {
                        rF.add(x);
                    }
                }
            }
            List<RestauranteEntity> rT1 = restauranteLogic.getRestaurantesPetFriendly(Boolean.TRUE);
            List<RestauranteEntity> rF1 = restauranteLogic.getRestaurantesPetFriendly(Boolean.FALSE);
            Assert.assertTrue(rT.size()==rT1.size());
            Assert.assertTrue(rT.size()==rT1.size());
            Assert.assertTrue((rT1.size()+rF1.size())==data.size());
        } catch (BusinessLogicException ex) {
            Assert.fail();
        }
    }
    @Test
    public void getRestaurantePetFriendlyNull()
    {
        try {
            List<RestauranteEntity> r = restauranteLogic.getRestaurantesPetFriendly(null);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    @Test
    public void getRestauranteMusicaEnVivoTest()
    {
        try {
            List<RestauranteEntity> rT = new ArrayList<>();
            List<RestauranteEntity> rF = new ArrayList<>();
            for(RestauranteEntity x : data)
            {
                if(x.getMusicaEnVivo()!=null)
                {
                    if(x.getMusicaEnVivo())
                    {
                        rT.add(x);
                    }
                    else
                    {
                        rF.add(x);
                    }
                }
            }
            List<RestauranteEntity> rT1 = restauranteLogic.getRestaurantesMusicaEnVivo(Boolean.TRUE);
            List<RestauranteEntity> rF1 = restauranteLogic.getRestaurantesMusicaEnVivo(Boolean.FALSE);
            Assert.assertTrue(rT.size()==rT1.size());
            Assert.assertTrue(rT.size()==rT1.size());
            Assert.assertTrue((rT1.size()+rF1.size())==data.size());
        } catch (BusinessLogicException ex) {
            Assert.fail();
        }
    }
    @Test
    public void getRestauranteMusicaEnVivoNull()
    {
        try {
            List<RestauranteEntity> r = restauranteLogic.getRestaurantesMusicaEnVivo(null);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    @Test
    public void getRestaurantesServicioALaMesaTest()
    {
        try {
            List<RestauranteEntity> rT = new ArrayList<>();
            List<RestauranteEntity> rF = new ArrayList<>();
            for(RestauranteEntity x : data)
            {
                if(x.getServicioALaMesa()!=null)
                {
                    if(x.getServicioALaMesa())
                    {
                        rT.add(x);
                    }
                    else
                    {
                        rF.add(x);
                    }
                }
            }
            List<RestauranteEntity> rT1 = restauranteLogic.getRestaurantesServicioALaMesa(Boolean.TRUE);
            List<RestauranteEntity> rF1 = restauranteLogic.getRestaurantesServicioALaMesa(Boolean.FALSE);
            Assert.assertTrue(rT.size()==rT1.size());
            Assert.assertTrue(rT.size()==rT1.size());
            Assert.assertTrue((rT1.size()+rF1.size())==data.size());
        } catch (BusinessLogicException ex) {
            Assert.fail();
        }
    }
    @Test
    public void getRestauranteServicioALaMesaNull()
    {
        try {
            List<RestauranteEntity> r = restauranteLogic.getRestaurantesServicioALaMesa(null);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    @Test
    public void getRestaurantesDescuentoCumpleanosTest()
    {
        try {
            List<RestauranteEntity> rT = new ArrayList<>();
            List<RestauranteEntity> rF = new ArrayList<>();
            for(RestauranteEntity x : data)
            {
                if(x.getDescuentaoCumpleanos()!=null)
                {
                    if(x.getDescuentaoCumpleanos())
                    {
                        rT.add(x);
                    }
                    else
                    {
                        rF.add(x);
                    }
                }
            }
            List<RestauranteEntity> rT1 = restauranteLogic.getRestaurantesDescuentoCumpleanos(Boolean.TRUE);
            List<RestauranteEntity> rF1 = restauranteLogic.getRestaurantesDescuentoCumpleanos(Boolean.FALSE);
            Assert.assertTrue(rT.size()==rT1.size());
            Assert.assertTrue(rT.size()==rT1.size());
            
        } catch (BusinessLogicException ex) {
            Assert.fail();
        }
    }
    @Test
    public void getRestauranteDescuentaoCumpleanosNull()
    {
        try {
            List<RestauranteEntity> r = restauranteLogic.getRestaurantesDescuentoCumpleanos(null);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    //--------------------------------------------------------------------------
    //Rangos
    //--------------------------------------------------------------------------
    @Test
    public void getRestaurantesPrecioReservaRango()
    {
        try {
            Double min=2.0;
            Double max=5.0;
            List<RestauranteEntity> r = new ArrayList<>();
            for(RestauranteEntity x : data)
            {
                if(x.getCostoReserva()>=min && x.getCostoReserva()<=max)
                {
                    r.add(x);
                }
            }
            List<RestauranteEntity> r1 = restauranteLogic.getRestaurantesPrecioReservaRango(max, min);
            Assert.assertTrue(r.size()==r1.size());
        } catch (BusinessLogicException ex) {
            Assert.fail();
        }
    }
    @Test 
    public void getRestaurantePrecioReservaRangoMaxNull()
    {
        try {
            List<RestauranteEntity> r1 = restauranteLogic.getRestaurantesPrecioReservaRango(null, 2.0);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    @Test 
    public void getRestaurantePrecioReservaRangoMinNull()
    {
        try {
            List<RestauranteEntity> r1 = restauranteLogic.getRestaurantesPrecioReservaRango(3.0, null);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    @Test 
    public void getRestaurantePrecioReservaRangoMaxMenorMin()
    {
        try {
            List<RestauranteEntity> r1 = restauranteLogic.getRestaurantesPrecioReservaRango(1.0, 2.0);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    @Test
    public void getRestaurantesPrecioPromedioRango()
    {
        try {
            Double min=2.0;
            Double max=5.0;
            List<RestauranteEntity> r = new ArrayList<>();
            for(RestauranteEntity x : data)
            {
                if(x.getPrecioPorPersona()>=min && x.getPrecioPorPersona()<=max)
                {
                    r.add(x);
                }
            }
            List<RestauranteEntity> r1 = restauranteLogic.getRestaurantesPrecioPromedioRango(max, min);
            Assert.assertTrue(r.size()==r1.size());
        } catch (BusinessLogicException ex) {
            Assert.fail();
        }
    }
    @Test 
    public void getRestaurantePrecioPromedioRangoMaxNull()
    {
        try {
            List<RestauranteEntity> r1 = restauranteLogic.getRestaurantesPrecioPromedioRango(null, 2.0);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    @Test 
    public void getRestaurantePrecioPromedioRangoMinNull()
    {
        try {
            List<RestauranteEntity> r1 = restauranteLogic.getRestaurantesPrecioPromedioRango(3.0, null);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    @Test 
    public void getRestaurantePrecioPromedioRangoMaxMenorMin()
    {
        try {
            List<RestauranteEntity> r1 = restauranteLogic.getRestaurantesPrecioPromedioRango(1.0, 2.0);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    @Test 
    public void getRestauranteDireccionNull()
    {
        try {
            List<RestauranteEntity> r1 = restauranteLogic.getRestaurantesDireccion(null);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    
    @Test 
    public void getRestauranteDireccionEmpty()
    {
        try {
            List<RestauranteEntity> r1 = restauranteLogic.getRestaurantesDireccion("");
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    @Test 
    public void getRestauranteNombreNull()
    {
        try {
            List<RestauranteEntity> r1 = restauranteLogic.getRestauranteNombre(null);
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    
    @Test 
    public void getRestauranteNombreEmpty()
    {
        try {
            List<RestauranteEntity> r1 = restauranteLogic.getRestauranteNombre("");
            Assert.fail();
        } catch (BusinessLogicException ex) {
            //pasa
        }
    }
    
    //--------------------------------------------------------------------------
    //delete
    //--------------------------------------------------------------------------
    @Test
    public void deleteRestauranteTest()
    {
        RestauranteEntity r = data.get(0);
        try {
            restauranteLogic.deleteRestaurante(r.getId());
        } catch (BusinessLogicException ex) {
            Assert.fail();
        }
        try
        {
            restauranteLogic.deleteRestaurante(r.getId());
        }
        catch(BusinessLogicException e)
        {
            //pasa
        }
    }
    //--------------------------------------------------------------------------
    //update
    //--------------------------------------------------------------------------
    @Test
    public void updateRestauranteNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        r.setId(data.get(0).getId());
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        try 
        {
            r1 = restauranteLogic.updateRestaurante(r,Long.parseLong(0+""));
            Assert.assertNotNull(r1);
            Assert.assertTrue(r1.equals(r));
        } 
        catch (BusinessLogicException ex) 
        {
            Assert.fail(ex.getMessage());
        }        
    }
    
    @Test
    public void updateRestaurante()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        r.setId(data.get(0).getId());
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        try 
        {
            r1 = restauranteLogic.updateRestaurante(r, r.getId());
            Assert.assertNotNull(r1);
            Assert.assertTrue(r1.equals(r));
        } 
        catch (BusinessLogicException ex) 
        {
            Assert.fail(ex.getMessage());
        }        
    }
    
    @Test
    public void updateRestauraneSinDescuentoCumpleanosTest()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;        
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        try 
        {
            r.setId(data.get(0).getId());
            r.setDescuentaoCumpleanos(null);
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail("No se puede crear sin descuento de cumpleaños");
        } 
        catch (BusinessLogicException ex) 
        {
            //pasa
        }
    }
    @Test
    public void updateRestauranteSinTestZonaFumadores()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        try 
        {
            r.setZonaDeFumadores(null);
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail("No se pude crear");
        } 
        catch (BusinessLogicException ex) 
        {
            //pasa
        }
    }
    @Test
    public void updateRestauranteSinPetFriendly()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }                
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        try 
        {
            r.setPetFriendly(null);
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail("No se puede crear");
        } 
        catch (BusinessLogicException ex) 
        {
            //pasa
        }
    }
    @Test
    public void updateRestauranteSinMusicaEnVivo()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        
        try 
        {
            r.setMusicaEnVivo(null);
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail("No se puede crear");
        } 
        catch (BusinessLogicException ex) 
        {
            //pasa
        }
    }    
    @Test
    public void updateRestauranteImagenNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setImagen(null);
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestauranteImagenEmpty()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setImagen("");
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestauranteNombreNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setNombre(null);
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestauranteNombreEmpty()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setNombre("");
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestauranteContrasenaNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setContrasena(null);
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestauranteContrasenaEmpty()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setContrasena("");
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestauranteContrasenaLess8()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setContrasena("123456");
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestauranteDireccionNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setDireccion(null);
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestauranteDireccionEmpty()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setDireccion("");
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestauranteTipoNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setTipoRestaurante(null);
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestauranteTipoEmpty()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setTipoRestaurante("");
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestaurantePrecioPorPersonaNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setPrecioPorPersona(null);
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestaurantePrecioPorPersona00()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setPrecioPorPersona(0.0);
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestauranteServicioALaMesaNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setServicioALaMesa(null);
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestauranteCostoReservaNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setCostoReserva(null);
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestauranteCostoReserva00()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setCostoReserva(0.0);
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
    @Test
    public void updateRestauranteHorarioNull()
    {
        RestauranteEntity r = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity r1;
        int n;
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleanos(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleanos(Boolean.TRUE);
           }
        }
        if(r.getZonaDeFumadores()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setZonaDeFumadores(Boolean.FALSE);
           }
           else
           {
               r.setZonaDeFumadores(Boolean.TRUE);
           }
        }        
        if(r.getPetFriendly()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setPetFriendly(Boolean.FALSE);
           }
           else
           {
               r.setPetFriendly(Boolean.TRUE);
           }
        }
        if(r.getMusicaEnVivo()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setMusicaEnVivo(Boolean.FALSE);
           }
           else
           {
               r.setMusicaEnVivo(Boolean.TRUE);
           }
        }
        r.setHorario(null);
        try 
        {
            r.setId(data.get(0).getId());
            r1 = restauranteLogic.updateRestaurante(r,r.getId());
            Assert.fail();
        } 
        catch (BusinessLogicException ex) 
        {
            //Pasa
        }
    }
}
