/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.persistence;

import co.edu.uniandes.csw.gastronomia.entities.ReservaEntity;
import co.edu.uniandes.csw.gastronomia.persistence.ReservaPersistence;
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
 * @author Cristina González
 */
@RunWith(Arquillian.class)
public class ReservaPersistenceTest {
    
    @Inject
    ReservaPersistence reservaPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<ReservaEntity> data = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
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
    private void clearData() {
        em.createQuery("delete from ReservaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un reserva.
     */
    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity result = reservaPersistence.create(newEntity);
        Assert.assertNotNull(result);
        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getMotivo(), entity.getMotivo());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
        Assert.assertEquals(newEntity.getNumPersonas(), entity.getNumPersonas());
        Assert.assertEquals(newEntity.getNombreCliente(),  entity.getNombreCliente());
        Assert.assertEquals(newEntity.isCancelada(), entity.isCancelada());
        Assert.assertEquals(newEntity.getNumeroContacto(), entity.getNumeroContacto());       
    }
    
    /**
     * Prueba para consultar una reserva.
     */
    @Test
    public void findTest() {
        ReservaEntity entity = data.get(0);
        ReservaEntity newEntity = reservaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getMotivo(), entity.getMotivo());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
        Assert.assertEquals(newEntity.getNumPersonas(), entity.getNumPersonas());
        Assert.assertEquals(newEntity.getNombreCliente(),  entity.getNombreCliente());
        Assert.assertEquals(newEntity.isCancelada(), entity.isCancelada());
        Assert.assertEquals(newEntity.getNumeroContacto(), entity.getNumeroContacto());
    }
    
    /**
     * Prueba para consultar la lista de reservas.
     */
    @Test
    public void findAllTest() {
        List<ReservaEntity> list = reservaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ReservaEntity ent : list) {
            boolean found = false;
            for (ReservaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para eliminar una reserva.
     */
    @Test
    public void deleteTest() {
        ReservaEntity entity = data.get(0);
        reservaPersistence.delete(entity.getId());
        ReservaEntity deleted = em.find(ReservaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una reserva.
     */
    @Test
    public void updateTest() {
        ReservaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);

        newEntity.setId(entity.getId());

        reservaPersistence.update(newEntity);

        ReservaEntity resp = em.find(ReservaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getMotivo(), resp.getMotivo());
        Assert.assertEquals(newEntity.getFecha(), resp.getFecha());
        Assert.assertEquals(newEntity.getNumPersonas(), resp.getNumPersonas());
        Assert.assertEquals(newEntity.getNombreCliente(),  resp.getNombreCliente());
        Assert.assertEquals(newEntity.isCancelada(), resp.isCancelada());
        Assert.assertEquals(newEntity.getNumeroContacto(), resp.getNumeroContacto());
    }
}
