package tp.bdd;

import javax.persistence.*;

public class Connexion {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public Connexion(String persistenceUnitName) {
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void fermer() {
        if (em != null && em.isOpen()) em.close();
        if (emf != null && emf.isOpen()) emf.close();
    }
}
