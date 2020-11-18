package com.mygdx.game.screens;

import com.mygdx.game.YorkDragonBoatRace;
import com.mygdx.game.utils.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.screens.*;

public class MainMenu extends AbstractScreen {
    
    private SpriteBatch batch;
    private OrthographicCamera camera;
    Rectangle startBtnRect;
    Rectangle infoBtnRect;
    Vector3 touchPoint;
    private Stage stage;

    public MainMenu(YorkDragonBoatRace game){
        super(game);

        stage = new Stage();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();

        infoBtnRect = new Rectangle(20,50,300,150);
        startBtnRect = new Rectangle(50,50,300,150);
        touchPoint = new Vector3();
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void hide(){
        super.hide();
        batch.dispose();
        stage.dispose();
    }
    @Override
    public void resize(int width,int height){
        super.resize(width,height);
        camera.setToOrtho(false);
        camera.update();
        stage.getViewport().update(width,height);
    }
    @Override
    public void render(float delta){
        super.render(delta);
        handleInput();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(Assets.mainBg,0,0,1980,1080);
        batch.draw(Assets.startBtn,1200,50,300,150);
        batch.draw(Assets.infoBtn,20,50,300,150);
        batch.end();
        stage.act(delta);
        stage.draw();
    }
    public void handleInput(){

        // Event Listener for Start Button click
        if(Gdx.input.justTouched()){
            camera.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));

            if(startBtnRect.contains(touchPoint.x,touchPoint.y)){
                game.setScreen(new GameScreen(game));
            }
            else if(infoBtnRect.contains(touchPoint.x,touchPoint.y)){
                game.setScreen(new InfoScreen(game));
            }
        }
    }
    @Override
    public void dispose(){
        batch.dispose();
        stage.dispose();
    }
}
