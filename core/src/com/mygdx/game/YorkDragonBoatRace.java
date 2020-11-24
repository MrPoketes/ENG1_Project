package com.mygdx.game;

import com.mygdx.game.screens.MainMenu;
import com.badlogic.gdx.Game;
import com.mygdx.game.utils.Assets;

import java.util.HashMap;

public class YorkDragonBoatRace extends Game {

	Integer chosenBoat;
	HashMap<Integer, Integer> bestTimes;

	@Override
	public void create () {
		Assets.load();
		super.setScreen(new MainMenu(this));

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