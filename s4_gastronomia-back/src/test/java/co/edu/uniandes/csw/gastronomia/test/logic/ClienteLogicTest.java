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
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.util.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Estudiante
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
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ClienteEntity.class)
                .addClass(ClienteLogic.class)
                .addClass(ClientePersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createCliente() throws BusinessLogicException {
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        ClienteEntity result = clienteLogic.createCliente(newEntity);
        Assert.notNull(result);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createClienteNombreNull() throws BusinessLogicException{
        ClienteEntity newEntity = factory.manufacturePojo(ClienteEntity.class);
        newEntity.setNombre(null);
        ClienteEntity result = clienteLogic.createCliente(newEntity);
    }
    
}
