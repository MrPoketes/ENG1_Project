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
            TextureRegion textureRegion = new TextureRegion(texture);
            float originX = (entity.getComponent(ScreenPosition.class).x)/2;
            float originY = (entity.getComponent(ScreenPosition.class).y)/2;
            batch.draw(
                textureRegion,
                // Screen position
                entity.getComponent(ScreenPosition.class).x,
                entity.getComponent(ScreenPosition.class).y,
                // Origin x and y
                originX,
                originY,
                // Width and height
                entity.getComponent(Size.class).x,
                entity.getComponent(Size.class).y,
                // ScaleX and scaleY
                1f,1f,
                // Rotation
                entity.getComponent(ScreenPosition.class).rotation
                );
        }
        batch.end();
    }
}
