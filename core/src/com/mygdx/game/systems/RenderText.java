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
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.components.Size;
import com.mygdx.game.components.Text;

import java.util.Iterator;

public class RenderText extends EntitySystem {

    private SpriteBatch batch;
    private final Family family;
    private BitmapFont font;
    public RenderText (SpriteBatch batch) {
        super();
        this.family = Family.all(ScreenPosition.class, Text.class).get();
        this.batch = batch;
        this.font = new BitmapFont();
    }
    @Override
    public void addedToEngine(Engine engine) {
        batch = new SpriteBatch();
    }
    @Override
    public void update(float deltaTime) {
        batch.begin();
        for (Entity entity: this.getEngine().getEntitiesFor(family)){
            CharSequence text = entity.getComponent(Text.class).text;
            float x = entity.getComponent(ScreenPosition.class).x;
            float y = entity.getComponent(ScreenPosition.class).y;
            font.draw(batch,text,x,y);
        }
        batch.end();
    }
    public void dispose(){
        batch.dispose();
        font.dispose();
    }
}
