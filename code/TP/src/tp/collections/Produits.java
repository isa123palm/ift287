package tp.collections;

import tp.bdd.Connexion;
import tp.objets.Produit;

import javax.persistence.EntityManager;

public class Produits {
    private final Connexion cx;
    private final EntityManager em;

    public Produits(Connexion cx) {
        this.cx = cx;
        this.em = cx.getConnection();
    }

    public void ajouter(Produit p) throws Exception {
        if (em.find(Produit.class, p.getNom()) != null)
            throw new Exception("Ce produit existe déjà.");
        em.persist(p);
    }

    public Produit chercher(String nom) {
        return em.find(Produit.class, nom);
    }

    public void supprimer(String nom) throws Exception {
        Produit p = em.find(Produit.class, nom);
        if (p == null)
            throw new Exception("Produit inexistant.");
        em.remove(p);
    }
}
