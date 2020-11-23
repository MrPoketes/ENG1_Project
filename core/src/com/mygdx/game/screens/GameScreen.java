package com.mygdx.game.screens;
import com.mygdx.game.YorkDragonBoatRace;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entities.*;
import com.mygdx.game.systems.*;

public class GameScreen extends AbstractScreen {

    Engine engine;
    SpriteBatch batch;
    Texture img;
    World world;

    public GameScreen(YorkDragonBoatRace game){
        super(game);
        world = new World(new Vector2(0,0), true);
        batch = new SpriteBatch();
        engine = new Engine();
        engine.addSystem(new RenderSprites(batch));
        engine.addSystem(new RenderText(batch));
        engine.addSystem(new UpdateRenderComponentsFromBody());
        engine.addSystem(new PhysicsUpdate(world));
        engine.addSystem(new PlayerBoatControl());
        engine.addSystem(new CPUBoatControl());
        //engine.addEntity(new TestEntity(50, 200));
        //engine.addEntity(new TestText(200,300,"Hello world"));
        engine.addEntity(new PlayerBoat(world, 0, 0, 0.1f, 0.3f, "badlogic.jpg", "testBoat", 1.5f, 3f, 5f, 10));
        engine.addEntity(new CPUBoat(world, -0.4f, 0, 0.1f, 0.3f, "badlogic.jpg", "testBoat", 1.5f, 3f, 5f, 10));
        engine.addEntity(new Obstacle(world, 0.4f, -0.1f, 0.1f, 0.1f, 0f, -0.05f, "Tree Branch.jpg"));
        engine.addEntity(new Obstacle(world, -0.6f, 0.5f, 0.1f, 0.1f, 0f, 0f, "Tree Branch.jpg"));
        engine.addEntity(new Obstacle(world, 0.6f, 0f, 0.1f, 0.1f, 0f, 0.05f, "Tree Branch.jpg"));
        engine.addEntity(new Obstacle(world, -0.2f, 0.6f, 0.1f, 0.1f, 0.1f, 0f, "Tree Branch.jpg"));

    }



    @Override
    public void render(float delta) {
        engine.update(delta);
    }

    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}
