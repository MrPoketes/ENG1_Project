package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.entities.TestEntity;
import com.mygdx.game.systems.RenderSprites;
import com.mygdx.game.systems.RenderText;
import com.mygdx.game.entities.TestText;
import com.mygdx.game.entities.box;
import com.mygdx.game.entities.healthBox;
import com.mygdx.game.systems.RenderBoxes;

public class YorkDragonBoatRace extends ApplicationAdapter {

	Engine engine;
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shape;
	

	@Override
	public void create () {
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		engine = new Engine();
		engine.addSystem(new RenderSprites(batch));
		engine.addSystem(new RenderText(batch));
		engine.addSystem(new RenderBoxes(shape));
		engine.addEntity(new TestEntity(50, 200));
		engine.addEntity(new TestText(200,300,"Hello world"));
		engine.addEntity(new box(530,430, 100,20, "outlineBar", Color.WHITE));
		engine.addEntity(new healthBox(530,430, 100,20, "healthBar", Color.GREEN));
		engine.addEntity(new box(530,400, 100,20, "exhuastionBar", Color.YELLOW));
		engine.addEntity(new box(530,370, 100,20, "cooldownbar", Color.BROWN));
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
