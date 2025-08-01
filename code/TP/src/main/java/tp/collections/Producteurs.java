package tp.collections;

import tp.bdd.Connexion;
import tp.objets.Producteur;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class Producteurs {
    //private final Connexion cx;
    private final EntityManager em;

    public Producteurs(Connexion cx) {
        //this.cx = cx;
        this.em = cx.getConnection();
    }

    public void ajouter(Producteur p) throws Exception {
        if (em.find(Producteur.class, p.getNom()) != null)
            throw new Exception("Ce producteur existe déjà.");
        em.persist(p);
    }

    public Producteur chercherParId(int id) {
        return em.find(Producteur.class, id);
    }

    public Producteur chercherParNom(String nom) {
        TypedQuery<Producteur> query = em.createQuery(
            "SELECT p FROM Producteur p WHERE p.nom = :nom", Producteur.class);
        query.setParameter("nom", nom);

        return query.getResultStream().findFirst().orElse(null);
    }

    public Producteur chercherParCourriel(String courriel) {
        TypedQuery<Producteur> query = em.createQuery(
            "SELECT p FROM Producteur p WHERE p.courriel = :courriel", Producteur.class);
        query.setParameter("courriel", courriel);

        return query.getResultStream().findFirst().orElse(null);
    }

    public List<Producteur> listerTous() {
        return em.createQuery("SELECT p FROM Producteur p", Producteur.class).getResultList();
    }

    public void supprimer(int id) throws Exception {
        Producteur p = em.find(Producteur.class, id);
        if (p == null)
            throw new Exception("Producteur inexistant.");
        em.remove(p);
    }
}
