package tp.collections;

import tp.bdd.Connexion;
import tp.objets.Fournisseur;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class Fournisseurs {
    //private final Connexion cx;
    private final EntityManager em;

    public Fournisseurs(Connexion cx) {
        //this.cx = cx;
        this.em = cx.getConnection();
    }

    public void ajouter(Fournisseur f) throws Exception {
        if (em.find(Fournisseur.class, f.getNom()) != null)
            throw new Exception("Ce fournisseur existe déjà.");
        em.persist(f);
    }

    public Fournisseur chercherParId(int id) {
        return em.find(Fournisseur.class, id);
    }

    public Fournisseur chercherParNom(String nom) {
        TypedQuery<Fournisseur> query = em.createQuery(
            "SELECT f FROM Fournisseur f WHERE f.nom = :nom", Fournisseur.class);
        query.setParameter("nom", nom);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    public Fournisseur chercherParCourriel(String courriel) {
        TypedQuery<Fournisseur> query = em.createQuery(
            "SELECT f FROM Fournisseur f WHERE f.courriel = :courriel", Fournisseur.class);
        query.setParameter("courriel", courriel);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    public List<Fournisseur> listerTous() {
        return em.createQuery("SELECT f FROM Fournisseur f", Fournisseur.class).getResultList();
    }

    public void supprimer(int id) throws Exception {
        Fournisseur f = em.find(Fournisseur.class, id);
        if (f == null) throw new Exception("Fournisseur inexistant.");
        em.remove(f);
    }
}
