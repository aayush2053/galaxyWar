package com.centroafuera.primerjuego.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

public class PantallaObjetivo implements Screen {

    Stage stage;

    @Override
    public void show() {
        if (!VisUI.isLoaded())
            VisUI.load();

        stage = new Stage();

        VisTable table = new VisTable(true);
        table.setFillParent(true);
        stage.addActor(table);

        VisLabel texto = new VisLabel("                   Winner!!!!!\nYou have completed your objective \n   Press Enter for the next game!!!");

        // Añade filas a la tabla y añade los componentes
        texto.setColor(Color.GOLD);
        texto.setFontScale(3);
        table.row();
        table.add(texto).center();

        Gdx.input.setInputProcessor(stage);
        Preferences prefs = Gdx.app.getPreferences("opciones");
        if (prefs.getBoolean("sonido")) {
            Sound sonido = Gdx.audio.newSound(Gdx.files.internal("sounds/applause7.mp3"));
            sonido.play();
        }
    }

    @Override
    public void render(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            ((Game)Gdx.app.getApplicationListener()).setScreen(new PantallaMenuPrinicpal());
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Pinta la UI en la pantalla
        stage.act(dt);
        stage.draw();
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
