package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.components.ScreenPosition;
import com.mygdx.game.components.Size;
import com.mygdx.game.components.Text;
import com.mygdx.game.components.TestComponent;

public class TestText extends Entity {

    //TestEntities are always created with fixed values for most components,
    //but the x and y Position must be given on creation.
    public TestText(Integer x, Integer y,String text) {
        super();
        this.add(new Text(text));
        this.add(new ScreenPosition(x, y));
    }
}
