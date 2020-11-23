package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Constants;
import com.mygdx.game.components.*;

public class Obstacle extends Entity {

    public Obstacle(World world, float posX, float posY, float sizeX, float sizeY, float fixedVelocityX, float fixedVelocityY, String sprite) {
        super();

        this.add(new ScreenPosition(0,0, 0f));
        this.add(new Size((int) (sizeX * Constants.PIXELS_PER_METER), (int) (sizeY * Constants.PIXELS_PER_METER)));
        this.add(new Sprite(sprite));
        //If an obstacle is created with a fixedVelocity of 0, it is made static.
        if ((fixedVelocityX == 0) & (fixedVelocityY == 0)){
            this.add(new Box2dBody(world, this, true, posX, posY, sizeX, sizeY));
        }
        else{
            this.add(new Box2dBody(world, this, false, posX, posY, sizeX, sizeY, 1));
        }
        this.add(new FixedVelocity(fixedVelocityX, fixedVelocityY));

    }
}
