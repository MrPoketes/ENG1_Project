package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Constants;
import com.mygdx.game.components.*;

//may want to come up with a better name
public class UpdateRenderComponentsFromBody extends EntitySystem {

    private final Family family;


    public UpdateRenderComponentsFromBody () {
        super();
        this.family = Family.all(ScreenPosition.class, Size.class, Box2dBody.class).get();
    }

    //Sets the values in the ScreenPosition component to match the ones in Box2dBody.
    //Maybe put this inside the physics step to avoid race conditions.
    @Override
    public void update(float deltaTime) {

        //get the player's boat (assumes there is only one)
        int coordinateOffsetX;
        int coordinateOffsetY;
        if (!Constants.NON_RELATIVE_CAMERA) {
            //to centre the "camera" on the player, all physical objects are rendered offset by the player's boat
            //and the centre of the screen
            Body playerBoat = this.getEngine().getEntitiesFor(Family.all(PlayerControlled.class).get()).first().getComponent(Box2dBody.class).body;
            coordinateOffsetX = (int) (-playerBoat.getPosition().x * Constants.PIXELS_PER_METER + (Gdx.graphics.getWidth() / 2));
            coordinateOffsetY = (int) (-playerBoat.getPosition().y * Constants.PIXELS_PER_METER + (Gdx.graphics.getHeight() / 2));
        }
        else{
            coordinateOffsetX = (int) (Gdx.graphics.getWidth() / 2);
            coordinateOffsetY = (int) (Gdx.graphics.getHeight() / 2);
        }

        for (Entity entity : this.getEngine().getEntitiesFor(family)) {
            Body body = entity.getComponent(Box2dBody.class).body;
            ScreenPosition screenPosition = entity.getComponent(ScreenPosition.class);
            Size size = entity.getComponent(Size.class);
            screenPosition.x = (int) (body.getPosition().x * Constants.PIXELS_PER_METER + coordinateOffsetX - size.x/2);
            screenPosition.y = (int) (body.getPosition().y * Constants.PIXELS_PER_METER + coordinateOffsetY - size.y/2);
            screenPosition.rotation = (float) (body.getAngle() * 180 / 3.14);
        }
    }
}
