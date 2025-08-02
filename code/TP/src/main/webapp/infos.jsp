<%@ page import="com.example.tp.Apce" %>
<%@ page import="tp.objets.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Informations complètes</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            margin: 30px;
            background-color: #f7f9fb;
        }

        .back-btn {
            display: inline-block;
            background-color: #3498db;
            color: white;
            padding: 8px 16px;
            text-decoration: none;
            border-radius: 4px;
            font-size: 14px;
            margin-bottom: 20px;
        }

        h1 {
            font-size: 28px;
            color: #2c3e50;
        }

        h2 {
            border-bottom: 2px solid #3498db;
            color: #2c3e50;
            margin-top: 40px;
        }

        ul {
            list-style: none;
            padding-left: 0;
        }

        li {
            background: #ffffff;
            padding: 15px;
            margin: 10px 0;
            border-radius: 6px;
            box-shadow: 0 1px 3px rgba(0,0,0,0.1);
        }

        li ul {
            padding-left: 20px;
            list-style: disc;
        }

        strong {
            font-size: 16px;
            color: #34495e;
        }

        .section {
            margin-top: 50px;
        }

        .label {
            color: #555;
            font-weight: bold;
        }
    </style>
</head>
<body>

<%
    String backUrl = "login.jsp";
    if ("producteur".equals(session.getAttribute("role"))) {
        backUrl = "dashboardProducteur.jsp";
    } else if ("fournisseur".equals(session.getAttribute("role"))) {
        backUrl = "dashboardFournisseur.jsp";
    } else if ("distributeur".equals(session.getAttribute("role"))) {
        backUrl = "dashboardPointDeVente.jsp";
    }
%>
<a href="<%= backUrl %>" class="back-btn">← Retour</a>


<h1>Listes complètes</h1>

<%
    try {
%>
<!-- Producteurs -->
<div class="section">
    <h2>Producteurs</h2>
    <ul>
    <%
        for (Producteur p : Apce.getGestionProducteurs().listerTous()) {
    %>
        <li>
            <strong><%= p.getNom() %></strong><br/>
            <span class="label">Courriel:</span> <%= p.getCourriel() %><br/>
            <span class="label">Adresse:</span> <%= p.getAdresse() %><br/>
            <span class="label">Employés:</span> <%= p.getNbEmployes() %><br/>

            <span class="label">Produits:</span>
            <ul>
                <% for (Produit prod : p.getProduits()) { %>
                    <li><%= prod.getNom() %></li>
                <% } %>
            </ul>

            <span class="label">Fournisseurs:</span>
            <ul>
                <% for (Fournisseur f : p.getFournisseurs()) { %>
                    <li><%= f.getNom() %></li>
                <% } %>
            </ul>

            <span class="label">Points de vente:</span>
            <ul>
                <% for (PointDeVente pdv : p.getPointsDeVente()) { %>
                    <li><%= pdv.getNom() %></li>
                <% } %>
            </ul>
        </li>
    <%
        }
    %>
    </ul>
</div>

<!-- Fournisseurs -->
<div class="section">
    <h2>Fournisseurs</h2>
    <ul>
    <%
        for (Fournisseur f : Apce.getGestionFournisseurs().listerTous()) {
    %>
        <li>
            <strong><%= f.getNom() %></strong><br/>
            <span class="label">Courriel:</span> <%= f.getCourriel() %><br/>
            <span class="label">Adresse:</span> <%= f.getAdresse() %><br/>

            <span class="label">Produits fournis:</span>
            <ul>
                <% for (Produit prod : f.getProduits()) { %>
                    <li><%= prod.getNom() %></li>
                <% } %>
            </ul>

            <span class="label">Producteurs associés:</span>
            <ul>
                <% for (Producteur p : f.getProducteurs()) { %>
                    <li><%= p.getNom() %></li>
                <% } %>
            </ul>
        </li>
    <%
        }
    %>
    </ul>
</div>

<!-- Points de vente -->
<div class="section">
    <h2>Points de vente</h2>
    <ul>
    <%
        for (PointDeVente pdv : Apce.getGestionPointsDeVente().listerTous()) {
    %>
        <li>
            <strong><%= pdv.getNom() %></strong><br/>
            <span class="label">Courriel:</span> <%= pdv.getCourriel() %><br/>
            <span class="label">Adresse:</span> <%= pdv.getAdresse() %><br/>

            <span class="label">Produits vendus:</span>
            <ul>
                <% for (Produit prod : pdv.getProduits()) { %>
                    <li><%= prod.getNom() %></li>
                <% } %>
            </ul>

            <span class="label">Producteurs associés:</span>
            <ul>
                <% for (Producteur p : pdv.getProducteurs()) { %>
                    <li><%= p.getNom() %></li>
                <% } %>
            </ul>
        </li>
    <%
        }
    %>
    </ul>
</div>

<!-- Produits -->
<div class="section">
    <h2>Produits</h2>
    <ul>
    <%
        for (Produit prod : Apce.getGestionProduits().listerTous()) {
    %>
        <li>
            <strong><%= prod.getNom() %></strong><br/>
            <span class="label">Catégorie:</span> <%= prod.getCategorie() %><br/>
            <span class="label">Prix:</span> <%= prod.getPrix() %><br/>
            <span class="label">Coût:</span> <%= prod.getCout() %><br/>
            <span class="label">Producteur:</span> <%= prod.getProducteur() != null ? prod.getProducteur().getNom() : "inconnu" %><br/>

            <span class="label">Fournisseurs:</span>
            <ul>
                <% for (Fournisseur f : prod.getFournisseurs()) { %>
                    <li><%= f.getNom() %></li>
                <% } %>
            </ul>

            <span class="label">Points de vente:</span>
            <ul>
                <% for (PointDeVente pdv : prod.getPointsDeVente()) { %>
                    <li><%= pdv.getNom() %></li>
                <% } %>
            </ul>
        </li>
    <%
        }
    %>
    </ul>
</div>

<%
    } catch (Exception e) {
        out.println("<p style='color:red;'>Erreur : " + e.getMessage() + "</p>");
    }
%>

</body>
</html>
