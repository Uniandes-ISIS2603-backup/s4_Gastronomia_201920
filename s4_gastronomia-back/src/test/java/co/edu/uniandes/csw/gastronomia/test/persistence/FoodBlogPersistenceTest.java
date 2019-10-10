/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.persistence;

import co.edu.uniandes.csw.gastronomia.entities.FoodBlogEntity;
import co.edu.uniandes.csw.gastronomia.persistence.FoodBlogPersistence;
import java.util.ArrayList;
import java.util.List;
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
 * @author David Martinez
 */
@RunWith(Arquillian.class)
public class FoodBlogPersistenceTest {
    
    @Inject
    private FoodBlogPersistence foodBlogPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<FoodBlogEntity> data = new ArrayList<>();

    /**
     * @return etorna el jar de arquilian que usa payara para crear la base dedatos.
     * 
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FoodBlogEntity.class.getPackage())
                .addPackage(FoodBlogPersistence.class.getPackage())
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
            em.joinTransaction();
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
     * Limpia los datos creados en la prueba de las tablas.
     */
    private void clearData() {
        em.createQuery("delete from FoodBlogEntity").executeUpdate();
    }

    /**
     * Inserta los datos creados en las tablas relacionales para iniciar las pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FoodBlogEntity entity = factory.manufacturePojo(FoodBlogEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
     /**
     * Crea el test creando una instancia de foodblogPersistnece
     */
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        FoodBlogEntity e = factory.manufacturePojo(FoodBlogEntity.class);
        FoodBlogEntity rta=foodBlogPersistence.create(e);
        Assert.assertNotNull(rta);
    }
    /**
     * Test del metodo buscar todos.
     */
    @Test
    public void findAlltest()
    {
        List<FoodBlogEntity> list = foodBlogPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FoodBlogEntity ent : list) {
            boolean found = false;
            for (FoodBlogEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    /**
     * Prueba para encontrar uno de los Foodblogs creados en el test con un id pasado por parametro
     */
    @Test
    public void findTest()
    {
        FoodBlogEntity entity = data.get(0);
        FoodBlogEntity newEntity = foodBlogPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getTexto(), newEntity.getTexto());
    
    }
    /**
     *Prueba el metodo update pasando un foodbog por parametro.
     */
    @Test
    public void updateTest()
    {
        FoodBlogEntity entity=data.get(0);
        PodamFactory fBFactory = new PodamFactoryImpl();
        FoodBlogEntity newFBEntity= fBFactory.manufacturePojo(FoodBlogEntity.class);
        
        newFBEntity.setId(entity.getId());
        foodBlogPersistence.update(newFBEntity);
        FoodBlogEntity resp = em.find(FoodBlogEntity.class, entity.getId());

        Assert.assertEquals(newFBEntity.getId(), resp.getId());
        
    }
    /**
     * Prueba el metodo delete pasando por parametro el id de un foodblog creado en el test.
     */
    @Test
    public void deleteTest()
    {
        FoodBlogEntity entity = data.get(0);
        foodBlogPersistence.delete(entity.getId());
        FoodBlogEntity deleted = em.find(FoodBlogEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba el metodo getTexto de la clase foodblogentity.
     */
     @Test
     public void getTextoTest()
     {
           FoodBlogEntity entity = data.get(0);
        String  entityString = entity.getTexto();
        Assert.assertNotNull(entityString);
        Assert.assertEquals(entity.getTexto(), entityString);
        
     }
     
     /**
      * Preuba el metodo setTexto de la clase fodblogentity
      */
     @Test
     public void setTextoTest()
     {
        FoodBlogEntity entity=data.get(0);
        PodamFactory fBFactory = new PodamFactoryImpl();
        String  newStringCreated= "Aa1";
        entity.setTexto(newStringCreated);
        Assert.assertNotNull(entity.getTexto());
        Assert.assertEquals(entity.getTexto(), newStringCreated);
        
     }
     
     /**
     * Prueba el metodo getTexto de la clase foodblogentity.
     */
     @Test
     public void getNumeroMeGustaTest()
     {
           FoodBlogEntity entity = data.get(0);
       Integer  entityint = entity.getNumeroMeGusta();
        Assert.assertNotNull(entityint);
        Assert.assertEquals(entityint, entity.getNumeroMeGusta());
     }
     
     /**
      * Preuba el metodo setTexto de la clase fodblogentity
      */
     @Test
     public void setNumeroMeGustaTest()
     {
        FoodBlogEntity entity=data.get(0);
        Integer  newIntreated= 12345;
        entity.setNumeroMeGusta(newIntreated);
        Assert.assertNotNull(entity.getNumeroMeGusta());
        Assert.assertEquals(entity.getNumeroMeGusta(), newIntreated);
        
     }
     
     /**
     * Prueba el metodo getTexto de la clase foodblogentity.
     */
     @Test
     public void getNumeroNoMeGustaTest()
     {
           FoodBlogEntity entity = data.get(0);
        Integer  entityint = entity.getNumeroNoMegusta();
        Assert.assertNotNull(entityint);
        Assert.assertEquals(entityint, entity.getNumeroNoMegusta());
     }
     
     /**
      * Preuba el metodo setTexto de la clase fodblogentity
      */
     @Test
     public void setNumeroNoMeGustaTest()
     {
        FoodBlogEntity entity=data.get(0);
        Integer  newIntreated= 12345;
        entity.setNumeroNoMegusta(newIntreated);
        Assert.assertNotNull(entity.getNumeroNoMegusta());
        Assert.assertEquals(entity.getNumeroNoMegusta(), newIntreated);
        
     }
     /**
     * Prueba el metodo getTexto de la clase foodblogentity.
     */
     @Test
     public void getArchivoMultimediaTest()
     {
           FoodBlogEntity entity = data.get(0);
        String  entityString = entity.getArchivoMultimedia();
        Assert.assertNotNull(entityString);
        Assert.assertEquals(entity.getArchivoMultimedia(), entityString);
        
     }
     
     /**
      * Preuba el metodo setTexto de la clase fodblogentity
      */
     @Test
     public void setArchivoMultimediaTest()
     {
        FoodBlogEntity entity=data.get(0);
        
        String  newStringCreated= "Aa1";
        entity.setArchivoMultimedia(newStringCreated);
        Assert.assertNotNull(entity.getArchivoMultimedia());
        Assert.assertEquals(entity.getArchivoMultimedia(), newStringCreated);
        
     }
     
     /**
     * Prueba el metodo getTexto de la clase foodblogentity.
     */
     @Test
     public void getComentariosTest()
     {
           FoodBlogEntity entity = data.get(0);
        String  entityString = entity.getComentarios();
        Assert.assertNotNull(entityString);
        Assert.assertEquals(entity.getComentarios(), entityString);
        
     }
     
     /**
      * Preuba el metodo setTexto de la clase fodblogentity
      */
     @Test
     public void setComentariosTest()
     {
        FoodBlogEntity entity=data.get(0);
        
        String  newStringCreated= "Aa1";
        entity.setComentarios(newStringCreated);
        Assert.assertNotNull(entity.getComentarios());
        Assert.assertEquals(entity.getComentarios(), newStringCreated);
        
     }
         
     
}