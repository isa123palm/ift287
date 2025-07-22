package tp.gestion;

import tp.collections.Produits;
import tp.collections.Producteurs;
import tp.objets.Produit;
import tp.objets.Producteur;

import javax.persistence.EntityManager;

public class GestionProduits {
    private final EntityManager em;
    private final Produits produits;
    private final Producteurs producteurs;

    public GestionProduits(EntityManager em) {
        this.em = em;
        this.produits = new Produits(em);
        this.producteurs = new Producteurs(em);
    }

    public void ajouter(String nom, double prix, double cout, String categorie, String nomProducteur) throws Exception {
        em.getTransaction().begin();
        Producteur prod = producteurs.chercher(nomProducteur);
        if (prod == null) throw new Exception("Producteur introuvable.");
        produits.ajouter(new Produit(nom, prix, cout, categorie, prod));
        em.getTransaction().commit();
    }

    public void afficher(String nom) throws Exception {
        Produit p = produits.chercher(nom);
        if (p == null) throw new Exception("Produit introuvable");
        System.out.println("Nom: " + p.getNom());
        System.out.println("Prix: " + p.getPrix());
        System.out.println("Coût: " + p.getCout());
        System.out.println("Catégorie: " + p.getCategorie());
        System.out.println("Producteur: " + p.getProducteur().getNom());
    }

    public void supprimer(String nom) throws Exception {
        em.getTransaction().begin();
        produits.supprimer(nom);
        em.getTransaction().commit();
    }
}
