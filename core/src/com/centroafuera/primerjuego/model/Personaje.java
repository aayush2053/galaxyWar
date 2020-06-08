package com.centroafuera.primerjuego.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;



public class Personaje {
    private Vector2 posicion;
    private Texture texture;
    private int vidas;
    private int velocidad;


    public Rectangle rect;
    private  int posiTexture;


    public Personaje(Vector2 posicion, Texture texture, int vidas, int velocidad) {
        this.posicion = posicion;
        this.texture = texture;
        this.vidas = vidas;
        this.velocidad = velocidad;
        posiTexture=1;
        rect = new Rectangle(posicion.x,posicion.y,texture.getWidth(),texture.getHeight());
    }
    public Personaje(Texture texture, int vidas, int velocidad) {

        this.texture = texture;
        this.vidas = vidas;
        this.velocidad = velocidad;
        posiTexture=1;



    }


    public void setVecConRec(Vector2 posicion){
        this.posicion=posicion;
        rect = new Rectangle(getPosicion().x,getPosicion().y,texture.getWidth(),texture.getHeight());
    }

    public Vector2 getPosicion() {
        return posicion;
    }

    public void setPosicion(Vector2 posicion) {
        this.posicion = posicion;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public int getVidas() {
        return vidas;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
    public int getPosiTexture() {
        return posiTexture;
    }

    public void setPosiTexture(int posiTexture) {
        this.posiTexture = posiTexture;
    }


    public void pintar(SpriteBatch batch){
        batch.draw(getTexture(),getPosicion().x,getPosicion().y);
    }

    public void mover(Vector2 movimiento){
        posicion.add(movimiento.scl(velocidad));
        rect.setPosition(posicion);
    }

    public void moverArriba(){
        mover(new Vector2(0,1));

    }

    public void moverAbajo(){
        mover(new Vector2(0,-1));
    }

    public void moverDerecha(){

        mover(new Vector2(1,0));
    }

    public void moverIzquierda(){
        mover(new Vector2(-1,0));
    }

    public void quitarVida(){
        int vida = this.getVidas()-1;
        setVidas(vida);



    }

    public void sumarVida(){
        int vida = this.getVidas()+1;
        setVidas(vida);

    }



}
