package tp.gestion;

import java.util.List;

import tp.bdd.Connexion;
import tp.collections.Produits;
import tp.collections.Producteurs;
import tp.objets.Produit;
import tp.objets.PointDeVente;
import tp.objets.Producteur;

public class GestionProduits {
    private final Connexion cx;
    private final Produits produits;
    private final Producteurs producteurs;

    public GestionProduits(Connexion cx) {
        this.cx = cx;
        this.produits = new Produits(cx);
        this.producteurs = new Producteurs(cx);
    }

    public void ajouterProduit(String nom, double prix, double cout, String categorie, String nomProducteur) throws Exception {
        try {
            // Démarrer la transaction
            cx.demarreTransaction();

            // Vérifier que le prix n'est pas négatif
            if (prix < 0) throw new Exception("Le prix ne peut pas être négatif.");

            // Vérifier si le producteur existe
            Producteur prod = producteurs.chercherParNom(nomProducteur);
            if (prod == null) throw new Exception("Producteur introuvable.");

            // Ajouter le produit
            produits.ajouter(new Produit(nom, prix, cout, categorie, prod));

            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public void afficherProduit(int id) throws Exception {
        try {
            // Chercher le produit
            Produit p = produits.chercherParId(id);
            if (p == null) throw new Exception("Produit introuvable");

            // Afficher les informations du produit
            System.out.println("Nom: " + p.getNom());
            System.out.println("Prix: " + p.getPrix());
            System.out.println("Coût: " + p.getCout());
            System.out.println("Catégorie: " + p.getCategorie());
            System.out.println("Producteur: " + p.getProducteur().getNom());

        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public void supprimerProduit(int id) throws Exception {
        try {
            cx.demarreTransaction();

            Produit produit = produits.chercherParId(id);
            if (produit == null)
                throw new Exception("Produit introuvable.");

            // Retirer le produit de son producteur
            Producteur prod = produit.getProducteur();
            if (prod != null) {
                prod.getProduits().remove(produit);
            }

            // Supprimer le produit (et associations)
            produits.supprimer(id);

            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public Produit chercherParId(int id) throws Exception {
        try {
            return produits.chercherParId(id);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la recherche par id", e);
        }
    }

    public List<Produit> listerTous() throws Exception {
        try {
            return produits.listerTous();
        } catch (Exception e) {
            throw new Exception("Erreur lors du listage des produits.", e);
        }
    }
}
