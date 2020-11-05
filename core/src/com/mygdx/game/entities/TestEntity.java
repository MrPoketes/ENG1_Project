package com.mygdx.game.entities;

import com.badlogic.ashley.core.Entity;
import com.mygdx.game.components.ScreenPosition;
import com.mygdx.game.components.Size;
import com.mygdx.game.components.Sprite;
import com.mygdx.game.components.TestComponent;

public class TestEntity extends Entity {

    //TestEntities are always created with fixed values for most components,
    //but the x and y Position must be given on creation.
    public TestEntity(Integer x, Integer y) {
        super();
        this.add(new TestComponent(10f, 3, "Blue"));
        //you can chain adds together if you want
        this.add(new Size(100, 100)).add(new ScreenPosition(x, y, 0f));
        this.add(new Sprite("badlogic.jpg"));
    }
}
