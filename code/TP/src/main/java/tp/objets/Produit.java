package tp.objets;

import javax.persistence.*;
import java.util.*;

@Entity
public class Produit {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String nom;

    private double prix;
    private double cout;
    private String categorie;

    @ManyToOne
    private Producteur producteur;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Fournisseur> fournisseurs = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PointDeVente> pointsDeVente = new ArrayList<>();

    public Produit() {}

    public Produit(String nom, double prix, double cout, String categorie, Producteur producteur) {
        this.nom = nom;
        this.prix = prix;
        this.cout = cout;
        this.categorie = categorie;
        this.producteur = producteur;
    }

    // === Getters ===
    public Integer getId() { return id; }
    public String getNom() { return nom; }
    public double getPrix() { return prix; }
    public double getCout() { return cout; }
    public String getCategorie() { return categorie; }
    
    public Producteur getProducteur() { return producteur; }
    public List<Fournisseur> getFournisseurs() { return fournisseurs; }
    public List<PointDeVente> getPointsDeVente() { return pointsDeVente; }

    // === Fournisseur association helpers ===
    public void ajouterFournisseur(Fournisseur f) {
        if (!fournisseurs.contains(f)) {
            fournisseurs.add(f);
        }
    }

    public void retirerFournisseur(Fournisseur f) {
        fournisseurs.remove(f);
    }

    // === PointDeVente association helpers ===
    public void ajouterPointDeVente(PointDeVente pdv) {
        if (!pointsDeVente.contains(pdv)) {
            pointsDeVente.add(pdv);
        }
    }

    public void retirerPointDeVente(PointDeVente pdv) {
        pointsDeVente.remove(pdv);
    }
}
