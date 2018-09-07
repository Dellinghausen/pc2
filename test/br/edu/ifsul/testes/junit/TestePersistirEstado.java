package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Estado;
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
public class TestePersistirEstado {
    
     EntityManager em;
    
    public TestePersistirEstado() {
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
            Estado e = new Estado();
            e.setNome("Rio Grande do Sul");
            e.setUf("RS");
            e.setPais(em.find(Pais.class, 1));
            em.getTransaction().begin();
            em.persist(e);
            em.getTransaction().commit();
        } catch (Exception e){
            exception = true;
        }
        // aqui comparo o resultado esperado com oque ocorreu
        Assert.assertEquals(false, exception);        
    }    
}
