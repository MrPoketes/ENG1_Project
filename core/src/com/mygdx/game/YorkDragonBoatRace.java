package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.entities.TestEntity;
import com.mygdx.game.systems.RenderSprites;
import com.mygdx.game.systems.RenderText;
import com.mygdx.game.entities.TestText;
import com.mygdx.game.entities.box;
import com.mygdx.game.systems.RenderBoxes;

public class YorkDragonBoatRace extends ApplicationAdapter {
	Engine engine;
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		engine = new Engine();
		engine.addSystem(new RenderBoxes(batch));
		engine.addSystem(new RenderSprites(batch));
		engine.addSystem(new RenderText(batch));
		engine.addEntity(new TestEntity(50, 200));
		engine.addEntity(new TestText(200,300,"Hello world"));
		engine.addEntity(new box(70,200, 80,20, "healthBar"));
	}

	@Override
	public void render () {
		engine.update(0f);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
