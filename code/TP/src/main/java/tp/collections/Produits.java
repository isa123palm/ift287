package tp.collections;

import tp.bdd.Connexion;
import tp.objets.Producteur;
import tp.objets.Produit;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class Produits {
    //private final Connexion cx;
    private final EntityManager em;

    public Produits(Connexion cx) {
        //this.cx = cx;
        this.em = cx.getConnection();
    }

    public void ajouter(Produit p) throws Exception {
        if (em.find(Produit.class, p.getNom()) != null)
            throw new Exception("Ce produit existe déjà.");
        
        Producteur producteur = p.getProducteur();
        if (producteur != null) {
            producteur.getProduits().add(p);
        }
        
        em.persist(p);
    }

    public Produit chercherParId(int id) {
        return em.find(Produit.class, id);
    }

    public Produit chercherParNom(String nom) {
        TypedQuery<Produit> query = em.createQuery(
            "SELECT p FROM Produit p WHERE p.nom = :nom", Produit.class);
        query.setParameter("nom", nom);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    public void supprimer(int id) throws Exception {
        Produit p = em.find(Produit.class, id);
        if (p == null)
            throw new Exception("Produit inexistant.");
        em.remove(p);
    }

    public List<Produit> listerTous() {
        return em.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
    }
}
