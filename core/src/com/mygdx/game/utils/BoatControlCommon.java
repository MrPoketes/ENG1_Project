package com.mygdx.game.utils;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.components.BoatStats;
import com.mygdx.game.components.Box2dBody;
import com.mygdx.game.components.DynamicBoatStats;

/*
Class that has a handful of common functions used by PlayerBoatControl and AIBoatControl.

 */
public class BoatControlCommon {

    /*
    Small helper function to check if the boat has lost all its health.
     */
    public static boolean isBoatDead(Entity boat) {
        return (boat.getComponent(DynamicBoatStats.class).health <= 0);
    }

    /*
    To be run every game tick, this updates things like the boat's cooldowns and the exhaustion.
    Dead boats don't update, because they're dead.
     */
    public static void boatUpdate(Entity boat) {
        if (isBoatDead(boat)){
            return;
        }
        DynamicBoatStats dynamicBoatStats = boat.getComponent(DynamicBoatStats.class);

        dynamicBoatStats.rightCooldown = Math.max(0, dynamicBoatStats.rightCooldown -1);
        dynamicBoatStats.leftCooldown = Math.max(0, dynamicBoatStats.leftCooldown -1);
        dynamicBoatStats.damageDebounce = Math.max(0, dynamicBoatStats.damageDebounce -1);
        //Exhaustion goes to a minimum of 0.3, and reduces by 0.01% every tick.
        dynamicBoatStats.exhaustion = Math.max(0.3f, dynamicBoatStats.exhaustion*0.9999);

        if (!dynamicBoatStats.isFinished) {
            dynamicBoatStats.time += 1;
        }
    }

    /*
    Identical to propelBoat, but also checks and updates cooldowns and exhaustion.
    Returns whether the row was performed (if the cooldown is above 60 or the boat is dead, rowing is not allowed).
     */
    public static boolean rowBoat(Entity boat, boolean isRight, boolean isForwards) {
        if (isBoatDead(boat)){
            return false;
        }
        DynamicBoatStats dynamicBoatStats = boat.getComponent(DynamicBoatStats.class);
        Integer cooldown;
        if (isRight) cooldown = dynamicBoatStats.rightCooldown;
        else cooldown = dynamicBoatStats.leftCooldown;

        if (cooldown < 60){
            propelBoat(boat, isRight, isForwards);
            //exhaustion increase is relative to remaining cooldown above 0
            dynamicBoatStats.exhaustion *= 1 - ((double) cooldown) / 10000;
            if (isRight) dynamicBoatStats.rightCooldown = 120;
            else dynamicBoatStats.leftCooldown = 120;
            return true;
        }
        return false;
    }

    /*
    Propels the boat - applies an angular and directional impulse as if it was rowed.
    Takes into account boat acceleration & size (vertical impulse magnitude), maneuverability (angular impulse magnitude), and exhaustion.
    If the boat is above it's "top" speed, the directional boost will be heavily reduced (no boost makes it look broken).

    Unless you're intentionally allowing the boat to cheat, rowBoat should be used instead.
    */
    public static void propelBoat(Entity boat, boolean isRight, boolean isForwards) {
        Body body = boat.getComponent(Box2dBody.class).body;
        BoatStats boatStats = boat.getComponent(BoatStats.class);
        DynamicBoatStats dynamicBoatStats = boat.getComponent(DynamicBoatStats.class);

        double directionalImpulseMagnitude = 0.1f; //base
        if (!isForwards){directionalImpulseMagnitude *= -1;} //invert if rowing backwards
        directionalImpulseMagnitude *= boatStats.acceleration;
        directionalImpulseMagnitude *= body.getMass(); //account for mass of boat
        directionalImpulseMagnitude *= dynamicBoatStats.exhaustion;
        //reduce boost if boat is at top speed
        if (body.getLinearVelocity().len() > boatStats.topSpeed * dynamicBoatStats.exhaustion){
            directionalImpulseMagnitude *= 0.2;
        }
        //convert to world's frame of reference (so a sideways pointing boat pushes itself sideways)
        Vector2 direction = body.getWorldVector(new Vector2(0, (float) directionalImpulseMagnitude));

        double angularImpulseMagnitude = 0.05f; //base
        //Using XOR, since left backwards cancels itself out.
        if (!isRight ^ !isForwards){angularImpulseMagnitude *= -1;}
        angularImpulseMagnitude *= boatStats.maneuverability;
        angularImpulseMagnitude *= body.getMass()*body.getMass();
        angularImpulseMagnitude *= dynamicBoatStats.exhaustion;

        Vector2 pos = body.getPosition();
        body.applyLinearImpulse(direction, pos, true);
        body.applyAngularImpulse((float) angularImpulseMagnitude, true);
    }
}
