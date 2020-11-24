package com.mygdx.game.utils;

import com.mygdx.game.entities.PlayerBoat;

import java.util.ArrayList;
import java.util.HashMap;

/*
Class containing static data about the boats.
 */
public class BoatData {
    public static int[][] starCount = {
            {3,5,4,1},
            {3,4,5,2},
            {5,1,1,5},
            {5,2,2,3},
            {4,4,2,3},
            {4,2,4,4}
    };

    /*
    Returns the index of a boat from it's name.
    Used to get the index of a boat from its entity.
     */
    public static Integer getIndexFromName(String name){
        if (name.equals("Cyan")) return 0;
        else if (name.equals("Brown")) return 1;
        else if (name.equals("Red")) return 2;
        else if (name.equals("White")) return 3;
        else if (name.equals("Pink")) return 4;
        else if (name.equals("Green")) return 5;
        return -1;
    }

    /*
    Returns a dictionary of dictionaries, where the subdictionaries contain the corresponding data about the boat:
    name, sprite, sizeX, sizeY, topSpeed, acceleration, maneuverability, robustness
     */
    public static HashMap<Integer, HashMap<String, Object>> getEntityData() {
        HashMap<Integer, HashMap<String, Object>> entityData;
        entityData = new HashMap<>();

        HashMap<String, Object> thisData;
        thisData = new HashMap<>();
        thisData.put("name", "Cyan");
        thisData.put("sprite", "boats/cyanBoat.png");
        thisData.put("sizeX", 0.2f);
        thisData.put("sizeY", 0.25f);
        thisData.put("topSpeed", 1f);
        thisData.put("acceleration", 4f);
        thisData.put("maneuverability", 4f);
        thisData.put("robustness", 3);
        entityData.put(0, thisData);

        thisData = new HashMap<>();
        thisData.put("name", "Brown");
        thisData.put("sprite", "boats/brownBoat.png");
        thisData.put("sizeX", 0.2f);
        thisData.put("sizeY", 0.25f);
        thisData.put("topSpeed", 1f);
        thisData.put("acceleration", 3.5f);
        thisData.put("maneuverability", 5f);
        thisData.put("robustness", 5);
        entityData.put(1, thisData);

        thisData = new HashMap<>();
        thisData.put("name", "Red");
        thisData.put("sprite", "boats/redBoat.png");
        thisData.put("sizeX", 0.2f);
        thisData.put("sizeY", 0.5f);
        thisData.put("topSpeed", 2f);
        thisData.put("acceleration", 2f);
        thisData.put("maneuverability", 2f);
        thisData.put("robustness", 15);
        entityData.put(2, thisData);

        thisData = new HashMap<>();
        thisData.put("name", "White");
        thisData.put("sprite", "boats/whiteBoat.png");
        thisData.put("sizeX", 0.2f);
        thisData.put("sizeY", 0.5f);
        thisData.put("topSpeed", 2f);
        thisData.put("acceleration", 2.5f);
        thisData.put("maneuverability", 3f);
        thisData.put("robustness", 8);
        entityData.put(3, thisData);

        thisData = new HashMap<>();
        thisData.put("name", "Pink");
        thisData.put("sprite", "boats/pinkBoat.png");
        thisData.put("sizeX", 0.2f);
        thisData.put("sizeY", 0.3f);
        thisData.put("topSpeed", 1.5f);
        thisData.put("acceleration", 3.5f);
        thisData.put("maneuverability", 2f);
        thisData.put("robustness", 8);
        entityData.put(4, thisData);

        thisData = new HashMap<>();
        thisData.put("name", "Green");
        thisData.put("sprite", "boats/greenBoat.png");
        thisData.put("sizeX", 0.2f);
        thisData.put("sizeY", 0.3f);
        thisData.put("topSpeed", 1.5f);
        thisData.put("acceleration", 2.5f);
        thisData.put("maneuverability", 4f);
        thisData.put("robustness", 10);
        entityData.put(5, thisData);

        return entityData;
    }
}
