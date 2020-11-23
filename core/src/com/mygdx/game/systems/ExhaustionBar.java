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
import com.mygdx.game.components.healthBar;
import com.mygdx.game.components.Exhaustion;
import java.util.Iterator;

public class ExhaustionBar extends EntitySystem {
    private final Family family;

    public ExhaustionBar() {
        super();
        this.family = Family.all(Size.class, ScreenPosition.class, Box.class, Colour.class, Exhaustion.class).get(); 
    }

    @Override
    public void update(float deltaTime) {
        //Integer currentExhaustion = this.getEngine().getEntitiesFor(Family.all(PlayerControlled.class).get()).first().getComponent(DynamicBoatStats.class).exhaustion;
        //Entity entity = this.getEngine().getEntitiesFor(family).first();
        //entity.getComponent(Size.class).x = currentExhaustion;
    }
}