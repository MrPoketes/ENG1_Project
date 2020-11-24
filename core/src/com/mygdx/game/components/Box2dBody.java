package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.*;

public class Box2dBody implements Component {
    public static final ComponentMapper<Box2dBody> Map = ComponentMapper.getFor(Box2dBody.class);

    public Body body;

    //Big constructor.
    public Box2dBody(World world, Entity entity, boolean isStatic, float positionX, float positionY, float sizeX, float sizeY, float density, boolean isSensor) {
        BodyDef bodyDef = new BodyDef();
        if (isStatic) bodyDef.type = BodyDef.BodyType.StaticBody;
        else bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(positionX, positionY);
        bodyDef.linearDamping = 0.2f;
        bodyDef.angularDamping = 1f;
        //Sensors are typically quite thin, and vulnerable to tunnelling. This ensures sensors won't be tunnelled through.
        bodyDef.bullet = isSensor;
        this.body = world.createBody(bodyDef);
        this.body.setSleepingAllowed(false);
        //puts a reference to the parent entity in the userData.
        body.setUserData(entity);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(sizeX/2, sizeY/2);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = density;
        fixtureDef.isSensor = isSensor;
        body.createFixture(fixtureDef);
        //need to dispose of the shapes manually
        shape.dispose();
    }

    //Convenience function for constructing a body without specifying a density (namely for static bodies).
    public Box2dBody(World world, Entity entity, boolean isStatic, float positionX, float positionY, float sizeX, float sizeY) {
        this(world, entity, isStatic, positionX, positionY, sizeX, sizeY, 0.0f);
    }
    //As above, for constructing bodies without specifying if it's a sensor.
    public Box2dBody(World world, Entity entity, boolean isStatic, float positionX, float positionY, float sizeX, float sizeY, float density) {
        this(world, entity, isStatic, positionX, positionY, sizeX, sizeY, density, false);
    }
}