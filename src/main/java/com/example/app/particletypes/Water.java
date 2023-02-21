package com.example.app.particletypes;

import com.example.app.Behavior;
import com.example.app.properties.Gravity;
import com.example.app.properties.Viscosity;
import com.example.app.properties.nonSolid;
import javafx.scene.paint.Color;

import static com.example.app.Controller.*;
import static com.example.app.Controller.particleArray;

public class Water extends Particle implements Gravity, Viscosity, nonSolid {
    public Water(Color c, int x, int y) {
        super(x, y, c);
    }

    @Override
    String ping() {
        return null;
    }

    //in both this and sand i feel like i could implement these better, but for now its staying like this.
    @Override
    public void drop(Particle p) {
        if ((p.getY() + force) < numCellsHeight) {
            if (particleArray[p.getX()][p.getY()+force] == null) {
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
            if (particleArray[p.getX()-1][p.getY()] == null && particleArray[p.getX()-1][p.getY()+1] != null) {
                Behavior.pushHorizontal(p, -force);
                return;
            }
            if (particleArray[p.getX()-1][p.getY()] == null && particleArray[p.getX()-1][p.getY()+1] == null) {
                Behavior.pushDiagonal(p, -1, force);
                return;
            }
        }

        if ((p.getX() + 1) < numCellsWidth ) {
            if (particleArray[p.getX()+1][p.getY()] == null && particleArray[p.getX()+1][p.getY()+1] != null) {
                Behavior.pushHorizontal(p, force);
                return;
            }
            if (particleArray[p.getX()+1][p.getY()] == null && particleArray[p.getX()+1][p.getY()+1] == null) {
                Behavior.pushDiagonal(p, 1, force);
            }
        }
    }
}
