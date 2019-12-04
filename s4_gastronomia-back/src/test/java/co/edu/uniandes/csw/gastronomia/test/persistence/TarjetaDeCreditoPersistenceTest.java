/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.persistence;

import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
import co.edu.uniandes.csw.gastronomia.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.gastronomia.persistence.TarjetaDeCreditoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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
public class TarjetaDeCreditoPersistenceTest {
    @Inject
    private TarjetaDeCreditoPersistence tarjetaPersistence; 
 
    @PersistenceContext(unitName = "gastronomiaPU")
    private EntityManager em;
    
    @Inject   
    UserTransaction utx;
     
    private ClienteEntity cliente;
    
    private List<TarjetaDeCreditoEntity> data = new ArrayList<TarjetaDeCreditoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class).
                addPackage(TarjetaDeCreditoEntity.class.getPackage())
                .addPackage(TarjetaDeCreditoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
     /**
     * Configuracion inicial para probar los test
     */
    @Before
    public void configList() 
    {
        try
        {
        utx.begin();
        em.joinTransaction();
        em.createQuery("delete from TarjetaDeCreditoEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
        PodamFactory factory = new PodamFactoryImpl();
        cliente = factory.manufacturePojo(ClienteEntity.class);
          for(int i = 0; i < 3; i++)
          {
            TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
            tarjeta.setCliente(cliente);
            em.persist(tarjeta); 
            data.add(tarjeta);
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
    
    
    
 /**
  * Prueba para crear una tarjeta de credio
  */
    @Test
    public void createTarjetaDeCreditoTest()
    {
      PodamFactory factory = new PodamFactoryImpl();
      TarjetaDeCreditoEntity tarjetaNueva = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
      
      TarjetaDeCreditoEntity result = tarjetaPersistence.create(tarjetaNueva);
      Assert.assertNotNull(result);
       
      TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, result.getId());
      Assert.assertEquals(tarjetaNueva.getNumero(), entity.getNumero());
      Assert.assertEquals(tarjetaNueva.getCvv(), entity.getCvv());
      Assert.assertEquals(tarjetaNueva.getFechaVencimiento(), entity.getFechaVencimiento());
       
    }
    /**
     * Prueba para actualizar una tarjeta de credito
     */
    @Test
    public void updateTarjetaDeCreditoTest()
    {
      PodamFactory factory = new PodamFactoryImpl();
      TarjetaDeCreditoEntity tarjeta = data.get(0);
      TarjetaDeCreditoEntity entity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
      entity.setId(tarjeta.getId());
      tarjetaPersistence.update(entity);
      TarjetaDeCreditoEntity resp = em.find(TarjetaDeCreditoEntity.class, tarjeta.getId());
      Assert.assertEquals(entity.getCvv(),resp.getCvv());
      Assert.assertEquals(entity.getFechaVencimiento(),resp.getFechaVencimiento());
      Assert.assertEquals(entity.getNumero(),resp.getNumero());
    }
    /**
     * Prueba para encontrar una tarjeta de credito
     */
    @Test
    public void findTarjetaDeCreditoTest()
    {
      PodamFactory factory = new PodamFactoryImpl();
      TarjetaDeCreditoEntity tarjeta = data.get(0);
      TarjetaDeCreditoEntity entity  = tarjetaPersistence.find(cliente.getId(), tarjeta.getId());
      
      Assert.assertNotNull(entity);
       
      Assert.assertEquals(tarjeta.getNumero(), entity.getNumero());
      Assert.assertEquals(tarjeta.getCvv(), entity.getCvv());
      Assert.assertEquals(tarjeta.getFechaVencimiento(), entity.getFechaVencimiento());
    }
    /**
     * Prueba para eliminar una tarjeta de credito
     */
    @Test
    public void deleteTarjetaDeCreditoTest()
    {
      
     TarjetaDeCreditoEntity result = data.get(0);
     tarjetaPersistence.delete(result.getId());
      
     TarjetaDeCreditoEntity borrada = em.find(TarjetaDeCreditoEntity.class,result.getId()); 
      Assert.assertNull(borrada);
    }
 
}
