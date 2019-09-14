/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.logic;
import co.edu.uniandes.csw.gastronomia.ejb.PlatoLogic;
import co.edu.uniandes.csw.gastronomia.entities.PlatoEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.PlatoPersistence;
import javax.inject.Inject;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.junit.Before;
import java.util.List;

/**
 *
 * @author je.canizarez
 */
@RunWith(Arquillian.class)
public class PlatoLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject 
    private PlatoLogic platoLogic;
    
    @Inject
    private UserTransaction utx;
    
    @PersistenceContext
    private EntityManager em; 
    
    
    private ArrayList<PlatoEntity> data = new ArrayList<PlatoEntity>();
    /**
     * Configuracion del ambiente
     * @return 
     */
     @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlatoEntity.class.getPackage())
                .addPackage(PlatoLogic.class.getPackage())
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
        PodamFactory factory = new PodamFactoryImpl();
          for(int i = 0; i < 3; i++)
          {
            PlatoEntity tarjeta = factory.manufacturePojo(PlatoEntity.class);
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
     * Metodo que crea un plato
     * @throws BusinessLogicException 
     */
    @Test
    public void createPlatoTest()throws BusinessLogicException
    {
        PlatoEntity plato = factory.manufacturePojo(PlatoEntity.class);
        PlatoEntity resultado = platoLogic.createPlato(plato);
        Assert.assertNotNull(resultado);
        PlatoEntity entidad = em.find(PlatoEntity.class, resultado.getId()); 
        Assert.assertEquals(entidad.getDescripcion(), resultado.getDescripcion());
        Assert.assertEquals(entidad.getNombreComida(), resultado.getNombreComida());
        Assert.assertEquals(entidad.getPrecio(), resultado.getPrecio(), 0.0);
        Assert.assertEquals(entidad.getRutaImagen(), resultado.getRutaImagen());
        
        
    }
    /**
     * Test para crear un plato con precio negativo
     * @throws BusinessLogicException 
     */
     @Test(expected = BusinessLogicException.class )
    public void createPlatoPrecioNegativoTest()throws BusinessLogicException
    {
        PlatoEntity plato = factory.manufacturePojo(PlatoEntity.class);
        plato.setPrecio(-1);
        PlatoEntity resultado = platoLogic.createPlato(plato);
    }
    /**
     * Test para crear un plato sin nombre
     * @throws BusinessLogicException 
     */
    @Test (expected = BusinessLogicException.class )
    public void createPlatoNombreNullTest()throws BusinessLogicException
    {
        PlatoEntity plato = factory.manufacturePojo(PlatoEntity.class);
        plato.setNombreComida(null);
        PlatoEntity resultado = platoLogic.createPlato(plato);
    }
    @Test(expected = BusinessLogicException.class)
    public void createPlatoExistenteTest()throws BusinessLogicException
    {
        PlatoEntity platoExistente = data.get(0); 
        platoLogic.createPlato(platoExistente);
    }
    /**
     * Test para crear un plato con una descripcion nula.
     * @throws BusinessLogicException 
     */
     @Test (expected = BusinessLogicException.class )
    public void createPlatoDescripcionNullTest()throws BusinessLogicException
    {
        PlatoEntity plato = factory.manufacturePojo(PlatoEntity.class);
        plato.setDescripcion(null);
        PlatoEntity resultado = platoLogic.createPlato(plato);
    }
    /**
     * Test para actualizar un plato
     * @throws BusinessLogicException 
     */
    @Test
    public void updatePlatoTest()throws BusinessLogicException
    {
        PlatoEntity plato = data.get(0); 
        PlatoEntity entity = factory.manufacturePojo(PlatoEntity.class); 
        entity.setId(plato.getId());
        platoLogic.updatePlato(entity.getId(), entity);
        PlatoEntity resp = em.find(PlatoEntity.class,plato.getId()); 
        Assert.assertEquals(entity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(entity.getNombreComida(), resp.getNombreComida());
        Assert.assertEquals(entity.getPrecio(), resp.getPrecio(), 0.0);
        Assert.assertEquals(entity.getRutaImagen(), resp.getRutaImagen());
    }
    /**
     * Test para actualizar plato con nombre nulo.
     * 
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class )
    public void updatePlatoNombreNullTest()throws BusinessLogicException
    {
        PlatoEntity plato = data.get(0); 
        PlatoEntity entity = factory.manufacturePojo(PlatoEntity.class); 
        entity.setNombreComida(null);
        platoLogic.updatePlato(entity.getId(), entity);
    }
    /**
     * Test para actualizar un plato con descripcion
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class )
    public void updatePlatoDescripcionNullTest()throws BusinessLogicException
    {
        PlatoEntity plato = data.get(0); 
        PlatoEntity entity = factory.manufacturePojo(PlatoEntity.class); 
        entity.setDescripcion(null);
        platoLogic.updatePlato(entity.getId(), entity);
    }
    /**
     * Test 
     * @throws BusinessLogicException 
     */
     @Test(expected = BusinessLogicException.class )
    public void updatePlatoPrecioNegativo()throws BusinessLogicException
    {
        PlatoEntity plato = data.get(0); 
        PlatoEntity entity = factory.manufacturePojo(PlatoEntity.class); 
        entity.setPrecio(-1);
        platoLogic.updatePlato(entity.getId(), entity);
    }
    
    
    /**
     * Test para consultar un plato.
     */
    @Test
    public void findPlatoTest()
    {
        PlatoEntity entity = data.get(0); 
        PlatoEntity resultado = platoLogic.findPlato(entity.getId());
        Assert.assertNotNull(resultado);
        Assert.assertEquals(entity.getPrecio(),resultado.getPrecio(), 0.0 );
        Assert.assertEquals(entity.getDescripcion(),resultado.getDescripcion());
        Assert.assertEquals(entity.getRutaImagen(),resultado.getRutaImagen());
        Assert.assertEquals(entity.getId(),resultado.getId());
    }
    /**
     * Test para consultar todos los platos.
     */
    @Test
    public void findAllPlatosTest()
    {
        List<PlatoEntity> platos = platoLogic.findAllPlato();
        Assert.assertEquals(data.size(), platos.size());
        for(PlatoEntity e: platos)
        {
            boolean found = false; 
            for(PlatoEntity f: data)
            {
                if(e.getId().equals(f.getId()))
                {
                    found = true; 
                }
            }
            Assert.assertTrue(found);
        }
    }
 
    
    
    
   
    
    
    
}
    