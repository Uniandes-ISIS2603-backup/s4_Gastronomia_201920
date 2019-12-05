/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.logic;

import co.edu.uniandes.csw.gastronomia.ejb.FacturaLogic;
import co.edu.uniandes.csw.gastronomia.entities.FacturaEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.FacturaPersistence;
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
 * @author af.benitez
 */
@RunWith(Arquillian.class)
public class FacturaLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FacturaLogic facturaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<FacturaEntity> data = new ArrayList<FacturaEntity>();

    

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaLogic.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
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
        em.createQuery("delete from FacturaEntity").executeUpdate();
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
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una factura
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createFacturaTest() throws BusinessLogicException 
    {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setValor(10);
        newEntity.setValorCompleto(11);
        FacturaEntity result = facturaLogic.createFactura(newEntity);
        Assert.assertNotNull(result);
        
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getValorCompleto(), entity.getValorCompleto());
        Assert.assertEquals(newEntity.getValor(), entity.getValor());
        Assert.assertEquals(newEntity.getSePago(), entity.getSePago());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
        
       
    }

    /**
     * Prueba para crear una factura con valor == 0
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaTestConValorInvalido() throws BusinessLogicException 
    {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setValor(-1);
        facturaLogic.createFactura(newEntity);
    }

     /**
     * Prueba para crear una factura con valorCompleto == 0
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaTestConValorCompletoInvalido() throws BusinessLogicException 
    {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setValorCompleto(-1);
        facturaLogic.createFactura(newEntity);
    }

    

    /**
     * Prueba para crear una factura que ya existe existente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaTestConIdExistente() throws BusinessLogicException 
    {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        
        newEntity.setId(data.get(0).getId());
        facturaLogic.createFactura(newEntity);
    }

    
    /**
     * Prueba para consultar la lista de facturas.
     */
    @Test
    public void getFacturasTest() 
    {
        List<FacturaEntity> list = facturaLogic.getFacturas();
        Assert.assertEquals(data.size(), list.size());
        for (FacturaEntity entity : list) 
        {
            boolean found = false;
            for (FacturaEntity storedEntity : data)
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
     * Prueba para consultar una factura.
     */
    @Test
    public void getFacturaTest() 
    {
        FacturaEntity entity = data.get(0);
        FacturaEntity resultEntity = facturaLogic.getFactura(entity.getId());
        Assert.assertNotNull(resultEntity);
        
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getValorCompleto(), resultEntity.getValorCompleto());
        Assert.assertEquals(entity.getValor(), resultEntity.getValor());
        Assert.assertEquals(entity.getSePago(), resultEntity.getSePago());
        Assert.assertEquals(entity.getFecha(), resultEntity.getFecha());
        

    }

    /**
     * Prueba para actualizar una factura.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateFacturaTest() throws BusinessLogicException 
    {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);
        pojoEntity.setId(entity.getId());        
        facturaLogic.updateFactura(pojoEntity.getId(), pojoEntity);
        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());
        
        
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getValorCompleto(), resp.getValorCompleto());
        Assert.assertEquals(pojoEntity.getValor(), resp.getValor());
        Assert.assertEquals(pojoEntity.getSePago(), resp.getSePago());
        Assert.assertEquals(pojoEntity.getFecha(), resp.getFecha());
    }

    /**
     * Prueba para eliminar una factura.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteFacturaTest() throws BusinessLogicException 
    {
        FacturaEntity entity = data.get(0);
        facturaLogic.deleteFactura(entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
         
    }

    
}
