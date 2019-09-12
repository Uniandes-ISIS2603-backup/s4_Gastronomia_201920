/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.persistence;

import co.edu.uniandes.csw.gastronomia.entities.FacturaEntity;
import co.edu.uniandes.csw.gastronomia.persistence.FacturaPersistence;
import java.util.*;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import static org.glassfish.api.admin.Supplemental.Timing.Before;
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
public class FacturaTest 
{
     @Inject
    private FacturaPersistence fPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

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
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
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
        for (int i = 0; i < 3; i++) {
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear una factura.
     */
    @Test
    public void createFacturaTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        FacturaEntity result = fPersistence.create(newEntity);

        Assert.assertNotNull(result);

        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getValor(), entity.getValor());
        Assert.assertEquals(newEntity.getValorCompleto(), entity.getValorCompleto());
       
    }

    /**
     * Prueba para consultar la lista de facturas.
     */
    @Test
    public void getListFacturaTest() {
        List<FacturaEntity> list = fPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FacturaEntity ent : list) 
        {
            boolean found = false;
            for (FacturaEntity entity : data)
            {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un factura.
     */
    @Test
    public void getFacturaTest() 
    {
        FacturaEntity entity = data.get(0);
        FacturaEntity newEntity = fPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getValor(), newEntity.getValor());
        Assert.assertEquals(entity.getValorCompleto(), newEntity.getValorCompleto());
        
    }

    /**
     * Prueba para eliminar una factura.
     */
    @Test
    public void deleteFacturaTest() {
        FacturaEntity entity = data.get(0);
        fPersistence.delete(entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una factura.
     */
    @Test
    public void updateFacturaTest()
    {
        FacturaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);

        newEntity.setId(entity.getId());

        fPersistence.update(newEntity);

        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getValor(), resp.getValor());
        Assert.assertEquals(newEntity.getValorCompleto(), resp.getValorCompleto());
      
    }

   
}
