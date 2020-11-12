package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.Color;

public class Colour implements Component {
    public static final ComponentMapper<Colour> Map = ComponentMapper.getFor(Colour.class);


    private String colour;

    public Colour(String colour) { 
        this.colour = colour;
    }
}
