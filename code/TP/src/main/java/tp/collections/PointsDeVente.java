package tp.collections;

import tp.bdd.Connexion;
import tp.objets.PointDeVente;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class PointsDeVente {
    //private final Connexion cx;
    private final EntityManager em;

    public PointsDeVente(Connexion cx) {
        //this.cx = cx;
        this.em = cx.getConnection();
    }

    public void ajouter(PointDeVente pdv) throws Exception {
        if (em.find(PointDeVente.class, pdv.getNom()) != null)
            throw new Exception("Ce point de vente existe déjà.");
        em.persist(pdv);
    }

    public PointDeVente chercherParId(int id) {
        return em.find(PointDeVente.class, id);
    }

    public PointDeVente chercherParNom(String nom) {
        TypedQuery<PointDeVente> query = em.createQuery(
            "SELECT p FROM PointDeVente p WHERE p.nom = :nom", PointDeVente.class);
        query.setParameter("nom", nom);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    public PointDeVente chercherParCourriel(String courriel) {
        TypedQuery<PointDeVente> query = em.createQuery(
            "SELECT p FROM PointDeVente p WHERE p.courriel = :courriel", PointDeVente.class);
        query.setParameter("courriel", courriel);
        return query.getResultList().stream().findFirst().orElse(null);
    }

    public List<PointDeVente> listerTous() {
        return em.createQuery("SELECT p FROM PointDeVente p", PointDeVente.class).getResultList();
    }

    public void supprimer(int id) throws Exception {
        PointDeVente pdv = em.find(PointDeVente.class, id);
        if (pdv == null) throw new Exception("Point de vente inexistant.");
        em.remove(pdv);
    }
}
