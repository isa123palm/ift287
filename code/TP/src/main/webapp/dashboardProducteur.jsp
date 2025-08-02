<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tp.objets.Producteur" %>

<%
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("producteur")) {
        response.sendRedirect("login.jsp");
        return;
    }

    Producteur utilisateur = (Producteur) session.getAttribute("utilisateur");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tableau de bord - Producteur</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .action-btn {
            display: block;
            width: 100%;
            margin-bottom: 10px;
            text-align: left;
        }
    </style>
</head>
<body class="p-4 bg-light">
    <div class="container">
        <h1 class="mb-4">Tableau de bord - Producteur</h1>

        <p><strong>Connecté en tant que :</strong> <%= utilisateur != null ? utilisateur.getNom() : "Inconnu" %></p>
        <hr>

        <h3 class="mb-3">Actions disponibles :</h3>

        <div class="d-grid gap-2">
            <a href="infos.jsp" class="btn btn-outline-secondary action-btn">Afficher informations (producteurs, produits, etc.)</a>
            <a href="ajouterProduit.jsp" class="btn btn-outline-primary action-btn">Ajouter un nouveau produit</a>
            <a href="supprimerProduit.jsp" class="btn btn-outline-danger action-btn">Supprimer un produit</a>
            <a href="associerFournisseur.jsp" class="btn btn-outline-success action-btn">Associer un fournisseur à un produit</a>
            <a href="associerDistributeur.jsp" class="btn btn-outline-success action-btn">Associer un distributeur à un produit</a>
            <a href="retirerFournisseur.jsp" class="btn btn-outline-warning action-btn">Retirer un fournisseur d’un produit</a>
            <a href="retirerDistributeur.jsp" class="btn btn-outline-warning action-btn">Retirer un distributeur d’un produit</a>
            <a href="supprimerProducteur.jsp" class="btn btn-outline-danger action-btn">Supprimer mon compte</a>
        </div>

        <form action="logout" method="post" class="mt-4">
            <button class="btn btn-danger">Se déconnecter</button>
        </form>
    </div>
</body>
</html>
