package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.components.ScreenPosition;
import com.mygdx.game.components.Size;
import com.mygdx.game.components.Sprite;
import com.mygdx.game.components.TestComponent;
import com.mygdx.game.components.BoxComponent;
import com.mygdx.game.components.Colour;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.components.LineComponent;
import com.mygdx.game.components.Box2dBody;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.components.*;

public class Line extends Entity {

    public Line(Integer x, Integer y, String sprite,World world,float posX,float posY, float sizeX,float sizeY) { 
        
        super();
        this.add(new ScreenPosition(0,-20, 0f));
        this.add(new Size(2000, 50));
        this.add(new Sprite(sprite));
        this.add(new Box2dBody(world, true, posX, posY, sizeX, sizeY,1f,true));
    }
    
}