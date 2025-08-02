package tp.objets;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Producteur {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String courriel;

    @Column(nullable = false)
    private String motDePasse;

    private int nbEmployes;
    private String adresse;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Fournisseur> fournisseurs = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<PointDeVente> pointsDeVente = new ArrayList<>();

    @OneToMany(mappedBy = "producteur", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Produit> produits = new ArrayList<>();

    public Producteur() {}

    public Producteur(String nom, String courriel, String motDePasse, int nbEmployes, String adresse) {
        this.nom = nom;
        this.courriel = courriel;
        this.motDePasse = motDePasse;
        this.nbEmployes = nbEmployes;
        this.adresse = adresse;
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public String getNom() { return nom; }
    public String getCourriel() { return courriel; }
    public String getMotDePasse() { return motDePasse; }
    public int getNbEmployes() { return nbEmployes; }
    public String getAdresse() { return adresse; }

    public List<Fournisseur> getFournisseurs() { return fournisseurs; }
    public List<PointDeVente> getPointsDeVente() { return pointsDeVente; }
    public List<Produit> getProduits() { return produits; }

    public void setNom(String nom) { this.nom = nom; }
    public void setCourriel(String courriel) { this.courriel = courriel; }
    public void setNbEmployes(int nbEmployes) { this.nbEmployes = nbEmployes; }
    public void setAdresse(String adresse) { this.adresse = adresse; }

    public void ajouterFournisseur(Fournisseur f) {
        if (!fournisseurs.contains(f)) {
            fournisseurs.add(f);
            f.ajouterProducteur(this);
        }
    }

    public void ajouterPointDeVente(PointDeVente p) {
        if (!pointsDeVente.contains(p)) {
            pointsDeVente.add(p);
            p.ajouterProducteur(this);
        }
    }

}
