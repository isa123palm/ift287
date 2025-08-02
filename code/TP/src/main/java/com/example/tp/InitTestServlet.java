package com.example.tp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            InitTestData.ajouterDonneesTest();
            resp.getWriter().write("Données de test ajoutées !");
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("Erreur : " + e.getMessage());
        }
    }
}
