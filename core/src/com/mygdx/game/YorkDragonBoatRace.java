package com.mygdx.game;

import com.mygdx.game.screens.MainMenu;
import com.badlogic.gdx.Game;
import com.mygdx.game.utils.Assets;

public class YorkDragonBoatRace extends Game {

	@Override
	public void create () {
		Assets.load();
		super.setScreen(new MainMenu(this)); //Takes the user to the MainMenu
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		Assets.dispose();
	}
}