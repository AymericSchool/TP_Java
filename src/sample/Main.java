package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.util.*;

/**
 * Représentation graphique du surakarta
 */
public class Main extends Application {
    /**
     * Liste de la réprésentation des pions
     */
    private List<DessinPion> dessinPions;
    /**
     * écartement entre les pions
     */
    private final int ecartement = 60;
    /**
     * Rayon des pions
     */
    private final int radiusPion = 15;
    /**
     * Décalage par rapport au bord gauche de la fenêtre
     */
    private final int xPos = 100;
    /**
     * Décalage par rapport au bord droite de la fenêtre
     */
    private final int yPos = 100;
    /**
     * Numero du tour actuel
     * Le joueur joue les tours impairs
     */
    public static int noTour = 1;

    /**
     * couleur de l'arc 1
     */
    private final Color couleur1 = Color.YELLOW;
    /**
     * couleur de l'arc 2
     */
    private final Color couleur2 = Color.GREEN;

    /**
     * Représente les cercles derrière les pions de couleur blanche ou rose si le mouvement est possible depuis le pion selectioné
     */
    private Place[][] rondsPossible;

    DessinPion ps;

    @Override
    public void start(Stage primaryStage) throws Exception {
        dessinPions = new ArrayList<>();
        construirePlateau(primaryStage);

    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Constructiion du plateau
     *
     * @param primaryStage
     */
    public void construirePlateau(Stage primaryStage) {

        // Initialisation des pions
        Pion.initializePions();

        Group troupe = new Group();
        Scene scene = new Scene(troupe, 630, 630, Color.BLACK);
        // definir les acteurs et les habiller
        dessinEnv(troupe);

        primaryStage.setTitle("Jeu du Surakarta");
        primaryStage.setScene(scene);
        // afficher le theatre
        primaryStage.show();

    }

    /**
     * Construction de tous elements graphique nécessaire à l'affichage du surakarta
     *
     * @param troupe permet d'ajouter les élements à la scéne
     */
    public void dessinEnv(Group troupe) {
        float lineWidth = 5;

        // Affichage des lignes
        Color c = Color.WHITE;
        for (int i = 1; i < 7; i++) {
            if (i == 1) c = couleur1;
            else c = Color.WHITE;

            Line l1 = new Line(xPos + ecartement, yPos + ecartement * i, xPos + ecartement * 6, yPos + ecartement * i);
            Line l2 = new Line(xPos + ecartement * i, yPos + ecartement, xPos + ecartement * i, yPos + ecartement * 6);
            l1.setStroke(Color.WHITE);
            l2.setStroke(Color.WHITE);
            l1.setStrokeWidth(lineWidth);
            l2.setStrokeWidth(lineWidth);
            troupe.getChildren().add(l1);
            troupe.getChildren().add(l2);
            if (i == 3 || i == 4) {
                l1.setStroke(couleur2);
                l2.setStroke(couleur2);
            }
            if (i == 2 || i == 5) {
                l1.setStroke(couleur1);
                l2.setStroke(couleur1);
            }


        }
        // Affichage des arcs
        Arc arc1 = new Arc(ecartement + xPos, ecartement + yPos, ecartement, ecartement, 0, 270);
        arc1.setType(ArcType.OPEN);
        arc1.setFill(Color.TRANSPARENT);
        arc1.setStroke(couleur1);
        arc1.setStrokeWidth(lineWidth);

        Arc arc2 = new Arc(ecartement * 6 + xPos, ecartement + yPos, ecartement, ecartement, 270, 270);
        arc2.setType(ArcType.OPEN);
        arc2.setFill(Color.TRANSPARENT);
        arc2.setStroke(couleur1);
        arc2.setStrokeWidth(lineWidth);


        Arc arc3 = new Arc(ecartement + xPos, ecartement * 6 + yPos, ecartement, ecartement, 90, 270);
        arc3.setType(ArcType.OPEN);
        arc3.setFill(Color.TRANSPARENT);
        arc3.setStroke(couleur1);
        arc3.setStrokeWidth(lineWidth);

        Arc arc4 = new Arc(ecartement * 6 + xPos, ecartement * 6 + yPos, ecartement, ecartement, 180, 270);
        arc4.setType(ArcType.OPEN);
        arc4.setFill(Color.TRANSPARENT);
        arc4.setStroke(couleur1);
        arc4.setStrokeWidth(lineWidth);

        Arc arc5 = new Arc(ecartement + xPos, ecartement + yPos, 2 * ecartement, 2 * ecartement, 0, 270);
        arc5.setType(ArcType.OPEN);
        arc5.setFill(Color.TRANSPARENT);
        arc5.setStroke(couleur2);
        arc5.setStrokeWidth(lineWidth);

        Arc arc6 = new Arc(ecartement * 6 + xPos, ecartement + yPos, 2 * ecartement, 2 * ecartement, 270, 270);
        arc6.setType(ArcType.OPEN);
        arc6.setFill(Color.TRANSPARENT);
        arc6.setStroke(couleur2);
        arc6.setStrokeWidth(lineWidth);

        Arc arc7 = new Arc(ecartement + xPos, ecartement * 6 + yPos, 2 * ecartement, 2 * ecartement, 90, 270);
        arc7.setType(ArcType.OPEN);
        arc7.setFill(Color.TRANSPARENT);
        arc7.setStroke(couleur2);
        arc7.setStrokeWidth(lineWidth);

        Arc arc8 = new Arc(ecartement * 6 + xPos, ecartement * 6 + yPos, 2 * ecartement, 2 * ecartement, 180, 270);
        arc8.setType(ArcType.OPEN);
        arc8.setFill(Color.TRANSPARENT);
        arc8.setStroke(couleur2);
        arc8.setStrokeWidth(lineWidth);

        troupe.getChildren().add(arc1);
        troupe.getChildren().add(arc2);
        troupe.getChildren().add(arc3);
        troupe.getChildren().add(arc4);
        troupe.getChildren().add(arc5);
        troupe.getChildren().add(arc6);
        troupe.getChildren().add(arc7);
        troupe.getChildren().add(arc8);


        // Affichage des ronds blancs
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                troupe.getChildren().add(new DessinPion(new Pion(i, j, Color.WHITE), ecartement, radiusPion, xPos, yPos));

            }
        }
        rondsPossible = new Place[6][6];

        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                Place pl = new Place(xPos + i * ecartement, yPos + j * ecartement, radiusPion, i, j);
                rondsPossible[i - 1][j - 1] = pl;

                rondsPossible[i - 1][j - 1].setFill(Color.TRANSPARENT);
                rondsPossible[i - 1][j - 1].setOnMouseClicked(e -> {
                    if (ps != null) {
                        int destX = pl.getX();
                        int destY = pl.getY();

                        if (ps.p.isMovePossible(destX, destY)) {
                            //si la pièce d'arrivé est vide on déplace le pion si le mouvement est possible
                            ps.p.move(destX, destY);
                            ps.refresh(xPos, yPos, ecartement);
                            ps.selected = false;
                            ps.setFill(ps.p.getCouleur());
                            ps = null;
                            affichageCoupPossible(ps);

                        }
                    }
                });
                troupe.getChildren().add(rondsPossible[i - 1][j - 1]);

            }
        }


        // Affichage des pions
        for (Pion p : Pion.listPions) {

            DessinPion dp = new DessinPion(p, ecartement, radiusPion - 2, xPos, yPos);
            dp.setOnMouseClicked(t -> {

                if (ps == null) {
                    ps = dp;

                    //ps.p.boucleMove(1,2);

                }


                if (ps != null) {

                    if (ps.p.getCouleur() != dp.p.getCouleur()) {

                        if (ps.p.isMovePossible(dp.p.getX(), dp.p.getY())) {
                            //on supprime le pion ecrasé et on bouge le pion selectioné
                            Pion.listPions.remove(dp.p);
                            troupe.getChildren().remove(dp);
                            ps.p.move(dp.p.getX(), dp.p.getY());
                            ps.refresh(xPos, yPos, ecartement);
                            ps.selected = false;
                            ps.setFill(ps.p.getCouleur());
                            ps = null;
                        }
                    } else {

                        ps.selected = false;
                        ps.setFill(ps.p.getCouleur());
                        ps = dp;
                        ps.selected = true;
                        ps.setFill(Color.ORANGE);
                        ps.refresh(xPos, yPos, ecartement);


                    }

                }
                affichageCoupPossible(ps);


            });

            dessinPions.add(dp);
            troupe.getChildren().add(dp);
        }


    }


    /**
     * Change la couleur des places si le coup est possible à partir d'un pion
     *
     * @param dp pion selectionée dont on souhaite afficher les coups possible
     */
    private void affichageCoupPossible(DessinPion dp) {
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                if (ps != null && dp.p.isMovePossible(i, j)) rondsPossible[i - 1][j - 1].setFill(Color.HOTPINK);
                else rondsPossible[i - 1][j - 1].setFill(Color.WHITE);
            }
        }
    }

}
