package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.Texture;

public class Sprite implements Component {
    public static final ComponentMapper<Sprite> Map = ComponentMapper.getFor(Sprite.class);

    public Texture currentSprite;

    public Sprite(String spritePath) {
        this.currentSprite = new Texture(spritePath);
    }
}