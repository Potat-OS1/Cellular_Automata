package com.example.app.particletypes;

import com.example.app.properties.NonSolid;
import javafx.scene.paint.Color;

public class Air extends Particle implements NonSolid {
    public Air(Color c, int x, int y) {
        super(x, y, c);
    }

}
