package com.mygdx.game.screens;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entities.*;
import com.mygdx.game.systems.*;
import com.mygdx.game.components.*;
import com.badlogic.gdx.Input;
import static com.badlogic.gdx.Gdx.graphics;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.*;

public class ResultsScreen extends AbstractScreen {

    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;
    Rectangle nextBtnRect;
    Vector3 touch;
    private Stage stage;
    boolean isGameOver;
    int legNumber;
    int selectedBoat;
    HashMap<Integer, Integer> times;

    public ResultsScreen(YorkDragonBoatRace game, boolean isGameOver, int legNumber, int selectedBoat, HashMap<Integer, Integer> times){
        super(game);
        stage = new Stage();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        font = new BitmapFont();
        // Making the font bigger
        font.getData().setScale(2,2);

        this.isGameOver = isGameOver;
        this.legNumber = legNumber;
        this.selectedBoat = selectedBoat;
        this.times = times;

        nextBtnRect = new Rectangle(1200,50,300,150);
        touch = new Vector3();
        Gdx.input.setInputProcessor(stage);
    }

    /*
    Convenience constructor for moving to the results screen on a game over.
    Obviously should only be used if the game is over.
     */
    public ResultsScreen(YorkDragonBoatRace game, boolean isGameOver){
        this(game, isGameOver, 0, 0, new HashMap<Integer, Integer>());
    }

    /*
    Returns the player's position in the leaderboard.
     */
    private int getPlayerPosition(){
        int playerScore = times.get(selectedBoat);
        int CPUThatBeatPlayer = 0;
        for(int i=0;i<=5;i++){
            if (i == selectedBoat){
                continue;
            }
            if ((times.get(i) < playerScore) && (times.get(i) != 0)){
                CPUThatBeatPlayer++;
            }
        }
        return CPUThatBeatPlayer+1;
    }

    @Override
    public void render(float delta){
        super.render(delta);
        batch.begin();
        batch.draw(Assets.mainBg,0,0,1980,1080);
        batch.draw(Assets.nextBtn,1200,30,300,150);

        if (isGameOver){
            font.draw(batch,"you drowned",70,800);
            font.draw(batch,"don't hit so many rocks",70,750);
        }
        else if (legNumber == 1) {
            font.draw(batch, "first leg is practice", 70, 800);
            font.draw(batch, "go to round 2", 70, 750);
        }
        else if (legNumber == 4) {
            font.draw(batch, "you did it", 70, 800);
            font.draw(batch, "you got ".concat(Integer.toString(getPlayerPosition())).concat(" place"), 70, 750);
        }
        else{
            font.draw(batch, "you are are in ".concat(Integer.toString(getPlayerPosition())).concat(" place"), 70, 800);
            font.draw(batch, "you must be in top 4 to qualify for finals", 70, 750);
        }

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

            if(nextBtnRect.contains(touch.x,touch.y)){
                if (isGameOver || legNumber == 4 || legNumber == 3 && getPlayerPosition() > 4){
                    game.setScreen(new MainMenu(game));
                }
                else {
                    game.setScreen(new GameScreen(game, legNumber+1, selectedBoat, times));
                }
            }
        }
    }
    public void dispose(){
        batch.dispose();
        stage.dispose();
    }
}
