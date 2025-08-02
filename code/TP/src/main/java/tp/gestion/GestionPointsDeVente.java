package tp.gestion;

import java.util.List;

import tp.bdd.Connexion;
import tp.collections.PointsDeVente;
import tp.collections.Produits;
import tp.objets.Fournisseur;
import tp.objets.PointDeVente;
import tp.objets.Producteur;
import tp.objets.Produit;

public class GestionPointsDeVente {
    private final Connexion cx;
    private final PointsDeVente pointsDeVente;
    private final Produits produits;

    public GestionPointsDeVente(Connexion cx) {
        this.cx = cx;
        this.pointsDeVente = new PointsDeVente(cx);
        this.produits = new Produits(cx);
    }

    public void ajouterPointDeVente(String nom, String courriel, String motDePasse, String adresse) throws Exception {
        try {
            cx.demarreTransaction();
            // Vérifier si le point de vente existe déjà
            if (pointsDeVente.chercherParNom(nom) != null || pointsDeVente.chercherParCourriel(courriel) != null) throw new Exception("Point de vente déjà existant.");
            
            // Ajouter le point de vente
            pointsDeVente.ajouter(new PointDeVente(nom, courriel, motDePasse, adresse));
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public void afficherPointDeVente(int id) throws Exception {
        // Chercher le point de vente
        PointDeVente pdv = pointsDeVente.chercherParId(id);

        if (pdv == null) throw new Exception("Point de vente introuvable.");

        // Afficher les informations du point de vente
        System.out.println("Nom: " + pdv.getNom());
        System.out.println("Courriel: " + pdv.getCourriel());
        System.out.println("Adresse: " + pdv.getAdresse());
        System.out.print("Produits vendus: ");
        for (Produit p : pdv.getProduits()) {
            System.out.print(p.getNom() + " ");
        }
        System.out.println();
    }

    public void supprimerPointDeVente(int id) throws Exception {
        try {
            cx.demarreTransaction();

            PointDeVente pdv = pointsDeVente.chercherParId(id);
            if (pdv == null)
                throw new Exception("Point de vente introuvable.");

            for (Produit p : pdv.getProduits()) {
                p.retirerPointDeVente(pdv);
            }

            for (Producteur prod : pdv.getProducteurs()) {
                prod.getPointsDeVente().remove(pdv);
            }

            pdv.getProduits().clear();
            pdv.getProducteurs().clear();

            pointsDeVente.supprimer(id);

            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public void vendreProduit(int idProduit, int idPointDeVente) throws Exception {
        try {
            cx.demarreTransaction();

            // Vérifier si le produit existe
            Produit p = produits.chercherParId(idProduit);
            if (p == null) throw new Exception("Produit introuvable.");

            // Vérifier si le point de vente existe
            PointDeVente pdv = pointsDeVente.chercherParId(idPointDeVente);
            if (pdv == null) throw new Exception("Point de vente introuvable.");

            // Ajouter le produit au point de vente
            pdv.ajouterProduit(p);

            // Ajouter le point de vente au produit
            p.ajouterPointDeVente(pdv);

            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public void retirerProduit(int idProduit, int idPointDeVente) throws Exception {
        try {
            cx.demarreTransaction();

            // Vérifier si le produit existe
            Produit p = produits.chercherParId(idProduit);
            if (p == null) throw new Exception("Produit introuvable.");

            // Vérifier si le point de vente existe
            PointDeVente pdv = pointsDeVente.chercherParId(idPointDeVente);
            if (pdv == null) throw new Exception("Point de vente introuvable.");

            // Retirer le produit du point de vente
            pdv.retirerProduit(p);
            // Retirer le point de vente du produit
            p.retirerPointDeVente(pdv);
            
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public PointDeVente chercherParId(int id) throws Exception {
        try {
            return pointsDeVente.chercherParId(id);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la recherche par id", e);
        }
    }

    public PointDeVente chercherParCourriel(String courriel) throws Exception {
        try {
            return pointsDeVente.chercherParCourriel(courriel);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la recherche par courriel", e);
        }
    }

    public List<PointDeVente> listerTous() throws Exception {
        try {
            return pointsDeVente.listerTous();
        } catch (Exception e) {
            throw new Exception("Erreur lors du listage des points de vente", e);
        }
    }

}
