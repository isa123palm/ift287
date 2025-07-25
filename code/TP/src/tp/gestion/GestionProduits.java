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

    public void ajouterProduit(String nom, double prix, double cout, String categorie, String nomProducteur) throws Exception {
        try {
            // Démarrer la transaction
            em.getTransaction().begin();
            // Vérifier si le producteur existe
            Producteur prod = producteurs.chercher(nomProducteur);
            if (prod == null) throw new Exception("Producteur introuvable.");
            // Ajouter le produit
        produits.ajouter(new Produit(nom, prix, cout, categorie, prod));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void afficherProduit(String nom) throws Exception {
        try {
            // Chercher le produit
            Produit p = produits.chercher(nom);
            if (p == null) throw new Exception("Produit introuvable");
            // Afficher les informations du produit
            System.out.println("Nom: " + p.getNom());
        System.out.println("Prix: " + p.getPrix());
        System.out.println("Coût: " + p.getCout());
        System.out.println("Catégorie: " + p.getCategorie());
        System.out.println("Producteur: " + p.getProducteur().getNom());
        
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void supprimerProduit(String nom) throws Exception {
        try {
            // Démarrer la transaction
            em.getTransaction().begin();
            // Vérifier si le produit existe
            if(produits.chercher(nom) == null) 
            throw new Exception("Produit introuvable.");
            // Supprimer le produit
            produits.supprimer(nom);
            // Valider la transaction
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
