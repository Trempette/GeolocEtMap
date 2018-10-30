package fr.edouardkerhir.geolocmap;

public class bonbonItemInfoWindow {
    private int indexSingleton;
    private int nbEachCandy;

    public bonbonItemInfoWindow(int indexSingleton, int nbEachCandy) {
        this.indexSingleton = indexSingleton;
        this.nbEachCandy = nbEachCandy;
    }

    public int getIndexSingleton() {
        return indexSingleton;
    }

    public void setIndexSingleton(int indexSingleton) {
        this.indexSingleton = indexSingleton;
    }

    public int getNbEachCandy() {
        return nbEachCandy;
    }

    public void setNbEachCandy(int nbEachCandy) {
        this.nbEachCandy = nbEachCandy;
    }
}
