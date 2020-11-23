package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class RightCooldown implements Component {
    public static final ComponentMapper<RightCooldown> Map = ComponentMapper.getFor(RightCooldown.class);

    public RightCooldown() {

    }
}