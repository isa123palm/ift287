

import tp.bdd.Connexion;
import tp.gestion.GestionProducteurs;
import tp.gestion.GestionProduits;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) throws Exception {
        // Import all tp.gestion
        Connexion cx = new Connexion("objectdbPU");
        EntityManager em = cx.getEntityManager();

        GestionProducteurs gp = new GestionProducteurs(em);
        GestionProduits gprod = new GestionProduits(em);

// Delete first if exists (clean reset)
        try {
            gprod.supprimerProduit("Confi-Mangue");  // Suppresses product first
            gp.supprimerProducteur("BellaJam");         // Then deletes producer
        } catch (Exception e) {
            System.out.println("warning :  " + e.getMessage());  // Already deleted is fine
        }

// re-add
        try {
            gp.ajouterProducteur("BellaJam", "bella@jam.ca", 7, "123 rue des fruits");
            gprod.ajouterProduit("Confi-Mangue", 8.99, 3.25, "mangue", "BellaJam");
            gprod.afficherProduit("Confi-Mangue");
        } catch (Exception e) {
            System.out.println("error : " + e.getMessage());
        }

        cx.fermer();
    }
}
