package com.example.app.particletypes;

import com.example.app.Controller;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Particle{
    Rectangle r;
    private int x;
    private int y;
    int lastX;
    int lastY;
    Particle neighbor1;
    Particle neighbor2;

    Particle (int x, int y, Color c) {
        this.x = x;
        this.y = y;
        r = new Rectangle(Controller.cellWidth, Controller.cellHeight);
        r.setFill(c);
        r.setOpacity(.5);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addToScene(Pane p) {
        p.getChildren().add(r);
        setPosition();
    }

    void getPosition() {
        System.out.println(ping() + "@" + r.getLayoutX() + " - " + r.getLayoutY());
    }

    public void setPosition(int yForce, int xForce) {
        lastX = this.x;
        lastY = this.y;
        x = x + xForce;
        y = y + yForce;
        r.setLayoutX(x*(r.getWidth()));
        r.setLayoutY(y*(r.getHeight()));
    }

    void setPosition() {
        r.setLayoutX(x*(r.getWidth()));
        r.setLayoutY(y*(r.getHeight()));
    }


    abstract String ping();

}
