package fr.edouardkerhir.geolocmap;

public class UserModel {
    private String nom;
    private double poid;
    private int candy;

    public UserModel(String nom, double poid, int candy) {
        this.nom = nom;
        this.poid = poid;
        this.candy = candy;
    }

    public UserModel() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPoid() {
        return poid;
    }

    public void setPoid(double poid) {
        this.poid = poid;
    }

    public int getCandy() {
        return candy;
    }

    public void setCandy(int candy) {
        this.candy = candy;
    }
}
