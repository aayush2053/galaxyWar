package com.centroafuera.primerjuego.model;

import com.badlogic.gdx.graphics.Texture;


public class Boss extends Personaje {
    boolean vivo;
    int vida=0;

    public Boss( Texture texture, int vidas, int velocidad) {
        super(texture, vidas, velocidad);
        this.vivo=false;

    }


    public boolean isVivo() {
        return vivo;
    }


    @Override
    public void quitarVida() {
        vida--;
        if (vida <= 0){
            super.quitarVida();
            setVivo(false);

        }
    }
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }



}
