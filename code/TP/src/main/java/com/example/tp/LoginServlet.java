package com.example.tp;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String courriel = req.getParameter("email");
        String motDePasse = req.getParameter("password");
        String role = req.getParameter("role");

        try {
            HttpSession session = req.getSession();

            switch (role) {
                case "producteur" -> {
                    var p = Apce.getGestionProducteurs();
                    if (p == null) throw new Exception("Service producteur non disponible");

                    var producteur = p.chercherParCourriel(courriel);
                    if (producteur != null && producteur.getMotDePasse().equals(motDePasse)) {
                        session.setAttribute("utilisateur", producteur);
                        session.setAttribute("role", "producteur");
                        resp.sendRedirect("accueil.jsp");
                        return;
                    }
                }

                case "fournisseur" -> {
                    var f = Apce.getGestionFournisseurs();
                    if (f == null) throw new Exception("Service fournisseur non disponible");

                    var fournisseur = f.chercherParCourriel(courriel);
                    if (fournisseur != null && fournisseur.getMotDePasse().equals(motDePasse)) {
                        session.setAttribute("utilisateur", fournisseur);
                        session.setAttribute("role", "fournisseur");
                        resp.sendRedirect("accueil.jsp");
                        return;
                    }
                }

                case "distributeur" -> {
                    var d = Apce.getGestionPointsDeVente();
                    if (d == null) throw new Exception("Service point de vente non disponible");

                    var pdv = d.chercherParCourriel(courriel);
                    if (pdv != null && pdv.getMotDePasse().equals(motDePasse)) {
                        session.setAttribute("utilisateur", pdv);
                        session.setAttribute("role", "distributeur");
                        resp.sendRedirect("accueil.jsp");
                        return;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            req.setAttribute("erreur", "Aucun compte ne correspond aux informations saisies.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
