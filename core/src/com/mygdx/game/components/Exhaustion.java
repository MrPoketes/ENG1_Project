package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class Exhaustion implements Component {
    public static final ComponentMapper<Box> Map = ComponentMapper.getFor(Box.class);

    public Exhaustion() {

    }
}