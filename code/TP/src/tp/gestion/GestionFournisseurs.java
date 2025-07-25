package tp.gestion;

import tp.collections.Fournisseurs;
import tp.collections.Produits;
import tp.objets.Fournisseur;
import tp.objets.Produit;

import javax.persistence.EntityManager;

public class GestionFournisseurs {
    private final EntityManager em;
    private final Fournisseurs fournisseurs;
    private final Produits produits;

    public GestionFournisseurs(EntityManager em) {
        this.em = em;
        this.fournisseurs = new Fournisseurs(em);
        this.produits = new Produits(em);
    }

    public void ajouterFournisseur(String nom, String courriel, String adresse) throws Exception {
        try {
            // Démarrer la transaction
            em.getTransaction().begin();
            // Vérifier si le fournisseur existe déjà
            if(fournisseurs.chercher(nom) != null) 
            throw new Exception("Fournisseur déjà existant.");
            // Ajouter le fournisseur
            fournisseurs.ajouter(new Fournisseur(nom, courriel, adresse));
            // Valider la transaction
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void afficherFournisseur(String nom) throws Exception {
        // Chercher le fournisseur
        Fournisseur f = fournisseurs.chercher(nom);
        if (f == null) throw new Exception("Fournisseur introuvable");
        // Afficher les informations du fournisseur
        System.out.println("Nom: " + f.getNom());
        System.out.println("Courriel: " + f.getCourriel());
        System.out.println("Adresse: " + f.getAdresse());
        System.out.print("Produits fournis: ");
        for (Produit p : f.getProduits()) {
            System.out.print(p.getNom() + " ");
        }
        System.out.println();
    }

    public void supprimerFournisseur(String nom) throws Exception {
        try {
            em.getTransaction().begin();
            // Vérifier si le fournisseur existe
            if(fournisseurs.chercher(nom) == null) 
            throw new Exception("Fournisseur introuvable.");
            // Supprimer le fournisseur
            fournisseurs.supprimer(nom);
            // Valider la transaction
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void fabriquerProduit(String nomProduit, String nomFournisseur) throws Exception {
        try {
            em.getTransaction().begin();
            // Vérifier si le produit existe
            Produit p = produits.chercher(nomProduit);
            if (p == null) throw new Exception("Produit introuvable.");
            // Vérifier si le fournisseur existe
        Fournisseur f = fournisseurs.chercher(nomFournisseur);
            if (f == null) throw new Exception("Fournisseur introuvable.");
            // Ajouter le produit au fournisseur
            f.ajouterProduit(p);
            // Ajouter le fournisseur au produit
            p.ajouterFournisseur(f);
            // Valider la transaction
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void retirerProduit(String nomProduit, String nomFournisseur) throws Exception {
        try {
            em.getTransaction().begin();
            // Vérifier si le produit existe
            Produit p = produits.chercher(nomProduit);
            if (p == null) throw new Exception("Produit introuvable.");
            // Vérifier si le fournisseur existe
        Fournisseur f = fournisseurs.chercher(nomFournisseur);
            if (f == null) throw new Exception("Fournisseur introuvable.");
            // Retirer le produit du fournisseur
            f.retirerProduit(p);
            // Retirer le fournisseur du produit
            p.retirerFournisseur(f);
            // Valider la transaction
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
