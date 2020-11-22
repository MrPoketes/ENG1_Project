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

	private static final int[] FONT_SIZES = new int[]{10, 20, 30, 50, 100};
	
	public static final int FONT_TEN = 0;
	public static final int FONT_TWENTY = 1;
	public static final int FONT_FORTY = 2;
	public static final int FONT_FIFTY = 3;
    public static final int FONT_HUNDRED = 4;
    
    private static final String MAINSCREEN_BG = "backgrounds/mainScreenBg.png";
    private static final String INFO_BUTTON = "buttons/infoButton.png";
    private static final String START_BUTTON = "buttons/startButton.png";
    private static final String FONT = "fonts/8BITWONDER";
    private static final String SKIN = "skins/skin.json";
    private static final String BACK_BUTTON = "buttons/backButton.png";
    private static final String GOLD_STAR = "stars/goldStar.png";
    private static final String GRAY_STAR = "stars/grayStar.png";

    // Boat selects file paths
    private static final String BROWN_SELECT = "boatSelect/brownBoatSelect.png";
    private static final String CYAN_SELECT = "boatSelect/cyanBoatSelect.png";
    private static final String RED_SELECT = "boatSelect/redBoatSelect.png";
    private static final String WHITE_SELECT = "boatSelect/whiteBoatSelect.png";
    private static final String PINK_SELECT = "boatSelect/pinkBoatSelect.png";
    private static final String GREEN_SELECT = "boatSelect/greenBoatSelect.png";

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

    public static BitmapFont[] fonts;
    public static Skin skin;

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

        skin = new Skin();
    }
    public static void dispose(){
        manager.dispose();
        
        for(BitmapFont f: fonts){
			f.dispose();
		}
    }
    
}
