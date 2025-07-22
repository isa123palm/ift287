package tp.objets;


import javax.persistence.*;


@Entity
public class Producteur {

    @Id
    private String nom;

    private String courriel;
    private int nbEmployes;
    private String adresse;

    public Producteur() {}  // Obligatoire pour JPA

    public Producteur(String nom, String courriel, int nbEmployes, String adresse) {
        this.nom = nom;
        this.courriel = courriel;
        this.nbEmployes = nbEmployes;
        this.adresse = adresse;
    }

    // Getters & Setters
    public String getNom() { return nom; }
    public String getCourriel() { return courriel; }
    public int getNbEmployes() { return nbEmployes; }
    public String getAdresse() { return adresse; }

    public void setNom(String nom) { this.nom = nom; }
    public void setCourriel(String courriel) { this.courriel = courriel; }
    public void setNbEmployes(int nbEmployes) { this.nbEmployes = nbEmployes; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
}
