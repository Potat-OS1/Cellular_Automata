package com.example.app.particletypes;

import com.example.app.properties.Flamability;
import com.example.app.properties.Solid;
import javafx.scene.paint.Color;

public class Stone extends Particle implements Flamability, Solid {
    public Stone(Color c, int x, int y) {
        super(x, y, c);
    }

    @Override
    public double catchChance() {
        return 0;
    }

    @Override
    public String ping() {
        return "STONE";
    }
}
