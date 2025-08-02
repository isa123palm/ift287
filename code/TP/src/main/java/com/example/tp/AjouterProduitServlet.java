package com.example.tp;

import tp.objets.Producteur;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AjouterProduitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"producteur".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String nom = req.getParameter("nom");
        String prixStr = req.getParameter("prix");
        String coutStr = req.getParameter("cout");
        String categorie = req.getParameter("categorie");

        try {
            double prix = Double.parseDouble(prixStr);
            double cout = Double.parseDouble(coutStr);

            Producteur producteur = (Producteur) session.getAttribute("utilisateur");

            Apce.getGestionProduits().ajouterProduit(nom, prix, cout, categorie, producteur.getNom());

            resp.sendRedirect("dashboardProducteur.jsp");

        } catch (Exception e) {
            req.setAttribute("erreur", "Erreur : " + e.getMessage());
            req.getRequestDispatcher("ajouterProduit.jsp").forward(req, resp);
        }
    }
}
