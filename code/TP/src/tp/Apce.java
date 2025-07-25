package tp;

import tp.bdd.Connexion;
import tp.gestion.*;

import java.io.*;
import java.util.StringTokenizer;

public class Apce {
    private static Connexion cx;

    private static GestionProducteurs gestionProducteurs;
    private static GestionProduits gestionProduits;
    private static GestionFournisseurs gestionFournisseurs;
    private static GestionPointsDeVente gestionPointsDeVente;

    public static void main(String[] args) {
        try {
            // Connexion à la base de données avec ObjectDB
            cx = new Connexion("objectdbPU");

            // Créer les gestionnaires avec Connexion
            gestionProducteurs = new GestionProducteurs(cx);
            gestionProduits = new GestionProduits(cx);
            gestionFournisseurs = new GestionFournisseurs(cx);
            gestionPointsDeVente = new GestionPointsDeVente(cx);

            // IMPORTANT : ajuster le chemin vers votre fichier transactions.txt si nécessaire
            BufferedReader reader = new BufferedReader(new FileReader(
                    "/Users/moru/Desktop/ift287/gitProjetFinal/ift287/code/TP/src/transactions.txt"));

            String ligne;
            while ((ligne = reader.readLine()) != null) {
                // Echo de la commande
                System.out.println(ligne);
                traiterTransaction(ligne);
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            if (cx != null && cx.getEntityManager().getTransaction().isActive())
                cx.getEntityManager().getTransaction().rollback();
        } finally {
            if (cx != null) cx.fermer();
        }
    }

    private static void traiterTransaction(String ligne) throws Exception {
        StringTokenizer st = new StringTokenizer(ligne);
        if (!st.hasMoreTokens()) return;
        String commande = st.nextToken();

        try {
            switch (commande) {
                case "ajouterProducteur" -> gestionProducteurs.ajouterProducteur(
                        st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), lireChaineAvecEspaces(st));

                case "afficherProducteur" -> gestionProducteurs.afficherProducteur(st.nextToken());

                case "supprimerProducteur" -> gestionProducteurs.supprimerProducteur(st.nextToken());

                case "ajouterProduit" -> gestionProduits.ajouterProduit(
                        st.nextToken(), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()),
                        st.nextToken(), st.nextToken());

                case "afficherProduit" -> gestionProduits.afficherProduit(st.nextToken());

                case "supprimerProduit" -> gestionProduits.supprimerProduit(st.nextToken());

                case "ajouterFournisseur" -> gestionFournisseurs.ajouterFournisseur(
                        st.nextToken(), st.nextToken(), lireChaineAvecEspaces(st));

                case "afficherFournisseur" -> gestionFournisseurs.afficherFournisseur(st.nextToken());

                case "supprimerFournisseur" -> gestionFournisseurs.supprimerFournisseur(st.nextToken());

                case "fabriquerProduit" -> gestionFournisseurs.fabriquerProduit(
                        st.nextToken(), st.nextToken());

                case "retirerProduitFournisseur" -> gestionFournisseurs.retirerProduit(
                        st.nextToken(), st.nextToken());

                case "ajouterPointDeVente" -> gestionPointsDeVente.ajouterPointDeVente(
                        st.nextToken(), st.nextToken(), lireChaineAvecEspaces(st));

                case "afficherPointDeVente" -> gestionPointsDeVente.afficherPointDeVente(st.nextToken());

                case "supprimerPointDeVente" -> gestionPointsDeVente.supprimerPointDeVente(st.nextToken());

                case "vendreProduit" -> gestionPointsDeVente.vendreProduit(
                        st.nextToken(), st.nextToken());

                case "retirerProduitPointDeVente" -> gestionPointsDeVente.retirerProduit(
                        st.nextToken(), st.nextToken());

                case "exit" -> System.exit(0);

                default -> System.out.println("Commande inconnue. Tapez 'exit' pour quitter.");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            if (cx.getEntityManager().getTransaction().isActive())
                cx.getEntityManager().getTransaction().rollback();
        }
    }

    private static String lireChaineAvecEspaces(StringTokenizer st) {
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            sb.append(st.nextToken());
            if (st.hasMoreTokens()) sb.append(" ");
        }
        return sb.toString();
    }
}
