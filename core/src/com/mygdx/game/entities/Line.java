package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.components.*;
import com.badlogic.gdx.physics.box2d.World;

import static com.mygdx.game.Constants.PIXELS_PER_METER;

public class Line extends Entity {

    public Line(World world, float posX, float posY, float sizeX, float sizeY, String sprite) {
        
        super();
        this.add(new ScreenPosition(0,-20, 0f));
        this.add(new Size((int) (PIXELS_PER_METER*sizeX), (int) (PIXELS_PER_METER*sizeY)));
        this.add(new Sprite(sprite));
        this.add(new Box2dBody(world, this, true, posX, posY, sizeX, sizeY,1f,true));
        this.add(new FinishLine());
    }
    
}