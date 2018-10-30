package fr.edouardkerhir.geolocmap;

public class Places {
    private String name;
    private String adress;
    private double longitude;
    private double latitude;
    private boolean visited;

    public Places(String name, String adress, double longitude, double latitude) {
        this.name = name;
        this.adress = adress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.visited = false;
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
