package com.centroafuera.primerjuego.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;


public class PantallaMenuPrinicpal implements Screen {
    Stage stage;
    boolean normal=true;
    int dificultad=1;
    Texture background1;
    public float screenHeight;
    public float screenWidth;
    @Override
    public void show() {
        screenHeight=Gdx.graphics.getHeight();
        screenWidth=Gdx.graphics.getWidth();
        background1= new Texture("backgrounds/back2.jpg");
            if (!VisUI.isLoaded())
                VisUI.load();

            stage = new Stage();

            VisTable table = new VisTable(true);
            table.setFillParent(true);
            stage.addActor(table);



        /*VisTextButton playButton = new VisTextButton("PLAY");
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preferences prefs = Gdx.app.getPreferences("opciones");
                if (normal) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaHistoria(dificultad));
                    dispose();
                } else {
                    if (prefs.getBoolean("modo")) {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaDificilNivel2());
                        dispose();
                    } else {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaJuego());
                        dispose();
                    }
                }
            }

        });*/
        VisTextButton playButton = new VisTextButton("PLAY");
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Preferences prefs = Gdx.app.getPreferences("opciones");
                if (prefs.getBoolean("modo")) {
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaHistoria3(dificultad));
                    dispose();
                } else {
                        ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaHistoria(dificultad));
                        dispose();
                    }
                }

        });

            VisTextButton multiJugador = new VisTextButton("MULTIPLAYER");
            multiJugador.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Preferences prefs = Gdx.app.getPreferences("opciones");
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaHistoria2(dificultad));
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

                VisLabel aboutLabel = new VisLabel("Demo libGDX\n(c)Aayush 2020 \nNote: SPACE Key-Shooting \n   Arrows Keys-Moving SpaceShip");
                aboutLabel.setColor(Color.RED);


        Drawable combat= new Drawable() {
            @Override
            public void draw(Batch batch, float x, float y, float width, float height) {
                batch.draw(new Texture("backgrounds/combat.jpg"),x,y);

                //comentario
            }

            @Override
            public float getLeftWidth() {
                return 0;
            }

            @Override
            public void setLeftWidth(float leftWidth) {

            }

            @Override
            public float getRightWidth() {
                return 0;
            }

            @Override
            public void setRightWidth(float rightWidth) {

            }

            @Override
            public float getTopHeight() {
                return 0;
            }

            @Override
            public void setTopHeight(float topHeight) {

            }

            @Override
            public float getBottomHeight() {
                return 0;
            }

            @Override
            public void setBottomHeight(float bottomHeight) {

            }

            @Override
            public float getMinWidth() {
                return 0;
            }

            @Override
            public void setMinWidth(float minWidth) {

            }

            @Override
            public float getMinHeight() {
                return 0;
            }

            @Override
            public void setMinHeight(float minHeight) {

            }

        };




                // Añade filas a la tabla y añade los componentes
                VisImage combatImage = new VisImage(combat);table.row();
                table.add(combatImage).center().width(400).height(0).pad(0);
                table.row();
                table.add(playButton).center().width(200).height(70).pad(5);
                table.row();
                table.add(multiJugador).center().width(200).height(70).pad(5);
                table.row();
                table.add(configButton).center().width(200).height(70).pad(5);
                table.row();
                table.add(quitButton).center().width(200).height(70).pad(5);
                table.row();
                table.add(aboutLabel).left().width(200).height(50).pad(20);

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
