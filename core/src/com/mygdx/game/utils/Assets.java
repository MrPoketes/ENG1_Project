package com.mygdx.game.utils;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Assets {

    private static final String MAINSCREEN_BG = "backgrounds/mainScreenBg.png";
    private static final String INFO_BUTTON = "buttons/infoButton.png";
    private static final String START_BUTTON = "buttons/startButton.png";
    private static final String FONT = "fonts/8BITWONDER";
    private static final String SKIN = "skins/skin.json";
    private static final String BACK_BUTTON = "buttons/backButton.png";
    private static final String GOLD_STAR = "stars/goldStar.png";
    private static final String GRAY_STAR = "stars/grayStar.png";

    // Boat select file paths
    private static final String BROWN_SELECT = "boatSelect/brownBoatSelect.png";
    private static final String CYAN_SELECT = "boatSelect/cyanBoatSelect.png";
    private static final String RED_SELECT = "boatSelect/redBoatSelect.png";
    private static final String WHITE_SELECT = "boatSelect/whiteBoatSelect.png";
    private static final String PINK_SELECT = "boatSelect/pinkBoatSelect.png";
    private static final String GREEN_SELECT = "boatSelect/greenBoatSelect.png";

    // Boats
    private static final String BROWN_BOAT ="boats/brownBoat.png";
    private static final String CYAN_BOAT = "boats/cyanBoat.png";
    private static final String RED_BOAT = "boats/redBoat.png";
    private static final String WHITE_BOAT = "boats/whiteBoat.png";
    private static final String PINK_BOAT = "boats/pinkBoat.png";
    private static final String GREEN_BOAT = "boats/greenBoat.png";

    private static AssetManager manager = new AssetManager();

    public static TextureRegion mainBg;
    public static TextureRegion infoBtn;
    public static TextureRegion startBtn;
    public static TextureRegion backBtn;
    public static TextureRegion goldStar;
    public static TextureRegion grayStar;

    // Boat texture regions
    public static TextureRegion brownSelect;
    public static TextureRegion cyanSelect;
    public static TextureRegion redSelect;
    public static TextureRegion whiteSelect;
    public static TextureRegion pinkSelect;
    public static TextureRegion greenSelect;

    // Boats
    public static TextureRegion brownBoat;
    public static TextureRegion cyanBoat;
    public static TextureRegion redBoat;
    public static TextureRegion whiteBoat;
    public static TextureRegion pinkBoat;
    public static TextureRegion greenBoat;

    @SuppressWarnings("deprecation")
    public static void load(){

        manager.load(MAINSCREEN_BG,Texture.class);
        manager.load(INFO_BUTTON,Texture.class);
        manager.load(START_BUTTON,Texture.class);
        manager.load(BACK_BUTTON,Texture.class);
        manager.load(GOLD_STAR,Texture.class);
        manager.load(GRAY_STAR,Texture.class);

        manager.load(BROWN_SELECT,Texture.class);
        manager.load(CYAN_SELECT,Texture.class);
        manager.load(RED_SELECT,Texture.class);
        manager.load(WHITE_SELECT,Texture.class);
        manager.load(PINK_SELECT,Texture.class);
        manager.load(GREEN_SELECT,Texture.class);

        manager.load(BROWN_BOAT,Texture.class);
        manager.load(CYAN_BOAT,Texture.class);
        manager.load(RED_BOAT,Texture.class);
        manager.load(WHITE_BOAT,Texture.class);
        manager.load(PINK_BOAT,Texture.class);
        manager.load(GREEN_BOAT,Texture.class);

        manager.finishLoading();

        mainBg = new TextureRegion(manager.get(MAINSCREEN_BG,Texture.class));
        infoBtn = new TextureRegion(manager.get(INFO_BUTTON,Texture.class));
        startBtn = new TextureRegion(manager.get(START_BUTTON,Texture.class));
        backBtn = new TextureRegion(manager.get(BACK_BUTTON,Texture.class));
        goldStar = new TextureRegion(manager.get(GOLD_STAR,Texture.class));
        grayStar = new TextureRegion(manager.get(GRAY_STAR,Texture.class));
        
        // Boat textureRegion assignment
        brownSelect = new TextureRegion(manager.get(BROWN_SELECT,Texture.class));
        cyanSelect = new TextureRegion(manager.get(CYAN_SELECT,Texture.class));
        redSelect = new TextureRegion(manager.get(RED_SELECT,Texture.class));
        whiteSelect = new TextureRegion(manager.get(WHITE_SELECT,Texture.class));
        pinkSelect = new TextureRegion(manager.get(PINK_SELECT,Texture.class));
        greenSelect = new TextureRegion(manager.get(GREEN_SELECT,Texture.class));

        // Boats
        brownBoat = new TextureRegion(manager.get(BROWN_BOAT,Texture.class));
        cyanBoat = new TextureRegion(manager.get(CYAN_BOAT,Texture.class));
        redBoat = new TextureRegion(manager.get(RED_BOAT,Texture.class));
        whiteBoat = new TextureRegion(manager.get(WHITE_BOAT,Texture.class));
        pinkBoat = new TextureRegion(manager.get(PINK_BOAT,Texture.class));
        greenBoat = new TextureRegion(manager.get(GREEN_BOAT,Texture.class));

    }
    public static void dispose(){
        manager.dispose();
        
    }
    
}
