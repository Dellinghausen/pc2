package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Pais;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tiago
 */
public class TestePersistirPais {
    
     EntityManager em;
    
    public TestePersistirPais() {
    }
    
    @Before
    public void setUp() {
        em = EntityManagerUtil.getEntityManager();
    }
    
    @After
    public void tearDown() {
        em.close();
    }
    
    @Test
    public void teste(){
        Boolean exception = false;
        
        try {
            Pais p = new Pais();
            p.setNome("Brasil");
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e){
            exception = true;
            e.printStackTrace();
        }
        // aqui comparo o resultado esperado com oque ocorreu
        Assert.assertEquals(false, exception);
        
    }
    
}
