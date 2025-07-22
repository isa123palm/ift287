package tp.collections;

import tp.objets.PointDeVente;

import javax.persistence.EntityManager;

public class PointsDeVente {
    private final EntityManager em;

    public PointsDeVente(EntityManager em) {
        this.em = em;
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
