package com.example.app;

import com.example.app.particletypes.Particle;
import com.example.app.properties.Gravity;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;

public class Update extends AnimationTimer {
    private long lastUpdate = 0;
    int randomPos = 0;
    int randomType = 1;

    @Override
    public void start() {
        lastUpdate = System.nanoTime();
        super.start();
    }

    @Override
    public void handle(long now) {
        if (now - lastUpdate >= 1_000.0) {
            //
            //randomPos = Tools.generateRandom(0, Controller.numCellsWidth-1);
            randomPos = Tools.generateRandom(0, 20);
            if (Controller.particleArray[randomPos][0] == null) {
                spawnParticle(1);
            }
            randomPos = Tools.generateRandom(20, 60);
            if (Controller.particleArray[randomPos][0] == null) {
                spawnParticle(0);
            }
            randomPos = Tools.generateRandom(60, 80);
            if (Controller.particleArray[randomPos][0] == null) {
                spawnParticle(1);
            }
            randomPos = Tools.generateRandom(58, Controller.numCellsWidth-1);
            if (Controller.particleArray[randomPos][0] == null) {
                spawnParticle(0);
            }
            for (Particle p : Controller.particle) {
                gravity(p);
            }
            //
            lastUpdate = now;
        }
    }

    void gravity(Particle p) {
        if (p instanceof Gravity) {
            ((Gravity) p).drop(p);
        }
    }

    void spawnParticle(int Type) {
        //randomType = Tools.generateRandom(0, 1);
        switch (Type) {
            case (0) -> Tools.add(randomPos, 0, Color.LIGHTBLUE, "WATER");
            case (1) -> Tools.add(randomPos, 0, Color.BISQUE, "SAND");
        }
    }
}
