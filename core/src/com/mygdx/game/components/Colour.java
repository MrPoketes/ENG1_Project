package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.Color;

public class ScreenPosition implements Component {
    public static final ComponentMapper<Colour> Map = ComponentMapper.getFor(Colour.class);

    public Colour(String colour) { 
        this.colour = Color colour
    }
