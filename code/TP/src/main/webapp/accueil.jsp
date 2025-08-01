<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <title>Accueil - APCE</title>
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
            <h3 class="text-center">Accueil</h3>
        </div>
    </body>
</html>
