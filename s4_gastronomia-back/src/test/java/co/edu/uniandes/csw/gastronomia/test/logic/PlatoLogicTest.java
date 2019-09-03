/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.logic;
import co.edu.uniandes.csw.gastronomia.ejb.PlatoLogic;
import co.edu.uniandes.csw.gastronomia.entities.PlatoEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.PlatoPersistence;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import javax.inject.Inject;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import javax.transaction.UserTransaction;

/**
 *
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class PlatoLogicTest {
     @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PlatoEntity.class.getPackage())
                .addPackage(PlatoLogic.class.getPackage())
                .addPackage(PlatoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Test
    public void createPlatoTest()throws BusinessLogicException
    {
        PlatoEntity plato = factory.manufacturePojo(PlatoEntity.class);
        
    }
    
    
}
