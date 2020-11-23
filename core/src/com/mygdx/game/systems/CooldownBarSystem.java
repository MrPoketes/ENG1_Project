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
import com.mygdx.game.components.BoxComponent;
import com.mygdx.game.components.HealthBar;
import com.mygdx.game.components.LeftCooldown;
import com.mygdx.game.components.RightCooldown;
import java.util.Iterator;

public class CooldownBarSystem extends EntitySystem {
    private final Family familyLeft;
    private final Family familyRight;

    public CooldownBarSystem() {
        super();
        this.familyLeft = Family.all(Size.class, ScreenPosition.class, BoxComponent.class, Colour.class, LeftCooldown.class).get();
        this.familyRight = Family.all(Size.class, ScreenPosition.class, BoxComponent.class, Colour.class, RightCooldown.class).get();
    }


    @Override
    public void update(float deltaTime) {
        //Integer currentCooldownLeft = this.getEngine().getEntitiesFor(Family.all(PlayerControlled.class).get()).first().getComponent(DynamicBoatStats.class).leftCooldown;
        //Integer currentCooldownRight = this.getEngine().getEntitiesFor(Family.all(PlayerControlled.class).get()).first().getComponent(DynamicBoatStats.class).rightCooldown;
        //Entity leftValue = this.getEngine().getEntitiesFor(familyLeft).first();
        //Entity rightValue = this.getEngine().getEntitiesFor(familyRight).first();
        
        //leftValue.getComponent(Size.class).y = currentCooldownLeft;
        //rightValue.getComponent(Size.class).y = currentCooldownRight;
    }
}



