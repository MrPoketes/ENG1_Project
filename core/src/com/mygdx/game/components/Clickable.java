package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Rectangle;

public class Clickable implements Component {

    public static final ComponentMapper<Clickable> Map = ComponentMapper.getFor(Clickable.class);
    public Rectangle bounds;
    public boolean clicked;

    public Clickable(float x, float y, float width, float height) {
        this.bounds = new Rectangle(x,y,width,height);
        this.clicked = false;
    }
}
