package com.example.app;

import com.example.app.particletypes.Particle;
import com.example.app.particletypes.Stone;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.io.InputStream;
import java.util.ArrayList;

import static com.example.app.Tools.add;


public class Controller extends Application {
    static int width = 600;
    static int height = 600;
    public static int numCellsHeight = 150;
    public static int numCellsWidth = 150;
    public static int cellHeight = height/numCellsHeight;
    public static int cellWidth = width/numCellsWidth;
    public static ArrayList<Particle> particle = new ArrayList();
    public static int mouseX;
    public static int mouseY;
    public static boolean brushPaneOpen;
    public static StackPane brushPane = new StackPane();

    public static Particle[][] particleArray;
    static Pane pane = new Pane();
    static Scene scene;

    public void start(Stage primaryStage) {
        pane.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        scene = new Scene(pane, width, height);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();

        startUp();
        //loadLevelData();

        AnimationTimer t = new Update();
        t.start();
    }

    void startUp() {
        particleArray = new Particle[numCellsHeight][numCellsWidth];
        Behavior.clickedOnScene(pane);
        Behavior.draggedOnScene(pane);
        pane.getChildren().add(brushPane);
        brushPane.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
        brushPane.setOpacity(.5);
        brushPane.setMinSize(0, height);
        Behavior.brushPaneToggle(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }



    // -1 is white
    // -16777216 is black

    void loadLevelData() {
        InputStream img = Controller.class.getResourceAsStream("/test2.png");
        Image Img = new Image(img, numCellsWidth, numCellsHeight, false, false);
        readPixelData(Img);
    }

    void readPixelData(Image img) {
        int something;
        boolean swap = true;
        for (int a = 0; a < img.getWidth(); a++) {
            for (int b = 0; b < img.getHeight(); b++) {
                something = img.getPixelReader().getArgb(a, b);
                if (something != -1) {
                    add(a, b, Color.BLUE, "STONE");
                }
                else {
                    //System.out.println(something);
                }
            }
            //System.out.println("");
        }
    }
}
