package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class DynamicBoatStats implements Component {
    public static final ComponentMapper<DynamicBoatStats> Map = ComponentMapper.getFor(DynamicBoatStats.class);

    public Integer health;
    public double exhaustion;
    public Integer leftCooldown;
    public Integer rightCooldown;
    public Integer damageDebounce;
    public Float time;
    public boolean isFinished;

    public DynamicBoatStats(Integer health) {
        this.health = health;
        this.exhaustion = 1f;
        this.leftCooldown = 0;
        this.rightCooldown = 0;
        this.damageDebounce = 0;
        this.time = 0f;
        this.isFinished = false;
    }
}