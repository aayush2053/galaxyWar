package com.centroafuera.primerjuego.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.centroafuera.primerjuego.model.*;
import com.centroafuera.primerjuego.util.Constantes;

import java.util.Random;


public class PantallaJuego implements Screen{

    private SpriteBatch batch;
    private Nave nave;

    private Array<Marciano> marcianos;
    private Array<Roca> rocas;
    private Array<Bala> balas;
    private Array<Vida> vidas;
    private long tiempoMarciano;
    private long tiempoRoca;
    private long tiempoVida;

    private int contaVidas=0;
    private int contaMarcianos=0;
    BitmapFont font;
    BitmapFont fontMarciano;
    BitmapFont totalMarciano;
    int vidaMarcianos = Constantes.VIDA_MARCIANOS;
    int vidaRocas = Constantes.VIDA_ROCA;
    int velMarcianos = Constantes.VELOCIDAD_MARCIANOS;
    int velRocas = Constantes.VELOCIDAD_ROCAS;
    int velBack=Constantes.BACKGROUND_VELOCIDAD;
    int velNave=Constantes.VELOCIDAD_NAVE;
    private long tiempoRotacionRoca;
    Music aSound;

    Texture background1;
    Texture background2;

    private float backX=0;
    private float screenHeight;
    private float screenWidth;


    @Override
    public void show() {
        batch = new SpriteBatch();
        nave = new Nave(new Vector2(10,200),new Texture("ship/f1.png"),3,velNave);

        marcianos = new Array<>();
        contaVidas=nave.getVidas();
        tiempoMarciano= TimeUtils.millis();
        tiempoRoca = TimeUtils.millis();
        tiempoVida=TimeUtils.millis();
        tiempoRotacionRoca = TimeUtils.millis();
        balas = new Array<>();
        rocas = new Array<>();
        vidas = new Array<>();

        font = new BitmapFont();
        font.setColor(Color.BLUE);
        fontMarciano = new BitmapFont();
        fontMarciano.setColor(Color.BLUE);
        totalMarciano = new BitmapFont();
        totalMarciano.setColor(Color.BLUE);
        Preferences prefs = Gdx.app.getPreferences("opciones");
        if (prefs.getBoolean("musica")) {
            aSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/starwar.mp3"));
            aSound.play();
            aSound.setLooping(true);
            aSound.setVolume((float) 0.05);
        }

        screenHeight=Gdx.graphics.getHeight();
        screenWidth=Gdx.graphics.getWidth();
        background1= new Texture("backgrounds/back2.jpg");
        background2= new Texture("backgrounds/back2.jpg");
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



        generarEnemigos();
        moverEnemigos();
        comprobarColisiones();
        disparar();
        moverBalas();


    }

    private void generarEnemigos(){
        Random r = new Random();
        int enX=0;
        int enY=0;
        if(TimeUtils.millis() -tiempoMarciano> Constantes.TIEMPO_ENTRE_MARCIANOS){


            Texture Texture = new Texture("enemy/e_f1.png");
            enX= Gdx.graphics.getWidth()-Texture.getWidth();
            enY= MathUtils.random(0,Gdx.graphics.getHeight());

            Marciano marciano = new Marciano(new Vector2(enX,enY),new Texture("enemy/e_f1.png"),vidaMarcianos,velMarcianos);
            marcianos.add(marciano);
            tiempoMarciano=TimeUtils.millis();

        }
        if(TimeUtils.millis()- tiempoRoca>Constantes.TIEMPO_ENTRE_ROCAS){
            Texture texRoca = new Texture("enemy/stone1.png");
            enX= Gdx.graphics.getWidth()-texRoca.getWidth();
            enY=MathUtils.random(0,Gdx.graphics.getHeight());
            Roca roca = new Roca(new Vector2(enX,enY),texRoca,vidaRocas,velRocas);
            rocas.add(roca);
            tiempoRoca=TimeUtils.millis();

        }
        if(TimeUtils.millis()- tiempoVida>Constantes.TIEMPO_ENTRE_VIDA){
            Texture texVida = new Texture("items/heart.png");
            enX= Gdx.graphics.getWidth()-texVida.getWidth();
            enY=MathUtils.random(0,Gdx.graphics.getHeight());
            Vida vida = new Vida(new Vector2(enX,enY),texVida,vidaMarcianos,velMarcianos);
            vidas.add(vida);
            tiempoVida=TimeUtils.millis();
        }


    }

    private void  disparar(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            int x = (int) (nave.getPosicion().x + nave.getTexture().getWidth());
            int y = (int) nave.getPosicion().y;
            Bala bala = new Bala(new Vector2(x, y), new Texture("ship/bullet.png"), 1, 10);
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
        for(Vida vida : vidas) {
            if (nave.rect.overlaps(vida.rect)) {
                vidas.removeValue(vida, true);
                nave.sumarVida();
            }
        }

        for(Marciano marciano : marcianos){
            if(nave.rect.overlaps(marciano.rect)){
                marcianos.removeValue(marciano,true);
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
                if (bala.rect.overlaps(marciano.rect)) {
                    marciano.quitarVida();
                    if(marciano.getVidas()<=0){
                        marcianos.removeValue(marciano,true);
                        balas.removeValue(bala,true);
                        contaMarcianos++;
                    }
                }
            }
        }
        for(Roca roca: rocas){
            if(nave.rect.overlaps((roca.rect))){
                nave.setTexture(new Texture("explosion/explosion0010.png"));
                ((Game)Gdx.app.getApplicationListener()).setScreen(new PantallaFin());
                Preferences prefs = Gdx.app.getPreferences("opciones");
                if (prefs.getBoolean("musica")) {
                    aSound.stop();
                }
            }

            for(Bala bala : balas){
                if(bala.rect.overlaps(roca.rect)){
                    balas.removeValue(bala,true);
                    roca.quitarVida();

                }
            }
        }

        contaVidas=nave.getVidas();
    }
    private void moverEnemigos(){

        for(Marciano marciano : marcianos){
            marciano.moverIzquierda();

            if(marciano.getPosicion().x<=0.5){
                marcianos.removeValue(marciano,true);
            }
        }
        for(Roca roca : rocas){
            roca.moverIzquierda();
            if(roca.getPosicion().x<=0.5){
                rocas.removeValue(roca,true);
            }
        }
        for(Vida vida : vidas){
            vida.moverIzquierda();
            if(vida.getPosicion().x<=0.5){
                vidas.removeValue(vida,true);
            }
        }
    }
    public void cambiarTextureMarcianos(){
        for(Marciano marciano:marcianos){
            int posi = marciano.getPosiTexture();
            switch (posi){
                case 1: marciano.setTexture(new Texture("enemy/e_f1.png"));
                    break;
                case 2: marciano.setTexture(new Texture("enemy/e_f2.png"));
                    break;
                case 3: marciano.setTexture(new Texture("enemy/e_f3.png"));
                    break;
                case 4: marciano.setTexture(new Texture("enemy/e_f4.png"));
                    break;
                case 5: marciano.setTexture(new Texture("enemy/e_f5.png"));
                    break;
                case 6: marciano.setTexture(new Texture("enemy/e_f6.png"));
                    break;
            }
            posi++;
            if(posi>6){
                posi=1;
            }
            marciano.setPosiTexture(posi);
        }

    }

    public void rotarRocas(Array<Roca> rocas){
        int posi;
        if(TimeUtils.millis()-tiempoRotacionRoca>Constantes.TIEMPO_ROTACION_ROCA){
            for(Roca roca:rocas){
                posi = roca.getPosiTexture();
                switch (posi){
                    case 1:roca.setTexture(new Texture("enemy/stone1.png"));
                        break;
                    case 2: roca.setTexture(new Texture("enemy/stone2.png"));
                        break;
                    case 3: roca.setTexture(new Texture("enemy/stone3.png"));
                        break;
                    case 4: roca.setTexture(new Texture("enemy/stone4.png"));
                        break;
                    case 5: roca.setTexture(new Texture("enemy/stone5.png"));
                        break;
                }
                posi++;
                if(posi>5){
                    posi=1;
                }
                roca.setPosiTexture(posi);
                tiempoRotacionRoca=TimeUtils.millis();
            }
        }
    }

    private  void pintar(){
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background1,backX,0,screenWidth,screenHeight);
        batch.draw(background2,backX+screenWidth,0,screenWidth,screenHeight);
        nave.pintar(batch);
        font.draw(batch, "Vidas: "+String.valueOf(contaVidas), 30, 450);
        fontMarciano.draw(batch,"Marcianos muertos: "+String.valueOf(contaMarcianos),90,450);
        totalMarciano.draw(batch,"Tus Objetivo para ir a Nivel 2: 10 Marcianos",250,450);
        cambiarTextureMarcianos();

        for(Marciano marciano: marcianos){
            marciano.pintar(batch);
        }
        rotarRocas(rocas);
        for (Roca roca : rocas){
            roca.pintar(batch);
        }

        for (Bala bala : balas){
            bala.pintar(batch);
        }
        for (Vida vida: vidas){
            vida.pintar(batch);
        }
        batch.end();
        backX -= velBack;
        if(backX+screenWidth==0){
            backX=0;

        }

        if(contaMarcianos>=10){
            ((Game)Gdx.app.getApplicationListener()).setScreen(new PantallaDificilNivel2());
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
