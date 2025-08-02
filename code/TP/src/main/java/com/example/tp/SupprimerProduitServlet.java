package com.example.tp;

import tp.objets.Producteur;
import tp.objets.Produit;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class SupprimerProduitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"producteur".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String idStr = req.getParameter("idProduit");
        try {
            int id = Integer.parseInt(idStr);

            Producteur producteur = (Producteur) session.getAttribute("utilisateur");
            Produit produit = Apce.getGestionProduits().chercherParId(id);

            if (produit == null) {
                throw new Exception("Produit introuvable.");
            }

            if (!produit.getProducteur().getId().equals(producteur.getId())) {
                throw new Exception("Ce produit ne vous appartient pas.");
            }

            Apce.getGestionProduits().supprimerProduit(id);
            resp.sendRedirect("dashboardProducteur.jsp");

        } catch (Exception e) {
            req.setAttribute("erreur", "Erreur lors de la suppression : " + e.getMessage());
            req.getRequestDispatcher("supprimerProduit.jsp").forward(req, resp);
        }
    }
}
