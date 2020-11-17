package com.mygdx.game.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.mygdx.game.entities.*;
import com.mygdx.game.components.*;
import com.mygdx.game.systems.*;
import com.mygdx.game.YorkDragonBoatRace;
import java.util.*;
import com.badlogic.ashley.core.ComponentMapper;

public class MainMenu {

    private ComponentMapper<Clickable> startButton;
    Color Green = Color.GREEN;

    public MainMenu(Engine engine){
        // Background entity
        Entity background = new Entity();
        background.add(new Size(1980,1080));
        background.add(new Sprite("mainScreenBg.png"));
        background.add(new ScreenPosition(0,0,0f));
        engine.addEntity(background);
        // Boat select boxes
        int x=50;
        for(int i=0;i!=6;i++){
            engine.addEntity(new SelectBox(x,630,200,200, Color.WHITE));
            x+=250;
        }
        // Buttons
        Entity startButton = new Entity();
        startButton.add(new Size(300,150)).add(new Sprite("startButton.png"));
        startButton.add(new ScreenPosition(1200,20,0f));
        startButton.add(new Clickable(1200,20,300,150));
        engine.addEntity(startButton);
        boolean startButtonClick = (startButton.getComponent(Clickable.class).clicked);
        Entity infoButton = new Entity();
        infoButton.add(new Size(300,150)).add(new Sprite("infoButton.png"));
        infoButton.add(new ScreenPosition(20,20,0f));
        engine.addEntity(infoButton);
    }
    public void update
}
