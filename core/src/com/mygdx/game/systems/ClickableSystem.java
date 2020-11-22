package com.mygdx.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.*;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Input.Buttons;

public class ClickableSystem extends IteratingSystem {

    private ComponentMapper<Clickable> clickable;
    private ComponentMapper<ScreenPosition> position;
    private OrthographicCamera camera;

    public ClickableSystem(OrthographicCamera cam){
        super(Family.all(Clickable.class,ScreenPosition.class).get());
        clickable = ComponentMapper.getFor(Clickable.class);
        position = ComponentMapper.getFor(ScreenPosition.class);
        camera = cam;
    }
    @Override
    protected void processEntity(Entity entity,float deltaTime){
        Clickable click = clickable.get(entity);
        ScreenPosition pos = position.get(entity);

        click.bounds.x = pos.x;
        click.bounds.y = pos.y;

        if(Gdx.input.isButtonPressed(Buttons.LEFT)){
            Vector3 clickPosition = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(),0));
            System.out.println(clickPosition+":"+click.bounds.toString());
            if(click.bounds.contains(clickPosition.x, clickPosition.y)){
                click.clicked = true;
            }
        }
    }
    
}
