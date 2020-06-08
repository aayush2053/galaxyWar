package com.centroafuera.primerjuego.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PantallaHistoria2  implements Screen {
    SpriteBatch batch;
    Texture historia;
    float x;
    float y;
    Sound canintro;
    int dificultad;
    PantallaMultiJugador elJuego;

    public PantallaHistoria2(int dificultad) {
        this.dificultad = dificultad;

        elJuego = new PantallaMultiJugador();
    }


    @Override
    public void show() {
        canintro = Gdx.audio.newSound(Gdx.files.internal("sounds/starwar.mp3"));
        canintro.play();
        batch = new SpriteBatch();
        historia = new Texture("backgrounds/intro.jpg");

        y= -historia.getHeight();



    }

    @Override
    public void render(float delta) {
        actualizar();
        pintar();


    }
    public void actualizar(){
        y=y+0.4f;
        lanzaJuego();
    }
    public void lanzaJuego(){
        if(y>=Gdx.graphics.getHeight()|| Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            canintro.stop();
            ((Game) Gdx.app.getApplicationListener()).setScreen(elJuego);
        }
    }
    public void pintar(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(historia,x,y);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
