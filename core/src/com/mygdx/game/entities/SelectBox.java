package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.components.ScreenPosition;
import com.mygdx.game.components.Size;
import com.mygdx.game.components.Colour;
import com.badlogic.gdx.graphics.Color;

public class SelectBox extends Entity {


    public SelectBox(Integer x, Integer y, Integer width, Integer length, Color colour) { 
        
        this.add(new Size(width, length));
        this.add(new ScreenPosition(x,y, 0f));
        this.add(new Colour(colour));
    }
    public void select(){
        this.add(new Colour(Color.GREEN));
    }
}