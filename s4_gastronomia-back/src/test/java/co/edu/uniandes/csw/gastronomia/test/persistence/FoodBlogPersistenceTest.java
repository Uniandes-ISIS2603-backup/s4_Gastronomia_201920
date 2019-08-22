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
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
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
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from FoodBlogEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FoodBlogEntity entity = factory.manufacturePojo(FoodBlogEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        FoodBlogEntity e = factory.manufacturePojo(FoodBlogEntity.class);
        FoodBlogEntity rta=foodBlogPersistence.create(e);
        Assert.assertNotNull(rta);
    }
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
    @Test
    public void findTest()
    {
        FoodBlogEntity entity = data.get(0);
        FoodBlogEntity newEntity = foodBlogPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getTexto(), newEntity.getTexto());
    
    }
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
    @Test
    public void deleteTest()
    {
        FoodBlogEntity entity = data.get(0);
        foodBlogPersistence.delete(entity.getId());
        FoodBlogEntity deleted = em.find(FoodBlogEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}