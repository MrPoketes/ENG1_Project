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
import com.mygdx.game.systems.RenderBoxes;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.screens.MainMenu;

public class YorkDragonBoatRace extends ApplicationAdapter {
	private Stage mainMenuStage;
	private Engine engine;
	SpriteBatch batch;
	Texture img;
	ShapeRenderer shape;
	Color Green = Color.GREEN; 
// asdsdffdg
// sdffdgdrgdrg
	@Override
	public void create () {
		batch = new SpriteBatch();
		engine = new Engine();
		engine.addSystem(new RenderSprites(batch));
		engine.addSystem(new RenderText(batch));
		engine.addEntity(new TestEntity(50, 200));
		engine.addEntity(new TestText(200,300,"Hello world"));

		// batch = new SpriteBatch();
		// shape = new ShapeRenderer();
		// engine = new Engine();
		// engine.addSystem(new RenderSprites(batch));
		// engine.addSystem(new RenderText(batch));
		// engine.addSystem(new RenderBoxes(shape));
		// engine.addEntity(new TestEntity(50, 200));
		// engine.addEntity(new TestText(200,300,"Hello world"));
		// engine.addEntity(new box(530,430, 80,20, "healthBar", Green));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mainMenuStage.draw();
	}
	
	@Override
	public void dispose () {
		mainMenuStage.dispose();
		batch.dispose();
		img.dispose();
	}
}
