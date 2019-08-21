/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.persistence;

import co.edu.uniandes.csw.gastronomia.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.gastronomia.persistence.TarjetaDeCreditoPersistence;
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
    
    
    
}
