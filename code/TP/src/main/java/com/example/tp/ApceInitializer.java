package com.example.tp;

import tp.bdd.Connexion;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApceInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Connexion cx = new Connexion("objectdbPU");
            Apce.init(cx);
            System.out.println("[APCE] Initialisation réussie via ServletContextListener");
        } catch (Exception e) {
            System.err.println("[APCE] Échec d'initialisation : " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {}

}
