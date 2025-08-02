package com.example.tp;

import tp.bdd.Connexion;
import tp.gestion.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

import javax.persistence.EntityManager;

public class Apce {
    private static Connexion cx;

    private static GestionProducteurs gestionProducteurs;
    private static GestionProduits gestionProduits;
    private static GestionFournisseurs gestionFournisseurs;
    private static GestionPointsDeVente gestionPointsDeVente;

    public static void main(String[] args) {
        try {
            // Connexion à la base de données
            cx = new Connexion("objectdbPU");
            gestionProducteurs = new GestionProducteurs(cx);
            gestionProduits = new GestionProduits(cx);
            gestionFournisseurs = new GestionFournisseurs(cx);
            gestionPointsDeVente = new GestionPointsDeVente(cx);

            // Exécution des transactions en console
            BufferedReader reader = new BufferedReader( new FileReader("code/TP/src/main/java/transactions.txt"));
            traiterTransactions(reader, true);

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
            if (cx != null && cx.getEntityManager().getTransaction().isActive())
                cx.getEntityManager().getTransaction().rollback();
        } finally {
            if (cx != null) cx.fermer();
        }
    }


    private static void traiterTransactions(BufferedReader reader, boolean echo) throws Exception {
        String ligne;
        while ((ligne = reader.readLine()) != null) {
            if (echo) System.out.println(ligne);
            StringTokenizer st = new StringTokenizer(ligne);
            if (!st.hasMoreTokens()) continue;
            executerTransaction(st);
        }
    }

    private static void executerTransaction(StringTokenizer st) throws Exception {
        String commande = st.nextToken();
        try {
            switch (commande) {
                case "ajouterProducteur" -> gestionProducteurs.ajouterProducteur(
                        st.nextToken(), st.nextToken(), st.nextToken(), Integer.parseInt(st.nextToken()), lireChaineAvecEspaces(st));

                case "afficherProducteur" -> gestionProducteurs.afficherProducteur(Integer.parseInt(st.nextToken()));

                case "supprimerProducteur" -> gestionProducteurs.supprimerProducteur(Integer.parseInt(st.nextToken()));

                case "ajouterProduit" -> gestionProduits.ajouterProduit(
                        st.nextToken(), Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()),
                        st.nextToken(), st.nextToken());

                case "afficherProduit" -> gestionProduits.afficherProduit(Integer.parseInt(st.nextToken()));

                case "supprimerProduit" -> gestionProduits.supprimerProduit(Integer.parseInt(st.nextToken()));

                case "ajouterFournisseur" -> gestionFournisseurs.ajouterFournisseur(
                        st.nextToken(), st.nextToken(), st.nextToken(), lireChaineAvecEspaces(st));

                case "afficherFournisseur" -> gestionFournisseurs.afficherFournisseur(Integer.parseInt(st.nextToken()));

                case "supprimerFournisseur" -> gestionFournisseurs.supprimerFournisseur(Integer.parseInt(st.nextToken()));

                case "fabriquerProduit" -> gestionFournisseurs.fabriquerProduit(
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

                case "retirerProduitFournisseur" -> gestionFournisseurs.retirerProduit(
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

                case "ajouterPointDeVente" -> gestionPointsDeVente.ajouterPointDeVente(
                    st.nextToken(), st.nextToken(), st.nextToken(), lireChaineAvecEspaces(st));

                case "afficherPointDeVente" -> gestionPointsDeVente.afficherPointDeVente(Integer.parseInt(st.nextToken()));

                case "supprimerPointDeVente" -> gestionPointsDeVente.supprimerPointDeVente(Integer.parseInt(st.nextToken()));

                case "vendreProduit" -> gestionPointsDeVente.vendreProduit(
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

                case "retirerProduitPointDeVente" -> gestionPointsDeVente.retirerProduit(
                        Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

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

    public static void init(Connexion connection) {
        cx = connection;
        gestionProducteurs = new GestionProducteurs(cx);
        gestionProduits = new GestionProduits(cx);
        gestionFournisseurs = new GestionFournisseurs(cx);
        gestionPointsDeVente = new GestionPointsDeVente(cx);
    }

    public static EntityManager getEntityManager() {
        return cx.getConnection();
    }

    public static GestionProducteurs getGestionProducteurs() {
        return gestionProducteurs;
    }

    public static GestionFournisseurs getGestionFournisseurs() {
        return gestionFournisseurs;
    }

    public static GestionPointsDeVente getGestionPointsDeVente() {
        return gestionPointsDeVente;
    }

    public static GestionProduits getGestionProduits() {
        return gestionProduits;
    }

}
