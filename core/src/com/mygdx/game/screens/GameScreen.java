package com.mygdx.game.screens;
import com.mygdx.game.YorkDragonBoatRace;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entities.*;
import com.mygdx.game.systems.*;
import com.mygdx.game.components.*;

import static com.badlogic.gdx.Gdx.graphics;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.utils.BoatData;
import com.mygdx.game.utils.Constants;

public class GameScreen extends AbstractScreen {

    private Engine engine;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    World world;
    private Stage stage;
    private ShapeRenderer shapeRenderer;
    //List of available boats. Used to ensure no duplicates are taken.
    private List<Integer> boatNames = new ArrayList<Integer>(){{
        add(0);
        add(1);
        add(2);
        add(3);
        add(4);
        add(5);
    }};

    public GameScreen(YorkDragonBoatRace game, int legNumber, int selectedBoat){
        super(game);
		world = new World(new Vector2(0,0), true);
		batch = new SpriteBatch();
        engine = new Engine();
        shapeRenderer = new ShapeRenderer();

        initialiseSystems(engine);
        //Create the finish line.
        engine.addEntity(new Line(world,0, Constants.RACE_LENGTH,10f,0.1f, "lines/checkpoint.png"));
        //Create the edges.
        engine.addEntity(new Obstacle(world, -Constants.RACE_WIDTH, Constants.RACE_LENGTH/2, Constants.RACE_WIDTH, Constants.RACE_LENGTH*1.5f, 0f, 0f, "lines/riverEdge.png"));
        engine.addEntity(new Obstacle(world, +Constants.RACE_WIDTH, Constants.RACE_LENGTH/2, Constants.RACE_WIDTH, Constants.RACE_LENGTH*1.5f, 0f, 0f, "lines/riverEdge.png"));
        //Create the player's boat.
        engine.addEntity(new PlayerBoat( world, -1f, 0, BoatData.getEntityData().get(selectedBoat)));
        boatNames.remove((Integer) selectedBoat);

        cpuSetup();
        createCollisionListener();
        addSystemBars();
        obstacleSetup(legNumber);
    }
    @Override
    public void resize(int width,int height){
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
    }

    /*
    Helper function to initialise all the systems.
     */
    private void initialiseSystems(Engine engine){
        engine.addSystem(new RenderSprites(this.batch));
		engine.addSystem(new RenderText(this.batch));
		engine.addSystem(new UpdateRenderComponentsFromBody());
		engine.addSystem(new PhysicsUpdate(this.world));
		engine.addSystem(new PlayerBoatControl());
        engine.addSystem(new CPUBoatControl());
        engine.addSystem(new RenderBoxes(this.shapeRenderer));
        engine.addSystem(new HealthBarSystem());
        engine.addSystem(new ExhaustionBarSystem());
        engine.addSystem(new CooldownBarSystem());
    }
    /*
    * Creates CPU boats.
    * Should be called after the player's boat has been created to avoid duplicates.
    */
    private void cpuSetup(){
        Random rand = new Random();
        float posX = -Constants.RACE_WIDTH/2+1;
        
        for(int i=0;i<=4;i++){
            //skip over the player's boat
            if(posX==-1f){
                posX+=1f;
            }
            int selectedBoatIndex = rand.nextInt(boatNames.size());
            engine.addEntity(new CPUBoat( world, posX, 0, BoatData.getEntityData().get(boatNames.get(selectedBoatIndex))));
            boatNames.remove(selectedBoatIndex);
            posX+=1f;
        }
    }

    // Adds all HUD entities (health/exhaustion/cooldown bars).
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

    /*
    Returns a random appropriate obstacle sprite, given if the obstacle is static or not.
     */
    private String getObstacleSprite(boolean isStatic){
        Random random = new Random();
        List<String> options;
        if (isStatic){
            options = new ArrayList<String>(){{
                add("obstacles/rock1.png");
                add("obstacles/rock2.png");
                add("obstacles/rock3.png");
            }};
        }
        else{
            options = new ArrayList<String>(){{
                add("obstacles/branch1.png");
                //add("obstacles/branch2.png"); //branch2 is too long and looks like a rock when squished
                add("obstacles/branch3.png");
            }};
        }
        return options.get(random.nextInt(options.size()));
    }

    /*
    Populates the map with obstacles.
    To ensure obstacles don't collide, the map is split into rows of 0.3m height.
        Only one obstacle can be placed on any given row.
        Higher legNumbers will mean more rows have obstacles, more obstacles will move, and moving obstacles will move faster.
     */
    private void obstacleSetup(int legNumber){
        Random random = new Random();
        for(float i = 1f; i < Constants.RACE_LENGTH; i += 0.3f){
            //Place obstacle?
            if (random.nextInt(10) <= legNumber){
                //Is obstacle moving?
                float movementSpeed = 0f;
                if (random.nextInt(10) <= legNumber){
                    movementSpeed = random.nextFloat() * 0.2f * legNumber;
                    if (random.nextBoolean()) movementSpeed *= -1;
                }
                //River is 8m wide, centered on 0.
                float posX = (random.nextFloat() * Constants.RACE_WIDTH) - Constants.RACE_WIDTH/2;
                String sprite = getObstacleSprite(movementSpeed == 0f);
                engine.addEntity(new Obstacle(this.world, posX, i, 0.2f, 0.2f, movementSpeed, 0, sprite));
            }
        }
    }

    //Collision handling logic:
        //Ignore collisions that do not contain a boat.
        //If the collision is with the finish line, stop the timer, and if it is the player boat, end the race.
        //If the collision is with anything else, reduce health of all involved boats.
    private void createCollisionListener() {
        world.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {
                //Once a collision is detected, identify the participating entities.
                //Userdata for our bodies is always an entity, so this cast is safe.
                Entity entityA = (Entity) contact.getFixtureA().getBody().getUserData();
                Entity entityB = (Entity) contact.getFixtureB().getBody().getUserData();

                boolean isEntityABoat = entityA.getComponent(DynamicBoatStats.class) != null;
                boolean isEntityBBoat = entityB.getComponent(DynamicBoatStats.class) != null;
                boolean isInvolvingPlayer = (entityA.getComponent(PlayerControlled.class) != null
                        || entityB.getComponent(PlayerControlled.class) != null);
                boolean isFinishLine = (entityA.getComponent(FinishLine.class) != null
                        || entityB.getComponent(FinishLine.class) != null);

                System.out.println("Collision occurred.");
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
                    else{
                        System.out.println("CPU finished race.");
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
