package com.centroafuera.primerjuego.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;


public class PantallaMenuPrinicpal implements Screen {
    Stage stage;
    @Override
    public void show() {
            if (!VisUI.isLoaded())
                VisUI.load();

            stage = new Stage();

            VisTable table = new VisTable(true);
            table.setFillParent(true);
            stage.addActor(table);




        VisTextButton playButton = new VisTextButton("PLAY");
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preferences prefs = Gdx.app.getPreferences("opciones");
                if (prefs.getBoolean("modo")) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaDificilNivel2());
                    dispose();
                } else {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaJuego());
                        dispose();
                    }
                }

        });

            VisTextButton multiJugador = new VisTextButton("MULTIJUGADOR");
            multiJugador.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Preferences prefs = Gdx.app.getPreferences("opciones");
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaMultiJugador());
                    dispose();
                }

            });


            VisTextButton configButton = new VisTextButton("CONFIGURATION");
            configButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    ((Game)Gdx.app.getApplicationListener()).setScreen(new PantallaConfiguracion());
                    dispose();
                }
            });

                VisTextButton quitButton = new VisTextButton("QUIT");
                quitButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        VisUI.dispose();
                        // Salir del juego
                        System.exit(0);
                    }
                });

                VisLabel aboutLabel = new VisLabel("Demo libGDX\n(c)Aayush 2020");
                aboutLabel.setColor(Color.RED);
                // Añade filas a la tabla y añade los componentes
                table.row();
                table.add(playButton).center().width(200).height(100).pad(5);
                table.row();
                table.add(multiJugador).left().width(200).height(50).pad(5);
                table.row();
                table.add(configButton).center().width(200).height(50).pad(5);
                table.row();
                table.add(quitButton).center().width(200).height(50).pad(5);
                table.row();
                table.add(aboutLabel).left().width(200).height(20).pad(5);

                Gdx.input.setInputProcessor((InputProcessor) stage);
        }
        @Override
        public void render(float dt) {
            Gdx.gl.glClearColor(0, 1, 1, 1);
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

        }



    }
