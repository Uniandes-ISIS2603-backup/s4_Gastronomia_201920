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
        
        Assert.assertTrue(entity.equals(newEntity));
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
        Assert.assertTrue(newEntity.equals(e));
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
        Assert.assertTrue(resp.equals(newEntity));
    }
    
    @Test
    public void deleteRestauranteTest()
    {
        RestauranteEntity e = data.get(0);
        persistencia.delete(e.getId());
        RestauranteEntity r = em.find(RestauranteEntity.class, e.getId());
        Assert.assertNull(r);
    }
    
    @Test
    public void findNombreTest1()
    {
        PodamFactory factory = new PodamFactoryImpl();
        RestauranteEntity e = factory.manufacturePojo(RestauranteEntity.class);
        persistencia.create(e);
        RestauranteEntity n = persistencia.findNombre(e.getNombre()).get(0);
        Assert.assertNotNull(n);
        Assert.assertTrue(e.equals(n));
    }
    @Test
    public void findNombreTestN()
    {
        PodamFactory factory = new PodamFactoryImpl();
        RestauranteEntity e = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity e1 = factory.manufacturePojo(RestauranteEntity.class);
        e1.setNombre(e.getNombre());
        persistencia.create(e);
        persistencia.create(e1);
        List<RestauranteEntity> n = persistencia.findNombre(e.getNombre());
        int c=0;
        for(RestauranteEntity x: n)
        {
            Assert.assertNotNull(x);
            if(x.equals(e))
            {
                c++;
            }
            else if(x.equals(e1))
            {
                c++;
            }
        }
        Assert.assertNotNull(n);
        Assert.assertTrue(c==2);
    }
    @Test
    public void findNombreTestNoExiste()
    {
        PodamFactory factory = new PodamFactoryImpl();
        RestauranteEntity e = factory.manufacturePojo(RestauranteEntity.class);
        
        List<RestauranteEntity> n = persistencia.findNombre(e.getNombre());
        Assert.assertTrue(n.isEmpty());
        
    }
    @Test
    public void findPreccioPromedioRangoTest()
    {
        List<RestauranteEntity> r =new ArrayList<>();
        Double min = 2.0;
        Double max= 5.0;
        for(RestauranteEntity x : data)
        {
            if(x.getPrecioPorPersona()>=min & x.getPrecioPorPersona()<=min)
            {
                r.add(x);
            }
        }
        List<RestauranteEntity> r1 = persistencia.findPrecioPromedioRango(min, max);
        Assert.assertTrue(r1.size()==r.size());
        if(!r.isEmpty())
        {
            int c=0;
            for(RestauranteEntity x : r1)
            {
                for(RestauranteEntity y : r)
                {
                    if(x.equals(y))
                    {
                        c++;
                    }
                }
            }
            Assert.assertTrue(c==r.size());
        }
    }
    @Test
    public void findNombrePasswordTest()
    {
        RestauranteEntity r = data.get(0);
        RestauranteEntity r1=persistencia.findNombrePassword(r.getNombre(),r.getContrasena() );
        Assert.assertNotNull(r1);
        Assert.assertTrue(r.equals(r1));
    }
    @Test
    public void findNombrePasswordNoExTest()
    {
        RestauranteEntity r1=persistencia.findNombrePassword("","");
        Assert.assertNull(r1);
       
    }
    @Test
    public void findPreccioReservaRangoTest()
    {
        List<RestauranteEntity> r =new ArrayList<>();
        Double min = 2.0;
        Double max= 5.0;
        for(RestauranteEntity x : data)
        {
            if(x.getCostoReserva()>=min & x.getCostoReserva()<=min)
            {
                r.add(x);
            }
        }
        List<RestauranteEntity> r1 = persistencia.findPrecioReservaRango(min, max);
        Assert.assertTrue(r1.size()==r.size());
        if(!r.isEmpty())
        {
            int c=0;
            for(RestauranteEntity x : r1)
            {
                for(RestauranteEntity y : r)
                {
                    if(x.equals(y))
                    {
                        c++;
                    }
                }
            }
            Assert.assertTrue(c==r.size());
        }
    }
    @Test
    public void findDescuentoCumpleanosTest()
    {
        List<RestauranteEntity> t = new ArrayList<>();
        List<RestauranteEntity> f = new ArrayList<>();
        for(RestauranteEntity x : data)
        {
            
            if(x.getDescuentaoCumpleanos()!=null)
            {
                if(x.getDescuentaoCumpleanos())
                {
                    t.add(x);
                }
                else
                {
                    f.add(x);
                }
            }
        }
        List<RestauranteEntity> t1 = persistencia.findDescuentoCumpleanos(Boolean.TRUE);
        List<RestauranteEntity> f1 = persistencia.findDescuentoCumpleanos(Boolean.FALSE);
        Assert.assertTrue(t.size()==t1.size());
        Assert.assertTrue(f.size()==f1.size());
        if(!t.isEmpty())
        {
            int ct=0;
            for(RestauranteEntity x : t1)
            {
                for(RestauranteEntity y : t)
                {
                    if(x.equals(y))
                    {
                        ct++;
                    }
                }
            }
            Assert.assertTrue(ct==t.size());
        }
        if(!f.isEmpty())
        {
            int cf=0;
            for(RestauranteEntity x : f1)
            {
                for(RestauranteEntity y : f)
                {
                    if(x.equals(y))
                    {
                        cf++;
                    }
                }
            }
            Assert.assertTrue(cf==t.size());
        }
    }
    @Test
    public void findServicioALaMesaTest()
    {
        List<RestauranteEntity> t = new ArrayList<>();
        List<RestauranteEntity> f = new ArrayList<>();
        for(RestauranteEntity x : data)
        {
            
            if(x.getServicioALaMesa()!=null)
            {
                if(x.getServicioALaMesa())
                {
                    t.add(x);
                }
                else
                {
                    f.add(x);
                }
            }
        }
        List<RestauranteEntity> t1 = persistencia.findServicioALaMesa(Boolean.TRUE);
        List<RestauranteEntity> f1 = persistencia.findServicioALaMesa(Boolean.FALSE);
        Assert.assertTrue(t.size()==t1.size());
        Assert.assertTrue(f.size()==f1.size());
        if(!t.isEmpty())
        {
            int ct=0;
            for(RestauranteEntity x : t1)
            {
                for(RestauranteEntity y : t)
                {
                    if(x.equals(y))
                    {
                        ct++;
                    }
                }
            }
            Assert.assertTrue(ct==t.size());
        }
        if(!f.isEmpty())
        {
            int cf=0;
            for(RestauranteEntity x : f1)
            {
                for(RestauranteEntity y : f)
                {
                    if(x.equals(y))
                    {
                        cf++;
                    }
                }
            }
            Assert.assertTrue(cf==t.size());
        }
    }
    @Test
    public void findMusicaEnVivoTest()
    {
        List<RestauranteEntity> t = new ArrayList<>();
        List<RestauranteEntity> f = new ArrayList<>();
        for(RestauranteEntity x : data)
        {
            
            if(x.getMusicaEnVivo()!=null)
            {
                if(x.getMusicaEnVivo())
                {
                    t.add(x);
                }
                else
                {
                    f.add(x);
                }
            }
        }
        List<RestauranteEntity> t1 = persistencia.findMusicaEnVivo(Boolean.TRUE);
        List<RestauranteEntity> f1 = persistencia.findMusicaEnVivo(Boolean.FALSE);
        Assert.assertTrue(t.size()==t1.size());
        Assert.assertTrue(f.size()==f1.size());
        if(!t.isEmpty())
        {
            int ct=0;
            for(RestauranteEntity x : t1)
            {
                for(RestauranteEntity y : t)
                {
                    if(x.equals(y))
                    {
                        ct++;
                    }
                }
            }
            Assert.assertTrue(ct==t.size());
        }
        if(!f.isEmpty())
        {
            int cf=0;
            for(RestauranteEntity x : f1)
            {
                for(RestauranteEntity y : f)
                {
                    if(x.equals(y))
                    {
                        cf++;
                    }
                }
            }
            Assert.assertTrue(cf==t.size());
        }
    }
    @Test
    public void findPetFriendlyTest()
    {
        List<RestauranteEntity> t = new ArrayList<>();
        List<RestauranteEntity> f = new ArrayList<>();
        for(RestauranteEntity x : data)
        {
            
            if(x.getPetFriendly()!=null)
            {
                if(x.getPetFriendly())
                {
                    t.add(x);
                }
                else
                {
                    f.add(x);
                }
            }
        }
        List<RestauranteEntity> t1 = persistencia.findPetFriendly(Boolean.TRUE);
        List<RestauranteEntity> f1 = persistencia.findPetFriendly(Boolean.FALSE);
        Assert.assertTrue(t.size()==t1.size());
        Assert.assertTrue(f.size()==f1.size());
        if(!t.isEmpty())
        {
            int ct=0;
            for(RestauranteEntity x : t1)
            {
                for(RestauranteEntity y : t)
                {
                    if(x.equals(y))
                    {
                        ct++;
                    }
                }
            }
            Assert.assertTrue(ct==t.size());
        }
        if(!f.isEmpty())
        {
            int cf=0;
            for(RestauranteEntity x : f1)
            {
                for(RestauranteEntity y : f)
                {
                    if(x.equals(y))
                    {
                        cf++;
                    }
                }
            }
            Assert.assertTrue(cf==t.size());
        }
    }
    @Test
    public void findDireccionTest1()
    {
        PodamFactory factory = new PodamFactoryImpl();
        RestauranteEntity e = factory.manufacturePojo(RestauranteEntity.class);
        persistencia.create(e);
        RestauranteEntity n = persistencia.findDireccion(e.getDireccion()).get(0);
        Assert.assertNotNull(n);
        Assert.assertTrue(e.equals(n));
    }
    @Test
    public void findDireccionTestN()
    {
        PodamFactory factory = new PodamFactoryImpl();
        RestauranteEntity e = factory.manufacturePojo(RestauranteEntity.class);
        RestauranteEntity e1 = factory.manufacturePojo(RestauranteEntity.class);
        e1.setDireccion(e.getDireccion());
        persistencia.create(e);
        persistencia.create(e1);
        List<RestauranteEntity> n = persistencia.findDireccion(e.getDireccion());
        int c=0;
        for(RestauranteEntity x: n)
        {
            Assert.assertNotNull(x);
            if(x.equals(e))
            {
                c++;
            }
            else if(x.equals(e1))
            {
                c++;
            }
        }
        Assert.assertNotNull(n);
        Assert.assertTrue(c==2);
    }
    @Test
    public void findDireccionTestNoExiste()
    {
        PodamFactory factory = new PodamFactoryImpl();
        RestauranteEntity e = factory.manufacturePojo(RestauranteEntity.class);
        
        List<RestauranteEntity> n = persistencia.findDireccion(e.getDireccion());
        Assert.assertTrue(n.isEmpty());
        
    }    
}
