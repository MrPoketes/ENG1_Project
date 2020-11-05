package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class ScreenPosition implements Component {
    public static final ComponentMapper<ScreenPosition> Map = ComponentMapper.getFor(ScreenPosition.class);

    public Integer x, y;
    public Float rotation;

    public ScreenPosition(Integer x, Integer y, Float rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }
}