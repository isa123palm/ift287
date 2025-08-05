package com.example.tp;

import tp.bdd.Connexion;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitConnexionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");  // "local" ou "personnalisee"
        String url = null;

        try {
            if ("local".equals(action)) {
                // Connexion statique via persistence.xml (ex: "objectdbPU")
                url = "objectdbPU";
            } else if ("personnalisee".equals(action)) {
                
                String serveur = request.getParameter("serveur");
                String bd = request.getParameter("bd");
                String user = request.getParameter("user");
                String pass = request.getParameter("pass");

                if (bd == null || bd.isBlank()) {
                    throw new Exception("Le nom de la base est requis.");
                }

                // Construction de l'URL ObjectDB dynamique
                if ("dinf".equals(serveur)) {
                    url = "objectdb://bd-info2.dinf.usherbrooke.ca:6136/" + bd
                            + ";user=" + user + ";password=" + pass;
                } else {
                    url = "db/" + bd; // Local (fichier dans /webapp/db/)
                }
            } else {
                throw new Exception("Action invalide.");
            }

            // Création de la connexion ObjectDB
            Connexion cx = new Connexion(url);
            getServletContext().setAttribute("connexion", cx);

            // Initialisation d'APCE
            Apce.init(cx);

            // Redirection vers login
            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            request.setAttribute("messageErreur", "Connexion échouée : " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
