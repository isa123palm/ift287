<%@ page import="tp.objets.Producteur, tp.objets.Produit, tp.objets.Fournisseur, tp.objets.PointDeVente" %>
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
    <title>Supprimer un produit</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container { margin-top: 40px; }
        .product-item { margin-bottom: 1.5rem; padding: 1rem; border: 1px solid #ccc; border-radius: 6px; }
        .assoc-box { margin-top: 0.5rem; }
        .assoc-box ul { padding-left: 1.25rem; }
    </style>
</head>
<body>
<div class="container">
    <a href="dashboardProducteur.jsp" class="btn btn-secondary mb-3">← Retour</a>
    <h2>Supprimer un de vos produits</h2>
    <p class="text-danger">Cette action supprimera aussi les associations avec les fournisseurs et les distributeurs.</p>

    <% if (request.getAttribute("erreur") != null) { %>
        <div class="alert alert-danger"><%= request.getAttribute("erreur") %></div>
    <% } %>

    <% for (Produit p : producteur.getProduits()) { %>
        <div class="product-item">
            <div>
                <h5><%= p.getNom() %></h5>
                <p class="mb-1">Catégorie : <%= p.getCategorie() %><br>
                Prix : <%= p.getPrix() %> | Coût : <%= p.getCout() %></p>

                <% if (!p.getFournisseurs().isEmpty() || !p.getPointsDeVente().isEmpty()) { %>
                    <div class="alert alert-warning assoc-box">
                        <strong>Associations existantes :</strong>
                        <ul>
                            <% if (!p.getFournisseurs().isEmpty()) { %>
                                <li>Fournisseurs :
                                    <ul>
                                        <% for (Fournisseur f : p.getFournisseurs()) { %>
                                            <li><%= f.getNom() %></li>
                                        <% } %>
                                    </ul>
                                </li>
                            <% } %>
                            <% if (!p.getPointsDeVente().isEmpty()) { %>
                                <li>Points de vente :
                                    <ul>
                                        <% for (PointDeVente pdv : p.getPointsDeVente()) { %>
                                            <li><%= pdv.getNom() %></li>
                                        <% } %>
                                    </ul>
                                </li>
                            <% } %>
                        </ul>
                    </div>
                <% } else { %>
                    <div class="text-muted">Aucune association.</div>
                <% } %>
            </div>

            <form method="post" action="supprimer_produit" class="mt-3">
                <input type="hidden" name="idProduit" value="<%= p.getId() %>">
                <button type="submit" class="btn btn-danger"
                        onclick="return confirm('Confirmer la suppression du produit « <%= p.getNom() %> » ? Cette action est irréversible.')">
                    Supprimer
                </button>
            </form>
        </div>
    <% } %>
</div>
</body>
</html>
