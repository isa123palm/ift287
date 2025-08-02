package com.example.tp;

import tp.objets.PointDeVente;
import tp.objets.Produit;
import tp.objets.Producteur;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AssocierDistributeurServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"producteur".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try {
            int idProduit = Integer.parseInt(req.getParameter("idProduit"));
            int idDistributeur = Integer.parseInt(req.getParameter("idDistributeur"));

            Produit produit = Apce.getGestionProduits().chercherParId(idProduit);
            PointDeVente distributeur = Apce.getGestionPointsDeVente().chercherParId(idDistributeur);

            if (produit == null || distributeur == null) {
                throw new Exception("Produit ou distributeur introuvable.");
            }

            produit.ajouterPointDeVente(distributeur);
            distributeur.ajouterProduit(produit);

            // Associer le distributeur au producteur
            Producteur producteur = produit.getProducteur();
            if (!producteur.getPointsDeVente().contains(distributeur)) {
                producteur.getPointsDeVente().add(distributeur);
            }

            // Sauvegarder
            Apce.getEntityManager().merge(produit);
            Apce.getEntityManager().merge(producteur);

            req.setAttribute("message", "Distributeur associé avec succès !");
        } catch (Exception e) {
            req.setAttribute("erreur", e.getMessage());
        }

        req.getRequestDispatcher("associerDistributeur.jsp").forward(req, resp);
    }
}
