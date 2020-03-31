package com.centroafuera.primerjuego.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Nave extends Personaje {


    public  enum TipoDisparo{
        UNO,RAFAGA
    }



    public Nave(Vector2 posicion, Texture texture, int vidas, int velocidad) {
        super(posicion, texture, vidas, velocidad);

    }


    @Override
    public void pintar(SpriteBatch batch){
        super.pintar(batch);
        //batch.draw(textureNaveAuxiliar,getPosicion().x,getPosicion().y);
    }
}

