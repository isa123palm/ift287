<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="tp.objets.PointDeVente" %>
<%
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("distributeur")) {
        response.sendRedirect("login.jsp");
        return;
    }

    PointDeVente utilisateur = (PointDeVente) session.getAttribute("utilisateur");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Tableau de bord - Distributeur</title>
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
    <h1 class="mb-4">Tableau de bord - Distributeur</h1>

    <p><strong>Connecté en tant que :</strong> <%= utilisateur != null ? utilisateur.getNom() : "Inconnu" %></p>

    <hr>

    <h3 class="mb-3">Actions disponibles :</h3>

    <div class="d-grid gap-2">
        <a href="infos.jsp" class="btn btn-outline-info action-btn">Afficher les informations</a>
        <a href="vendreProduit.jsp" class="btn btn-outline-primary action-btn">Vendre un produit</a>
        <a href="retirerProduitPointDeVente.jsp" class="btn btn-outline-warning action-btn">Retirer un produit de la vente</a>
        <a href="supprimerPointDeVente.jsp" class="btn btn-outline-danger action-btn">Supprimer mon compte</a>
    </div>

    <form action="logout" method="post" class="mt-4">
        <button class="btn btn-danger">Se déconnecter</button>
    </form>
</div>

</body>
</html>
