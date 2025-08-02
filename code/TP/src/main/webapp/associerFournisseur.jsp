<%@ page import="tp.objets.Producteur, tp.objets.Produit, tp.objets.Fournisseur" %>
<%@ page import="com.example.tp.Apce" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String role = (String) session.getAttribute("role");
    if (role == null || !role.equals("producteur")) {
        response.sendRedirect("login.jsp");
        return;
    }
    Producteur producteur = (Producteur) session.getAttribute("utilisateur");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Associer un fournisseur</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>.container { margin-top: 40px; }</style>
</head>
<body>
<div class="container">
    <a href="dashboardProducteur.jsp" class="btn btn-secondary mb-3">← Retour</a>
    <h2>Associer un fournisseur à un produit</h2>

    <% if (request.getAttribute("erreur") != null) { %>
        <div class="alert alert-danger"><%= request.getAttribute("erreur") %></div>
    <% } else if (request.getAttribute("message") != null) { %>
        <div class="alert alert-success"><%= request.getAttribute("message") %></div>
    <% } %>

    <form action="associer_fournisseur" method="post">
        <div class="mb-3">
            <label class="form-label">Produit :</label>
            <select name="idProduit" class="form-select" required>
                <% for (Produit prod : producteur.getProduits()) { %>
                    <option value="<%= prod.getId() %>"><%= prod.getNom() %></option>
                <% } %>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Fournisseur :</label>
            <select name="idFournisseur" class="form-select" required>
                <% for (Fournisseur f : Apce.getGestionFournisseurs().listerTous()) { %>
                    <option value="<%= f.getId() %>"><%= f.getNom() %> — <%= f.getCourriel() %></option>
                <% } %>
            </select>
        </div>

        <button type="submit" class="btn btn-primary">Associer</button>
    </form>
</div>
</body>
</html>
