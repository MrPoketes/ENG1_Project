package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class Size implements Component {
    public static final ComponentMapper<Size> Map = ComponentMapper.getFor(Size.class);

    public Integer x, y;

    public Size(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }
}        