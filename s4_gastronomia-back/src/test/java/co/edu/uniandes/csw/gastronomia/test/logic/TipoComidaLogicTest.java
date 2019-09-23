/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.logic;

import co.edu.uniandes.csw.gastronomia.ejb.TipoComidaLogic;
import co.edu.uniandes.csw.gastronomia.entities.TipoComidaEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.TipoComidaPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import java.util.ArrayList;
import java.util.List;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/**
 *
 * @author af.benitez
 */
@RunWith(Arquillian.class)
public class TipoComidaLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private TipoComidaLogic tipoComidaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<TipoComidaEntity> data = new ArrayList<TipoComidaEntity>();

    

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TipoComidaEntity.class.getPackage())
                .addPackage(TipoComidaLogic.class.getPackage())
                .addPackage(TipoComidaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
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
        catch (Exception e) 
        {
            e.printStackTrace();
            try 
            {
                utx.rollback();
            } 
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() 
    {
        em.createQuery("delete from TipoComidaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
         PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {
            TipoComidaEntity entity = factory.manufacturePojo(TipoComidaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un tipo comida
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createTipoComidaTest() throws BusinessLogicException 
    {
        TipoComidaEntity newEntity = factory.manufacturePojo(TipoComidaEntity.class);
        
        TipoComidaEntity result = tipoComidaLogic.createTipoComida(newEntity);
        Assert.assertNotNull(result);
        
        TipoComidaEntity entity = em.find(TipoComidaEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
     
        
    }

    /**
     * Prueba para crear una factura con nombre == ""
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createTipoComidaTestConNombreInvalido() throws BusinessLogicException 
    {
        TipoComidaEntity newEntity = factory.manufacturePojo(TipoComidaEntity.class);
       
        newEntity.setNombre("");
        tipoComidaLogic.createTipoComida(newEntity);
    }


    /**
     * Prueba para crear un tipo comida que ya existe existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createTipoComidaTestConIdExistente() throws BusinessLogicException 
    {
        TipoComidaEntity newEntity = factory.manufacturePojo(TipoComidaEntity.class);
        
        newEntity.setId(data.get(0).getId());
        tipoComidaLogic.createTipoComida(newEntity);
    }

    
    /**
     * Prueba para consultar la lista de tipos de comidas.
     */
    @Test
    public void getTiposTest() 
    {
        List<TipoComidaEntity> list = tipoComidaLogic.getTipos();
        Assert.assertEquals(data.size(), list.size());
        for (TipoComidaEntity entity : list) 
        {
            boolean found = false;
            for (TipoComidaEntity storedEntity : data)
            {
                if (entity.getId().equals(storedEntity.getId()))
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un tipo de comida.
     */
    @Test
    public void getTipoComidaTest() 
    {
        TipoComidaEntity entity = data.get(0);
        TipoComidaEntity resultEntity = tipoComidaLogic.getTipoComida(entity.getId());
        Assert.assertNotNull(resultEntity);
        
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());  
    }

    /**
     * Prueba para actualizar un tipo de comida.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateTipoComidaTest() throws BusinessLogicException 
    {
        TipoComidaEntity entity = data.get(0);
        TipoComidaEntity pojoEntity = factory.manufacturePojo(TipoComidaEntity.class);
        pojoEntity.setId(entity.getId());
        tipoComidaLogic.updateTipoComida(pojoEntity.getId(), pojoEntity);
        TipoComidaEntity resp = em.find(TipoComidaEntity.class, entity.getId());
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }

    /**
     * Prueba para eliminar una factura.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteTipoComidaTest() throws BusinessLogicException 
    {
        TipoComidaEntity entity = data.get(0);
        tipoComidaLogic.deleteTipoComida(entity.getId());
        TipoComidaEntity deleted = em.find(TipoComidaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}