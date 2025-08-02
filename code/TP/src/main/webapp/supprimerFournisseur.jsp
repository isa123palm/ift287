<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="tp.objets.Fournisseur, tp.objets.Produit, tp.objets.Producteur" %>
<%
    String role = (String) session.getAttribute("role");
    if (role == null || !"fournisseur".equals(role)) {
        response.sendRedirect("login.jsp");
        return;
    }
    Fournisseur fournisseur = (Fournisseur) session.getAttribute("utilisateur");
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
    <a href="dashboardFournisseur.jsp" class="btn btn-secondary mb-3">← Retour</a>

    <h2 class="text-danger">Supprimer mon compte fournisseur</h2>
    <p>Cette action supprimera :</p>
    <ul>
        <li>Toutes les associations avec les produits</li>
        <li><strong>Les produits et producteurs ne seront pas supprimés</strong></li>
    </ul>

    <h5 class="mt-4">Résumé :</h5>
    <p><strong>Nom :</strong> <%= fournisseur.getNom() %></p>

    <ul class="list-group mb-3">
        <% for (Produit prod : fournisseur.getProduits()) { %>
            <li class="list-group-item">
                <strong><%= prod.getNom() %></strong>
                <ul>
                    <li>Catégorie : <%= prod.getCategorie() %></li>
                    <li>Producteur :
                        <%= prod.getProducteur() != null ? prod.getProducteur().getNom() : "Inconnu" %>
                    </li>
                </ul>
            </li>
        <% } %>
    </ul>

    <% if (request.getAttribute("erreur") != null) { %>
        <div class="alert alert-danger"><%= request.getAttribute("erreur") %></div>
    <% } %>

    <form method="post" action="supprimer_fournisseur"
          onsubmit="return confirm('Êtes-vous certain de vouloir supprimer définitivement ce compte fournisseur ?')">
        <button type="submit" class="btn btn-danger">Supprimer mon compte</button>
    </form>
</div>
</body>
</html>
