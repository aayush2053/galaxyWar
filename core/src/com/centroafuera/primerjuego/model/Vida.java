package com.centroafuera.primerjuego.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Vida extends  Personaje {
    public Vida(Vector2 posicion, Texture texture, int vidas, int velocidad) {
        super(posicion, texture, vidas, velocidad);
    }
}
