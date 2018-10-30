package fr.edouardkerhir.geolocmap;

public class CandyModel {
    private String nom;
    private int image;
    private double poids;

    public CandyModel(String nom, int image, double poids) {
        this.nom = nom;
        this.poids = poids;
        this.image=image;
    }

    public CandyModel() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
