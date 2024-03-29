    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.logic;
import co.edu.uniandes.csw.gastronomia.ejb.TarjetaDeCreditoLogic;
import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
import co.edu.uniandes.csw.gastronomia.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.TarjetaDeCreditoPersistence;
import java.security.SecureRandom;
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
public class TarjetaDeCreditoLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject 
    private TarjetaDeCreditoLogic tarjetaLogic;
    
    @PersistenceContext
    private EntityManager em; 
    
    @Inject
    private UserTransaction utx;
    
    private ClienteEntity cliente;
    
    private List<TarjetaDeCreditoEntity> data = new ArrayList<TarjetaDeCreditoEntity>();
    
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
        
        cliente = factory.manufacturePojo(ClienteEntity.class);
        em.persist(cliente);
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
     * Metodo para crear una tarjeta de credito
     * @throws BusinessLogicException 
     */
    @Test
    public void createTarjetaDeCredito() throws BusinessLogicException
    {
        TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        tarjeta.setCliente(cliente);
        TarjetaDeCreditoEntity resultado = tarjetaLogic.createTarjetaDeCredito(cliente.getId(),tarjeta);
        Assert.assertNotNull(resultado);
        TarjetaDeCreditoEntity entidad = em.find(TarjetaDeCreditoEntity.class, resultado.getId());
        Assert.assertEquals(entidad.getCvv(), resultado.getCvv());
        Assert.assertEquals(entidad.getFechaVencimiento(), resultado.getFechaVencimiento());
        Assert.assertEquals(entidad.getNumero(), resultado.getNumero());
    }
    /**
     * Test para crear una tarjeta con Cvv negativo
     * @throws BusinessLogicException 
     */
     @Test(expected = BusinessLogicException.class )
    public void createTarjetaDeCreditoCvvNegativoTest()throws BusinessLogicException
    {
        
        TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class); 
        tarjeta.setCliente(cliente);
        tarjeta.setCvv(-11);
        TarjetaDeCreditoEntity resultado = tarjetaLogic.createTarjetaDeCredito(cliente.getId(),tarjeta);
    }
    /**
     * Test para crear una tarjeta con un Cvv que no es de 3 digitos.
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class )
    public void createTarjetaDeCreditoCvvNoValidoTest()throws BusinessLogicException
    {
        
        TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class); 
        tarjeta.setCliente(cliente);
        tarjeta.setCvv(4444);
        TarjetaDeCreditoEntity resultado = tarjetaLogic.createTarjetaDeCredito(cliente.getId(),tarjeta);
    }
    /**
     * Test para crear una tarjeta con un numero que no es de 16 digitos.
     * @throws BusinessLogicException 
     */
     @Test(expected = BusinessLogicException.class )
    public void createTarjetaDeCreditoNumeroNoValidoTest()throws BusinessLogicException
    {
        
        TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class); 
        tarjeta.setCliente(cliente);
        tarjeta.setNumero(new Long(44444));
        TarjetaDeCreditoEntity resultado = tarjetaLogic.createTarjetaDeCredito(cliente.getId(),tarjeta);
    }
    /**
     * Test para crear una tarjeta con un numero negativo
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class )
    public void createTarjetaDeCreditoNumeroNegativoValidoTest()throws BusinessLogicException
    {
        
        TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class); 
        tarjeta.setCliente(cliente);
        tarjeta.setNumero(Long.parseLong("-44444444444444"));
        TarjetaDeCreditoEntity resultado = tarjetaLogic.createTarjetaDeCredito(cliente.getId(),tarjeta);
    }
    /**
     * Test para crear una tarjeta con un banco no soportado. 
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class )
    public void createTarjetaDeCreditoBancoNoValidoTest()throws BusinessLogicException
    {
        
        TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class); 
        tarjeta.setCliente(cliente);
        tarjeta.setNumero(Long.parseLong("111111111111111"));
        TarjetaDeCreditoEntity resultado = tarjetaLogic.createTarjetaDeCredito(cliente.getId(), tarjeta);
    }
    /**
     * Test para actualizar una tarjeta de credito
     * @throws BusinessLogicException 
     */
    @Test
    public void updateTarjetaDeCreditoTest() throws BusinessLogicException
    {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        tarjeta.setId(entity.getId());
        tarjetaLogic.updatetarjetaDeCredito(tarjeta.getId(), tarjeta);
        TarjetaDeCreditoEntity result = em.find(TarjetaDeCreditoEntity.class, entity.getId()); 
        
      Assert.assertEquals(tarjeta.getCvv(),result.getCvv());
      Assert.assertEquals(tarjeta.getFechaVencimiento(),result.getFechaVencimiento());
      Assert.assertEquals(tarjeta.getNumero(),result.getNumero());
    }
    /**
     * Test para verficar regla de negocio. Cvv no puede ser negativo
     * @throws BusinessLogicException d
     */
    @Test(expected = BusinessLogicException.class )
    public void updateTarjetaCvvNegativoTest() throws BusinessLogicException
    {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        tarjeta.setId(entity.getId());
        tarjeta.setCvv(-12);
        tarjetaLogic.updatetarjetaDeCredito(tarjeta.getId(), tarjeta);
    }
    /**
     * Test para verificar la regla de negocio Cvv no valido.
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class )
    public void updateTarjetaCvvNoValidoTest() throws BusinessLogicException
    {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        tarjeta.setId(entity.getId());
        tarjeta.setCvv(45656465);
        tarjetaLogic.updatetarjetaDeCredito(tarjeta.getId(), tarjeta);
    }
    /**
     * Test para verificar la regla de negocio. La fecha no puede ser null.
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class )
    public void updateTarjetaFechaNullTest() throws BusinessLogicException
    {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        tarjeta.setId(entity.getId());
        tarjeta.setFechaVencimiento(null);
        tarjetaLogic.updatetarjetaDeCredito(tarjeta.getId(), tarjeta);
    }
    /**
     * Test para verificar que el numero de la tarjeta de credito tenga la longitud deseada.
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class )
    public void updateTarjetaNumeroNoValidoTest() throws BusinessLogicException
    {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        tarjeta.setId(entity.getId());
        tarjeta.setNumero(new Long(44444));
        tarjetaLogic.updatetarjetaDeCredito(tarjeta.getId(), tarjeta);
    }
    /**
     * Test para verficar que la tarjeta de credito no tenga un numero negativo
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class )
    public void updateTarjetaNumeroNegativoTest() throws BusinessLogicException
    {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        tarjeta.setId(entity.getId());
        tarjeta.setNumero(Long.parseLong("-444444444444444"));
        tarjetaLogic.updatetarjetaDeCredito(tarjeta.getId(), tarjeta);
    }
    /**
     * Test para vertificar que la tarjeta pertenezca a un Banco valido.
     * @throws BusinessLogicException 
     */
    @Test(expected = BusinessLogicException.class )
    public void updateTarjetaBancoNoValidoTest() throws BusinessLogicException
    {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity tarjeta = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        tarjeta.setId(entity.getId());
        tarjeta.setNumero(Long.parseLong("1111111111111111"));
        tarjetaLogic.updatetarjetaDeCredito(tarjeta.getId(), tarjeta);
    }
    /**
     * Test para consultar una tarjeta de credito
     */
    @Test
    public void findTarjetaTest()
    {
       TarjetaDeCreditoEntity entity = data.get(0);
       TarjetaDeCreditoEntity resultado = tarjetaLogic.findTarjetaDeCredito(cliente.getId(), entity.getId());
       Assert.assertNotNull(resultado);
       Assert.assertEquals(resultado.getNumero(), entity.getNumero());
       Assert.assertEquals(resultado.getCvv(), entity.getCvv());
       Assert.assertEquals(resultado.getFechaVencimiento(), entity.getFechaVencimiento());
    }
    
    
     /**
      * Test para eliminar una tarjeta que no esta asociada a un cliente
      * @throws BusinessLogicException 
      */
     @Test(expected = BusinessLogicException.class )
    public void deleteTarjetaDeCreditoTestClienteInexistente() throws BusinessLogicException
    {
        TarjetaDeCreditoEntity tarjeta = data.get(0);
        ClienteEntity nuevo = factory.manufacturePojo(ClienteEntity.class);
        tarjetaLogic.deleteTarjetaDeCredito( nuevo.getId(), tarjeta.getId());
    
    }
    
    /**
     * Test para borrar una tarjeta decredito
     * @throws BusinessLogicException
     */
    @Test
    public void deleteTarjetaDeCreditoTest()throws BusinessLogicException
    {
      
      TarjetaDeCreditoEntity tarjeta = data.get(0);

      tarjetaLogic.deleteTarjetaDeCredito( cliente.getId(), tarjeta.getId());
      
      TarjetaDeCreditoEntity borrada = em.find(TarjetaDeCreditoEntity.class,tarjeta.getId()); 
      Assert.assertNull(borrada);
    }
   
            
                
            
   
    
}
