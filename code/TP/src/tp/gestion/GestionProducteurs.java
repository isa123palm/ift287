package tp.gestion;

import tp.collections.Producteurs;
import tp.objets.Producteur;

import javax.persistence.EntityManager;

public class GestionProducteurs {
    private final EntityManager em;
    private final Producteurs producteurs;

    public GestionProducteurs(EntityManager em) {
        this.em = em;
        this.producteurs = new Producteurs(em);
    }

    public void ajouterProducteur(String nom, String courriel, int nbEmp, String adresse) throws Exception {
        try {
            em.getTransaction().begin();
            producteurs.ajouter(new Producteur(nom, courriel, nbEmp, adresse));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void afficherProducteur(String nom) throws Exception {
        try {
        Producteur p = producteurs.chercher(nom);
        if (p == null) throw new Exception("Producteur introuvable");
        System.out.println("Nom: " + p.getNom());
        System.out.println("Courriel: " + p.getCourriel());
            System.out.println("Adresse: " + p.getAdresse());
            System.out.println("Nb employés: " + p.getNbEmployes());
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public void supprimerProducteur(String nom) throws Exception {
        try {
            em.getTransaction().begin();
            producteurs.supprimer(nom);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
