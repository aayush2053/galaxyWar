package com.centroafuera.primerjuego.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import com.centroafuera.primerjuego.model.*;
import com.centroafuera.primerjuego.util.Constantes;




public class PantallaBoss implements Screen{

    private SpriteBatch batch;
    private Nave nave;


    private Array<Bala> balas;


    Boss elBoss;


    int velBack=Constantes.BACKGROUND_VELOCIDAD;


    Music aSound;

    Texture background1;
    Texture background2;

    private float backX=0;
    private float screenHeight;
    private float screenWidth;


    @Override
    public void show() {
        batch = new SpriteBatch();
        nave = new Nave(new Vector2(10,200),new Texture("ship/f1.png"),3,10);
        balas = new Array<>();
        Preferences prefs = Gdx.app.getPreferences("opciones");
        if (prefs.getBoolean("musica")) {
            aSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/starwar.mp3"));
            aSound.play();
            aSound.setLooping(true);
            aSound.setVolume((float) 0.15);
        }


        elBoss = new Boss(new Texture("enemy/boss.png"), Constantes.VIDAS_BOSS, Constantes.VELOCIDAD_BOSS);
        int enX = Gdx.graphics.getWidth() - elBoss.getTexture().getWidth();
        int enY = MathUtils.random(0, Gdx.graphics.getHeight());
        elBoss.setVecConRec(new Vector2(enX, enY));

        screenHeight=Gdx.graphics.getHeight();
        screenWidth=Gdx.graphics.getWidth();
        background1= new Texture("backgrounds/back4.jpg");
        background2= new Texture("backgrounds/back4.jpg");
    }

    @Override
    public void render(float delta) {
        actualizar();
        pintar();


    }
    private void actualizar(){
        // input de usuario
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            if(nave.getPosicion().y<Gdx.graphics.getHeight()-nave.getTexture().getWidth()){
                nave.moverArriba();

            }


        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(nave.getPosicion().x<Gdx.graphics.getWidth()-nave.getTexture().getWidth()){
                nave.moverDerecha();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if(nave.getPosicion().x>0){
                nave.moverIzquierda();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            if(nave.getPosicion().y>0){
                nave.moverAbajo();

            }


        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            ((Game)Gdx.app.getApplicationListener()).setScreen(new PantallaMenuPrinicpal());
        }




        comprobarColisiones();
        disparar();
        moverBalas();
        enemigosYBoss();


    }

    private void enemigosYBoss(){
            generaBoss();

    }
    private void generaBoss(){
        if(!elBoss.isVivo()){
            elBoss.setVivo(true);
        }

        if((nave.getPosicion().x-elBoss.getPosicion().x)<0){
            elBoss.moverIzquierda();
        }else{
            if((nave.getPosicion().x-elBoss.getPosicion().x)>0){
                elBoss.moverDerecha();
            }
        }
        if((nave.getPosicion().y-elBoss.getPosicion().y)<0){
            elBoss.moverAbajo();
        }else{
            if((nave.getPosicion().y-elBoss.getPosicion().y)>0){
                elBoss.moverArriba();
            }
        }


    }



    private void  disparar(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            int x = (int) (nave.getPosicion().x + nave.getTexture().getWidth());
            int y = (int) nave.getPosicion().y;
            Bala bala = new Bala(new Vector2(x, y), new Texture("ship/bullet.png"), 1, 12);
            Preferences prefs = Gdx.app.getPreferences("opciones");
            if (prefs.getBoolean("sonido")) {
                Sound aSound = Gdx.audio.newSound(Gdx.files.internal("sounds/disparo.mp3"));
                aSound.play();
            }
            balas.add(bala);
        }

    }
    private void moverBalas(){
        if(balas.size>0){
            for(Bala bala : balas){
                bala.moverDerecha();
            }
        }
    }



    private void comprobarColisiones(){
        if (elBoss.isVivo()){
            if(nave.rect.overlaps(elBoss.rect)){
                nave.quitarVida();
            }

            if(nave.getVidas()<=0){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new PantallaFin());
                Preferences prefs = Gdx.app.getPreferences("opciones");
                if (prefs.getBoolean("musica")) {
                    aSound.stop();
                }
            }

            for(Bala bala : balas){
                if (bala.rect.overlaps(elBoss.rect)) {
                    elBoss.quitarVida();
                    if(elBoss.getVidas()<=0){
                        elBoss.isVivo();
                        balas.removeValue(bala,true);
                    }
                }
            }
        }

        /*if(elBoss.isVivo()){
            for(Bala bala : balas){
                if(bala.rect.overlaps(elBoss.rect)){
                    elBoss.quitarVida();
                }
            }
            if(elBoss.rect.overlaps(nave.rect)){

                nave.setVidas(0);
            }

        }*/

        /*if(nave.getVidas()<=0){
                ((Game)Gdx.app.getApplicationListener()).setScreen(new PantallaFin());
                Preferences prefs = Gdx.app.getPreferences("opciones");
                if (prefs.getBoolean("musica")) {
                    aSound.stop();
                }
            }*/

            /*for(Bala bala : balas){
                if (bala.rect.overlaps(elBoss.rect)) {
                    elBoss.quitarVida();
                    if(elBoss.getVidas()<=0){
                        balas.removeValue(bala,true);
                        contaMarcianos++;
                    }
                }
            }*/

    }





    private  void pintar(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background1,backX,0,screenWidth,screenHeight);
        batch.draw(background2,backX+screenWidth,0,screenWidth,screenHeight);
        nave.pintar(batch);
        /*font.draw(batch, "Vidas: "+String.valueOf(contaVidas), 10, 700);
        fontMarciano.draw(batch,"Marcianos muertos: "+String.valueOf(contaMarcianos),90,700);
        totalMarciano.draw(batch,"Tus Objetivo para ir a Nivel 2: 30 Marcianos",250,700);
        cambiarTextureMarcianos();*/

        if(elBoss.isVivo()){
            elBoss.pintar(batch);
        }


        for (Bala bala : balas){
            bala.pintar(batch);
        }


        batch.end();
        backX -= velBack;
        if(backX+screenWidth==0){
            backX=0;

        }

        if(!elBoss.isVivo()){

            ((Game)Gdx.app.getApplicationListener()).setScreen(new PantallaObjetivo());
        }
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


        batch.dispose();
    }
}
