package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class BoxComponent implements Component {
    public static final ComponentMapper<BoxComponent> Map = ComponentMapper.getFor(BoxComponent.class);

    public String box;

    public BoxComponent(String box) {
        this.box = box;
    }
}
