package com.example.app;

import com.example.app.particletypes.*;

import java.util.concurrent.ThreadLocalRandom;


public class Tools extends Controller{
    static int generateRandom(int min, int max) {
        //i took this code from stack overflow trying to find the old method that i did it that i couldn't quite
        //remember, the max bound needs to be one more than the max bound you intend. this method of
        //getting a random int has the upside of not creating a .random instance.
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static void add(int x, int y, String type) {
        Particle p;
        switch(type) {
            case ("SAND") -> p = new Sand(particleColors.get(0), x , y);
            case ("STONE") -> p = new Stone(particleColors.get(1), x, y);
            case ("WATER") -> p = new Water(particleColors.get(2), x, y);
            case ("OIL") -> p = new Water(particleColors.get(4), x, y);
            default -> p = new Air(particleColors.get(3), x, y);
        }
        p.addToScene(pane);
        particle.add(p);
        particleArray[x][y] = p;
    }
}
