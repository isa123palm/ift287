<%@ page import="com.example.tp.Apce" %>
<%@ page import="tp.objets.Producteur, tp.objets.Fournisseur, tp.objets.PointDeVente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Utilisateurs enregistrés</title>
</head>
<body>

    <p>Classe Apce chargée : <%= Apce.class.getName() %></p>

    <p>Initialisation Apce réussie ? <%= (Apce.getGestionFournisseurs() != null) %></p>

    <h2>Fournisseurs</h2>
    <ul>
        <%
            try {
                for (Fournisseur f : Apce.getGestionFournisseurs().listerTous()) {
                    out.println("<li>" + f.getNom() + " (" + f.getCourriel() + ")</li>");
                }
            } catch (Exception e) {
                out.println("<li>Erreur : " + e.getMessage() + "</li>");
            }
        %>
    </ul>
</body>
</html>
