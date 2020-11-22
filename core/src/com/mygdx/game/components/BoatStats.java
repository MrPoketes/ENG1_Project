package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class BoatStats implements Component {
    public static final ComponentMapper<BoatStats> Map = ComponentMapper.getFor(BoatStats.class);

    public String name;
    public Float topSpeed;
    public Float acceleration;
    public Float maneuverability;
    public Integer robustness;

    public BoatStats(String name, Float topSpeed, Float acceleration, Float maneuverability, Integer robustness) {
        this.name = name;
        this.topSpeed = topSpeed;
        this.acceleration = acceleration;
        this.maneuverability = maneuverability;
        this.robustness = robustness;
    }
}