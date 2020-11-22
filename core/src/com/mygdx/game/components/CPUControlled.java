package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class CPUControlled implements Component {
    public static final ComponentMapper<CPUControlled> Map = ComponentMapper.getFor(CPUControlled.class);
}
