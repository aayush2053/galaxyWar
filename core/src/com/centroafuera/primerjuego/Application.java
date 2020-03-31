package com.centroafuera.primerjuego;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.centroafuera.primerjuego.screens.PantallaMenuPrinicpal;

public class Application extends Game {



	@Override
	public void create () {
		((Game) Gdx.app.getApplicationListener()).setScreen(new PantallaMenuPrinicpal());
	}

	@Override
	public void render () {
		super.render();

	}

	@Override
	public void dispose () {

		//img.dispose();
	}
}
