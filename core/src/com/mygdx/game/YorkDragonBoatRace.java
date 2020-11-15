package com.mygdx.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entities.Obstacle;
import com.mygdx.game.entities.PlayerBoat;
import com.mygdx.game.entities.TestEntity;
import com.mygdx.game.systems.RenderSprites;
import com.mygdx.game.systems.RenderText;
import com.mygdx.game.entities.TestText;
import com.mygdx.game.systems.UpdateRenderComponentsFromBody;

public class YorkDragonBoatRace extends ApplicationAdapter {
	Engine engine;
	SpriteBatch batch;
	Texture img;
	World world;
	
	@Override
	public void create () {
		world = new World(new Vector2(0,0), true);
		batch = new SpriteBatch();
		engine = new Engine();
		engine.addSystem(new RenderSprites(batch));
		engine.addSystem(new RenderText(batch));
		engine.addSystem(new UpdateRenderComponentsFromBody());
		//engine.addEntity(new TestEntity(50, 200));
		//engine.addEntity(new TestText(200,300,"Hello world"));
		engine.addEntity(new PlayerBoat(world, 3, 2, 50, 150, "badlogic.jpg", "testBoat", 10f, 10f, 10f, 10));
		engine.addEntity(new Obstacle(world, 4, -2, 50, 50, 0, "Tree Branch.jpg"));

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
