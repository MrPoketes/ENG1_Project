package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Constants;
import com.mygdx.game.components.*;

public class PlayerBoat extends Entity {

    public PlayerBoat(World world, Integer posX, Integer posY, Integer sizeX, Integer sizeY, String sprite,
                      String name, Float topSpeed, Float acceleration, Float maneuverability, Integer robustness) {
        super();
        //render related components
        this.add(new ScreenPosition(0,0, 0f));
        this.add(new Size(sizeX, sizeY));
        this.add(new Sprite(sprite));

        this.add(new Box2dBody(world, false, posX, posY, sizeX, sizeY, 1));
        this.add(new BoatStats(name, topSpeed, acceleration, maneuverability, robustness));
        this.add(new DynamicBoatStats(robustness));
        this.add(new PlayerControlled());

    }
}
