package fr.edouardkerhir.geolocmap;

import java.util.ArrayList;

public class UserModel {
    private double poid = 0.0;
    private int candy = 0;
    private int level = 0;
    private ArrayList<CandyModel> usersCandies = null;

    public UserModel(double poid, int candy, ArrayList usersCandies) {
        this.poid = poid;
        this.candy = candy;
        this.level=0;
        this.usersCandies= usersCandies;
    }

    public UserModel() {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<CandyModel> getUsersCandies() {
        return usersCandies;
    }

    public void setUsersCandies(ArrayList<CandyModel> usersCandies) {
        this.usersCandies = usersCandies;
    }
}
