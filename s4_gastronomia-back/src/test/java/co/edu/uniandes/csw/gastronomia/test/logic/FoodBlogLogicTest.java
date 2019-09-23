        /*
        * To change this license header, choose License Headers in Project Properties.
        * To change this template file, choose Tools | Templates
        * and open the template in the editor.
        */
        package co.edu.uniandes.csw.gastronomia.test.logic;

        import co.edu.uniandes.csw.gastronomia.entities.FoodBlogEntity;
        import co.edu.uniandes.csw.gastronomia.persistence.FoodBlogPersistence;
        import co.edu.uniandes.csw.gastronomia.ejb.FoodBlogLogic;
        import co.edu.uniandes.csw.gastronomia.exceptions.BusinessLogicException;
        import java.util.ArrayList;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Set;
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
        *  food David Martinez
        */
        @RunWith(Arquillian.class)
        public class FoodBlogLogicTest {
        private PodamFactory factory = new PodamFactoryImpl();

        @Inject
        private FoodBlogLogic foodblogLogic;

        @PersistenceContext
        private EntityManager em;

        @Inject
        private UserTransaction utx;

        private List<FoodBlogEntity> data = new ArrayList<>();

        /**
        * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
        * El jar contiene las clases, el descriptor de la base de datos y el
        * archivo beans.xml para resolver la inyección de dependencias.
        */
        @Deployment
        public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
            .addPackage(FoodBlogEntity.class.getPackage())
            .addPackage(FoodBlogLogic.class.getPackage())
            .addPackage(FoodBlogPersistence.class.getPackage())
            .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
            .addAsManifestResource("META-INF/beans.xml", "beans.xml");
        }

/**
 * 
 * @throws BusinessLogicException 
 */
        @Test
        public void createFoodBlogTest() throws BusinessLogicException
        {
        FoodBlogEntity fb= factory.manufacturePojo(FoodBlogEntity.class);
        FoodBlogEntity rta= foodblogLogic.createFoodBlog(fb);
        Assert.assertNotNull(rta);
        
        Assert.assertNotNull(rta.getArchivoMultimedia());
        Assert.assertNotNull(rta.getComentarios());
        Assert.assertNotNull(rta.getNumeroMeGusta());
        Assert.assertNotNull(rta.getNumeroNoMegusta());
        Assert.assertNotNull(rta.getTexto());          
        
        FoodBlogEntity entity= em.find(FoodBlogEntity.class, rta.getId());
        Assert.assertEquals(entity.getComentarios(), rta.getComentarios());
        Assert.assertEquals(entity.getNumeroMeGusta(), rta.getNumeroMeGusta());
        Assert.assertEquals(entity.getNumeroNoMegusta(), rta.getNumeroNoMegusta());
        Assert.assertEquals(entity.getArchivoMultimedia(), rta.getArchivoMultimedia());
        Assert.assertEquals(entity.getTexto(), rta.getTexto());
        }
        /**
         * 
         * @throws BusinessLogicException 
         */
        @Test    public void updateFoodBlogTest()throws BusinessLogicException
        {
        FoodBlogEntity fb= factory.manufacturePojo(FoodBlogEntity.class);
        FoodBlogEntity rta= foodblogLogic.createFoodBlog(fb);
        Assert.assertNotNull(rta);
        FoodBlogEntity entity= em.find(FoodBlogEntity.class, rta.getId());
        FoodBlogEntity updated= factory.manufacturePojo(FoodBlogEntity.class);
        Assert.assertNotNull(updated);
        
        rta.setArchivoMultimedia(updated.getArchivoMultimedia());
        rta.setComentarios(updated.getComentarios());
        rta.setNumeroMeGusta(updated.getNumeroMeGusta());
        rta.setNumeroNoMegusta(updated.getNumeroNoMegusta());
        rta.setTexto(updated.getTexto());    

        Assert.assertEquals(updated.getComentarios(), rta.getComentarios());
        Assert.assertEquals(updated.getNumeroMeGusta(), rta.getNumeroMeGusta());
        Assert.assertEquals(updated.getNumeroNoMegusta(), rta.getNumeroNoMegusta());
        Assert.assertEquals(updated.getArchivoMultimedia(), rta.getArchivoMultimedia());
        Assert.assertEquals(updated.getTexto(), rta.getTexto());
        
            try {
                    foodblogLogic.updatefoodBlog(updated.getId(), updated);
            } catch (BusinessLogicException e) {
                Assert.fail();
            }
        }
        @Test
        public void deleteTest()
        {
            FoodBlogEntity fb= factory.manufacturePojo(FoodBlogEntity.class);
            foodblogLogic.createFoodBlog(fb);
            try {
                foodblogLogic.deleteFoodBlog(fb.getId());
            } catch (BusinessLogicException e) {
                Assert.fail();
                
            }
            try {
              foodblogLogic.deleteFoodBlog(fb.getId());    
            } catch (BusinessLogicException e) {
                //No hubo errores
            }
        }
        @Test 
        public void getFoodblogTest()
        {
        
            FoodBlogEntity fb= factory.manufacturePojo(FoodBlogEntity.class);
            foodblogLogic.createFoodBlog(fb);
            
            Long idFB=fb.getId();
            
            FoodBlogEntity rta= foodblogLogic.getfoodBlog(idFB);
            Assert.assertEquals(rta, fb);   

        }
        @Test
        public void getFoodBlogsTest()
        {
            
            FoodBlogEntity fb1= factory.manufacturePojo(FoodBlogEntity.class);
            FoodBlogEntity fb2= factory.manufacturePojo(FoodBlogEntity.class);
            FoodBlogEntity fb3= factory.manufacturePojo(FoodBlogEntity.class);
            foodblogLogic.createFoodBlog(fb1);
            foodblogLogic.createFoodBlog(fb2);
            foodblogLogic.createFoodBlog(fb3);
            List<FoodBlogEntity> foodBlogs=foodblogLogic.getFoodblogs();
           
            Assert.assertEquals(foodBlogs.contains(fb1), true);
            Assert.assertEquals(foodBlogs.contains(fb2), true);
            Assert.assertEquals(foodBlogs.contains(fb3), true);
            
        }
        }


