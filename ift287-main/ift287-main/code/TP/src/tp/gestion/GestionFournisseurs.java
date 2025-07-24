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
            em.getTransaction().begin();
            fournisseurs.ajouter(new Fournisseur(nom, courriel, adresse));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void afficherFournisseur(String nom) throws Exception {
        Fournisseur f = fournisseurs.chercher(nom);
        if (f == null) throw new Exception("Fournisseur introuvable");
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
            fournisseurs.supprimer(nom);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void fabriquerProduit(String nomProduit, String nomFournisseur) throws Exception {
        try {
            em.getTransaction().begin();

        Produit p = produits.chercher(nomProduit);
        if (p == null) throw new Exception("Produit introuvable.");

        Fournisseur f = fournisseurs.chercher(nomFournisseur);
        if (f == null) throw new Exception("Fournisseur introuvable.");

        f.ajouterProduit(p);
        p.ajouterFournisseur(f);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void retirerProduit(String nomProduit, String nomFournisseur) throws Exception {
        try {
            em.getTransaction().begin();

        Produit p = produits.chercher(nomProduit);
        if (p == null) throw new Exception("Produit introuvable.");

        Fournisseur f = fournisseurs.chercher(nomFournisseur);
        if (f == null) throw new Exception("Fournisseur introuvable.");

        f.retirerProduit(p);
        p.retirerFournisseur(f);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
