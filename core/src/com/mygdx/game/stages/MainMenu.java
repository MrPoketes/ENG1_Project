package com.mygdx.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
// import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
public class MainMenu extends Actor{

    public SpriteBatch batch;
    public Texture background;
    public ShapeRenderer shapeRenderer = new ShapeRenderer();
    public MainMenu(){
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("mainScreenBg.png"));
    }
    @Override
    public void draw(Batch batch,float alpha){
        this.batch.begin();
        this.batch.draw(this.background,0,0);
        this.batch.end();
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(0,0,300,20);
        shapeRenderer.end();
    }
}
