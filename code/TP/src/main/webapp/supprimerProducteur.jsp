<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="tp.objets.Producteur, tp.objets.Produit, tp.objets.Fournisseur, tp.objets.PointDeVente" %>
<%
    String role = (String) session.getAttribute("role");
    if (role == null || !"producteur".equals(role)) {
        response.sendRedirect("login.jsp");
        return;
    }
    Producteur producteur = (Producteur) session.getAttribute("utilisateur");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Supprimer mon compte</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="p-4">
<div class="container">
    <a href="dashboardProducteur.jsp" class="btn btn-secondary mb-3">← Retour</a>

    <h2 class="text-danger">Supprimer mon compte producteur</h2>
    <p>Cette action supprimera :</p>
    <ul>
        <li>Tous vos produits</li>
        <li>Les associations avec les fournisseurs et distributeurs de ces produits</li>
        <li><strong>Les fournisseurs et distributeurs eux-mêmes ne seront pas supprimés</strong></li>
    </ul>

    <h5 class="mt-4">Résumé :</h5>
    <p><strong>Nom :</strong> <%= producteur.getNom() %></p>

    <ul class="list-group mb-3">
        <% for (Produit prod : producteur.getProduits()) { %>
            <li class="list-group-item">
                <strong><%= prod.getNom() %></strong>
                <ul>
                    <li>Fournisseurs :
                        <%= prod.getFournisseurs().stream()
                            .map(Fournisseur::getNom)
                            .reduce((a, b) -> a + ", " + b)
                            .orElse("Aucun") %>
                    </li>
                    <li>Distributeurs :
                        <%= prod.getPointsDeVente().stream()
                            .map(PointDeVente::getNom)
                            .reduce((a, b) -> a + ", " + b)
                            .orElse("Aucun") %>
                    </li>
                </ul>
            </li>
        <% } %>
    </ul>

    <% if (request.getAttribute("erreur") != null) { %>
        <div class="alert alert-danger"><%= request.getAttribute("erreur") %></div>
    <% } %>

    <form method="post" action="supprimer_producteur"
          onsubmit="return confirm('Êtes-vous certain de vouloir supprimer définitivement ce compte producteur ?')">
        <button type="submit" class="btn btn-danger">Supprimer mon compte</button>
    </form>
</div>
</body>
</html>
