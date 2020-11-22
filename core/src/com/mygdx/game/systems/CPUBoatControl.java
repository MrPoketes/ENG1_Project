package com.mygdx.game.systems;


import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.mygdx.game.BoatCommon;
import com.mygdx.game.components.BoatStats;
import com.mygdx.game.components.Box2dBody;
import com.mygdx.game.components.CPUControlled;
import com.mygdx.game.components.DynamicBoatStats;

public class CPUBoatControl extends EntitySystem {

    private final Family family;

    public CPUBoatControl() {
        super();
        this.family = Family.all(CPUControlled.class, BoatStats.class, DynamicBoatStats.class).get();
    }

    @Override
    public void update(float deltaTime) {
        /*
        Placeholder AI.
        Rows as soon as the cooldown reaches 0 - essentially meaning it rows in a straight line,
        as fast as possible without exhausting itself.
        */
        for (Entity entity : this.getEngine().getEntitiesFor(family)) {
            if (entity.getComponent(DynamicBoatStats.class).rightCooldown == 0){
                BoatCommon.rowBoat(entity, true, true);
            }
            if (entity.getComponent(DynamicBoatStats.class).leftCooldown == 0){
                BoatCommon.rowBoat(entity, false, true);
                System.out.println(entity.getComponent(Box2dBody.class).body.getPosition().y);
            }
            BoatCommon.boatUpdate(entity);
        }
    }
}