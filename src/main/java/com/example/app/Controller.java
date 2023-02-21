package com.example.app;

import com.example.app.particletypes.Particle;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.*;
import java.util.ArrayList;

import static com.example.app.Tools.add;


public class Controller extends Application {
    static int width = 600;
    static int height = 600;
    public static int numCellsHeight = 120;
    public static int numCellsWidth = 120;
    public static int cellHeight = height/numCellsHeight;
    public static int cellWidth = width/numCellsWidth;
    public static ArrayList<Particle> particle = new ArrayList();

    public static Particle[][] particleArray;
    static Pane pane = new Pane();

    public void start(Stage primaryStage) {
        pane.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        Scene scene = new Scene(pane, width, height);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

        startUp();

//        add(3, 6, Color.BLUE, "STONE");
//        add(4, 6, Color.BLUE, "STONE");
//        add(5, 7, Color.BLUE, "STONE");
//        add(6, 7, Color.BLUE, "STONE");
//        add(7, 7, Color.BLUE, "STONE");
//        add(8, 7, Color.BLUE, "STONE");

        AnimationTimer t = new Update();
        t.start();
    }

    void startUp() {
        particleArray = new Particle[numCellsHeight][numCellsWidth];
        // in the future have it draw pixels to locations based on a text file.

    }

    public static void main(String[] args) {
        launch(args);
    }
}
