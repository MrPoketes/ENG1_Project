package com.mygdx.game.utils;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Game;
import com.mygdx.game.YorkDragonBoatRace;
import com.mygdx.game.components.BoatStats;
import com.mygdx.game.components.Box2dBody;
import com.mygdx.game.components.DynamicBoatStats;
import com.mygdx.game.components.PlayerControlled;
import com.mygdx.game.screens.ResultsScreen;

import java.util.HashMap;

/*
this function is common to a couple of systems
 */
public class MoveToResults extends EntitySystem {
    
    private static class MoveToResultsListener implements Listener<Object>{
        private Engine engine;
        private YorkDragonBoatRace game;
        private int legNumber;
        private int selectedBoat;
        private HashMap<Integer, Integer> times;
        
        public MoveToResultsListener(Engine engine, YorkDragonBoatRace game, int legNumber, int selectedBoat, HashMap<Integer, Integer> times){
            this.engine = engine;
            this.game = game;
            this.legNumber = legNumber;
            this.selectedBoat = selectedBoat;
            this.times = times;
        }

        @Override
        public void receive(Signal<Object> signal, Object object) {
                Entity playerBoat = engine.getEntitiesFor(Family.all(PlayerControlled.class).get()).first();
                Family boatsFamily = Family.all(BoatStats.class, DynamicBoatStats.class).get();

                if (BoatControlCommon.isBoatDead(playerBoat)){
                    game.setScreen(new ResultsScreen(game, true));
                    return;
                }

                //For the final leg, only the times for the final are sent to the results screen, previous times aren't kept.
                if (legNumber == 4){
                    //Clear all boat times - this ensures boats not participating in the final don't keep their score.
                    times = new HashMap<Integer, Integer>(){{
                        put(0,0);
                        put(1,0);
                        put(2,0);
                        put(3,0);
                        put(4,0);
                        put(5,0);
                    }};
                    //Boats slower than the player are irrelevant, so we only update the times for boats that beat the player.
                    for (Entity entity : engine.getEntitiesFor(boatsFamily)) {
                        int boatIndex = BoatData.getIndexFromName(entity.getComponent(BoatStats.class).name);
                        if (entity.getComponent(DynamicBoatStats.class).isFinished){
                            int time = entity.getComponent(DynamicBoatStats.class).time;
                            times.put(boatIndex, time);
                        }
                    }
                }
                //For legs 2 and 3, the best time is kept.
                else if (legNumber != 1){
                    for (Entity entity : engine.getEntitiesFor(boatsFamily)) {
                        int boatIndex = BoatData.getIndexFromName(entity.getComponent(BoatStats.class).name);
                        int time;
                        if (entity.getComponent(DynamicBoatStats.class).isFinished){
                            time = entity.getComponent(DynamicBoatStats.class).time;
                        }
                        //If the boat hasn't finished yet, approximate its time by how close it is to the end.
                        //Dead boats and boats behind the starting line get 0 (DNF).
                        else{
                            time = entity.getComponent(DynamicBoatStats.class).time;
                            float posY = entity.getComponent(Box2dBody.class).body.getPosition().y;
                            if ((!BoatControlCommon.isBoatDead(entity)) && posY > 0f) {
                                time *= (Constants.RACE_LENGTH / posY);
                            }
                            else{
                                time = 0;
                            }
                        }
                        //If the time is better than the boat's best, set it as the boat's time.
                        if (time != 0 && time < times.get(boatIndex) || times.get(boatIndex) == 0) {
                            times.put(boatIndex, time);
                        }
                    }
                }

                game.setScreen(new ResultsScreen(game, false, legNumber, selectedBoat, times));
            }
        }

    YorkDragonBoatRace game;
    int legNumber;
    int selectedBoat;
    HashMap<Integer, Integer> times;
    Signal signal;

    public MoveToResults(Engine engine, YorkDragonBoatRace game, int legNumber, int selectedBoat, HashMap<Integer, Integer> times, Signal signal){
        this.game = game;
        this.legNumber = legNumber;
        this.selectedBoat = selectedBoat;
        this.times = times;
        this.signal = signal;
    }

    @Override
    public void addedToEngine(Engine engine) {
        super.addedToEngine(engine);
        signal.add(new MoveToResultsListener(engine, game, legNumber, selectedBoat, times));
    }
}
