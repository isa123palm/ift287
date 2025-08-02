<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Ajouter un produit</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .btn-retour {
            position: absolute;
            top: 20px;
            left: 20px;
        }
    </style>
</head>
<body class="p-4">

    <!-- Bouton retour -->
    <a href="dashboardProducteur.jsp" class="btn btn-secondary btn-retour">← Retour</a>

    <div class="container mt-5">
        <h2>Ajouter un produit</h2>

        <form action="ajouter_produit" method="post" class="mt-4">
            <div class="mb-3">
                <label>Nom du produit</label>
                <input type="text" name="nom" class="form-control" required>
            </div>
            <div class="mb-3">
                <label>Prix</label>
                <input type="number" name="prix" class="form-control" step="0.01" required>
            </div>
            <div class="mb-3">
                <label>Coût</label>
                <input type="number" name="cout" class="form-control" step="0.01" required>
            </div>
            <div class="mb-3">
                <label>Catégorie</label>
                <input type="text" name="categorie" class="form-control" required>
            </div>

            <button type="submit" class="btn btn-primary">Ajouter le produit</button>

            <% if (request.getAttribute("erreur") != null) { %>
                <div class="alert alert-danger mt-3">
                    <%= request.getAttribute("erreur") %>
                </div>
            <% } %>
        </form>
    </div>

</body>
</html>
