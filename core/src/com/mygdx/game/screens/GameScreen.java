package com.mygdx.game.screens;
import com.badlogic.ashley.core.Family;
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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entities.*;
import com.mygdx.game.systems.*;
import com.mygdx.game.components.*;
import com.badlogic.gdx.Input;
import static com.badlogic.gdx.Gdx.graphics;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.screens.LeaderboardScreen;

public class GameScreen extends AbstractScreen {

    private Engine engine;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    World world;
    private Stage stage;
    private ShapeRenderer shape;
    // This variable is used to determine what boats will the CPU have.
    private List<String> boatNames = new ArrayList<String>();
    private Entity playerBoat;
    private long startTime;
    private List<Long> time = new ArrayList<Long>();

    public GameScreen(YorkDragonBoatRace game, String selectedBoat){
        super(game);
		world = new World(new Vector2(0,0), true);
		batch = new SpriteBatch();
        engine = new Engine();
        shape = new ShapeRenderer();
        boatNames.add("Cyan");
        boatNames.add("Brown");
        boatNames.add("Red");
        boatNames.add("White");
        boatNames.add("Pink");
        boatNames.add("Green");

        addingSystems();
        engine.addEntity(new Line(world,0,20,1,1, "lines/checkpoint.png"));
        playerSetup(selectedBoat);
        cpuSetup();
        createCollisionListener();
        addSystemBars();
        obstacleSetup();
        startTime = System.currentTimeMillis();
    }
    @Override
    public void resize(int width,int height){
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }
    private void addingSystems(){
        engine.addSystem(new RenderSprites(batch));
		engine.addSystem(new RenderText(batch));
		engine.addSystem(new UpdateRenderComponentsFromBody());
		engine.addSystem(new PhysicsUpdate(world));
		engine.addSystem(new PlayerBoatControl());
        engine.addSystem(new CPUBoatControl());
        engine.addSystem(new RenderBoxes(shape));
        engine.addSystem(new HealthBarSystem());
        engine.addSystem(new ExhaustionBarSystem());
        engine.addSystem(new CooldownBarSystem());
    }
    /*
    * Method to assign a boat to the player from his selection in the MainMenu screen
    */
    private void playerSetup(String selectedBoat){
        /*
        * Every boat should have different stats
        */
        if(selectedBoat == "Cyan"){
            // playerBoat = new PlayerBoat(world, 0, 0, 1f, 1f, "boats/cyanBoat.png", selectedBoat, 3f, 5f, 4f, 10);
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
    /*
    * Method to assign boats to cpu. 
    * After the player has selected his boat, 4 random boats will be chosen for cpu.
    */
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
    // Method to draw the Health, exhaustion and cooldown bars.
    // Currently not functional 
    private void addSystemBars(){
        engine.addEntity(new Box(525,755, 510,30, "outlineBarHealth", Color.WHITE));
		engine.addEntity(new Box(525,715, 510,30, "outlineBarExhaustion", Color.WHITE));
		engine.addEntity(new Box(725,175, 30, 110, "outlineLeft", Color.WHITE));
		engine.addEntity(new Box(765,175, 30, 110, "outlineRight", Color.WHITE));

		engine.addEntity(new HealthBox(530,760, 500,20, "healthBar", Color.GREEN));
		engine.addEntity(new ExhaustionBox(530,720,500,20,"Exhaustion",Color.ORANGE));

		engine.addEntity(new LeftCooldownBox(730,180, 20, 100, "leftCooldown", Color.RED));
		engine.addEntity(new RightCooldownBox(770,180, 20, 80, "rightCooldown", Color.RED));
    }
    // Method to draw obstacles randomly in the map
    // Not functional
    private void obstacleSetup(){

    }

    private void createCollisionListener() {
        world.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {
                //Once a collision is detected, identify the participating entities.
                //Userdata for our bodies is always an entity, so this cast is safe.
                Entity entityA = (Entity) contact.getFixtureA().getBody().getUserData();
                Entity entityB = (Entity) contact.getFixtureB().getBody().getUserData();

                //Collision handling logic:
                    //Ignore collisions that do not contain a boat.
                    //If the collision is with the finish line, stop the timer, and if it is the player boat, end the race.
                    //If the collision is with anything else, reduce health of all involved boats.


                boolean isEntityABoat = entityA.getComponent(DynamicBoatStats.class) != null;
                boolean isEntityBBoat = entityB.getComponent(DynamicBoatStats.class) != null;
                boolean isInvolvingPlayer = (entityA.getComponent(PlayerControlled.class) != null
                        || entityB.getComponent(PlayerControlled.class) != null);
                boolean isFinishLine = (entityA.getComponent(FinishLine.class) != null
                        || entityB.getComponent(FinishLine.class) != null);

                //No special logic needs to occur if it's not a collision between boats.
                if (!isEntityABoat && !isEntityBBoat){
                    return;
                }
                if (isFinishLine){
                    if (isEntityABoat){
                        entityA.getComponent(DynamicBoatStats.class).isFinished = true;
                    }
                    if (isEntityBBoat){
                        entityB.getComponent(DynamicBoatStats.class).isFinished = true;
                    }
                    if (isInvolvingPlayer){
                        game.setScreen(new LeaderboardScreen(game));
                    }
                }
                //Non-finish line collisions are collisions with obstacles or other boats.
                else {
                    //If the boat has taken damage too recently (damageDebounce is above 0), do not damage it.
                    if (isEntityABoat && entityA.getComponent(DynamicBoatStats.class).damageDebounce == 0){
                        entityA.getComponent(DynamicBoatStats.class).health -= 1;
                        entityA.getComponent(DynamicBoatStats.class).damageDebounce = 180;
                    }
                    if (isEntityBBoat && entityB.getComponent(DynamicBoatStats.class).damageDebounce == 0){
                        entityB.getComponent(DynamicBoatStats.class).health -= 1;
                        entityB.getComponent(DynamicBoatStats.class).damageDebounce = 180;
                    }
                }
            }
            //not using any of these
            @Override
            public void endContact(Contact contact){}
            @Override
            public void preSolve(Contact contact, Manifold manifold){}
            @Override
            public void postSolve(Contact contact, ContactImpulse contactImpulse){}

        });
    }

    @Override
    public void render(float delta){
        super.render(delta);
        engine.update(graphics.getDeltaTime());
    }
}
