<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%
    String role = request.getParameter("role") != null ? request.getParameter("role") : "producteur";
    String nom = request.getParameter("nom") != null ? request.getParameter("nom") : "";
    String adresse = request.getParameter("adresse") != null ? request.getParameter("adresse") : "";
    String email = request.getParameter("email") != null ? request.getParameter("email") : "";
    String password = request.getParameter("password") != null ? request.getParameter("password") : "";
    String nbEmployes = request.getParameter("nbEmployes") != null ? request.getParameter("nbEmployes") : "";
    String erreur = (String) request.getAttribute("erreur");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Inscription - APCE</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to bottom, #e0f7ff, #ffffff);
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }
        .card {
            max-width: 450px;
            width: 100%;
            padding: 2rem;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
            border-radius: 1rem;
            text-align: center;
        }
        img.logo {
            width: 60px;
            height: auto;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
    <div class="card">
        <img src="res/logo_apce.png" alt="Logo APCE" class="logo mb-3">

        <div class="text-center mt-3">
            <a href="login.jsp">Déjà un compte?</a>
        </div>

        <h4 class="mb-3">Créer un compte</h4>

        <% if (erreur != null) { %>
            <div class="alert alert-danger text-start" role="alert">
                <%= erreur %>
            </div>
        <% } %>

        <form action="register" method="post">
            <div class="mb-3 text-start">
                <label class="form-label">Rôle</label>
                <select name="role" id="role" class="form-select" onchange="toggleFields()" required>
                    <option value="producteur" <%= "producteur".equals(role) ? "selected" : "" %>>Producteur</option>
                    <option value="fournisseur" <%= "fournisseur".equals(role) ? "selected" : "" %>>Fournisseur</option>
                    <option value="distributeur" <%= "distributeur".equals(role) ? "selected" : "" %>>Distributeur</option>
                </select>
            </div>

            <div class="mb-3 text-start">
                <label class="form-label">Nom</label>
                <input type="text" name="nom" class="form-control" value="<%= nom %>" required>
            </div>

            <div class="mb-3 text-start">
                <label class="form-label">Adresse</label>
                <input type="text" name="adresse" class="form-control" value="<%= adresse %>" required>
            </div>

            <div class="mb-3 text-start">
                <label class="form-label">Courriel</label>
                <input type="email" name="email" class="form-control" value="<%= email %>" required>
            </div>

            <div class="mb-3 text-start">
                <label class="form-label">Mot de passe</label>
                <input type="password" name="password" class="form-control" value="<%= password %>" required>
            </div>

            <div id="champEmployes" class="mb-3 text-start" style="<%= "producteur".equals(role) ? "" : "display: none;" %>">
                <label class="form-label">Nombre d’employés</label>
                <input type="number" name="nbEmployes" class="form-control" value="<%= nbEmployes %>">
            </div>

            <button type="submit" class="btn btn-primary w-100">S'inscrire</button>
        </form>
    </div>

    <script>
        function toggleFields() {
            const role = document.getElementById("role").value;
            const champ = document.getElementById("champEmployes");
            champ.style.display = (role === "producteur") ? "block" : "none";
        }
        window.onload = toggleFields;
    </script>
</body>
</html>
