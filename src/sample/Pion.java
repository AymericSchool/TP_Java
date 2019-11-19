package sample;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

class Pion {
    private int x;
    private int y;
    private Color couleur;

    static List<Pion> listPions = new ArrayList<Pion>();

    // Constructors
    public Pion(int x, int y, Color couleur) {
        this.x = x;
        this.y = y;
        this.couleur = couleur;
    }

    // Getters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getCouleur() {
        return couleur;
    }

    // Setters
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    // Methods
    static void initializePions() {
        listPions.add(new Pion(1, 1, Color.BLUE));
        listPions.add(new Pion(2, 1, Color.BLUE));
        listPions.add(new Pion(3, 1, Color.BLUE));
        listPions.add(new Pion(4, 1, Color.BLUE));
        listPions.add(new Pion(5, 1, Color.BLUE));
        listPions.add(new Pion(6, 1, Color.BLUE));
        listPions.add(new Pion(1, 2, Color.BLUE));
        listPions.add(new Pion(2, 2, Color.BLUE));
        listPions.add(new Pion(3, 2, Color.BLUE));
        listPions.add(new Pion(4, 2, Color.BLUE));
        listPions.add(new Pion(5, 2, Color.BLUE));
        listPions.add(new Pion(6, 2, Color.BLUE));
    }

}
