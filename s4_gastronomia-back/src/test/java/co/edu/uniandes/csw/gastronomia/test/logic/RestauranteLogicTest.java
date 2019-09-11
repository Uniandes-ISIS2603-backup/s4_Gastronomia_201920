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
               r.setDescuentaoCumpleaños(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleaños(Boolean.TRUE);
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
        if(r.getDescuentaoCumpleanos()==null)
        {
           n = (int)Math.random();
           if(n==0)
           {
               r.setDescuentaoCumpleaños(Boolean.FALSE);
           }
           else
           {
               r.setDescuentaoCumpleaños(Boolean.TRUE);
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
}
