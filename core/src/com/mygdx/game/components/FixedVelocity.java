package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class FixedVelocity implements Component  {
    public static final ComponentMapper<FixedVelocity> Map = ComponentMapper.getFor(FixedVelocity.class);

    public Float velocityX;
    public Float velocityY;

    public FixedVelocity(Float velocityX, Float velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }
}
