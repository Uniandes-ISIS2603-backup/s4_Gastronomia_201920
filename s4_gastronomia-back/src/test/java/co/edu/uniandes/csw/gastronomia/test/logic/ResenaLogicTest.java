/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.gastronomia.test.logic;

import co.edu.uniandes.csw.gastronomia.ejb.ResenaLogic;
import co.edu.uniandes.csw.gastronomia.entities.ResenaEntity;
import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.gastronomia.persistence.ResenaPersistence;
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
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author David Martinez
 */
@RunWith(Arquillian.class)
public class ResenaLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
     @Inject
        private ResenaLogic resenaLogic;

        @PersistenceContext
        private EntityManager em;

        @Inject
        private UserTransaction utx;

        private List<ResenaEntity> data = new ArrayList<>();

        /**
        * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
        * El jar contiene las clases, el descriptor de la base de datos y el
        * archivo beans.xml para resolver la inyección de dependencias.
        */
        @Deployment
        public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addPackage(ResenaEntity.class.getPackage())
            .addPackage(ResenaLogic.class.getPackage())
            .addPackage(ResenaPersistence.class.getPackage())
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource("META-INF/beans.xml", "beans.xml");
        }
        
        @Test
        public void creaResenaTest() throws BusinessLogicException
        {
             ResenaEntity fb= factory.manufacturePojo(ResenaEntity.class);
             int n = (int)(Math.random()*4)+1;
             fb.setCalificacion(n);
             ResenaEntity rta= resenaLogic.createResena(fb);
             Assert.assertEquals(rta.getCalificacion()<=5,true);
             Assert.assertEquals(rta.getCalificacion()>=1 ,true);
             Assert.assertNotNull(rta);
             Assert.assertEquals(rta, fb);
             Assert.assertNotNull(rta.getCalificacion());
             Assert.assertNotNull(rta.getComentario());
               
        }
        
        @Test
        public void getResenasTest() throws BusinessLogicException
        {
            ResenaEntity fb1= factory.manufacturePojo(ResenaEntity.class);
            ResenaEntity fb2= factory.manufacturePojo(ResenaEntity.class);
            ResenaEntity fb3= factory.manufacturePojo(ResenaEntity.class);
            int n = (int)(Math.random()*4)+1;
             fb1.setCalificacion(n);
             fb2.setCalificacion(n);
             fb3.setCalificacion(n);
            resenaLogic.createResena(fb1);
            resenaLogic.createResena(fb2);
            resenaLogic.createResena(fb3);
            List<ResenaEntity> resenas=resenaLogic.getResenas();
            Assert.assertEquals(3, resenas.size());
            Assert.assertEquals(resenas.contains(fb1), true);
            Assert.assertEquals(resenas.contains(fb2), true);
            Assert.assertEquals(resenas.contains(fb3), true);
        }
        @Test 
        public void getFoodblogTest()throws BusinessLogicException
        {
        
           ResenaEntity fb= factory.manufacturePojo(ResenaEntity.class);
             int n = (int)(Math.random()*4)+1;
             fb.setCalificacion(n);
             ResenaEntity rta= resenaLogic.createResena(fb);
            Long idFB=fb.getId();
            
            ResenaEntity rta2= resenaLogic.getResena(idFB);
            Assert.assertEquals(rta, rta2);   

        }
        
        @Test
        public void updateResenaTest() throws BusinessLogicException
        {
             ResenaEntity fb= factory.manufacturePojo(ResenaEntity.class);
             int n = (int)(Math.random()*4)+1;
             fb.setCalificacion(n);
             ResenaEntity rta= resenaLogic.createResena(fb);
             
             ResenaEntity fb2= factory.manufacturePojo(ResenaEntity.class);
             int n2 = (int)(Math.random()*4)+1;
             fb2.setCalificacion(n2);
             fb2.setId(fb.getId());

             rta = resenaLogic.updateResena(rta.getId(), fb2);
             Assert.assertEquals(fb2.getCalificacion(), rta.getCalificacion());
             Assert.assertEquals(fb2.getComentario(), rta.getComentario());   
        }
        @Test
        public void deleteTest() throws BusinessLogicException
        {
            ResenaEntity fb= factory.manufacturePojo(ResenaEntity.class);
            int n = (int)(Math.random()*4)+1;
             fb.setCalificacion(n);
            ResenaEntity rta=resenaLogic.createResena(fb);
            try {
                resenaLogic.deleteResena(fb.getId());
            } catch (BusinessLogicException e) {
                Assert.fail();
                
            }
            try {
              resenaLogic.deleteResena(fb.getId());    
            } catch (BusinessLogicException e) {
                //No hubo errores
            }
        }
        
}
