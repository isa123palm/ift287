package tp.objets;

import javax.persistence.*;
import java.util.*;

@Entity
public class Fournisseur {

    @Id
    private String nom;

    private String courriel;
    private String adresse;

    @ManyToMany(mappedBy = "fournisseurs", cascade = CascadeType.ALL)
    private List<Produit> produits = new ArrayList<>();

    public Fournisseur() {}

    public Fournisseur(String nom, String courriel, String adresse) {
        this.nom = nom;
        this.courriel = courriel;
        this.adresse = adresse;
    }

    // Getters
    public String getNom() { return nom; }
    public String getCourriel() { return courriel; }
    public String getAdresse() { return adresse; }
    public List<Produit> getProduits() { return produits; }

    // Link
    public void ajouterProduit(Produit p) {
        if (!produits.contains(p)) {
            produits.add(p);
        }
    }

    public void retirerProduit(Produit p) {
        produits.remove(p);
    }
}
