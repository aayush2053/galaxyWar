package com.centroafuera.primerjuego.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bala extends Personaje {
    public Bala(Vector2 posicion, Texture texture, int vidas, int velocidad) {
        super(posicion, texture, vidas, velocidad);
    }
}
