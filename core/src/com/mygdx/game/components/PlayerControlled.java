package com.mygdx.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class PlayerControlled implements Component {
    public static final ComponentMapper<PlayerControlled> Map = ComponentMapper.getFor(PlayerControlled.class);
}
