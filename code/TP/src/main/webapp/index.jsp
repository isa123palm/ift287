<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion à la base de données</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <h2>Connexion à la base de données</h2>
    <form method="post" action="init_bd" class="col-md-6">

        <div class="mb-3">
            <label for="serveur" class="form-label">Serveur</label>
            <select class="form-select" name="serveur" id="serveur">
                <option value="local">Local</option>
                <option value="dinf">DINF</option>
            </select>
        </div>

        <div class="mb-3">
            <label for="bd" class="form-label">Nom de la base (.odb)</label>
            <input type="text" class="form-control" name="bd" placeholder="ift287_43db.odb">
        </div>

        <div class="mb-3">
            <label for="user" class="form-label">Nom d'utilisateur</label>
            <input type="text" class="form-control" name="user" placeholder="ift287_43">
        </div>

        <div class="mb-3">
            <label for="pass" class="form-label">Mot de passe</label>
            <input type="password" class="form-control" name="pass">
        </div>

        <div class="d-flex gap-3">
            <button type="submit" class="btn btn-secondary" name="action" value="local">Connexion locale</button>
            <button type="submit" class="btn btn-primary" name="action" value="personnalisee">Connexion personnalisée</button>
        </div>

        <% if (request.getAttribute("messageErreur") != null) { %>
            <div class="alert alert-danger mt-3">
                <%= request.getAttribute("messageErreur") %>
            </div>
        <% } %>
    </form>
</body>
</html>
