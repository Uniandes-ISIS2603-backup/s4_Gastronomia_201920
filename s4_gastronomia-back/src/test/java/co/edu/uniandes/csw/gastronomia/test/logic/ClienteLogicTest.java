/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.logic;

import co.edu.uniandes.csw.gastronomia.ejb.ClienteLogic;
import co.edu.uniandes.csw.gastronomia.entities.ClienteEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.ClientePersistence;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Cristina Isabel González Osorio
 */
@RunWith(Arquillian.class)
public class ClienteLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject 
    ClienteLogic clienteLogic;
    
    @Inject
    ClientePersistence clientePersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<ClienteEntity> data = new ArrayList<ClienteEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ClienteEntity.class.getPackage())
                .addPackage(ClienteLogic.class.getPackage())
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
     * Prueba para crear un Cliente
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test
    public void createClienteTest() throws BusinessLogicException {
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setPuntos(0);
        ClienteEntity result = clienteLogic.createCliente(newEntity);
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
    }
    
    /**
     * Prueba para crear un Cliente con nombre inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createClienteTestNombreInvalido() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setNombre(null);
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para crear un Cliente con nombre inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createClienteTestNombreInvalido2() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setNombre("");
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para crear un Cliente con apellido inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createClienteTestApellidoInvalido() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setApellido(null);
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para crear un Cliente con apellido inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createClienteTestApellidoInvalido2() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setApellido("");
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para crear un Cliente con username inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createClienteTestUsernameInvalido() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setUsername(null);
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para crear un Cliente con username inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createClienteTestUsernameInvalido2() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setUsername("");
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para crear un Cliente con username existente.
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createClienteTestConUsernameExistente() throws BusinessLogicException {
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setUsername(data.get(0).getUsername());
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para crear un Cliente con email inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createClienteTestEmailInvalido() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setEmail(null);
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para crear un Cliente con email inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createClienteTestEmailInvalido2() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setEmail("");
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para crear un Cliente con email existente.
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createClienteTestConEmailExistente() throws BusinessLogicException {
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setEmail(data.get(0).getEmail());
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para crear un Cliente con contraseña inválida
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createClienteTestContrasenaInvalida() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setContrasena(null);
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para crear un Cliente con contraseña inválida
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createClienteTestContrasenaInvalida2() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setContrasena("");
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para crear un Cliente con número de contacto inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createClienteTestNumeroContactoInvalido() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setNumeroContacto(null);
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para crear un Cliente con número de contacto inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createClienteTestNumeroContactoInvalido2() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setNumeroContacto("");
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para crear un Cliente con número de puntos inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void createClienteTestPuntosInvalido() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setPuntos(10);
        clienteLogic.createCliente(newEntity);
    }
    
    /**
     * Prueba para consultar la lista de clientes.
     */
    @Test
    public void getClientesTest() {
        List<ClienteEntity> list = clienteLogic.getClientes();
        Assert.assertEquals(data.size(), list.size());
        for (ClienteEntity entity : list) {
            boolean found = false;
            for (ClienteEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un cliente.
     */
    @Test
    public void getClienteTest() {
        ClienteEntity entity = data.get(0);
        ClienteEntity resultEntity = clienteLogic.getCliente(entity.getId());
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
     * Prueba para actualizar un cliente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateClienteTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
        ClienteEntity resp = em.find(ClienteEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getApellido(), resp.getApellido());
        Assert.assertEquals(pojoEntity.getUsername(), resp.getUsername());
        Assert.assertEquals(pojoEntity.getEmail(), resp.getEmail());
        Assert.assertEquals(pojoEntity.getContrasena(), resp.getContrasena());
        Assert.assertEquals(pojoEntity.getCumpleanos(), resp.getCumpleanos());
        Assert.assertEquals(pojoEntity.getNumeroContacto(), resp.getNumeroContacto());
        Assert.assertEquals(pojoEntity.getPuntos(), resp.getPuntos());
    }
    
    /**
     * Prueba para actualizar un Cliente con nombre inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void updateClienteTestNombreInvalido() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setNombre(null);
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar un Cliente con nombre inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void updateClienteTestNombreInvalido2() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setNombre("");
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar un Cliente con apellido inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void updateClienteTestApellidoInvalido() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setApellido(null);
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar un Cliente con apellido inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void updateClienteTestApellidoInvalido2() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setApellido("");
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar un Cliente con username inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void updateClienteTestUsernameInvalido() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setUsername(null);
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar un Cliente con username inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void updateClienteTestUsernameInvalido2() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setUsername("");
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar un Cliente con email inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void updateClienteTestEmailInvalido() throws BusinessLogicException{
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setEmail(null);
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar un Cliente con email inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void updateClienteTestEmailInvalido2() throws BusinessLogicException{
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setEmail("");
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar un Cliente con contraseña inválida
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void updateClienteTestContrasenaInvalida() throws BusinessLogicException{
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setContrasena(null);
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar un Cliente con contraseña inválida
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void updateClienteTestContrasenaInvalida2() throws BusinessLogicException{
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setContrasena("");
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar un Cliente con número de contacto inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void updateClienteTestNumeroContactoInvalido() throws BusinessLogicException{
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setNumeroContacto(null);
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar un Cliente con número de contacto inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void upduateClienteTestNumeroContactoInvalido2() throws BusinessLogicException{
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setNumeroContacto("");
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar un Cliente con número de puntos inválido
     *
     * @throws co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException
     */
    @Test (expected = BusinessLogicException.class)
    public void updateClienteTestPuntosInvalido() throws BusinessLogicException{
        ClienteEntity entity = data.get(0);
        ClienteEntity pojoEntity = factory.manufacturePojo(ClienteEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setPuntos(-10);
        clienteLogic.updateCliente(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para eliminar un cliente.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteClienteTest() throws BusinessLogicException {
        ClienteEntity entity = data.get(0);
        clienteLogic.deleteCliente(entity.getId());
        ClienteEntity deleted = em.find(ClienteEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
