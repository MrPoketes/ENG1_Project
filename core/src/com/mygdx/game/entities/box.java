package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.components.ScreenPosition;
import com.mygdx.game.components.Size;
import com.mygdx.game.components.Sprite;
import com.mygdx.game.components.TestComponent;
import com.mygdx.game.components.Box;
import com.mygdx.game.components.Colour;
import com.badlogic.gdx.graphics.Color;

public class box extends Entity {


    public box(Integer x, Integer y, Integer width, Integer length, String name, Color colour) { 
        
        this.add(new Size(width, length));
        this.add(new ScreenPosition(x,y, 0f));
        this.add(new Box(name));
        this.add(new Colour(colour));
    }
}