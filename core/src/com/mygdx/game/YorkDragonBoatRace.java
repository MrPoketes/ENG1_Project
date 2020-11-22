package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.entities.TestEntity;
import com.mygdx.game.systems.RenderSprites;
import com.mygdx.game.systems.RenderText;
import com.mygdx.game.entities.TestText;
import com.mygdx.game.entities.*;
import com.mygdx.game.systems.RenderBoxes;
import com.mygdx.game.screens.MainMenu;
import com.badlogic.gdx.Game;
import com.mygdx.game.utils.Assets;

public class YorkDragonBoatRace extends Game {


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