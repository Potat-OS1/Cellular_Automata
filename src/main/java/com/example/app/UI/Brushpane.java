package com.example.app.UI;

import com.example.app.Controller;
import com.example.app.brushbehavior.Behavior;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Objects;

import static com.example.app.Controller.particleColors;
import static com.example.app.Controller.particleName;
import static com.example.app.Controller.width;
import static com.example.app.Controller.height;

public class Brushpane extends Behavior{
    public static boolean brushPaneOpen = false;
    static double sliderValue = 1.0;

    public void createBrushPane(Pane p, Scene s) {
        StackPane brushPane = new StackPane();
        p.getChildren().add(brushPane);
        brushPane.setBackground(new Background(new BackgroundFill(new Color(0.15, 0.15, 0.15, 1.0), null, null)));
        brushPane.setMinSize(0, Controller.height);
        brushPaneToggle(s, brushPane, p);
        brushPane.setVisible(false);
        //add things to brush pane
        brushPane.getChildren().add(paneContainer());
    }

    private void brushPaneToggle(Scene s, StackPane brushPane, Pane p) {
        s.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ESCAPE)) {
                if (brushPaneOpen) {
                    brushPane.setVisible(false);
                    p.setVisible(false);
                }
                else {
                    brushPane.setMinWidth(width/3.0);
                    brushPane.setVisible(true);
                    p.setVisible(true);
                }
                brushPaneOpen = !brushPaneOpen;
            }
        });
    }

    private Slider brushSizeSlider() {
        Slider s = new Slider(0, 10, sliderValue);
        s.setShowTickMarks(true);
        s.setOrientation(Orientation.HORIZONTAL);
        s.setMinWidth(width/3.5);
        s.setMajorTickUnit(1);

        Behavior b = new Behavior();

        s.valueProperty().addListener((observable, oldValue, newValue) -> {
            sliderValue = (double) newValue;
            b.setBrush((Double) newValue * brushSize);
            System.out.println(newValue);
        });
        return s;
    }

    private VBox paneContainer() {
        VBox v = new VBox();
        double inset = width/36.0;
        v.setPadding(new Insets(inset, inset, 0, inset));
        //add things to container
        v.getChildren().add(brushSizeSlider());
        v.getChildren().add(particleContainer());
        v.getChildren().add(createRegion(false));
        v.getChildren().add(brushContainer());
        v.getChildren().add(createRegion(false));

        return v;
    }

    private ScrollPane particleContainer() {
        ScrollPane sp = new ScrollPane();
        sp.setMinHeight(height/3.0);
        VBox vb = new VBox();
        double inset = width/144.0;
        vb.setPadding(new Insets(inset, inset, inset, inset));
        double rectSize = (width/3.5 - (width/36.0) - (width/72.0) - (4 * width/144.0))/4.0;

        for (int a = 0; a < Math.ceil(Controller.particleColors.size()/4.0); a++) {
            HBox hb = new HBox();
            hb.setMinWidth(width/3.5 - (width/72.0));
            vb.getChildren().add(hb);
            for (int b = 0; b < 4; b++) {
                try {
                    Rectangle r = new Rectangle(rectSize, rectSize, particleColors.get((a*4)+b));
                    r.setStroke(Color.BLACK);
                    r.setStrokeWidth(width/288.0);

                    Behavior.swapBrushContent(r, particleName.get((a*4)+b));



                    hb.getChildren().add(r);
                    if (b < 3) {
                        hb.getChildren().add(createRegion(true));
                    }
                }
                catch(Exception e) {
                    //reached end of colors.
                }
            }
        }
        sp.setContent(vb);
        return sp;
    }

    private ScrollPane brushContainer() {
        ScrollPane sp = new ScrollPane();
        sp.setMinHeight(height/2.0);
        VBox vb = new VBox();
        vb.setSpacing(width/144.0);
        double inset = width/144.0;
        vb.setPadding(new Insets(inset, inset, inset, inset));

        getBrushes(vb);

        sp.setContent(vb);

        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        return sp;
    }

    private Region createRegion(Boolean hbox) {
        Region reg = new Region();
        if (hbox) {
            HBox.setHgrow(reg, Priority.ALWAYS);
        }
        else {
            VBox.setVgrow(reg, Priority.ALWAYS);
        }
        //reg.setBackground(Background.fill(Color.BLUEVIOLET));
        return reg;
    }

    private StackPane brush(ImagePattern ip, String name) {
        StackPane p = new StackPane();
        p.setMinSize(width/4, height/12.0);
        p.setOnMouseClicked(Event -> {
            setBrush(name, (brushSize * sliderValue));
        });

        HBox hb = new HBox();
        hb.setBackground(Background.fill(Color.GRAY));
        hb.setPadding(new Insets(width/144.0, width/144.0, width/144.0, width/144.0));

        //without scroll on the right, the width 3.5 fits well.
        Rectangle r = new Rectangle(height/12.0, height/12.0, Color.DARKGRAY);
        r.setFill(ip);

        Label l = new Label(name);
        l.setFont(new Font("Arial", width/40.0));
        l.setAlignment(Pos.CENTER);

        hb.getChildren().add(r);
        hb.getChildren().add(createRegion(true));
        hb.getChildren().add(l);
        hb.getChildren().add(createRegion(true));
        p.getChildren().add(hb);

        return p;
    }

    private void getBrushes(VBox vb) {
        BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream("/brushes/brush list.txt"))));
        String line;
        String brushName;
        while (true) {
            try {
                if ((line = br.readLine()) != null) {
                    if (line.contains("break")) {
                        break;
                    }
                    brushName = line + ".png";
                    URL url = getClass().getResource("/brushes/" + brushName);
                    Image i = new Image(url.toExternalForm(), height/9.0, height/9.0, false, false);
                    ImagePattern img = new ImagePattern(i);
                    vb.getChildren().add(brush(img, line));
                }
            }
            catch (IOException e) {
                break;
            }
        }
    }

}
