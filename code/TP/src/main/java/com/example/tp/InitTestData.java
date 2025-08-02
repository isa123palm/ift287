package com.example.tp;

import tp.objets.*;

import javax.persistence.EntityManager;

public class InitTestData {

    public static void ajouterDonneesTest() throws Exception {
        EntityManager em = Apce.getEntityManager();
        em.getTransaction().begin();

        // === Producteurs ===
        Producteur prod1 = new Producteur("ConfiTest Inc.", "prod1@gmail.com", "123", 10, "123 Rue Test");
        Producteur prod2 = new Producteur("JamJam", "jamjam@gmail.com", "123", 5, "456 Rue Sucrée");
        Producteur prod3 = new Producteur("Délices du Nord", "prod3@gmail.com", "123", 7, "789 Rue Boréale");

        // === Produits ===
        Produit p1 = new Produit("Fraise Magique", 4.99, 2.50, "Fruits rouges", prod1);
        Produit p2 = new Produit("Abricot Soleil", 5.49, 2.80, "Exotiques", prod1);
        Produit p3 = new Produit("Bleuet Boréal", 6.99, 3.00, "Nordiques", prod2);
        Produit p4 = new Produit("Cerise Noire", 5.25, 2.40, "Fruits rouges", prod3);
        Produit p5 = new Produit("Pomme d’Or", 4.79, 2.20, "Classiques", prod3);

        prod1.getProduits().add(p1);
        prod1.getProduits().add(p2);
        prod2.getProduits().add(p3);
        prod3.getProduits().add(p4);
        prod3.getProduits().add(p5);

        // === Fournisseurs ===
        Fournisseur f1 = new Fournisseur("Fournisaveurs", "fourn1@gmail.com", "123", "789 Rue des Champs");
        Fournisseur f2 = new Fournisseur("ÉpicéAgréable", "fourn2@gmail.com", "123", "321 Rue des Épices");
        Fournisseur f3 = new Fournisseur("NatureSupreme", "fourn3@gmail.com", "123", "654 Rue Bio");

        // Association Produits ↔ Fournisseurs
        p1.ajouterFournisseur(f1);
        f1.ajouterProduit(p1);

        p2.ajouterFournisseur(f2);
        f2.ajouterProduit(p2);

        p3.ajouterFournisseur(f1);
        f1.ajouterProduit(p3);

        p4.ajouterFournisseur(f3);
        f3.ajouterProduit(p4);

        p5.ajouterFournisseur(f2);
        p5.ajouterFournisseur(f3);
        f2.ajouterProduit(p5);
        f3.ajouterProduit(p5);

        // Producteurs ↔ Fournisseurs
        prod1.ajouterFournisseur(f1);
        prod1.ajouterFournisseur(f2);
        prod2.ajouterFournisseur(f1);
        prod3.ajouterFournisseur(f2);
        prod3.ajouterFournisseur(f3);

        // === Points de vente ===
        PointDeVente pdv1 = new PointDeVente("Marché du Coin", "pdv1@gmail.com", "123", "1 Rue Marché");
        PointDeVente pdv2 = new PointDeVente("Boutique Saveurs", "pdv2@gmail.com", "123", "2 Rue Gourmande");
        PointDeVente pdv3 = new PointDeVente("DistriBio", "pdv3@gmail.com", "123", "3 Rue Verte");

        // Association Produits ↔ Distributeurs
        p1.ajouterPointDeVente(pdv1);
        p2.ajouterPointDeVente(pdv1);
        p3.ajouterPointDeVente(pdv2);
        p4.ajouterPointDeVente(pdv2);
        p5.ajouterPointDeVente(pdv3);

        pdv1.ajouterProduit(p1);
        pdv1.ajouterProduit(p2);
        pdv2.ajouterProduit(p3);
        pdv2.ajouterProduit(p4);
        pdv3.ajouterProduit(p5);

        // Producteurs ↔ Distributeurs
        prod1.ajouterPointDeVente(pdv1);
        prod2.ajouterPointDeVente(pdv2);
        prod3.ajouterPointDeVente(pdv2);
        prod3.ajouterPointDeVente(pdv3);

        em.persist(f1);
        em.persist(f2);
        em.persist(f3);
        em.persist(pdv1);
        em.persist(pdv2);
        em.persist(pdv3);
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.persist(p4);
        em.persist(p5);
        em.persist(prod1);
        em.persist(prod2);
        em.persist(prod3);

        em.getTransaction().commit();
        System.out.println("Données de test insérées");
    }
}
