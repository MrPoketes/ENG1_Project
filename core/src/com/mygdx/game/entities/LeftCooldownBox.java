package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.components.ScreenPosition;
import com.mygdx.game.components.Size;
import com.mygdx.game.components.Sprite;
import com.mygdx.game.components.BoxComponent;
import com.mygdx.game.components.Colour;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.components.HealthBar;
import com.mygdx.game.components.LeftCooldown;

public class LeftCooldownBox extends Entity {

    public LeftCooldownBox(Integer x, Integer y, Integer width, Integer length, String name, Color colour) { 
        this.add(new Size(width, length));
        this.add(new ScreenPosition(x,y, 0f));
        this.add(new BoxComponent(name));
        this.add(new Colour(colour));
        this.add(new LeftCooldown());
    }


}