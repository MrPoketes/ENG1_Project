package com.mygdx.game.screens;
import com.mygdx.game.YorkDragonBoatRace;
import com.mygdx.game.utils.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entities.*;
import com.mygdx.game.systems.*;
import static com.badlogic.gdx.Gdx.graphics;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends AbstractScreen {

    private Engine engine;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    World world;

    // This variable is used to determine what boats will the CPU have.
    private List<String> boatNames = new ArrayList<String>();
    

    public GameScreen(YorkDragonBoatRace game, String selectedBoat){
        super(game);
		world = new World(new Vector2(0,0), true);
		batch = new SpriteBatch();
        engine = new Engine();

        boatNames.add("Cyan");
        boatNames.add("Brown");
        boatNames.add("Red");
        boatNames.add("White");
        boatNames.add("Pink");
        boatNames.add("Green");

        addingSystems();
        playerSetup(selectedBoat);
        cpuSetup();
        // obstacleSetup();
		// engine.addEntity(new Obstacle(world, 0.4f, -0.1f, 0.1f, 0.1f, 0f, -0.05f, "Tree Branch.jpg"));
		// engine.addEntity(new Obstacle(world, -0.6f, 0.5f, 0.1f, 0.1f, 0f, 0f, "Tree Branch.jpg"));
		// engine.addEntity(new Obstacle(world, 0.6f, 0f, 0.1f, 0.1f, 0f, 0.05f, "Tree Branch.jpg"));
		// engine.addEntity(new Obstacle(world, -0.2f, 0.6f, 0.1f, 0.1f, 0.1f, 0f, "Tree Branch.jpg"));
    }
    private void addingSystems(){
        engine.addSystem(new RenderSprites(batch));
		engine.addSystem(new RenderText(batch));
		engine.addSystem(new UpdateRenderComponentsFromBody());
		engine.addSystem(new PhysicsUpdate(world));
		engine.addSystem(new PlayerBoatControl());
		engine.addSystem(new CPUBoatControl());
    }
    private void playerSetup(String selectedBoat){
        /*
        * Every boat should have different stats
        */
        if(selectedBoat == "Cyan"){
            engine.addEntity(new PlayerBoat(world, 0, 0, 1f, 1f, "boats/cyanBoat.png", selectedBoat, 3f, 5f, 4f, 10));
        }
        else if(selectedBoat == "Brown"){
            engine.addEntity(new PlayerBoat(world, 0, 0, 1f, 1f, "boats/brownBoat.png", selectedBoat, 1.5f, 3f, 5f, 10));
        }
        else if(selectedBoat == "Red"){
            engine.addEntity(new PlayerBoat(world, 0, 0, 1f, 1f, "boats/redBoat.png", selectedBoat, 1.5f, 3f, 5f, 10));
        }
        else if(selectedBoat == "White"){
            engine.addEntity(new PlayerBoat(world, 0, 0, 1f, 1f, "boats/whiteBoat.png", selectedBoat, 1.5f, 3f, 5f, 10));
        }
        else if(selectedBoat == "Pink"){
            engine.addEntity(new PlayerBoat(world, 0, 0, 1f, 1f, "boats/pinkBoat.png", selectedBoat, 1.5f, 3f, 5f, 10));
        }
        else if(selectedBoat == "Green"){
            engine.addEntity(new PlayerBoat(world, 0, 0,1f, 1f, "boats/greenBoat.png", selectedBoat, 1.5f, 3f, 5f, 10));
        }
        boatNames.remove(selectedBoat);

    }
    private void cpuSetup(){
        Random rand = new Random();
        float x = -4f;
        
        for(int i=0;i!=4;i++){
            if(x==0f){
                x+=2f;
            }
            int n = rand.nextInt(boatNames.size()-1);
            if(boatNames.get(n) == "Cyan"){
                engine.addEntity(new CPUBoat(world, x, 0, 1f, 1f, "boats/cyanBoat.png", boatNames.get(n), 3f, 5f, 4f, 10));
            }
            else if(boatNames.get(n) == "Brown"){
                engine.addEntity(new CPUBoat(world, x, 0, 1f, 1f, "boats/brownBoat.png", boatNames.get(n), 1.5f, 3f, 5f, 10));
            }
            else if(boatNames.get(n) == "Green"){
                engine.addEntity(new CPUBoat(world, x, 0, 1f, 1f, "boats/greenBoat.png", boatNames.get(n), 1.5f, 3f, 5f, 10));
            }
            else if(boatNames.get(n) == "Pink"){
                engine.addEntity(new CPUBoat(world, x, 0, 1f, 1f, "boats/pinkBoat.png", boatNames.get(n), 1.5f, 3f, 5f, 10));
            }
            else if(boatNames.get(n) == "Red"){
                engine.addEntity(new CPUBoat(world, x, 0, 1f, 1f, "boats/redBoat.png", boatNames.get(n), 1.5f, 3f, 5f, 10));
            }
            else if(boatNames.get(n) == "White"){
                engine.addEntity(new CPUBoat(world, x, 0, 1f, 1f, "boats/whiteBoat.png", boatNames.get(n), 1.5f, 3f, 5f, 10));
            }
            x+=2f;
            boatNames.remove(n);
        }
    }
    @Override
    public void render(float delta){
        super.render(delta);
        engine.update(graphics.getDeltaTime());
    }
}
