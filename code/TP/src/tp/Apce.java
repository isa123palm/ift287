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
            cx = new Connexion("objectdbPU");

            gestionProducteurs = new GestionProducteurs(cx.getEntityManager());
            gestionProduits = new GestionProduits(cx.getEntityManager());
            gestionFournisseurs = new GestionFournisseurs(cx.getEntityManager());
            gestionPointsDeVente = new GestionPointsDeVente(cx.getEntityManager());

            //IMPORTANT : CHANGE SOURCE ROOT FILE PATH
            BufferedReader reader = new BufferedReader(new FileReader("/Users/bellapalm/Library/CloudStorage/OneDrive-USherbrooke/4.E2025/IFT287/Projet final/GitHub/ift287/code/TP/src/transactions.txt"));
            String ligne;
            while ((ligne = reader.readLine()) != null) {
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
                case "ajouterProducteur" -> gestionProducteurs.ajouter(
                        st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), lireChaineAvecEspaces(st));

                case "afficherProducteur" -> gestionProducteurs.afficher(st.nextToken());

                case "supprimerProducteur" -> gestionProducteurs.supprimer(st.nextToken());

                case "ajouterProduit" -> gestionProduits.ajouter(
                        st.nextToken(), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()),
                        st.nextToken(), st.nextToken());

                case "afficherProduit" -> gestionProduits.afficher(st.nextToken());

                case "supprimerProduit" -> gestionProduits.supprimer(st.nextToken());

                case "ajouterFournisseur" -> gestionFournisseurs.ajouter(
                        st.nextToken(), st.nextToken(), lireChaineAvecEspaces(st));

                case "afficherFournisseur" -> gestionFournisseurs.afficher(st.nextToken());

                case "supprimerFournisseur" -> gestionFournisseurs.supprimer(st.nextToken());

                case "fabriquerProduit" -> gestionFournisseurs.fabriquerProduit(
                        st.nextToken(), st.nextToken());

                case "retirerProduitFournisseur" -> gestionFournisseurs.retirerProduit(
                        st.nextToken(), st.nextToken());

                case "ajouterPointDeVente" -> gestionPointsDeVente.ajouter(
                        st.nextToken(), st.nextToken(), lireChaineAvecEspaces(st));

                case "afficherPointDeVente" -> gestionPointsDeVente.afficher(st.nextToken());

                case "supprimerPointDeVente" -> gestionPointsDeVente.supprimer(st.nextToken());

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
