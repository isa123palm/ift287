<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Connexion - APCE</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background: linear-gradient(to bottom right, #e0f7fa, #ffffff);
                min-height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
            }
            .card {
                border-radius: 20px;
                box-shadow: 0 5px 15px rgba(0,0,0,0.1);
            }
            .logo {
                width: 60px;
                margin: 0 auto;
                display: block;
            }
        </style>
    </head>
    <body>
        <div class="card p-4" style="width: 100%; max-width: 400px;">
            <img src="res/logo_apce.png" alt="Logo APCE" class="logo mb-3">
            <h3 class="text-center">Connexion</h3>
            <p class="text-center text-muted">Connectez-vous à votre compte</p>

            <form action="login" method="post">
                <div class="mb-3">
                    <label for="email" class="form-label">Courriel</label>
                    <input type="email" name="email" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="password" class="form-label">Mot de passe</label>
                    <input type="password" name="password" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="role" class="form-label">Rôle</label>
                    <select name="role" class="form-select" required>
                        <option value="">-- Choisissez un rôle --</option>
                        <option value="producteur">Producteur</option>
                        <option value="fournisseur">Fournisseur</option>
                        <option value="distributeur">Distributeur</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-dark w-100">Se connecter</button>
            </form>

            <% if (request.getAttribute("erreur") != null) { %>
                <div class="alert alert-danger mt-3 text-center">
                    <%= request.getAttribute("erreur") %>
                </div>
            <% } %>


            <div class="text-center mt-3">
                <a href="register.jsp">Créer un compte</a>
            </div>
        </div>
    </body>
</html>
