package com.mygdx.game.components;
import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.files.FileHandle;
public class Text implements Component {
    public static final ComponentMapper<Text> Map = ComponentMapper.getFor(Text.class);

    public String text;
    public BitmapFont font;
    public Text(String text) {
        this.text = text;
    }
    public Text(String text, FileHandle fontFile){
        this.text = text;
        this.font = new BitmapFont(fontFile);
    }
    public Text(String text,FileHandle fontFile, float[] rgba){
        this.text = text;
        this.font = new BitmapFont(fontFile);
    }
}