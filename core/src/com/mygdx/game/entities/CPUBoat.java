package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Constants;
import com.mygdx.game.components.*;

public class CPUBoat extends Entity {

    public CPUBoat(World world, float posX, float posY, float sizeX, float sizeY, String sprite,
                   String name, Float topSpeed, Float acceleration, Float maneuverability, Integer robustness) {
        super();
        //render related components
        //screen position is set on first physics tick
        this.add(new ScreenPosition(0,0, 0f));
        this.add(new Size((int) (sizeX * Constants.PIXELS_PER_METER), (int) (sizeY * Constants.PIXELS_PER_METER)));
        this.add(new Sprite(sprite));

        this.add(new Box2dBody(world, false, posX, posY, sizeX, sizeY, 1));
        this.add(new BoatStats(name, topSpeed, acceleration, maneuverability, robustness));
        this.add(new DynamicBoatStats(robustness));
        this.add(new CPUControlled());

    }
}
