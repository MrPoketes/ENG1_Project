package com.mygdx.game.screens;

import com.mygdx.game.YorkDragonBoatRace;
import com.mygdx.game.utils.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.utils.BoatData;

public class MainMenu extends AbstractScreen {
    
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    // All Rectangle class variables are hitboxes for the event listener to work

    // Button hitboxes
    Rectangle startBtnRect;
    Rectangle infoBtnRect;

    // Boat select hitboxes
    Rectangle brownRect;
    Rectangle cyanRect;
    Rectangle redRect;
    Rectangle whiteRect;
    Rectangle pinkRect;
    Rectangle greenRect;

    // touchPoint is the mouse/ pointer
    Vector3 touchPoint;

    private Stage stage;
    private int selectedBoat = 0;

    public MainMenu(YorkDragonBoatRace game){
        // The constructor just assigns default values for variables
        super(game);

        stage = new Stage();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        font = new BitmapFont();
        // Making the font bigger
        font.getData().setScale(2,2);

        infoBtnRect = new Rectangle(20,50,300,150);
        startBtnRect = new Rectangle(1200,50,300,150);
        
        cyanRect = new Rectangle(70,640,150,150);
        brownRect = new Rectangle(320,640,150,150);
        redRect = new Rectangle(570,640,150,150);
        whiteRect = new Rectangle(820,640,150,150);
        pinkRect = new Rectangle(1070,640,150,150);
        greenRect = new Rectangle(1320,640,150,150);

        touchPoint = new Vector3();
        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void hide(){
        super.hide();
        batch.dispose();
        stage.dispose();
    }
    @Override
    public void resize(int width,int height){
        super.resize(width,height);
        camera.setToOrtho(false);
        camera.update();
        stage.getViewport().update(width,height);
    }
    @Override
    public void render(float delta){
        super.render(delta);
        // Event Listener
        handleInput();

        //Initialise drawing stuff:
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(Assets.mainBg,0,0,1980,1080);

        batch.draw(Assets.startBtn,1200,30,300,150);
        batch.draw(Assets.infoBtn,20,30,300,150);

        drawStars(selectedBoat);
        drawBoatSelect();
        drawText();

        batch.end();
        stage.act(delta);
        stage.draw();
    }
    // 
    private void drawText(){
        font.draw(batch,"Top Speed",350,500);
        font.draw(batch,"Acceleration",350,420);
        font.draw(batch,"Maneuverability",350,340);
        font.draw(batch,"Robustness",350,260);
        font.draw(batch,BoatData.getEntityData().get(selectedBoat).get("name") + " boat",710,580);
        font.draw(batch,"Select a boat",710,140);
    }
    // A click event listener that when fired will call appropriate functions to handle the event
    private void handleInput(){
        if(Gdx.input.justTouched()){
            camera.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));

            // Event Listener for Start Button click
            handleScreenChange();

            // Event Listener for boats
            handleBoatSelect();
        }
    }

    // Click handler function for changing the screen
    private void handleScreenChange(){
        if(startBtnRect.contains(touchPoint.x,touchPoint.y)){
            game.setScreen(new GameScreen(game, 1, selectedBoat));
        }
        // Event Listener for Info button click
        else if(infoBtnRect.contains(touchPoint.x,touchPoint.y)){
            game.setScreen(new InfoScreen(game));
        }
    }

    // Click handler function for changing the selected boat
    private void handleBoatSelect(){
        if(cyanRect.contains(touchPoint.x,touchPoint.y)){
            selectedBoat = 0;
        }
        else if(brownRect.contains(touchPoint.x,touchPoint.y)){
            selectedBoat = 1;
        }
        else if(redRect.contains(touchPoint.x,touchPoint.y)){
            selectedBoat = 2;
        }
        else if(whiteRect.contains(touchPoint.x,touchPoint.y)){
            selectedBoat = 3;
        }
        else if(pinkRect.contains(touchPoint.x,touchPoint.y)){
            selectedBoat = 4;
        }
        else if(greenRect.contains(touchPoint.x,touchPoint.y)){
            selectedBoat = 5;
        }
    }

    /*
    Function that draws stars for stats of the boat.
    Checks what the current selected boat is and loops through the boat stats to draw the needed amount of gold and gray stars
    */
    private void drawStars(int boatIndex){
        int[] stats = BoatData.starCount[boatIndex];
        int[] yLevels = {460,380,300,220};
        for(int i=0;i!=4;i++){
            int goldenStars = 0;
            int y = yLevels[i];
            int x = 600;
            for(int j=0;j!=5;j++){
                if(goldenStars != stats[i]){
                    batch.draw(Assets.goldStar,x,y,80,80);
                    goldenStars+=1;
                }
                else{
                    batch.draw(Assets.grayStar,x,y,80,80);
                }
                x+=100;
            }
        }
    }
    private void drawBoatSelect(){
        // x+250 each time
        batch.draw(Assets.cyanSelect,70,640,150,150);
        batch.draw(Assets.brownSelect,320,640,150,150);
        batch.draw(Assets.redSelect,570,640,150,150);
        batch.draw(Assets.whiteSelect,820,640,150,150);
        batch.draw(Assets.pinkSelect,1070,640,150,150);
        batch.draw(Assets.greenSelect,1320,640,150,150);
    }
    @Override
    public void dispose(){
        batch.dispose();
        stage.dispose();
    }
}
