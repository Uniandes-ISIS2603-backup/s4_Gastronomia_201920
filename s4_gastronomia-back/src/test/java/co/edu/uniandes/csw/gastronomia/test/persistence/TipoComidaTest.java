/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.persistence;


import co.edu.uniandes.csw.gastronomia.entities.TipoComidaEntity;
import co.edu.uniandes.csw.gastronomia.persistence.TipoComidaPersistence;
import java.util.*;
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
public class TipoComidaTest 
{
    @Inject
    private TipoComidaPersistence tPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

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
        em.createQuery("delete from TipoComidaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData()
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TipoComidaEntity entity = factory.manufacturePojo(TipoComidaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un tipo de comida.
     */
    @Test
    public void createTipoTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        TipoComidaEntity newEntity = factory.manufacturePojo(TipoComidaEntity.class);
        TipoComidaEntity result = tPersistence.create(newEntity);

        Assert.assertNotNull(result);

        TipoComidaEntity entity = em.find(TipoComidaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para consultar la lista de tipos de comidas.
     */
    @Test
    public void getListTipoTest() {
        List<TipoComidaEntity> list = tPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(TipoComidaEntity ent : list) 
        {
            boolean found = false;
            for (TipoComidaEntity entity : data)
            {
                if (ent.getId().equals(entity.getId())) {
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
    public void getTipoTest() 
    {
        TipoComidaEntity entity = data.get(0);
        TipoComidaEntity newEntity = tPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
    }

    /**
     * Prueba para eliminar un tipo de comida.
     */
    @Test
    public void deleteTipoTest() {
        TipoComidaEntity entity = data.get(0);
        tPersistence.delete(entity.getId());
        TipoComidaEntity deleted = em.find(TipoComidaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un tipo de comida.
     */
    @Test
    public void updateTipoTest()
    {
        TipoComidaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TipoComidaEntity newEntity = factory.manufacturePojo(TipoComidaEntity.class);

        newEntity.setId(entity.getId());

        tPersistence.update(newEntity);

        TipoComidaEntity resp = em.find(TipoComidaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }

    
}
