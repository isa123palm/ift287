<%@ page import="tp.objets.Produit, tp.objets.PointDeVente, com.example.tp.Apce" %>
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
    <title>Vendre un produit</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="p-4">
<div class="container">
    <a href="dashboardPointDeVente.jsp" class="btn btn-secondary mb-3">← Retour</a>

    <h2>Vendre un produit</h2>
    <p>Choisissez un produit existant que vous souhaitez vendre dans votre point de vente.</p>

    <% if (request.getAttribute("message") != null) { %>
        <div class="alert alert-success"><%= request.getAttribute("message") %></div>
    <% } %>
    <% if (request.getAttribute("erreur") != null) { %>
        <div class="alert alert-danger"><%= request.getAttribute("erreur") %></div>
    <% } %>

    <form method="post" action="vendre_produit">
        <div class="mb-3">
            <label for="idProduit" class="form-label">Produit existant :</label>
            <select class="form-select" id="idProduit" name="idProduit" required>
                <%
                    for (Produit p : Apce.getGestionProduits().listerTous()) {
                        if (!pdv.getProduits().contains(p)) {
                %>
                    <option value="<%= p.getId() %>"><%= p.getNom() %> — <%= p.getCategorie() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Commencer la vente</button>
    </form>
</div>
</body>
</html>
