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

    private static AssetManager manager = new AssetManager();

    public static TextureRegion mainBg;
    public static TextureRegion infoBtn;
    public static TextureRegion startBtn;

    public static BitmapFont[] fonts;
    public static Skin skin;

    @SuppressWarnings("deprecation")
    public static void load(){

        manager.load(MAINSCREEN_BG,Texture.class);
        manager.load(INFO_BUTTON,Texture.class);
        manager.load(START_BUTTON,Texture.class);

        manager.finishLoading();

        mainBg = new TextureRegion(manager.get(MAINSCREEN_BG,Texture.class));
        infoBtn = new TextureRegion(manager.get(INFO_BUTTON,Texture.class));
        startBtn = new TextureRegion(manager.get(START_BUTTON,Texture.class));
        
        // Fonts


        fonts = new BitmapFont[FONT_SIZES.length];
		
        skin = new Skin();
    }
    public static void dispose(){
        manager.dispose();
        
        for(BitmapFont f: fonts){
			f.dispose();
		}
    }
    
}
