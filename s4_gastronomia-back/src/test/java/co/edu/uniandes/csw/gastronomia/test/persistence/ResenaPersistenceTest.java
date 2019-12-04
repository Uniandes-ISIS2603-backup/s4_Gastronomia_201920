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
import java.util.Objects;
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
public class ResenaPersistenceTest {
      @Inject
    private ResenaPersistence resenaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ResenaEntity> data = new ArrayList<>();

    /**
     * @return etorna el jar de arquilian que usa payara para crear la base dedatos.
     * 
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
     * Limpia los datos creados en la prueba de las tablas.
     */
    private void clearData() {
        em.createQuery("delete from ResenaEntity").executeUpdate();
    }

    /**
    * Inserta los datos creados en las tablas relacionales para iniciar las pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ResenaEntity entity = factory.manufacturePojo(ResenaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    /**
     * Crea el test creando una instancia de ResenaPersistence
     */
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ResenaEntity e = factory.manufacturePojo(ResenaEntity.class);
        ResenaEntity rta=resenaPersistence.create(e);
        Assert.assertNotNull(rta);
    }
    /**
     * Test del metodo buscar todos.
     */
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
     /**
     * Prueba para encontrar uno de los Foodblogs creados en el test con un id pasado por parametro
     */
    @Test
    public void findTest()
    {
        ResenaEntity entity = data.get(0);
        ResenaEntity newEntity = resenaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getCalificacion(), newEntity.getCalificacion());
    
    }
    /**
     *Prueba el metodo update pasando una resena por parametro.
     */
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
    /**
     * Prueba el metodo delete pasando por parametro el id de un a resena creada en el test.
     */
    @Test
    public void deleteTest()
    {
        ResenaEntity entity = data.get(0);
        resenaPersistence.delete(entity.getId());
        ResenaEntity deleted = em.find(ResenaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba el metodo getTexto de la clase ResenaEntity.
     */
     @Test
     public void getCalificacionTest()
     {
           ResenaEntity entity = data.get(0);
       int  entityint = entity.getCalificacion();
        Assert.assertNotNull(entityint);
        Assert.assertTrue(Objects.equals(entity.getCalificacion(), entityint));
        
     }
     
     /**
      * Preuba el metodo setTexto de la clase ResenaEntity
      */
     @Test
     public void setCalificacionTest()
     {
        ResenaEntity entity=data.get(0);
        Integer  newIntreated= 12345;
        entity.setCalificacion(newIntreated);
        Assert.assertNotNull(entity.getCalificacion());
        Assert.assertTrue(Objects.equals(entity.getCalificacion(), newIntreated));
        
     }
    /**
     * Prueba el metodo getTexto de la clase ResenaEntity.
     */
     @Test
     public void getComentarioTest()
     {
           ResenaEntity entity = data.get(0);
        String  entityString = entity.getComentario();
        Assert.assertNotNull(entityString);
        Assert.assertEquals(entity.getComentario(), entityString);
        
     }
     
     /**
      * Preuba el metodo setTexto de la clase ResenaEntity
      */
     @Test
     public void setComentarioTest()
     {
        ResenaEntity entity=data.get(0);
        
        String  newStringCreated= "Aa1";
        entity.setComentario(newStringCreated);
        Assert.assertNotNull(entity.getComentario());
        Assert.assertEquals(entity.getComentario(), newStringCreated);
     }
     
}
