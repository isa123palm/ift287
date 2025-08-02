<%@ page import="tp.objets.PointDeVente, tp.objets.Produit, com.example.tp.Apce" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("distributeur")) {
        response.sendRedirect("login.jsp");
        return;
    }

    PointDeVente pdv = (PointDeVente) session.getAttribute("utilisateur");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Retirer un produit vendu</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="p-4 bg-light">
<div class="container">
    <a href="dashboardPointDeVente.jsp" class="btn btn-secondary mb-3">← Retour</a>

    <h2>Retirer un produit de votre point de vente</h2>
    <p class="text-danger">Cette action retirera le lien entre vous et le produit. Cela ne supprimera pas le produit.</p>

    <% if (request.getAttribute("erreur") != null) { %>
        <div class="alert alert-danger"><%= request.getAttribute("erreur") %></div>
    <% } else if (request.getAttribute("message") != null) { %>
        <div class="alert alert-success"><%= request.getAttribute("message") %></div>
    <% } %>

    <ul class="list-group">
        <% for (Produit p : pdv.getProduits()) { %>
            <li class="list-group-item">
                <strong><%= p.getNom() %></strong>
                — <%= p.getCategorie() %><br>
                Producteur : <%= p.getProducteur() != null ? p.getProducteur().getNom() : "Inconnu" %>

                <form action="retirer_produit_pointdevente" method="post" class="mt-2"
                      onsubmit="return confirm('Voulez-vous vraiment retirer ce produit ? Cette action supprimera l’association.')">
                    <input type="hidden" name="idProduit" value="<%= p.getId() %>">
                    <button type="submit" class="btn btn-warning btn-sm">Retirer</button>
                </form>
            </li>
        <% } %>
    </ul>
</div>
</body>
</html>
