package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.ScreenPosition;
import com.mygdx.game.components.Size;
import com.mygdx.game.components.Sprite;
import com.mygdx.game.components.Colour;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.components.Box;
import java.util.Iterator;

public class RenderBoxes extends EntitySystem {
    private ShapeRenderer shape; 
    private final Family family; 

    public RenderBoxes(ShapeRenderer shape) { 
        super(); 
        this.family = Family.all(Size.class, ScreenPosition.class, Box.class, Colour.class).get();
        this.shape = shape;

    }

    @Override 
    public void addedToEngine(Engine engine) { 
       shape = new ShapeRenderer();
    }

    @Override 
    public void update(float deltaTime) { 
        for (Entity entity: this.getEngine().getEntitiesFor(family)){
            float x = entity.getComponent(ScreenPosition.class).x;
            float y = entity.getComponent(ScreenPosition.class).y;

            Integer xSize = entity.getComponent(Size.class).x;
            Integer ySize = entity.getComponent(Size.class).y;
            Color colour = entity.getComponent(Colour.class).colour;

            shape.begin(ShapeType.Filled);
            shape.setColor(colour);
            shape.rect(x,y,xSize,ySize);
            shape.end();
        }
    }

  


}