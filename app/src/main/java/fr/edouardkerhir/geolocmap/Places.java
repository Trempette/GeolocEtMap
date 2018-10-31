package fr.edouardkerhir.geolocmap;

import java.util.ArrayList;

public class Places {
    private String name;
    private String adress;
    private double longitude;
    private double latitude;
    private boolean visited;
    private int nbInstanceCandy;
    private ArrayList<bonbonItemInfoWindow> candyPlaces;


    public Places(String name, String adress, double longitude, double latitude, int nbInstanceCandy, ArrayList<bonbonItemInfoWindow> candyPlaces) {
        this.name = name;
        this.adress = adress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.nbInstanceCandy = nbInstanceCandy;
        this.candyPlaces = candyPlaces;
    }

    public Places() {

    }

    public ArrayList<bonbonItemInfoWindow> getCandyPlaces() {
        return candyPlaces;
    }

    public void setCandyPlaces(ArrayList<bonbonItemInfoWindow> candyPlaces) {
        this.candyPlaces = candyPlaces;
    }

    public int getNbInstanceCandy() {
        return nbInstanceCandy;
    }

    public void setNbInstanceCandy(int nbInstanceCandy) {
        this.nbInstanceCandy = nbInstanceCandy;
    }


    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
