package com.mygdx.game.screens;
import com.mygdx.game.YorkDragonBoatRace;
import com.mygdx.game.utils.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.screens.*;
import com.badlogic.gdx.Input;

public class InfoScreen extends AbstractScreen {

    private SpriteBatch batch;
    private OrthographicCamera camera;
    Rectangle backBtnRect;
    Vector3 touch;
    private Stage stage;

    public InfoScreen(YorkDragonBoatRace game){
        super(game);
        stage = new Stage();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        backBtnRect = new Rectangle(1200,50,300,150);
        touch = new Vector3();
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void render(float delta){
        super.render(delta);
        batch.begin();
        batch.draw(Assets.mainBg,0,0,1980,1080);
        batch.draw(Assets.backBtn,1200,30,300,150);
        batch.end();
        stage.act(delta);
        stage.draw();
        handleInput();
    }
    public void resize(int width,int height){
        super.resize(width,height);
        camera.setToOrtho(false);
        camera.update();
        stage.getViewport().update(width,height);
    }
    public void handleInput(){

        // Event Listener for Back Button click
        if(Gdx.input.justTouched()){
            camera.unproject(touch.set(Gdx.input.getX(),Gdx.input.getY(),0));

            if(backBtnRect.contains(touch.x,touch.y)){
                game.setScreen(new MainMenu(game));
            }
        }
    }
    public void dispose(){
        batch.dispose();
        stage.dispose();
    }
}
