package com.example.app.particletypes;

import javafx.scene.paint.Color;

public class Air extends Particle{
    public Air(Color c, int x, int y) {
        super(x, y, c);
    }

    @Override
    String ping() {
        return null;
    }
}
