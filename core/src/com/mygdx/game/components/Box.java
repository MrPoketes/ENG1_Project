package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class Box implements Component {
    public static final ComponentMapper<Box> Map = ComponentMapper.getFor(Box.class);

    public String box;

    public Box(String box) {
        this.box = box;

    }
}
