package com.example.app.particletypes;

import com.example.app.Rules;
import com.example.app.properties.Gravity;
import com.example.app.properties.Viscosity;
import com.example.app.properties.NonSolid;
import javafx.scene.paint.Color;

import static com.example.app.Controller.*;
import static com.example.app.Controller.particleArray;

public class Water extends Particle implements Gravity, Viscosity, NonSolid {
    public Water(Color c, int x, int y) {
        super(x, y, c);
    }

    //in both this and sand i feel like i could implement these better, but for now its staying like this.
    @Override
    public void drop(Particle p) {
        if ((p.getY() + force) < numCellsHeight) {
            if (particleArray[p.getX()][p.getY()+force] == null) {
                Rules.gravityDrop(p, force);
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
                Rules.pushHorizontal(p, -force);
                return;
            }
            if (particleArray[p.getX()-1][p.getY()] == null && particleArray[p.getX()-1][p.getY()+1] == null) {
                Rules.pushDiagonal(p, -1, force);
                return;
            }
        }

        if ((p.getX() + 1) < numCellsWidth ) {
            if (particleArray[p.getX()+1][p.getY()] == null && particleArray[p.getX()+1][p.getY()+1] != null) {
                Rules.pushHorizontal(p, force);
                return;
            }
            if (particleArray[p.getX()+1][p.getY()] == null && particleArray[p.getX()+1][p.getY()+1] == null) {
                Rules.pushDiagonal(p, 1, force);
                return;
            }
        }
    }
}
