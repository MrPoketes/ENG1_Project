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
import com.mygdx.game.components.*;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.graphics.Color;

import java.util.Iterator;

public class HealthBarSystem extends EntitySystem {
    private final Family family;

    public HealthBarSystem() {
        super();
        this.family = Family.all(Size.class, ScreenPosition.class, BoxComponent.class, Colour.class, HealthBar.class).get(); 
    }


    @Override
    public void update(float deltaTime) {
        Integer currentHealth = this.getEngine().getEntitiesFor(Family.all(PlayerControlled.class).get()).first().getComponent(DynamicBoatStats.class).health;
        Entity entity = this.getEngine().getEntitiesFor(family).first();
        entity.getComponent(Size.class).x = currentHealth*50;
    }   
    

} 