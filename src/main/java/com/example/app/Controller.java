package com.example.app;

import com.example.app.UI.Brushpane;
import com.example.app.particletypes.Particle;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.io.InputStream;
import java.util.ArrayList;

import static com.example.app.Tools.add;
import static com.example.app.brushbehavior.Behavior.clickedOnScene;
import static com.example.app.brushbehavior.Behavior.draggedOnScene;


public class Controller extends Application {
    public static int width = 600;
    public static int height = 600;
    public static int numCellsHeight = 150;
    public static int numCellsWidth = 150;
    public static int cellHeight = height/numCellsHeight;
    public static int cellWidth = width/numCellsWidth;
    public static ArrayList<Particle> particle = new ArrayList();
    public static int mouseX;
    public static int mouseY;
    public static ArrayList<Color> particleColors = new ArrayList<>();
    public static ArrayList<String> particleName = new ArrayList<>();


    public static Particle[][] particleArray;
    static Pane pane = new Pane();
    static Pane ui = new Pane();
    Scene scene;

    public void start(Stage primaryStage) {
        Pane basePane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        pane.setMinSize(width, height);
        basePane.getChildren().addAll(pane, ui);
        ui.setVisible(false);

        scene = new Scene(basePane, width, height);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

        startUp();
        loadLevelData();
        //loadBlankLevel();
        AnimationTimer t = new Update();
        t.start();
    }

    void startUp() {
        particleArray = new Particle[numCellsHeight][numCellsWidth];
        clickedOnScene(pane);
        draggedOnScene(pane);
        particleColors();
        Brushpane bp = new Brushpane();
        bp.createBrushPane(ui, scene);
    }

    public static void main(String[] args) {
        launch(args);
    }



    // -1 is white
    // -.16777216 is black

    void loadLevelData() {
        InputStream img = Controller.class.getResourceAsStream("/test2.png");
        Image Img = new Image(img, numCellsWidth, numCellsHeight, false, false);
        readPixelData(Img);
    }

    void loadBlankLevel() {
        for (int a = 0; a < numCellsWidth; a++) {
            for (int b = 0; b < numCellsHeight; b++) {
                add(a, b, "WATER");
            }
        }
    }

    void readPixelData(Image img) {
        int something;
        boolean swap = true;
        for (int a = 0; a < img.getWidth(); a++) {
            for (int b = 0; b < img.getHeight(); b++) {
                something = img.getPixelReader().getArgb(a, b);
                if (something != -1) {
                    add(a, b, "WATER");
                }
                else {
                    //System.out.println(something);
                }
            }
            //System.out.println("");
        }
    }

    void particleColors() {
        Color sand = Color.BISQUE;
        particleColors.add(sand);
        particleName.add("SAND");

        Color stone = Color.BLUE;
        particleColors.add(stone);
        particleName.add("STONE");

        Color water = Color.LIGHTBLUE;
        particleColors.add(water);
        particleName.add("WATER");

        Color air = Color.SKYBLUE;
        particleColors.add(air);
        particleName.add("AIR");

        Color oil = Color.BLACK;
        particleColors.add(oil);
        particleName.add("OIL");

    }
}
