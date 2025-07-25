package tp.collections;

import tp.bdd.Connexion;
import tp.objets.PointDeVente;

import javax.persistence.EntityManager;

public class PointsDeVente {
    private final Connexion cx;
    private final EntityManager em;

    public PointsDeVente(Connexion cx) {
        this.cx = cx;
        this.em = cx.getConnection();
    }

    public void ajouter(PointDeVente pdv) throws Exception {
        if (em.find(PointDeVente.class, pdv.getNom()) != null)
            throw new Exception("Ce point de vente existe déjà.");
        em.persist(pdv);
    }

    public PointDeVente chercher(String nom) {
        return em.find(PointDeVente.class, nom);
    }

    public void supprimer(String nom) throws Exception {
        PointDeVente pdv = em.find(PointDeVente.class, nom);
        if (pdv == null) throw new Exception("Point de vente inexistant.");
        em.remove(pdv);
    }
}
