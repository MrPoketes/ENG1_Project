package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Constants;
import com.mygdx.game.components.*;

public class Obstacle extends Entity {

    public Obstacle(World world, Integer posX, Integer posY, Integer sizeX, Integer sizeY, float fixedVelocity, String sprite) {
        super();

        this.add(new ScreenPosition(0,0, 0f));
        this.add(new Size(sizeX, sizeY));
        this.add(new Sprite(sprite));
        //If an obstacle is created with a fixedVelocity of 0, it is made static.
        if (fixedVelocity == 0){
            this.add(new Box2dBody(world, true, posX, posY, sizeX, sizeY));
        }
        else{
            this.add(new Box2dBody(world, false, posX, posY, sizeX, sizeY, 1));
        }
        this.add(new FixedVelocity(fixedVelocity));

    }
}
