package tp.gestion;

import tp.bdd.Connexion;
import tp.collections.Producteurs;
import tp.objets.Producteur;

public class GestionProducteurs {
    private final Connexion cx;
    private final Producteurs producteurs;

    public GestionProducteurs(Connexion cx) {
        this.cx = cx;
        this.producteurs = new Producteurs(cx);
    }

    public void ajouterProducteur(String nom, String courriel, int nbEmp, String adresse) throws Exception {
        try {
            // Démarrer la transaction
            cx.demarreTransaction();
            // Vérifier si le producteur existe déjà
            if(producteurs.chercher(nom) != null)
                throw new Exception("Producteur déjà existant.");
            // Ajouter le producteur
            producteurs.ajouter(new Producteur(nom, courriel, nbEmp, adresse));
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }

    public void afficherProducteur(String nom) throws Exception {
        try {
            // Chercher le producteur
            Producteur p = producteurs.chercher(nom);
            if (p == null) throw new Exception("Producteur introuvable");
            // Afficher les informations du producteur
            System.out.println("Nom: " + p.getNom());
            System.out.println("Courriel: " + p.getCourriel());
            System.out.println("Adresse: " + p.getAdresse());
            System.out.println("Nb employés: " + p.getNbEmployes());
        } catch (Exception e) {
            cx.rollback(); // même si pas de commit, rollback protège contre bugs
            throw e;
        }
    }

    public void supprimerProducteur(String nom) throws Exception {
        try {
            cx.demarreTransaction();
            // Vérifier si le producteur existe
            if(producteurs.chercher(nom) == null)
                throw new Exception("Producteur introuvable.");
            // Supprimer le producteur
            producteurs.supprimer(nom);
            // Valider la transaction
            cx.commit();
        } catch (Exception e) {
            cx.rollback();
            throw e;
        }
    }
}
