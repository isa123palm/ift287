package tp.collections;


import tp.objets.Producteur;

import javax.persistence.EntityManager;

public class Producteurs {
    private final EntityManager em;

    public Producteurs(EntityManager em) {
        this.em = em;
    }

    public void ajouter(Producteur p) throws Exception {
        if (em.find(Producteur.class, p.getNom()) != null)
            throw new Exception("Ce producteur existe déjà.");
        em.persist(p);
    }

    public Producteur chercher(String nom) {
        return em.find(Producteur.class, nom);
    }

    public void supprimer(String nom) throws Exception {
        Producteur p = em.find(Producteur.class, nom);
        if (p == null)
            throw new Exception("Producteur inexistant.");
        em.remove(p);
    }
}
