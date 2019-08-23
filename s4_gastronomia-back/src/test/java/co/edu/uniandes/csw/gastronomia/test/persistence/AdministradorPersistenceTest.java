
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
    private AdministradorPersistence administradorPersistence;
    
    @PersistenceContext(unitName = "gastronomiaPU")
    private EntityManager em;
    
   @Inject
   UserTransaction utx;
   
   private List<AdministradorEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml" , "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml" , "beans.xml");
        
        
    }
    
    
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

      @Test
    public void createAdministradorTest() {
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        AdministradorEntity result = administradorPersistence.create(newEntity);

        Assert.assertNotNull(result);

        AdministradorEntity entity = em.find(AdministradorEntity.class, result.getId());

        Assert.assertEquals(entity.getId(), result.getId());
    }
    
    @Test
    public void updateAdministradorTest() {
        AdministradorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);

        newEntity.setId(entity.getId());

        administradorPersistence.update(newEntity);

        AdministradorEntity resp = em.find(AdministradorEntity.class, entity.getId());

        //Assert.assertEquals(newEntity., resp.getName());
    }
    
     @Test
    public void getAdministradorTest() {
        AdministradorEntity entity = data.get(0);
        AdministradorEntity newEntity = administradorPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
       
    }
}
