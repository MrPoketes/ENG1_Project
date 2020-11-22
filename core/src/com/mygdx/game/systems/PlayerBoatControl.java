package com.mygdx.game.systems;


import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.BoatCommon;
import com.mygdx.game.components.*;

import java.util.Random;

public class PlayerBoatControl extends EntitySystem {

    private final Family family;
    private boolean qDown = false;
    private boolean aDown = false;
    private boolean eDown = false;
    private boolean dDown = false;

    public PlayerBoatControl() {
        super();
        this.family = Family.all(PlayerControlled.class, BoatStats.class, DynamicBoatStats.class).get();
    }

    @Override
    public void update(float deltaTime) {
        //first ensure the player only has one boat
        assert(this.getEngine().getEntitiesFor(family).size() <= 1);
        if (this.getEngine().getEntitiesFor(family).size() == 1){
            Entity playerBoat = this.getEngine().getEntitiesFor(family).first();

            /*
            For all of Q, A, E and D, row the boat in the corresponding direction if the key is pressed, but not if it
            was pressed last frame.
            No, it's completely impossible that there might potentially be a better way of writing this.
            */
            if (Gdx.input.isKeyPressed(Input.Keys.Q)){
                if (!qDown) BoatCommon.rowBoat(playerBoat, false, true);
                qDown = true;
            }
            else qDown = false;

            if (Gdx.input.isKeyPressed(Input.Keys.A)){
                if (!aDown) BoatCommon.rowBoat(playerBoat, false, false);
                aDown = true;
            }
            else aDown = false;

            if (Gdx.input.isKeyPressed(Input.Keys.E)){
                if (!eDown) eDown = true;
                BoatCommon.rowBoat(playerBoat, true, true);
                eDown = true;
            }
            else eDown = false;

            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                if (!dDown) dDown = true;
                BoatCommon.rowBoat(playerBoat, true, false);
                dDown = true;
            }
            else dDown = false;

            BoatCommon.boatUpdate(playerBoat);
        }
    }
}