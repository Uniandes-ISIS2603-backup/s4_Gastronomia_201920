/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.logic;
import co.edu.uniandes.csw.gastronomia.ejb.TarjetaDeCreditoLogic;
import co.edu.uniandes.csw.gastronomia.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.TarjetaDeCreditoPersistence;
import java.security.SecureRandom;
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
public class TarjetaDeCreditoLogicTest {
      private PodamFactory factory = new PodamFactoryImpl();
    @Inject 
    private TarjetaDeCreditoLogic tarjetaLogic;
    @PersistenceContext
    private EntityManager em; 
    
    SecureRandom random = new SecureRandom();
    
    
    /**
     * Configuracion del ambiente
     * @return 
     */
     @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaDeCreditoEntity.class.getPackage())
                .addPackage(TarjetaDeCreditoLogic.class.getPackage())
                .addPackage(TarjetaDeCreditoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Metodo para crear una tarjeta de credito
     * @throws BusinessLogicException 
     */
    @Test
    public void createTarjetaDeCredito() throws BusinessLogicException
    {
        TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class); 
        TarjetaDeCreditoEntity resultado = tarjetaLogic.createTarjetaDeCredito(tarjeta);
        Assert.assertNotNull(resultado);
        TarjetaDeCreditoEntity entidad = em.find(TarjetaDeCreditoEntity.class, resultado.getId());
        Assert.assertEquals(entidad.getCvv(), resultado.getCvv());
        Assert.assertEquals(entidad.getFechaDeVencimiento(), resultado.getFechaDeVencimiento());
        Assert.assertEquals(entidad.getNumero(), resultado.getNumero());
    }
    @Test(expected = BusinessLogicException.class )
    public void createTarjetaDeCreditoBancoNullTest()
    {
        
        
        
    }
    
}
