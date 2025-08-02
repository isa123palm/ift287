package tp.gestion;

import java.util.ArrayList;
import java.util.List;

import tp.bdd.Connexion;
import tp.collections.Producteurs;
import tp.objets.Fournisseur;
import tp.objets.PointDeVente;
import tp.objets.Producteur;
import tp.objets.Produit;

public class GestionProducteurs {
    private final Connexion cx;
    private final Producteurs producteurs;

    public GestionProducteurs(Connexion cx) {
        this.cx = cx;
        this.producteurs = new Producteurs(cx);
    }

    public void ajouterProducteur(String nom, String courriel, String motDePasse, int nbEmp, String adresse) throws Exception {
        try {
            // Démarrer la transaction
            cx.demarreTransaction();

            // Vérifier si le producteur existe déjà
            if (producteurs.chercherParNom(nom) != null || producteurs.chercherParCourriel(courriel) != null) throw new Exception("Producteur déjà existant.");

            // Ajouter le producteur
            producteurs.ajouter(new Producteur(nom, courriel, motDePasse, nbEmp, adresse));

            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public void afficherProducteur(int id) throws Exception {
        try {
            // Chercher le producteur
            Producteur p = producteurs.chercherParId(id);
            if (p == null) throw new Exception("Producteur introuvable");

            // Afficher les informations du producteur
            System.out.println("Nom: " + p.getNom());
            System.out.println("Courriel: " + p.getCourriel());
            System.out.println("Adresse: " + p.getAdresse());
            System.out.println("Nb employés: " + p.getNbEmployes());

        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public void supprimerProducteur(int id) throws Exception {
        try {
            cx.demarreTransaction();

            Producteur p = producteurs.chercherParId(id);
            if (p == null) throw new Exception("Producteur introuvable.");

            for (Produit prod : new ArrayList<>(p.getProduits())) {

                prod.getFournisseurs().forEach(f -> f.getProduits().remove(prod));
                prod.getPointsDeVente().forEach(pdv -> pdv.getProduits().remove(prod));

                prod.getFournisseurs().clear();
                prod.getPointsDeVente().clear();
            }

            producteurs.supprimer(id);

            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }


    public Producteur chercherParId(int id) throws Exception {
        try {
            return producteurs.chercherParId(id);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la recherche par id", e);
        }
    }

    public Producteur chercherParCourriel(String courriel) throws Exception {
        try {
            return producteurs.chercherParCourriel(courriel);
        } catch (Exception e) {
            throw new Exception("Erreur lors de la recherche par courriel", e);
        }
    }

    public List<Producteur> listerTous() throws Exception {
        try {
            return producteurs.listerTous();
        } catch (Exception e) {
            throw new Exception("Erreur lors du listage des producteurs.", e);
        }
    }

}
