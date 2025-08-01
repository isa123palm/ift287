package tp.gestion;

import java.util.List;

import tp.bdd.Connexion;
import tp.collections.Producteurs;
import tp.objets.Fournisseur;
import tp.objets.Producteur;

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
            if (producteurs.chercherParNom(nom) != null) throw new Exception("Producteur déjà existant.");

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

            // Vérifier si le producteur existe
            if (producteurs.chercherParId(id) == null) throw new Exception("Producteur introuvable.");

            // Supprimer le producteur
            producteurs.supprimer(id);

            // Valider la transaction
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
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
