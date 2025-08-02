package com.example.tp;

import tp.objets.PointDeVente;
import tp.objets.Produit;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RetirerDistributeurServlet extends HttpServlet {
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
            PointDeVente pdv = Apce.getGestionPointsDeVente().chercherParId(idDistributeur);

            if (produit == null || pdv == null) {
                throw new Exception("Produit ou distributeur introuvable.");
            }

            produit.retirerPointDeVente(pdv);
            pdv.retirerProduit(produit);

            Apce.getEntityManager().merge(produit);

            req.setAttribute("message", "Distributeur retiré avec succès !");
        } catch (Exception e) {
            req.setAttribute("erreur", e.getMessage());
        }

        req.getRequestDispatcher("retirerDistributeur.jsp").forward(req, resp);
    }
}
