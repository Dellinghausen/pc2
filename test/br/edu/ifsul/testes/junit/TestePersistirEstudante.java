package br.edu.ifsul.testes.junit;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Cidade;
import br.edu.ifsul.modelo.Estado;
import br.edu.ifsul.modelo.Estudante;
import java.util.Calendar;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Tiago
 */
public class TestePersistirEstudante {
    
     EntityManager em;
    
    public TestePersistirEstudante() {
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
        boolean exception = false;
        
        try {
            Estudante e = new Estudante();
            e.setNome("Tiago Lopes");
            e.setLogin("tiagolopes");
            e.setSenha("123456");
            e.setEmail("tiago_lopes58@msn.com");
            e.setDataNascimento(Calendar.getInstance());
            e.setTelefone("6515986189");
            e.setCurso("TSPI");
            e.setTelefoneEmergencia("16519819161");
            e.setCpf("81959796020");
            e.setCidade(em.find(Cidade.class, 2));
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
