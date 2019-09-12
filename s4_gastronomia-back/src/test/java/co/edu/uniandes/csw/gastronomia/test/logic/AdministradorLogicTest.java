/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.logic;

import co.edu.uniandes.csw.gastronomia.ejb.AdministradorLogic;
import co.edu.uniandes.csw.gastronomia.entities.AdministradorEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
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
 * @author Angela Suárez 
 */
@RunWith(Arquillian.class)
public class AdministradorLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private AdministradorLogic administradorLogic;
    
    @PersistenceContext
    private EntityManager em; 
    
    @Inject
    private UserTransaction utx;
    
    private List<AdministradorEntity> data = new ArrayList<AdministradorEntity>();
    
   /**
    *
    * @return Devuelve el jar que Arquilliam va a desplegar en payara embebido
    * El jar contiene las clases, el descriptor de la base de datos y el archivo
    * beans.xml para resolver la inyección de dependencia
    */
    
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(AdministradorLogic.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
    *
    * Configuración Inicial de la prueba
    */
    @Before
    public void configTest()
    {
        try
        {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            try
            {
                utx.rollback();
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
            }
        }
        
    }
    
    /**
    *
    * Limpia las tablas que está implicadas en la prueba
    */
    private void clearData()
    {
        em.createQuery("delete from AdministradorEntity").executeUpdate();
       
    }
    
     /**
    *
    * Inserta los datos iniciales para el correcto funcionamiento de las pruebas
    */
    private void insertData()
    {
        for( int i = 0 ; i < 3 ; i ++)
        {
            AdministradorEntity entity = factory.manufacturePojo(AdministradorEntity.class);
            em.persist(entity);
            data.add(entity);
         }
    }
    
     /**
    *
    * Prueba para crear un administrador
    * @Throws co.edu.uniandes.ccs.pararalo.BusinessLogicException
    */
    @Test
    public void createAdministradorTest() throws BusinessLogicException
    {
        AdministradorEntity newAdministrador = factory.manufacturePojo(AdministradorEntity.class);
        AdministradorEntity result = administradorLogic.createAdministrador(newAdministrador);
        Assert.assertNotNull(result);
        AdministradorEntity entity = em.find(AdministradorEntity.class, result.getId());
        Assert.assertEquals(newAdministrador.getId(), entity.getId());
        Assert.assertEquals(newAdministrador.getApellido(), entity.getApellido());
        Assert.assertEquals(newAdministrador.getNombre(), entity.getNombre());
        Assert.assertEquals(newAdministrador.getContrasena(), entity.getContrasena());
        Assert.assertEquals(newAdministrador.getEmail(), entity.getEmail());
        Assert.assertEquals(newAdministrador.getUsername(), entity.getUsername());
        Assert.assertEquals(newAdministrador.getPhone(), entity.getPhone());
    }
    
    @Test( expected = BusinessLogicException.class)
    public void createAdministradorUserNameNull() throws BusinessLogicException
    {
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        newEntity.setUsername(null);
        AdministradorEntity resultado = administradorLogic.createAdministrador(newEntity);
    }
     /**
    *
    * Prueba para consultar un administrador
    * @Throws co.edu.uniandes.ccs.pararalo.BusinessLogicException
    */
    @Test public void getAdministradorTest()
    {
        AdministradorEntity getAdministrador = data.get(0);
        AdministradorEntity entity = administradorLogic.getAdministrador(getAdministrador.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(getAdministrador.getEmail(), entity.getEmail());
        Assert.assertEquals(getAdministrador.getUsername(), entity.getUsername());
        Assert.assertEquals(getAdministrador.getPhone(), entity.getPhone());
        Assert.assertEquals(getAdministrador.getContrasena(), entity.getContrasena());
        Assert.assertEquals(getAdministrador.getId(), entity.getId());
        Assert.assertEquals(getAdministrador.getNombre(), entity.getNombre());
        Assert.assertEquals(getAdministrador.getApellido(), entity.getApellido());
    }
    
    /**
    *
    * Prueba para consultar la lista de  administradores
    * @Throws co.edu.uniandes.ccs.pararalo.BusinessLogicException
    */
    @Test
    public void getAdministradoresTes()
    {
        List<AdministradorEntity> lista = administradorLogic.getAdministradores();
        Assert.assertEquals(data.size(), lista.size());
        for ( AdministradorEntity entity : lista)
        {
        boolean found = false;
            for ( AdministradorEntity storedEntity : data)
            {
                if ( entity.getId().equals(storedEntity.getId()) )
                {
                    found = true;
                }
            }
                Assert.assertTrue(found);
        
        }
        
    }
    
    @Test
    public void updateAdministradorTest() throws BusinessLogicException 
    {
        AdministradorEntity entity = data.get(0);
        AdministradorEntity pojoEntity = factory.manufacturePojo(AdministradorEntity.class);
        pojoEntity.setId(entity.getId());
        administradorLogic.updateAdministrador(pojoEntity.getId(), pojoEntity);
        AdministradorEntity resp = em.find(AdministradorEntity.class, entity.getId());
         Assert.assertEquals(pojoEntity.getEmail(), resp.getEmail());
        Assert.assertEquals(pojoEntity.getUsername(), resp.getUsername());
        Assert.assertEquals(pojoEntity.getPhone(), resp.getPhone());
        Assert.assertEquals(pojoEntity.getContrasena(), resp.getContrasena());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getApellido(), resp.getApellido());
   
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateAdministradorConUserNameInvdalido() throws BusinessLogicException
    {
        //update
        AdministradorEntity entity  = data.get(0);
        AdministradorEntity pojoEntity = factory.manufacturePojo(AdministradorEntity.class);
        pojoEntity.setUsername(null);
        pojoEntity.setId(entity.getId());
        administradorLogic.updateAdministrador(pojoEntity.getId(), pojoEntity);
        
        
    }
    
     
    
    @Test 
    public void deleteAdministrador() throws BusinessLogicException 
    {
        AdministradorEntity admin = data.get(1);
        administradorLogic.deleteAdministrador(admin.getId());
    }
}
