package com.example.app.brushbehavior;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.net.URL;

import static com.example.app.Controller.*;
import static com.example.app.Controller.mouseY;
import static com.example.app.Tools.add;

public class Behavior {
    public static double brushSize = width/200;
    public static Image brush = new Image("/brushes/Line.png", brushSize, brushSize, false, false);
    public static String brushContent = "SAND";

    public static void clickedOnScene(Pane p) {
        p.setOnMouseClicked(event -> {
            mouseX = (int) event.getX()/(width/numCellsWidth);
            mouseY = (int) event.getY()/(height/numCellsHeight);

            if (brush != null) {
                for (int a = 0; a < brush.getWidth(); a++) {
                    for (int b = 0; b < brush.getHeight(); b++) {
                        drawTheBrush(a, b);
                    }
                }
            }
        });
    }

    public static void draggedOnScene(Pane p) {
        p.setOnMouseDragged(event -> {
            mouseX = (int) event.getX()/(width/numCellsWidth);
            mouseY = (int) event.getY()/(height/numCellsHeight);

            if (brush != null) {
                for (int a = 0; a < brush.getWidth(); a++) {
                    for (int b = 0; b < brush.getHeight(); b++) {
                        drawTheBrush(a, b);
                    }
                }
            }
        });
    }

    public static void swapBrushContent(Rectangle r, String s) {
        r.setOnMouseClicked(event -> {
            System.out.println(s);
            brushContent = s;
        });
    }

    private static void drawTheBrush(int a, int b) {
        if (brush.getPixelReader().getArgb(a, b) != -0) {
            try {
                if (particleArray[(int) ((a)+mouseX-(brush.getWidth()/2))][(int) ((b)+mouseY-(brush.getHeight()/2))] == null) {
                    particleArray[(int) ((a)+mouseX-(brush.getWidth()/2))][(int) ((b)+mouseY-(brush.getHeight()/2))] = null;
                    add((int) ((a)+mouseX-(brush.getWidth()/2)), (int) ((b)+mouseY-(brush.getHeight())/2), brushContent);
                }
            }
            catch (Exception e) {
            }
        }
    }

    public void setBrush(String br, double size) {
        URL url = getClass().getResource("/brushes/" + br + ".png");
        brush = new Image(url.toExternalForm(), size, size, false, false);
    }

    public void setBrush(double size) {
        brush = new Image(brush.getUrl(), size, size, false, false);
    }
}
