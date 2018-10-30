package fr.edouardkerhir.geolocmap;

public class CandyModel {
    private String nom;
    private int poids;
    private double position;

    public CandyModel(String nom, int poids, double position) {
        this.nom = nom;
        this.poids = poids;
        this.position = position;
    }

    public CandyModel() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }
}
