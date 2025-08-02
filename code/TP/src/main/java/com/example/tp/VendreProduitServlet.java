package com.example.tp;

import tp.objets.PointDeVente;
import tp.objets.Produit;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class VendreProduitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"distributeur".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        try {
            int idProduit = Integer.parseInt(req.getParameter("idProduit"));

            PointDeVente pdv = (PointDeVente) session.getAttribute("utilisateur");
            Produit produit = Apce.getGestionProduits().chercherParId(idProduit);

            if (produit == null || pdv == null) {
                throw new Exception("Produit ou point de vente introuvable.");
            }

            pdv.ajouterProduit(produit);
            produit.ajouterPointDeVente(pdv);

            if (produit.getProducteur() != null) {
                produit.getProducteur().ajouterPointDeVente(pdv);
            }

            Apce.getEntityManager().merge(pdv);

            req.setAttribute("message", "Produit ajouté à votre point de vente !");
        } catch (Exception e) {
            req.setAttribute("erreur", e.getMessage());
        }

        req.getRequestDispatcher("vendreProduit.jsp").forward(req, resp);
    }
}
