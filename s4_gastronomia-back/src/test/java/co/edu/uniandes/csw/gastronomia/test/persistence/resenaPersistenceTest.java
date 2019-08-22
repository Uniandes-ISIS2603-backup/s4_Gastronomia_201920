/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.persistence;

import co.edu.uniandes.csw.gastronomia.entities.ResenaEntity;
import co.edu.uniandes.csw.gastronomia.persistence.ResenaPersistence;
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
public class resenaPersistenceTest {
      @Inject
    private ResenaPersistence resenaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ResenaEntity> data = new ArrayList<>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ResenaEntity.class.getPackage())
                .addPackage(ResenaPersistence.class.getPackage())
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
        em.createQuery("delete from ResenaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ResenaEntity entity = factory.manufacturePojo(ResenaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ResenaEntity e = factory.manufacturePojo(ResenaEntity.class);
        ResenaEntity rta=resenaPersistence.create(e);
        Assert.assertNotNull(rta);
    }
    @Test
    public void findAlltest()
    {
        List<ResenaEntity> list = resenaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ResenaEntity ent : list) {
            boolean found = false;
            for (ResenaEntity entity : data) {
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
        ResenaEntity entity = data.get(0);
        ResenaEntity newEntity = resenaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getCalificacion(), newEntity.getCalificacion());
    
    }
    @Test
    public void updateTest()
    {
        ResenaEntity entity=data.get(0);
        PodamFactory fBFactory = new PodamFactoryImpl();
        ResenaEntity newFBEntity= fBFactory.manufacturePojo(ResenaEntity.class);
        
        newFBEntity.setId(entity.getId());
        resenaPersistence.update(newFBEntity);
        ResenaEntity resp = em.find(ResenaEntity.class, entity.getId());

        Assert.assertEquals(newFBEntity.getId(), resp.getId());
        
    }
    @Test
    public void deleteTest()
    {
        ResenaEntity entity = data.get(0);
        resenaPersistence.delete(entity.getId());
        ResenaEntity deleted = em.find(ResenaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
