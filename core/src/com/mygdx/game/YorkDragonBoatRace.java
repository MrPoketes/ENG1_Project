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
import com.mygdx.game.entities.ExhaustionBox;
import com.mygdx.game.entities.leftCooldownBox;
import com.mygdx.game.entities.rightCooldownBox;
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

		engine.addEntity(new box(530,430, 100,20, "outlineBarHealth", Color.WHITE));
		engine.addEntity(new box(530,400, 100,20, "outlineBarExhaustion", Color.WHITE));
		engine.addEntity(new box(20,350, 20, 100, "outlineLeft", Color.WHITE));
		engine.addEntity(new box(60,350, 20, 100, "outlineRight", Color.WHITE));

		engine.addEntity(new healthBox(530,430, 100,20, "healthBar", Color.GREEN));
		engine.addEntity(new ExhaustionBox(530,400,100,20,"Exhaustion",Color.YELLOW));

		engine.addEntity(new leftCooldownBox(20,350, 20, 100, "leftCooldown", Color.RED));
		engine.addEntity(new rightCooldownBox(60,350, 20, 80, "rightCooldown", Color.RED));
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
