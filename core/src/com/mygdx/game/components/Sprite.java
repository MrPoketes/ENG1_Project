package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class Sprite implements Component {
    public static final ComponentMapper<Sprite> Map = ComponentMapper.getFor(Sprite.class);

    public String currentSprite;

    public Sprite(String currentSprite) {
        this.currentSprite = currentSprite;
    }
}