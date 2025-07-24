package tp.gestion;

import tp.collections.PointsDeVente;
import tp.collections.Produits;
import tp.objets.PointDeVente;
import tp.objets.Produit;

import javax.persistence.EntityManager;

public class GestionPointsDeVente {
    private final EntityManager em;
    private final PointsDeVente pointsDeVente;
    private final Produits produits;

    public GestionPointsDeVente(EntityManager em) {
        this.em = em;
        this.pointsDeVente = new PointsDeVente(em);
        this.produits = new Produits(em);
    }

    public void ajouterPointDeVente(String nom, String courriel, String adresse) throws Exception {
        try {
            em.getTransaction().begin();
            pointsDeVente.ajouter(new PointDeVente(nom, courriel, adresse));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void afficherPointDeVente(String nom) throws Exception {
        PointDeVente pdv = pointsDeVente.chercher(nom);
        if (pdv == null) throw new Exception("Point de vente introuvable.");
        System.out.println("Nom: " + pdv.getNom());
        System.out.println("Courriel: " + pdv.getCourriel());
        System.out.println("Adresse: " + pdv.getAdresse());
        System.out.print("Produits vendus: ");
        for (Produit p : pdv.getProduits()) {
            System.out.print(p.getNom() + " ");
        }
        System.out.println();
    }

    public void supprimerPointDeVente(String nom) throws Exception {
        try {
            em.getTransaction().begin();
            pointsDeVente.supprimer(nom);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void vendreProduit(String nomProduit, String nomPointDeVente) throws Exception {
        try {
            em.getTransaction().begin();
        Produit p = produits.chercher(nomProduit);
        if (p == null) throw new Exception("Produit introuvable.");
        PointDeVente pdv = pointsDeVente.chercher(nomPointDeVente);
        if (pdv == null) throw new Exception("Point de vente introuvable.");

        pdv.ajouterProduit(p);
        p.ajouterPointDeVente(pdv);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void retirerProduit(String nomProduit, String nomPointDeVente) throws Exception {
        try {
            em.getTransaction().begin();
        Produit p = produits.chercher(nomProduit);
        if (p == null) throw new Exception("Produit introuvable.");
        PointDeVente pdv = pointsDeVente.chercher(nomPointDeVente);
        if (pdv == null) throw new Exception("Point de vente introuvable.");

        pdv.retirerProduit(p);
        p.retirerPointDeVente(pdv);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
