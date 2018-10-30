package fr.edouardkerhir.geolocmap;

public class UserModel {
    private String nom;
    private int poid;
    private String candy;

    public UserModel(String nom, int poid, String candy) {
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

    public int getPoid() {
        return poid;
    }

    public void setPoid(int poid) {
        this.poid = poid;
    }

    public String getCandy() {
        return candy;
    }

    public void setCandy(String candy) {
        this.candy = candy;
    }
}
