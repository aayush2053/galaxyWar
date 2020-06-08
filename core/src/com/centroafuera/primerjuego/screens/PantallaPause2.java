package com.centroafuera.primerjuego.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;

public class PantallaPause2 implements Screen {
    Stage stage;
    String playString;
    String confString;
    String salirString;

    VisTextButton playButton;
    VisTextButton quitButton;
    VisTextButton configButton;
    PantallaNivel2 elJuego;



    public PantallaPause2(PantallaNivel2 elJuego) {
        this.elJuego = elJuego;
    }




    @Override
    public void show() {

        if (!VisUI.isLoaded())
            VisUI.load();

        stage = new Stage();

        VisTable table = new VisTable(true);
        table.setFillParent(true);
        stage.addActor(table);

        playString = "CONTINUE";
        confString= "NEW GAME";
        salirString = "BACK TO LOBBY";

        playButton = new VisTextButton(playString);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(elJuego);

                dispose();

            }
        });

        quitButton = new VisTextButton(salirString);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                elJuego.dispose();

                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaMenuPrinicpal());
                dispose();
            }
        });
        configButton = new VisTextButton(confString);
        configButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Ir a la pantalla de configuración
                elJuego.dispose();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaNivel2());


            }
        });

        VisLabel aboutLabel = new VisLabel("Demo libGDX\n(c)Aayush 2020");


        table.row();
        table.add(playButton).center().width(200).height(100).pad(5);
        table.row();
        table.add(configButton).center().width(200).height(50).pad(5);
        table.row();
        table.add(quitButton).center().width(200).height(50).pad(5);
        table.row();
        table.add(aboutLabel).left().width(200).height(20).pad(5);

        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Pinta la UI en la pantalla
        stage.act(dt);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Redimensiona la escena al redimensionar la ventana del juego
        stage.getViewport().update(width, height);
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
        // Libera los recursos de la escena
        stage.dispose();
    }


}
