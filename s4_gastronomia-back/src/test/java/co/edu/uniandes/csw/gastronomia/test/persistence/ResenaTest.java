/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.persistence;

import co.edu.uniandes.csw.gastronomia.entities.ResenaEntity;
import co.edu.uniandes.csw.gastronomia.persistence.ResenaPersistence;
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
public class ResenaTest 
{
     @Inject
    private ResenaPersistence rPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ResenaEntity> data = new ArrayList<ResenaEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ResenaEntity.class.getPackage())
                .addPackage(ResenaPersistence.class.getPackage())
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
        em.createQuery("delete from BookEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ResenaEntity entity = factory.manufacturePojo(ResenaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Book.
     */
    @Test
    public void createBookTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ResenaEntity newEntity = factory.manufacturePojo(ResenaEntity.class);
        ResenaEntity result = rPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ResenaEntity entity = em.find(ResenaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
        Assert.assertEquals(newEntity.getIsbn(), entity.getIsbn());
        Assert.assertEquals(newEntity.getImage(), entity.getImage());
    }

    /**
     * Prueba para consultar la lista de Books.
     */
    @Test
    public void getBooksTest() {
        List<BookEntity> list = bookPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (BookEntity ent : list) {
            boolean found = false;
            for (BookEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Book.
     */
    @Test
    public void getBookTest() {
        BookEntity entity = data.get(0);
        BookEntity newEntity = bookPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getName(), newEntity.getName());
        Assert.assertEquals(entity.getDescription(), newEntity.getDescription());
        Assert.assertEquals(entity.getIsbn(), newEntity.getIsbn());
        Assert.assertEquals(entity.getImage(), newEntity.getImage());
    }

    /**
     * Prueba para eliminar un Book.
     */
    @Test
    public void deleteBookTest() {
        BookEntity entity = data.get(0);
        bookPersistence.delete(entity.getId());
        BookEntity deleted = em.find(BookEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Book.
     */
    @Test
    public void updateBookTest() {
        BookEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        BookEntity newEntity = factory.manufacturePojo(BookEntity.class);

        newEntity.setId(entity.getId());

        bookPersistence.update(newEntity);

        BookEntity resp = em.find(BookEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getName(), resp.getName());
        Assert.assertEquals(newEntity.getDescription(), resp.getDescription());
        Assert.assertEquals(newEntity.getIsbn(), resp.getIsbn());
        Assert.assertEquals(newEntity.getImage(), resp.getImage());
    }

    /**
     * Prueba para consultasr un Book por ISBN.
     */
    @Test
    public void findBookByISBNTest() {
        BookEntity entity = data.get(0);
        BookEntity newEntity = bookPersistence.findByISBN(entity.getIsbn());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getIsbn(), newEntity.getIsbn());

        newEntity = bookPersistence.findByISBN(null);
        Assert.assertNull(newEntity);
    }
}
