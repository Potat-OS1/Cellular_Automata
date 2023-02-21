package com.example.app;

import com.example.app.particletypes.Particle;
import com.example.app.properties.nonSolid;
import static com.example.app.Controller.particleArray;

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
}
