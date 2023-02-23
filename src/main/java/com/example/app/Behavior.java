package com.example.app;

import com.example.app.particletypes.Particle;
import com.example.app.properties.nonSolid;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import static com.example.app.Controller.*;
import static com.example.app.Tools.add;

public class Behavior {
    static Particle neighbor;
    public static void gravityDrop(Particle p, int force) {
        neighbor = particleArray[p.getX()][p.getY()+force];
        if ((p.getY() + force) >= particleArray.length) {
            return;
        }
        if (neighbor == null) {
            particleArray[p.getX()][p.getY()] = null;
        }
        else if (neighbor instanceof nonSolid) {
            particleArray[p.getX()][p.getY()] = neighbor;
            particleArray[p.getX()][p.getY()].setPosition(-force, 0);
        }

        particleArray[p.getX()][p.getY()+force] = p;
        p.setPosition(force, 0);
    }

    //negative to the left, positive to the right.
    public static void pushHorizontal(Particle p, int force) {
        particleArray[p.getX()][p.getY()] = null;
        particleArray[p.getX() + force][p.getY()] = p;
        p.setPosition(0, force);
    }

    public static void pushDiagonal(Particle p, int xForce, int yForce) {
        neighbor = particleArray[p.getX()+xForce][p.getY()+yForce];

        if (neighbor == null) {
            particleArray[p.getX()][p.getY()] = null;
            particleArray[p.getX()+xForce][p.getY()+yForce] = p;
            p.setPosition(yForce, xForce);
        }
        else if (neighbor instanceof nonSolid) {
            particleArray[p.getX()][p.getY()] = neighbor;
            particleArray[p.getX()][p.getY()].setPosition(-yForce, -xForce);
            particleArray[p.getX()+xForce][p.getY()+yForce] = p;
            p.setPosition(yForce, xForce);
        }
    }

    public static void clickedOnScene(Pane p) {
        p.setOnMouseClicked(event -> {
            mouseX = (int) event.getX()/(width/numCellsWidth);
            mouseY = (int) event.getY()/(height/numCellsHeight);
            if (particleArray[mouseX][mouseY] == null) {
                add(mouseX, mouseY, Color.BLUE, "STONE");
            }
        });
    }

    public static void draggedOnScene(Pane p) {
        p.setOnMouseDragged(event -> {
            mouseX = (int) event.getX()/(width/numCellsWidth);
            mouseY = (int) event.getY()/(height/numCellsHeight);
            if (particleArray[mouseX][mouseY] == null) {
                add(mouseX, mouseY, Color.BLUE, "STONE");
            }
        });
    }

    public static void brushPaneToggle(Scene s) {
        s.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                if (brushPaneOpen) {
                    brushPane.setVisible(false);
                }
                else {
                    brushPane.setMinWidth(width/3);
                    brushPane.setVisible(true);
                }
                brushPaneOpen = !brushPaneOpen;
            }
        });
    }
}
