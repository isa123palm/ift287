package tp.objets;

import javax.persistence.*;
import java.util.*;

@Entity
public class PointDeVente {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String courriel;

    @Column(nullable = false)
    private String motDePasse;

    private String adresse;

    @ManyToMany(mappedBy = "pointsDeVente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Produit> produits = new ArrayList<>();

    @ManyToMany(mappedBy = "pointsDeVente", fetch = FetchType.LAZY)
    private List<Producteur> producteurs = new ArrayList<>();

    public PointDeVente() {}

    public PointDeVente(String nom, String courriel, String motDePasse, String adresse) {
        this.nom = nom;
        this.courriel = courriel;
        this.motDePasse = motDePasse;
        this.adresse = adresse;
    }

    // Getters
    public Integer getId() { return id; }
    public String getNom() { return nom; }
    public String getCourriel() { return courriel; }
    public String getMotDePasse() { return motDePasse; }
    public String getAdresse() { return adresse; }

    public List<Produit> getProduits() { return produits; }
    public List<Producteur> getProducteurs() { return producteurs; }

    public void ajouterProduit(Produit p) {
        if (!produits.contains(p)) {
            produits.add(p);
        }
    }

    public void retirerProduit(Produit p) {
        produits.remove(p);
    }

    public void ajouterProducteur(Producteur p) {
        if (!producteurs.contains(p)) {
            producteurs.add(p);
        }
    }
}
