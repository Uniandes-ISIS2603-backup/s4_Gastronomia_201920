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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


/**
 *
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class PlatoLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    @Inject 
    private PlatoLogic platoLogic;
    @PersistenceContext
    private EntityManager em; 
    
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
    
    
    
   
    
    
    
}
    