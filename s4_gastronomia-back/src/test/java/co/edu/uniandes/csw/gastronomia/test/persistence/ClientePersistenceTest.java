/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.persistence;

import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
import co.edu.uniandes.csw.gastronomia.entities.FacturaEntity;
import co.edu.uniandes.csw.gastronomia.entities.FoodBlogEntity;
import co.edu.uniandes.csw.gastronomia.entities.ReservaEntity;
import co.edu.uniandes.csw.gastronomia.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.gastronomia.entities.TipoComidaEntity;
import co.edu.uniandes.csw.gastronomia.persistence.ClientePersistence;
import co.edu.uniandes.csw.gastronomia.persistence.FacturaPersistence;
import co.edu.uniandes.csw.gastronomia.persistence.FoodBlogPersistence;
import co.edu.uniandes.csw.gastronomia.persistence.ReservaPersistence;
import co.edu.uniandes.csw.gastronomia.persistence.TarjetaDeCreditoPersistence;
import co.edu.uniandes.csw.gastronomia.persistence.TipoComidaPersistence;
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
 * @author Cristina Isabel González Osorio
 */
@RunWith(Arquillian.class)
public class ClientePersistenceTest {
    
    @Inject
    ClientePersistence clientePersistence;
    
    @Inject
    TarjetaDeCreditoPersistence tarjetaPersistence;
    
    @Inject
    FoodBlogPersistence foogBlogPersistence;
    
    @Inject
    TipoComidaPersistence preferenciasPersistence;
    
    @Inject
    FacturaPersistence facturaPersistence;
    
    @Inject
    ReservaPersistence reservaPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<ClienteEntity> data = new ArrayList<>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClientePersistence.class.getPackage())
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
        em.createQuery("delete from FacturaEntity").executeUpdate();
        em.createQuery("delete from TipoComidaEntity").executeUpdate();
        em.createQuery("delete from FoodBlogEntity").executeUpdate();
        em.createQuery("delete from TarjetaDeCreditoEntity").executeUpdate();
        em.createQuery("delete from ClienteEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ClienteEntity entity = factory.manufacturePojo(ClienteEntity.class);
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
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        ClienteEntity result = clientePersistence.create(newEntity);
        
        TarjetaDeCreditoEntity tarjetaEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        tarjetaEntity.setCliente(result);
        TarjetaDeCreditoEntity tarjetaResult = tarjetaPersistence.create(tarjetaEntity);
        ArrayList tarjetas = new ArrayList<>();
        tarjetas.add(tarjetaResult);
        newEntity.setTarjetas(tarjetas);
        
        FoodBlogEntity foodBlogEntity = factory.manufacturePojo(FoodBlogEntity.class);
        foodBlogEntity.setCliente(result);
        FoodBlogEntity foodBlogResult = foogBlogPersistence.create(foodBlogEntity);
        ArrayList foodBlogs = new ArrayList<>();
        foodBlogs.add(foodBlogResult);
        //newEntity.setFoodBlogs(foodBlogs);
        
        TipoComidaEntity tipoComidaEntity = factory.manufacturePojo(TipoComidaEntity.class);
        tipoComidaEntity.setCliente(result);
        TipoComidaEntity tipoComidaResult = preferenciasPersistence.create(tipoComidaEntity);
        ArrayList preferencias = new ArrayList<>();
        preferencias.add(tipoComidaResult);
        //newEntity.setPreferencias(preferencias);
        
        FacturaEntity facturaEntity = factory.manufacturePojo(FacturaEntity.class);
        facturaEntity.setCliente(result);
        FacturaEntity facturaResult = facturaPersistence.create(facturaEntity);
        ArrayList facturas = new ArrayList<>();
        facturas.add(facturaResult);
        newEntity.setFacturas(facturas);
        
        ReservaEntity reservaEntity = factory.manufacturePojo(ReservaEntity.class);
        reservaEntity.setCliente(result);
        ReservaEntity reservaResult = reservaPersistence.create(reservaEntity);
        ArrayList reservas = new ArrayList<>();
        reservas.add(reservaResult);
        newEntity.setReservas(reservas);
        
        Assert.assertNotNull(result);
        ClienteEntity entity = em.find(ClienteEntity.class, result.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getApellido(), entity.getApellido());
        Assert.assertEquals(newEntity.getUsername(), entity.getUsername());
        Assert.assertEquals(newEntity.getEmail(), entity.getEmail());
        Assert.assertEquals(newEntity.getContrasena(), entity.getContrasena());
        Assert.assertEquals(newEntity.getCumpleanos(), entity.getCumpleanos());
        Assert.assertEquals(newEntity.getNumeroContacto(), entity.getNumeroContacto());
        Assert.assertEquals(newEntity.getPuntos(), entity.getPuntos());
        Assert.assertEquals(newEntity.getTarjetas(), entity.getTarjetas());
        Assert.assertEquals(newEntity.getFoodBlogs(), entity.getFoodBlogs());
        Assert.assertEquals(newEntity.getPreferencias(), entity.getPreferencias());
        Assert.assertEquals(newEntity.getFacturas(), entity.getFacturas());
        Assert.assertEquals(newEntity.getReservas(), entity.getReservas());
        
    }
    
    /**
     * Prueba para consultar un cliente.
     */
    @Test
    public void findTest() {
        ClienteEntity entity = data.get(0);
        ClienteEntity resultEntity = clientePersistence.find(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(resultEntity.getApellido(), entity.getApellido());
        Assert.assertEquals(resultEntity.getUsername(), entity.getUsername());
        Assert.assertEquals(resultEntity.getEmail(), entity.getEmail());
        Assert.assertEquals(resultEntity.getContrasena(), entity.getContrasena());
        Assert.assertEquals(resultEntity.getCumpleanos(), entity.getCumpleanos());
        Assert.assertEquals(resultEntity.getNumeroContacto(), entity.getNumeroContacto());
        Assert.assertEquals(resultEntity.getPuntos(), entity.getPuntos());
    }
    
    /**
     * Prueba para consultar la lista de clientes.
     */
    @Test
    public void findAllTest() {
        List<ClienteEntity> list = clientePersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ClienteEntity ent : list) {
            boolean found = false;
            for (ClienteEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para eliminar un cliente.
     */
    @Test
    public void deleteTest() {
        ClienteEntity entity = data.get(0);
        clientePersistence.delete(entity.getId());
        ClienteEntity deleted = em.find(ClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un cliente.
     */
    @Test
    public void updateTest() {
        ClienteEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);

        newEntity.setId(entity.getId());

        clientePersistence.update(newEntity);

        ClienteEntity resp = em.find(ClienteEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(newEntity.getApellido(), resp.getApellido());
        Assert.assertEquals(newEntity.getUsername(), resp.getUsername());
        Assert.assertEquals(newEntity.getEmail(), resp.getEmail());
        Assert.assertEquals(newEntity.getContrasena(), resp.getContrasena());
        Assert.assertEquals(newEntity.getCumpleanos(), resp.getCumpleanos());
        Assert.assertEquals(newEntity.getNumeroContacto(), resp.getNumeroContacto());
        Assert.assertEquals(newEntity.getPuntos(), resp.getPuntos());
    }
    
}
