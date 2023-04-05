package neu.cs.sacrifice.api.scene;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import javafx.scene.image.Image;
import neu.cs.sacrifice.SACRIFICE;

public abstract class GameScene {

    private String sceneID;
    private Image background;

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

    public final void initScene() {
        // Reset the entity factory in case switch between scenes
        FXGL.getGameWorld().addEntity(buildBackground());
        SACRIFICE.viewport.setBounds(0, 0, getBackground().widthProperty().intValue(), SACRIFICE.WINDOW_HEIGHT);
    }

    private Entity buildBackground(){
        ScrollingBackgroundView scrollingBackgroundView = new ScrollingBackgroundView(getBackground(),
                SACRIFICE.WINDOW_WIDTH, SACRIFICE.WINDOW_HEIGHT);
        return FXGL.entityBuilder().view(scrollingBackgroundView).zIndex(-1).build();
    }

    public abstract EntityFactory getSceneEntityFactory();
}
