/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.logic;
import co.edu.uniandes.csw.gastronomia.ejb.PlatoLogic;
import co.edu.uniandes.csw.gastronomia.entities.PlatoEntity;
import co.edu.uniandes.csw.gastronomia.entities.RestauranteEntity;
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
    
    
    private RestauranteEntity restaurante; 
    
    
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
        em.createQuery("delete from RestauranteEntity").executeUpdate();

        
        restaurante = factory.manufacturePojo(RestauranteEntity.class);
        em.persist(restaurante);
        
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
     * Metodo que crea un plato
     * @throws BusinessLogicException 
     */
    @Test
    public void createPlatoTest()throws BusinessLogicException
    {
        PlatoEntity plato = factory.manufacturePojo(PlatoEntity.class);
        plato.setRestaurante(restaurante);
        PlatoEntity resultado = platoLogic.createPlato(restaurante.getId(), plato);
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
        plato.setRestaurante(restaurante);
        plato.setPrecio(-1.0);
        PlatoEntity resultado = platoLogic.createPlato(restaurante.getId(),  plato);
    }
    /**
     * Test para crear un plato sin nombre
     * @throws BusinessLogicException 
     */
    @Test (expected = BusinessLogicException.class )
    public void createPlatoNombreNullTest()throws BusinessLogicException
    {
        PlatoEntity plato = factory.manufacturePojo(PlatoEntity.class);
        plato.setRestaurante(restaurante);
        plato.setNombreComida(null);
        PlatoEntity resultado = platoLogic.createPlato(restaurante.getId(), plato);
    }
    @Test(expected = BusinessLogicException.class)
    public void createPlatoExistenteTest()throws BusinessLogicException
    {
        PlatoEntity platoExistente = data.get(0);
        platoLogic.createPlato( restaurante.getId(), platoExistente);
    }
    /**
     * Test para crear un plato con una descripcion nula.
     * @throws BusinessLogicException 
     */
     @Test (expected = BusinessLogicException.class )
    public void createPlatoDescripcionNullTest()throws BusinessLogicException
    {
        PlatoEntity plato = factory.manufacturePojo(PlatoEntity.class);
        plato.setRestaurante(restaurante);
        plato.setDescripcion(null);
        PlatoEntity resultado = platoLogic.createPlato(restaurante.getId(), plato);
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
        entity.setPrecio(-1.0);
        platoLogic.updatePlato(entity.getId(), entity);
    }
    
    
    /**
     * Test para consultar un plato.
     */
    @Test
    public void findPlatoTest()
    {
        PlatoEntity entity = data.get(0); 
        PlatoEntity resultado = platoLogic.findPlato(restaurante.getId(), entity.getId());
        Assert.assertNotNull(resultado);
        Assert.assertEquals(entity.getPrecio(),resultado.getPrecio(), 0.0 );
        Assert.assertEquals(entity.getDescripcion(),resultado.getDescripcion());
        Assert.assertEquals(entity.getRutaImagen(),resultado.getRutaImagen());
        Assert.assertEquals(entity.getId(),resultado.getId());
    }
    
    /**
     * Test para crear un plato a un restaurante inexistente
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class )
    public void deleteTarjetaDeCreditoTestClienteInexistente() throws BusinessLogicException
    {
        PlatoEntity tarjeta = data.get(0);
        RestauranteEntity nuevo = factory.manufacturePojo(RestauranteEntity.class);
        platoLogic.deletePlato(nuevo.getId(), tarjeta.getId());
    
    }
    /**
     * Test para borrar un plato
     * @throws BusinessLogicException
     */
    @Test
    public void deleteTarjetaDeCreditoTest()throws BusinessLogicException
    {
      
      PlatoEntity plato = data.get(0);

      platoLogic.deletePlato(restaurante.getId(), plato.getId());
      
      PlatoEntity borrada = em.find(PlatoEntity.class,plato.getId()); 
      Assert.assertNull(borrada);
    }
    
    
  
 
    
    
    
   
    
    
    
}
    