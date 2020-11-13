import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.components.ScreenPosition;
import com.mygdx.game.components.Size;
import com.mygdx.game.components.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.Iterator;

public class RenderBoxes extends EntitySystem {
    private SpriteBatch batch; 
    private final Family family; 

    public RenderBoxes(SpriteBatch batch) { 
        super(); 
        this.family.all(Size.class, ScreenPosition.class, Box.class);
        this.batch = batch;
    }

    @override 
    public void addedToEngine(Engine engine) { 
       batch = new SpriteBatch();
    }

    @override 
    public void update(float deltaTime) { 
        batch.begin();
        for (Entity entity: this.getEngine().getEntitiesFor(family)){
            float x = entity.getComponent(ScreenPosition.class).x;
            float y = entity.getComponent(ScreenPosition.class).y;

            Integer xSize = entity.getComponent(Size.class).x;
            Integer ySize = entity.getComponent(Size.class).y;
            
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.rect(x,y,xSize,ySize);
        }
        batch.end();
    }


}