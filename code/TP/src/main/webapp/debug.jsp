<%@ page import="com.example.tp.Apce" %>
<%@ page import="tp.objets.Producteur, tp.objets.Fournisseur, tp.objets.PointDeVente, tp.objets.Produit" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Utilisateurs enregistrés</title>
</head>
<body>

    <p>Classe Apce chargée : <%= Apce.class.getName() %></p>

    <p>Initialisation Apce réussie ? 
        <%= (Apce.getGestionFournisseurs() != null && Apce.getGestionProducteurs() != null && Apce.getGestionPointsDeVente() != null && Apce.getGestionProduits() != null) %>
    </p>

    <h2>Fournisseurs</h2>
    <ul>
        <%
            try {
                for (Fournisseur f : Apce.getGestionFournisseurs().listerTous()) {
                    out.println("<li>" + f.getNom() + " (" + f.getCourriel() + ")</li>");
                }
            } catch (Exception e) {
                out.println("<li>Erreur Fournisseurs : " + e.getMessage() + "</li>");
            }
        %>
    </ul>

    <h2>Producteurs</h2>
    <ul>
        <%
            try {
                for (Producteur p : Apce.getGestionProducteurs().listerTous()) {
                    out.println("<li>" + p.getNom() + " (" + p.getCourriel() + ") — " + p.getNbEmployes() + " employés</li>");
                }
            } catch (Exception e) {
                out.println("<li>Erreur Producteurs : " + e.getMessage() + "</li>");
            }
        %>
    </ul>

    <h2>Points de vente</h2>
    <ul>
        <%
            try {
                for (PointDeVente pdv : Apce.getGestionPointsDeVente().listerTous()) {
                    out.println("<li>" + pdv.getNom() + " (" + pdv.getCourriel() + ")</li>");
                }
            } catch (Exception e) {
                out.println("<li>Erreur Points de vente : " + e.getMessage() + "</li>");
            }
        %>
    </ul>

    <h2>Produits</h2>
    <ul>
        <%
            try {
                for (Produit produit : Apce.getGestionProduits().listerTous()) {
                    out.println("<li>" + produit.getNom() + " – " + produit.getCategorie() +
                                " | Prix: " + produit.getPrix() + " | Coût: " + produit.getCout() +
                                " | Producteur: " + produit.getProducteur().getNom() + "</li>");
                }
            } catch (Exception e) {
                out.println("<li>Erreur Produits : " + e.getMessage() + "</li>");
            }
        %>
    </ul>

</body>
</html>
