package com.mygdx.game.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.components.Box2dBody;
import com.mygdx.game.components.FixedVelocity;

public class PhysicsUpdate extends EntitySystem {

    private final Family family;
    private final World world;


    public PhysicsUpdate(World world) {
        super();
        this.world = world;
        this.family = Family.all(Box2dBody.class).get();
    }

    //Sets all FixedVelocity objects to their velocity.
    //Runs a physics tick on all objects.
    @Override
    public void update(float deltaTime) {
        Family fixedVelocityEntities = Family.all(Box2dBody.class, FixedVelocity.class).get();

        for (Entity entity : this.getEngine().getEntitiesFor(fixedVelocityEntities)) {
            float velocityX = entity.getComponent(FixedVelocity.class).velocityX;
            float velocityY = entity.getComponent(FixedVelocity.class).velocityY;
            entity.getComponent(Box2dBody.class).body.setLinearVelocity(new Vector2(velocityX, velocityY));
        }

        world.step(deltaTime, 6, 2);



    }
}
