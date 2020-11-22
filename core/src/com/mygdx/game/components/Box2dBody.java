package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Constants;

public class Box2dBody implements Component {
    public static final ComponentMapper<Box2dBody> Map = ComponentMapper.getFor(Box2dBody.class);

    public Body body;

    //Big constructor.
    public Box2dBody(World world, boolean isStatic, float positionX, float positionY, float sizeX, float sizeY, float density) {
        BodyDef bodyDef = new BodyDef();
        if (isStatic) bodyDef.type = BodyDef.BodyType.StaticBody;
        else bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(positionX, positionY);
        bodyDef.linearDamping = 0.3f;
        bodyDef.angularDamping = 1f;
        this.body = world.createBody(bodyDef);
        this.body.setSleepingAllowed(false);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sizeX/2, sizeY/2);
        body.createFixture(shape, density);
        //box2d doesn't dispose of shapes automatically for some reason
        shape.dispose();
    }

    //Convenience function for constructing a body without specifying a density (namely for static bodies).
    public Box2dBody(World world, boolean isStatic, float positionX, float positionY, float sizeX, float sizeY) {
        this(world, isStatic, positionX, positionY, sizeX, sizeY, 0.0f);
    }
}