package tp.collections;

import tp.bdd.Connexion;
import tp.objets.Fournisseur;

import javax.persistence.EntityManager;

public class Fournisseurs {
    private final Connexion cx;
    private final EntityManager em;

    public Fournisseurs(Connexion cx) {
        this.cx = cx;
        this.em = cx.getConnection();
    }

    public void ajouter(Fournisseur f) throws Exception {
        if (em.find(Fournisseur.class, f.getNom()) != null)
            throw new Exception("Ce fournisseur existe déjà.");
        em.persist(f);
    }

    public Fournisseur chercher(String nom) {
        return em.find(Fournisseur.class, nom);
    }

    public Fournisseur chercherParId(int id) {
        return em.find(Fournisseur.class, id);
    }

    public void supprimer(int id) throws Exception {
        Fournisseur f = em.find(Fournisseur.class, id);
        if (f == null) throw new Exception("Fournisseur inexistant.");
        em.remove(f);
    }
}
