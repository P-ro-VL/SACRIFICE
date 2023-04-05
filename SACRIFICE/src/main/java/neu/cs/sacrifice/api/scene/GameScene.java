package neu.cs.sacrifice.api.scene;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import javafx.scene.image.Image;
import neu.cs.sacrifice.SACRIFICE;

import java.util.ArrayList;
import java.util.List;

public abstract class GameScene {

    private String sceneID;
    private Image background;

    private List<Entity> entities = new ArrayList<>();

    public GameScene(String ID) {
        this.sceneID = ID;
    }

    public String getSceneID() {
        return sceneID;
    }

    public Image getBackground() {
        return background;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public void setBackground(String texturesPath) {
        this.background = FXGL.getAssetLoader().loadImage(texturesPath);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void addEntity(String entityName, int x, int y) {
        FXGL.getGameWorld().spawn(entityName, x, y);
    }

    public void addEntity(Entity entity, double x, double y) {
        entity.setX(x);
        entity.setY(y);
        this.entities.add(entity);
        FXGL.getGameWorld().addEntity(entity);
    }

    public void remove(Entity entity) {
        this.entities.remove(entity);
        FXGL.getGameWorld().removeEntity(entity);
    }

    public final void initScene() {
        // Reset the entity factory in case switch between scenes
        FXGL.getGameWorld().addEntity(buildBackground());
        SACRIFICE.viewport.setBounds(0, 0, getBackground().widthProperty().intValue(), SACRIFICE.WINDOW_HEIGHT);
    }

    private Entity buildBackground() {
        ScrollingBackgroundView scrollingBackgroundView = new ScrollingBackgroundView(getBackground(),
                SACRIFICE.WINDOW_WIDTH, SACRIFICE.WINDOW_HEIGHT);
        return FXGL.entityBuilder().view(scrollingBackgroundView).zIndex(-1).build();
    }

    public abstract EntityFactory getSceneEntityFactory();
}
