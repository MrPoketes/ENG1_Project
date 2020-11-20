package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.mygdx.game.components.ScreenPosition;
import com.mygdx.game.components.Size;
import com.mygdx.game.components.Sprite;
import com.mygdx.game.components.TestComponent;
import com.mygdx.game.components.Box;

public class DialogueBox extends Entity {
    // DialogueBox is a dialog box that asks whether the user would like to act on
    // the action.
    // Attributes:
    // question, It is the question asks what to do next.
    // firstOption, It is the first option for the user to choose.
    // secondOption, It is the second option for the user to choose.
    // frameBox, It is background for the dialogue box.
    // subEntitites, It is a collection of all entites in Dialogue Box, including
    // question, firstOption, secondOption and framebox.

    public TestText question;
    public TestText firstOption;
    public TestText secondOption;
    public box_copy frameBox;

    public Array<Entity> subEntities;

    public DialogueBox(String questionText) {
        this(questionText, "Yes(y)", "No(n)");
    }

    public DialogueBox(String questionText, String firstOptionText, String secondOptionText) {
        this(questionText, firstOptionText, secondOptionText,
                new box_copy(100, 100, 400, 300, "NameUndefined", Color.BLACK));
    }

    public DialogueBox(String questionText, String firstOptionText, String secondOptionText, box_copy frameBox) {
        super();
        this.frameBox = frameBox;
        this.question = new TestText(frameBox.position.x + frameBox.size.x / 2 - 100,
                frameBox.position.y + frameBox.size.y / 2 + 50, questionText);
        // to centre the question
        this.firstOption = new TestText(frameBox.position.x + frameBox.size.x / 2 - 80,
                frameBox.position.y + frameBox.size.y / 2 - 50, firstOptionText);
        // to put the first option in the bottom left of the dialogue box
        this.secondOption = new TestText(frameBox.position.x + frameBox.size.x / 2 + 80,
                frameBox.position.y + frameBox.size.y / 2 - 50, secondOptionText);
        // to put the first option in the bottom right of the dialogue box

        this.subEntities = new Array<Entity>();
        // add all other entites in this class to subEntites
        this.subEntities.add(this.question);
        this.subEntities.add(this.firstOption);
        this.subEntities.add(this.secondOption);
        this.subEntities.add(this.frameBox);// Note: It currently hides the other texts.
    }

    // This method is to detect if the user types Key 'Y' or 'N'.
    // It returns true if Key 'Y' is pressed while returns false if Key 'N' is
    // pressed.
    public boolean isConfirmed() {
        if (Gdx.input.isKeyPressed(Keys.Y)) {
            return true;
        } else if (Gdx.input.isKeyPressed(Keys.N)) {
            return false;
        }
        return true;
    }

    // This method is to detect if the user types Key 'Y' or 'N'.
    // It returns true if Key 'N' is pressed while returns false if Key 'Y' is
    // pressed.
    public boolean isCancelled() {
        return !this.isConfirmed();
    }

    // This method is to return an array of all entities in the dialogue box.
    public Array<Entity> getAll() {
        return this.subEntities;
    }
}
