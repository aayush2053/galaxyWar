package com.centroafuera.primerjuego.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisImage;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

public class PantallaFin implements Screen {
    Stage stage;

    @Override
    public void show() {
        if (!VisUI.isLoaded())
            VisUI.load();

        stage = new Stage();

        VisTable table = new VisTable(true);
        table.setFillParent(true);
        stage.addActor(table);

        VisLabel texto = new VisLabel("You Lose!! Best of luck next time!!! \n\t    Press Enter for the next game!!!");
        texto.setColor(Color.GOLD);
        texto.setFontScale(3);
        Drawable combat= new Drawable() {
            @Override
            public void draw(Batch batch, float x, float y, float width, float height) {
                batch.draw(new Texture("explosion/Gameover.png"),x,y);

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
        VisImage combatImage = new VisImage(combat);
        table.row();
        table.add(combatImage).left();
        //        // Añade filas a la tabla y añade los componentes
        table.row();
        table.add(texto).center();

        Gdx.input.setInputProcessor(stage);
        Preferences prefs = Gdx.app.getPreferences("opciones");
        if (prefs.getBoolean("sonido")) {
            Sound sonido = Gdx.audio.newSound(Gdx.files.internal("sounds/over.mp3"));
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
