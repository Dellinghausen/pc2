package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Categoria;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Tiago
 */
public class TestePersistirCategoria {

    public static void main(String[] args) {
        EntityManagerFactory emf
                = Persistence.createEntityManagerFactory("PC2_TesteModeloPU");
        EntityManager em = emf.createEntityManager();
        Categoria c = new Categoria();
        c.setNome("Descritiva");
        em.getTransaction().begin();
        em.persist(c);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }
    
}
