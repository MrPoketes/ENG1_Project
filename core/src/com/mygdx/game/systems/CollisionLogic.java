package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.components.DynamicBoatStats;
import com.mygdx.game.components.FinishLine;
import com.mygdx.game.components.PlayerControlled;

public class CollisionLogic extends EntitySystem {

    World world;
    Signal moveToResultsSignal;

    public CollisionLogic(World world, Signal moveToResultsSignal){
        this.world = world;
        this.moveToResultsSignal = moveToResultsSignal;

    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
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
                        moveToResultsSignal.dispatch(null);
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
}
