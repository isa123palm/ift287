package com.example.tp;

import tp.objets.Fournisseur;
import tp.objets.Produit;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class FabriquerProduitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"fournisseur".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        Fournisseur fournisseur = (Fournisseur) session.getAttribute("utilisateur");

        try {
            int idProduit = Integer.parseInt(req.getParameter("idProduit"));

            Apce.getGestionFournisseurs().fabriquerProduit(idProduit, fournisseur.getId());

            req.setAttribute("message", "Produit ajouté à votre liste de fabrications avec succès !");
        } catch (Exception e) {
            req.setAttribute("erreur", "Erreur : " + e.getMessage());
        }

        req.getRequestDispatcher("fabriquerProduit.jsp").forward(req, resp);
    }
}
