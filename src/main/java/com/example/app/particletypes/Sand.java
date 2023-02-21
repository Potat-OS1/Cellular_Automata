package com.example.app.particletypes;

import com.example.app.Behavior;
import com.example.app.properties.Flamability;
import com.example.app.properties.Gravity;
import com.example.app.properties.Solid;
import com.example.app.properties.nonSolid;
import javafx.scene.paint.Color;

import static com.example.app.Controller.*;

public class Sand extends Particle implements Gravity, Flamability, Solid {

    public Sand(Color c, int x, int y) {
        super (x, y, c);
    }

    @Override
    public double catchChance() {
        return 0.0;
    }

    @Override
    public String ping() {
        return "SAND";
    }

    @Override
    public void drop(Particle p) {
        if ((p.getY() + force) < numCellsHeight) {
            neighbor1 = particleArray[p.getX()][p.getY()+force];
            if (neighbor1 == null || neighbor1 instanceof nonSolid) {
                Behavior.gravityDrop(p, force);
                return;
            }
        }

        if ((p.getY() + force) < numCellsHeight) {
            sideCheck(p);
        }

    }

    private void sideCheck(Particle p) {
        if ((p.getX() - 1) > -1) {
            neighbor1 = particleArray[p.getX()-1][p.getY()];
            neighbor2 = particleArray[p.getX()-1][p.getY()+1];
            if (neighbor1 == null || neighbor1 instanceof nonSolid) {
                if (neighbor2 == null) {
                    Behavior.pushDiagonal(p, -1, force);
                    return;
                }
                if (neighbor2 instanceof nonSolid) {
                    Behavior.pushDiagonal(p, -1, force);
                    return;
                }
            }
        }

        if ((p.getX() + 1) < numCellsWidth ) {
            neighbor1 = particleArray[p.getX()+1][p.getY()];
            neighbor2 = particleArray[p.getX()+1][p.getY()+1];
            if (neighbor1 == null || neighbor1 instanceof nonSolid) {
                if (neighbor2 == null) {
                    Behavior.pushDiagonal(p, 1, force);
                    return;
                }
                if (neighbor2 instanceof nonSolid) {
                    Behavior.pushDiagonal(p, 1, force);
                }
            }
        }
    }
}
