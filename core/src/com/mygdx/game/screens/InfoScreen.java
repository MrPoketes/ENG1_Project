package com.mygdx.game.screens;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private BitmapFont font;
    private OrthographicCamera camera;
    Rectangle backBtnRect;
    Vector3 touch;
    private Stage stage;

    public InfoScreen(YorkDragonBoatRace game){
        super(game);
        stage = new Stage();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        font = new BitmapFont();
        // Making the font bigger
        font.getData().setScale(2,2);

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
        font.draw(batch,"Instructions for the York Dragon Boat Race.",70,800);
        font.draw(batch,"Controls - Buttons Q and E rows the boat, buttons A and D rows the boat backwards.",70,750);
        font.draw(batch,"The first leg is a practice leg and is not timed. After the practice leg you will race in 2 timed legs.",70,700);
        font.draw(batch,"Out of the 2 legs best time is picked and checked against the competition.",70,650);
        font.draw(batch,"You must be top 4 to qualify for the finals",70,600);
        font.draw(batch,"Have fun!",70,550);
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
