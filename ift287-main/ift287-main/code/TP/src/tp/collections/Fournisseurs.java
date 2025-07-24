package tp.collections;

import tp.objets.Fournisseur;

import javax.persistence.EntityManager;

public class Fournisseurs {
    private final EntityManager em;

    public Fournisseurs(EntityManager em) {
        this.em = em;
    }

    public void ajouter(Fournisseur f) throws Exception {
        if (em.find(Fournisseur.class, f.getNom()) != null)
            throw new Exception("Ce fournisseur existe déjà.");
        em.persist(f);
    }

    public Fournisseur chercher(String nom) {
        return em.find(Fournisseur.class, nom);
    }

    public void supprimer(String nom) throws Exception {
        Fournisseur f = em.find(Fournisseur.class, nom);
        if (f == null) throw new Exception("Fournisseur inexistant.");
        em.remove(f);
    }
}
