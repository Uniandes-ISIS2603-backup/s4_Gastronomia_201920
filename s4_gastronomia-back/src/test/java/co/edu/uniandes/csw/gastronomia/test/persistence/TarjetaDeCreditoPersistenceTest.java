/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.persistence;

import co.edu.uniandes.csw.gastronomia.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.gastronomia.persistence.TarjetaDeCreditoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        Assert.assertEquals(tarjetaNueva.getBanco(),entity.getBanco());
        Assert.assertEquals(tarjetaNueva.getFechaDeVencimiento(), entity.getFechaDeVencimiento());
       
    }
    /**
     * Prueba para actualizar una tarjeta de credito
     */
    @Test
    public void updateTarjetaDeCreditoTest()
    {
         PodamFactory factory = new PodamFactoryImpl();
      TarjetaDeCreditoEntity tarjetaNueva = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
      TarjetaDeCreditoEntity tarjetaVieja = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
      
      TarjetaDeCreditoEntity nueva = tarjetaPersistence.create(tarjetaNueva);
      TarjetaDeCreditoEntity vieja = tarjetaPersistence.create(tarjetaVieja);
      
      nueva.setId(vieja.getId());
      tarjetaPersistence.update(nueva);
      TarjetaDeCreditoEntity respuesta = em.find(TarjetaDeCreditoEntity.class, vieja.getId()); 
      Assert.assertEquals(nueva.getBanco(),respuesta.getBanco());
      Assert.assertEquals(nueva.getCvv(),respuesta.getCvv());
      Assert.assertEquals(nueva.getFechaDeVencimiento(),respuesta.getFechaDeVencimiento());
      Assert.assertEquals(nueva.getNumero(),respuesta.getNumero());
    }
    /**
     * Prueba para encontrar una tarjeta de credito
     */
    @Test
    public void findTarjetaDeCreditoTest()
    {
         PodamFactory factory = new PodamFactoryImpl();
      TarjetaDeCreditoEntity tarjetaNueva = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
      
      TarjetaDeCreditoEntity result = tarjetaPersistence.create(tarjetaNueva);
       Assert.assertNotNull(result);
       
       TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, result.getId());
       Assert.assertEquals(tarjetaNueva.getNumero(), entity.getNumero());
        Assert.assertEquals(tarjetaNueva.getCvv(), entity.getCvv());
        Assert.assertEquals(tarjetaNueva.getBanco(),entity.getBanco());
        Assert.assertEquals(tarjetaNueva.getFechaDeVencimiento(), entity.getFechaDeVencimiento());
    }
    /**
     * Prueba para eliminar una tarjeta de credito
     */
    @Test
    public void deleteTarjetaDeCreditoTest()
    {
      PodamFactory factory = new PodamFactoryImpl();
      TarjetaDeCreditoEntity tarjetaNueva = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
      
      TarjetaDeCreditoEntity result = tarjetaPersistence.create(tarjetaNueva);

      tarjetaPersistence.delete(result.getId());
      
      TarjetaDeCreditoEntity borrada = em.find(TarjetaDeCreditoEntity.class,result.getId()); 
      Assert.assertNull(borrada);
    }
    /**
     * Prueba para encontrar las tarjetas de credito
     */
    @Test
    public void findAllTarjetaDeCreditoTest()
    {
        List<TarjetaDeCreditoEntity> data = new ArrayList<TarjetaDeCreditoEntity>();
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0 ; i < 3; i++)
        {
            TarjetaDeCreditoEntity tarjetaNueva = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
            TarjetaDeCreditoEntity result = tarjetaPersistence.create(tarjetaNueva);
            data.add(result);
        }
        List<TarjetaDeCreditoEntity> lista = tarjetaPersistence.findAll();
        Assert.assertEquals(data.size(), lista.size());
        for(TarjetaDeCreditoEntity e: data)
        {
            boolean encontrado = false;
            for(TarjetaDeCreditoEntity f: data)
            {
                if(e.getId().equals(f.getId()))
                {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
        
        
    }
    
    
    
    
    
}
