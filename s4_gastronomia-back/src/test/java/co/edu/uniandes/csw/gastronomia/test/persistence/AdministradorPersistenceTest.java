
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.persistence;

import co.edu.uniandes.csw.gastronomia.entities.AdministradorEntity;
import co.edu.uniandes.csw.gastronomia.persistence.AdministradorPersistence;
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
 * @author Estudiante Angela Maria Suarez P
 */
@RunWith(Arquillian.class)
public class AdministradorPersistenceTest 
{
   @Inject
   AdministradorPersistence administradorPersistencia;
           
   @Inject   
   UserTransaction utx;
    
   @PersistenceContext(unitName = "gastronomiaPU")
   private EntityManager em;
   
   private List<AdministradorEntity> data = new ArrayList<>();
    
   @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    
    @Before
    public void configList() 
    {
        try
        {
        utx.begin();
        em.joinTransaction();
        em.createQuery("delete from AdministradorEntity").executeUpdate();
        PodamFactory factory = new PodamFactoryImpl();
          for(int i = 0; i < 3; i++)
          {
            AdministradorEntity admin = factory.manufacturePojo(AdministradorEntity.class);
            em.persist(admin); 
            data.add(admin);
          }
          utx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
           
    }
        
    private void clearData() {
        em.createQuery("delete from AdministardorEntity").executeUpdate();
    }

     private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AdministradorEntity entity = factory.manufacturePojo(AdministradorEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
     
     /**
     * Prueba para crear un administrador 
     */
    @Test
    public void createAdministradorEntityTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity admin = factory.manufacturePojo(AdministradorEntity.class);
        AdministradorEntity result = administradorPersistencia.create(admin);
        Assert.assertNotNull(result);
        AdministradorEntity entity = em.find(AdministradorEntity.class, result.getId()); 
        
       
        
        Assert.assertEquals(entity.getApellido(), admin.getApellido());
        Assert.assertEquals(entity.getNombre(), admin.getNombre());
        Assert.assertEquals(entity.getPhone() , admin.getPhone() );
        Assert.assertEquals(entity.getEmail() ,admin.getEmail());
        Assert.assertEquals(entity.getUsername(), admin.getUsername());
        Assert.assertEquals(entity.getContrasena() , admin.getContrasena());
        
        
        
    }
    /**
     * prueba para actualizar un admin
     */
    @Test
    public void updateAdministradorEntityTest()
    {
        AdministradorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        newEntity.setId(entity.getId());

        administradorPersistencia.update(newEntity);

        AdministradorEntity resp = em.find(AdministradorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getContrasena() , resp.getContrasena());
        Assert.assertEquals(newEntity.getApellido(), resp.getApellido());
        Assert.assertEquals(newEntity.getNombre(),resp.getNombre());
        Assert.assertEquals(newEntity.getPhone() , resp.getPhone() );
        Assert.assertEquals(newEntity.getEmail() ,resp.getEmail());
        Assert.assertEquals(newEntity.getUsername(), resp.getUsername());
        
       
    }
    /**
     * Prueba par encontrar un admin
     */
    @Test
    public void findAdministradorTest()
    {
        AdministradorEntity admin = data.get(0); 
        AdministradorEntity entity = em.find(AdministradorEntity.class, admin.getId()); 
        Assert.assertNotNull(entity);
         Assert.assertEquals(entity.getApellido(), admin.getApellido());
        Assert.assertEquals(entity.getNombre(),admin.getNombre());
        Assert.assertEquals(entity.getPhone() , admin.getPhone() );
        Assert.assertEquals(entity.getEmail() ,admin.getEmail());
        Assert.assertEquals(entity.getUsername(), admin.getUsername());
        Assert.assertEquals(entity.getContrasena() , admin.getContrasena());
    }
    /**
     * Prueba para encontrar todos los administradores de la base de datos
     */
    @Test
    public void findAllAdministradorEntityTest()
    {
        List<AdministradorEntity> lista = administradorPersistencia.findAll(); 
        Assert.assertEquals(data.size(), lista.size());
        for(AdministradorEntity e: lista)
        {
            boolean found = false; 
            for(AdministradorEntity f: data)
            {
                if(e.getId().equals(f.getId()))
                {
                    found = true; 
                }
            }
            Assert.assertTrue(found);
        }
    }
    /**
     * 
     * Prueba para probar la eliminacion de un administrador de la base de datos
     */
    @Test
    public void deleteAdministradorEntityTest()
    {
       AdministradorEntity admin =  data.get(0); 

       administradorPersistencia.delete(admin.getId());
       AdministradorEntity borrado = em.find(AdministradorEntity.class,admin.getId()); 
       Assert.assertNull(borrado);

    }

     /**
     * 
     * Prueba para probar que funciona el método equals
     */
    @Test
    public void equalsAdministradorEntityTest()
    {
        AdministradorEntity admin = data.get(0); 
        AdministradorEntity entity = em.find(AdministradorEntity.class, admin.getId());
        
      Assert.assertTrue( !entity.equals(344));
      
    }
    
    
}
