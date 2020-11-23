package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.graphics.Texture;

public class LineComponent implements Component {
    public static final ComponentMapper<BoxComponent> Map = ComponentMapper.getFor(BoxComponent.class);

    public Image lineImage;
    public LineComponent(String sprite) {
        Texture currentSprite = new Texture(sprite);
        this.lineImage = new Image(currentSprite);
    }
}