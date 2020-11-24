package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class FinishLine implements Component {
    public static final ComponentMapper<FinishLine> Map = ComponentMapper.getFor(FinishLine.class);
}
