package com.mygdx.game.screens;

import com.mygdx.game.YorkDragonBoatRace;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public abstract class AbstractScreen implements Screen {
    protected final YorkDragonBoatRace game;

    // clear background
    protected Color clear = new Color(0f,0f,0f,1f);

    public AbstractScreen(YorkDragonBoatRace game){
        this.game = game;
    }
    
    protected String getName(){
        return getClass().getSimpleName();
    }
    @Override
    public void show(){
    }
    @Override
    public void resize(int width,int height){}
    @Override
    public void render(float delta){
        Gdx.gl.glClearColor( clear.r, clear.g, clear.b, clear.a);
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
    }
    @Override
    public void hide(){}
    @Override
    public void resume(){}
    @Override
    public void pause(){}
    @Override
    public void dispose(){}
}
