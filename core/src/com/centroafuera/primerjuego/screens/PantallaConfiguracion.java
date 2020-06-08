package com.centroafuera.primerjuego.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.*;


public class PantallaConfiguracion implements Screen {
    Preferences prefs;
    Stage stage;

    @Override
    public void show() {
        prefs = Gdx.app.getPreferences("opciones");
            if (!VisUI.isLoaded())
                VisUI.load();

            stage = new Stage();

            VisTable table = new VisTable(true);
            table.setFillParent(true);
            stage.addActor(table);

        final VisCheckBox checkMusica = new VisCheckBox("EFECTOS DE MUSICA");
        checkMusica.setChecked(prefs.getBoolean("musica"));
        checkMusica.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prefs.putBoolean("musica", checkMusica.isChecked());
                prefs.flush();
            }
        });

        final VisCheckBox checkSound = new VisCheckBox("EFECTOS DE SONIDO");
        checkSound.setChecked(prefs.getBoolean("sonido"));
        checkSound.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prefs.putBoolean("sonido", checkSound.isChecked());
                prefs.flush();
            }
        });


        final VisRadioButton checkModo = new VisRadioButton(" NIVEL 2");
        checkModo.setChecked(prefs.getBoolean("modo"));
        checkModo.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prefs.putBoolean("modo", checkModo.isChecked());
                prefs.flush();

            }
        });



        VisTextButton quitButton = new VisTextButton("SALIR");
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaMenuPrinicpal());
                dispose();
            }
        });



            VisLabel aboutLabel = new VisLabel("Demo libGDX\n(c)Aayush 2020");
            aboutLabel.setColor(Color.RED);
            // Añade filas a la tabla y añade los componentes
        table.row();
        table.add(checkSound).center().width(200).height(80).pad(3);
        table.row();
        table.add(checkMusica).center().width(200).height(80).pad(3);
        table.row();
        table.add(checkModo).center().width(200).height(80).pad(3);
        table.row();

        table.add(quitButton).center().width(200).height(80).pad(3);
        table.row();

        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void render(float dt) {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Pinta la UI en la pantalla
        stage.act(dt);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        // Redimensiona la escena al redimensionar la ventana del juego

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
