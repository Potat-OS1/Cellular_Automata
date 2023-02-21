package com.example.app.properties;

import com.example.app.particletypes.Particle;

public interface Gravity {
    int force = 1;
    void drop(Particle self);
}
