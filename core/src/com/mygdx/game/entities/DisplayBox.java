package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.mygdx.game.components.ScreenPosition;
import com.mygdx.game.components.Size;
import com.mygdx.game.components.Sprite;
import com.mygdx.game.components.TestComponent;
import com.mygdx.game.components.Box;

public class DisplayBox extends DialogueBox {
    // Please use for-each loop to render displaybox.
    // For example,
    // for (Entity e : (new DisplayBox()).getAll()){
    //      engine.addEntity(e);}
    public DisplayBox() {
        super("Do you want to go back to Selection Menu?");// Display "Do you want to go back to Selection Menu?"
                                                           // and give either 'Yes(y)' or 'No(n)' options
    }
}
