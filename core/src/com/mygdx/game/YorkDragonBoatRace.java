package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.entities.DisplayBox;
import com.mygdx.game.entities.TestEntity;
import com.mygdx.game.systems.RenderSprites;
import com.mygdx.game.systems.RenderText;
import com.mygdx.game.entities.TestText;
// import com.mygdx.game.entities.box;
import com.mygdx.game.entities.box_copy;
import com.mygdx.game.systems.RenderBoxes;
import com.badlogic.ashley.core.Entity;

public class YorkDragonBoatRace extends ApplicationAdapter {

	Engine engine;
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shape;
	Color Green = Color.GREEN;

	@Override
	public void create() {
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		engine = new Engine();
		engine.addSystem(new RenderSprites(batch));
		engine.addSystem(new RenderText(batch));
		engine.addSystem(new RenderBoxes(shape));
		// engine.addEntity(new TestEntity(50, 200));
		// engine.addEntity(new TestText(200, 300, "Hello world"));
		// engine.addEntity(new TestText(540, 440, "Name should below healthBar"));
		// engine.addEntity(new box_copy(530, 430, 80, 20, "healthBar", Green));
		// engine.addEntity(new TestText(540, 440, "Name should above healthBar"));

		DisplayBox displayBoxInstance = new DisplayBox();
		for (Entity e : displayBoxInstance.getAll()) {//use for-each loop to render all entites in the displaybox instance
			engine.addEntity(e);
		}

	}

	@Override
	public void render() {
		engine.update(0f);
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
