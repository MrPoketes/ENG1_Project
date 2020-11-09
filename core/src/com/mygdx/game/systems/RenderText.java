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

import java.util.Iterator;

public class RenderText extends EntitySystem {

    private SpriteBatch batch;
    private final Family family;

    public RenderText (SpriteBatch batch) {
        super();
        this.family = Family.all(ScreenPosition.class, Size.class, Sprite.class).get();
        this.batch = batch;
    }
    @Override
    public void addedToEngine(Engine engine) {
        // batch = new SpriteBatch();
    }
    @Override
    public void update(float deltaTime) {
    }
}
