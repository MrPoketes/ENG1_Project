package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;

public class TestComponent implements Component {
    //Create a ComponentMap for the component.
    //This is used to provide the ComponentMapper.has(Entity) and ComponentMapper.get(Entity) functions, which are fast
    //and easy ways to check if an entity has a particular component, and to get a particular component from an entity.
    public static final ComponentMapper<TestComponent> Map = ComponentMapper.getFor(TestComponent.class);

    //Declare the properties of the component.
    public Float height;
    public Integer numberOfCats;
    public String favouriteColour;

    //Constructor for the class. Can be skipped if there's no properties.
    public TestComponent(Float height, Integer numberOfCats, String favouriteColour) {
        this.height = height;
        this.numberOfCats = numberOfCats;
        this.favouriteColour = favouriteColour;
    }
}