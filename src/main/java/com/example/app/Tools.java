package com.example.app;

import com.example.app.particletypes.*;
import javafx.scene.paint.Color;

import java.util.concurrent.ThreadLocalRandom;


public class Tools extends Controller{
    static int generateRandom(int min, int max) {
        //i took this code from stack overflow trying to find the old method that i did it that i couldn't quite
        //remember, the max bound needs to be one more than the max bound you intend. this method of
        //getting a random int has the upside of not creating a .random instance.
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static void add(int x, int y, Color c, String type) {
        Particle p;
        switch(type) {
            case ("SAND") -> p = new Sand(c, x , y);
            case ("STONE") -> p = new Stone(c, x, y);
            case ("WATER") -> p = new Water(c, x, y);
            default -> p = new Air(c, x, y);
        }
        p.addToScene(pane);
        particle.add(p);
        particleArray[x][y] = p;
    }
}
