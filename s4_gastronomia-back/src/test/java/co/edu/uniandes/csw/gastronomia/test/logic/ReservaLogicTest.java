/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.logic;

import co.edu.uniandes.csw.gastronomia.ejb.ReservaLogic;
import co.edu.uniandes.csw.gastronomia.entities.ReservaEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.ReservaPersistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
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
 * @author Cristina Isabel González Osorio
 */
@RunWith(Arquillian.class)
public class ReservaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject 
    ReservaLogic reservaLogic;
    
    @Inject
    ReservaPersistence reservaPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<ReservaEntity> data = new ArrayList<ReservaEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaLogic.class.getPackage())
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
    
    @Test
    public void createReserva() throws BusinessLogicException {
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        newEntity.setNumPersonas(1);
        newEntity.setCancelada(false);
        ReservaEntity result = reservaLogic.createReserva(newEntity);
        Assert.assertNotNull(result);
        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getMotivo(), entity.getMotivo());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
        Assert.assertEquals(newEntity.getNumPersonas(), entity.getNumPersonas());
        Assert.assertEquals(newEntity.isCancelada(), entity.isCancelada());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createReservaFechaNull() throws BusinessLogicException {
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        newEntity.setNumPersonas(1);
        newEntity.setCancelada(false);
        newEntity.setFecha(null);
        reservaLogic.createReserva(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createReservaNumPersonasInvalido() throws BusinessLogicException {
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        newEntity.setFecha(new Date(System.currentTimeMillis()+86400000));
        newEntity.setCancelada(false);
        newEntity.setNumPersonas(0);
        reservaLogic.createReserva(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createReservaCancelada() throws BusinessLogicException {
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        newEntity.setFecha(new Date(System.currentTimeMillis()+86400000));
        newEntity.setNumPersonas(1);
        newEntity.setCancelada(true);
        reservaLogic.createReserva(newEntity);
    }
    
    /**
     * Prueba para consultar la lista de reservas.
     */
    @Test
    public void getReservasTest() {
        List<ReservaEntity> list = reservaLogic.getReservas();
        Assert.assertEquals(data.size(), list.size());
        for (ReservaEntity entity : list) {
            boolean found = false;
            for (ReservaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una reserva.
     */
    @Test
    public void getReservaTest() {
        ReservaEntity entity = data.get(0);
        ReservaEntity resultEntity = reservaLogic.getReserva(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getMotivo(), entity.getMotivo());
        Assert.assertEquals(resultEntity.getFecha(), entity.getFecha());
        Assert.assertEquals(resultEntity.getNumPersonas(), entity.getNumPersonas());
        Assert.assertEquals(resultEntity.isCancelada(), entity.isCancelada());
    }

    /**
     * Prueba para actualizar una reserva.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateReservaTest() throws BusinessLogicException {
        ReservaEntity entity = data.get(0);
        ReservaEntity pojoEntity = factory.manufacturePojo(ReservaEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setNumPersonas(1);
        reservaLogic.updateReserva(pojoEntity.getId(), pojoEntity);
        ReservaEntity resp = em.find(ReservaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getMotivo(), resp.getMotivo());
        Assert.assertEquals(pojoEntity.getFecha(), resp.getFecha());
        Assert.assertEquals(pojoEntity.getNumPersonas(), resp.getNumPersonas());
        Assert.assertEquals(pojoEntity.isCancelada(), resp.isCancelada());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateReservaFechaNull() throws BusinessLogicException {
        ReservaEntity entity = data.get(0);
        ReservaEntity pojoEntity = factory.manufacturePojo(ReservaEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setNumPersonas(1);
        pojoEntity.setFecha(null);
        reservaLogic.updateReserva(pojoEntity.getId(), pojoEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateReservaNumPersonasInvalido() throws BusinessLogicException {
        ReservaEntity entity = data.get(0);
        ReservaEntity pojoEntity = factory.manufacturePojo(ReservaEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setFecha(new Date(System.currentTimeMillis()+86400000));
        pojoEntity.setNumPersonas(0);
        reservaLogic.updateReserva(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para eliminar una reserva.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteReservaTest() throws BusinessLogicException {
        ReservaEntity entity = data.get(0);
        reservaLogic.deleteReserva(entity.getId());
        ReservaEntity deleted = em.find(ReservaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
