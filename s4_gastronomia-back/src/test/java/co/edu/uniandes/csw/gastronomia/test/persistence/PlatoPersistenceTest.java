/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.persistence;

import co.edu.uniandes.csw.gastronomia.persistence.PlatoPersistence;
import co.edu.uniandes.csw.gastronomia.entities.PlatoEntity;
import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
import javax.inject.Inject;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class PlatoPersistenceTest {
    @Inject
    private PlatoPersistence platoPersistence;
    
    @Inject   
    UserTransaction utx;
    
    @PersistenceContext(unitName = "gastronomiaPU")
    private EntityManager em;
    
    private List<PlatoEntity> data = new ArrayList<PlatoEntity>();
    
    private RestauranteEntity restaurante;
    
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlatoEntity.class.getPackage())
                .addPackage(PlatoPersistence.class.getPackage())
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
        em.createQuery("delete from PlatoEntity").executeUpdate();
        em.createQuery("delete from RestauranteEntity").executeUpdate();
        PodamFactory factory = new PodamFactoryImpl();
        restaurante = factory.manufacturePojo(RestauranteEntity.class);
          for(int i = 0; i < 3; i++)
          {
            PlatoEntity plato = factory.manufacturePojo(PlatoEntity.class);
            plato.setRestaurante(restaurante);
            em.persist(plato); 
            data.add(plato);
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
     * Prueba para crear un plato 
     */
    @Test
    public void createPlatoEntityTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        PlatoEntity plato = factory.manufacturePojo(PlatoEntity.class);
        PlatoEntity result = platoPersistence.create(plato);
        Assert.assertNotNull(result);
        PlatoEntity entity = em.find(PlatoEntity.class, result.getId()); 
        Assert.assertEquals(entity.getDescripcion(),plato.getDescripcion() );
        Assert.assertEquals(entity.getNombreComida(), plato.getNombreComida());
        Assert.assertEquals(entity.getPrecio(),plato.getPrecio(),0.0);
        Assert.assertEquals(entity.getRutaImagen(), plato.getRutaImagen());
        
    }
    /**
     * prueba para actualizar un plato
     */
      @Test
    public void updatePlatoEntityTest()
    {
        PlatoEntity plato = data.get(0); 
        PodamFactory factory = new PodamFactoryImpl(); 
        PlatoEntity entity = factory.manufacturePojo(PlatoEntity.class); 
        entity.setId(plato.getId());
        platoPersistence.update(entity); 
        PlatoEntity resp = em.find(PlatoEntity.class,plato.getId()); 
        Assert.assertEquals(entity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(entity.getNombreComida(), resp.getNombreComida());
        Assert.assertEquals(entity.getPrecio(), resp.getPrecio(), 0.0);
        Assert.assertEquals(entity.getRutaImagen(), resp.getRutaImagen());
    }
    /**
     * Prueba par encontrar un plato
     */
    @Test
    public void findPlatoTest()
    {
        PlatoEntity plato = data.get(0); 
        PlatoEntity entity = platoPersistence.find(restaurante.getId(), plato.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(entity.getDescripcion(),plato.getDescripcion() );
        Assert.assertEquals(entity.getNombreComida(), plato.getNombreComida());
        Assert.assertEquals(entity.getPrecio(),plato.getPrecio(),0.0);
        Assert.assertEquals(entity.getRutaImagen(), plato.getRutaImagen());
        
    }
    
    /**
     * 
     * Prueba para probar la eliminacion de un plato de la base de datos
     */
    @Test
    public void deletePlatoEntityTest()
    {
       PlatoEntity plato =  data.get(0); 
       
       platoPersistence.delete(plato.getId());
       PlatoEntity borrado = em.find(PlatoEntity.class,plato.getId()); 
       Assert.assertNull(borrado);
        
    }
    
    


    
    
}

