package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.ScreenPosition;
import com.mygdx.game.components.Size;
import com.mygdx.game.components.Sprite;

import java.util.Iterator;

public class RenderSprites extends EntitySystem {

    private SpriteBatch batch;
    private final Family family;

    public RenderSprites (SpriteBatch batch) {
        super();
        this.family = Family.all(ScreenPosition.class, Size.class, Sprite.class).get();
        this.batch = batch;
    }

    @Override
    public void addedToEngine(Engine engine) {
        batch = new SpriteBatch();
    }

    //Draws a blue background, and then iterates over every entity in the family.
    @Override
    public void update(float deltaTime) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        for (Entity entity : this.getEngine().getEntitiesFor(family)) {
            Texture texture = new Texture(entity.getComponent(Sprite.class).currentSprite);
            //currently doesn't handle rotations because that's harder
            batch.draw(
                    texture,
                    entity.getComponent(ScreenPosition.class).x,
                    entity.getComponent(ScreenPosition.class).y,
                    entity.getComponent(Size.class).x,
                    entity.getComponent(Size.class).y
            );
        }
        batch.end();
        //finish later
    }
}
