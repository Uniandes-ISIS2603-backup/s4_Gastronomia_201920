/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.persistence;

import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
import co.edu.uniandes.csw.gastronomia.persistence.RestaurantePersistence;
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
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class RestaurantePersistenciaTest 
{
    @Inject 
    private RestaurantePersistence persistencia;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction ut;
    
    private List<RestauranteEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RestauranteEntity.class.getPackage())
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
            ut.begin();
            em.joinTransaction();
            clearData();
            insertData();
            ut.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                ut.rollback();
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
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            RestauranteEntity entity = factory.manufacturePojo(RestauranteEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test 
    public void createRestauranteTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        RestauranteEntity newEntity = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity result = persistencia.create(newEntity);
        
        Assert.assertNotNull(result);
        
        RestauranteEntity entity = em.find(RestauranteEntity.class, result.getId());
        
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }
    
    @Test
    public void getRestaurantesTest()
    {
        List<RestauranteEntity> list = persistencia.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(RestauranteEntity e : list)
        {
            boolean flag = false;
            for(RestauranteEntity r : data)
            {
                if(e.getId()==r.getId())
                {
                    flag=true;
                }
            }
            Assert.assertTrue(flag);
        }
        
        
    }
    
    @Test
    public void getRestauranteTest()
    {
        RestauranteEntity e = data.get(0);
        RestauranteEntity newEntity = persistencia.find(e.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(e.getNombre(), newEntity.getNombre());
        Assert.assertEquals(e.getContrasena(), newEntity.getContrasena());
        Assert.assertEquals(e.getCostoReserva(), newEntity.getCostoReserva());
        Assert.assertEquals(e.getDireccion(), newEntity.getDireccion());
        Assert.assertEquals(e.getHorario(), newEntity.getHorario());
        Assert.assertEquals(e.getPrecioPorPersona(), newEntity.getPrecioPorPersona());
        Assert.assertEquals(e.getRUTA_IMAGEN_RESTAURANTE(), newEntity.getRUTA_IMAGEN_RESTAURANTE());
        Assert.assertEquals(e.getPetFriendly(), newEntity.getPetFriendly());
    }
    
    @Test
    public void updateRestauranteTest()
    {
        RestauranteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        RestauranteEntity newEntity = factory.manufacturePojo(RestauranteEntity.class);

        newEntity.setId(entity.getId());

        persistencia.update(newEntity);

        RestauranteEntity resp = em.find(RestauranteEntity.class, entity.getId());
        Assert.assertEquals(resp.getNombre(), newEntity.getNombre());
    }
    
    @Test
    public void deleteRestauranteTest()
    {
        RestauranteEntity e = data.get(0);
        persistencia.delete(e.getId());
        RestauranteEntity r = em.find(RestauranteEntity.class, e.getId());
        Assert.assertNull(r);
    }
}
