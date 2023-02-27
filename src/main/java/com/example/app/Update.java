package com.example.app;

import com.example.app.particletypes.Air;
import com.example.app.particletypes.Particle;
import com.example.app.properties.Gravity;
import javafx.animation.AnimationTimer;

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
        if (now - lastUpdate >= 1_000_000.0) {
            //
//            randomPos = Tools.generateRandom(0, Controller.numCellsWidth-1);
//            if (Controller.particleArray[randomPos][0] == null) {
//                spawnParticle();
//            }
//
//            else {
//                if (Controller.particleArray[randomPos][0] instanceof Air) {
//                    Controller.particleArray[randomPos][0] = null;
//                    spawnParticle();
//                }
//            }

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
        switch (Type) {
            case (0) -> Tools.add(randomPos, 0, "WATER");
            case (1) -> Tools.add(randomPos, 0, "SAND");
        }
    }
    void spawnParticle() {
        randomType = Tools.generateRandom(0, 1);
        switch (randomType) {
            case (0) -> Tools.add(randomPos, 0, "WATER");
            case (1) -> Tools.add(randomPos, 0, "SAND");
        }
    }
}
