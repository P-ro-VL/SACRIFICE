package neu.cs.sacrifice.api.scene;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.EntityFactory;
import javafx.animation.FadeTransition;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import neu.cs.sacrifice.SACRIFICE;
import neu.cs.sacrifice.api.entity.Entity;
import neu.cs.sacrifice.api.event.type.PostGameSceneChangeEvent;
import neu.cs.sacrifice.api.event.type.PreGameSceneChangeEvent;
import neu.cs.sacrifice.api.utils.Loggers;

import java.util.ArrayList;
import java.util.List;

public abstract class GameScene {

    private String sceneID;
    private Image background;
    private Point2D spawnPoint;

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

    public Point2D getSpawnPoint() {
        return spawnPoint;
    }

    public void setBackground(Image background) {
        this.background = background;
    }

    public void setBackground(String texturesPath) {
        this.background = FXGL.getAssetLoader().loadImage(texturesPath);
    }

    public void setSpawnPoint(Point2D spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    public void setSpawnPoint(double x, double y) {
        setSpawnPoint(new Point2D(x, y));
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void addEntity(String entityName, int x, int y) {
        FXGL.getGameWorld().spawn(entityName, x, y);
    }

    public void addEntity(Entity entity, double x, double y) {
        this.entities.add(entity);
        com.almasb.fxgl.entity.Entity fxglEntity = entity.toFXGLEntity();
        fxglEntity.setX(x);
        fxglEntity.setY(y);
        FXGL.getGameWorld().addEntity(fxglEntity);
    }

    public void remove(Entity entity) {
        this.entities.remove(entity);
        FXGL.getGameWorld().removeEntity(entity.toFXGLEntity());
    }

    public final void initScene() {
        // Reset the entity factory in case switch between scenes
        FXGL.getGameWorld().addEntity(buildBackground());
        SACRIFICE.getInstance().getViewport().setBounds(0, 0, getBackground().widthProperty().intValue(), SACRIFICE.WINDOW_HEIGHT);
    }

    public final void switchScene(GameScene goTo) {
        if (!SACRIFICE.getInstance().getAllScenes().containsKey(goTo.getSceneID())) {
            throw new IllegalArgumentException("The GameScene whose id is '"
                    + goTo.getSceneID() + "' has not been registered yet.");
        }

        GameScene pre = this;
        PreGameSceneChangeEvent gameSceneChangeEvent = new PreGameSceneChangeEvent(pre, goTo);
        SACRIFICE.getInstance().getEventManagingService().callEvent(gameSceneChangeEvent);

        if (gameSceneChangeEvent.isCancelled()) return;

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), FXGL.getGameScene().getContentRoot());
        fadeIn.setFromValue(1.0);
        fadeIn.setToValue(0.0);
        fadeIn.play();

        fadeIn.setOnFinished((e) -> {
            synchronized (FXGL.getGameWorld().getEntities()) {
                List<com.almasb.fxgl.entity.Entity> currentEntities = FXGL.getGameWorld().getEntities();
                for (int i = 0; i < currentEntities.size(); i++)
                    FXGL.getGameWorld().removeEntity(currentEntities.get(i));
            }

            goTo.initScene();
            SACRIFICE.getInstance().getPlayer().setPosition(100, 100);

            Loggers.info("Switched game scene to " + goTo.getSceneID());
            synchronized (FXGL.getGameWorld().getEntities()) {
                List<com.almasb.fxgl.entity.Entity> currentEntities = FXGL.getGameWorld().getEntities();
                for (int i = 0; i < currentEntities.size(); i++)
                    FXGL.getGameWorld().removeEntity(currentEntities.get(i));
            }

            goTo.initScene();
            SACRIFICE.getInstance().getPlayer().setPosition(100, 100);

            Loggers.info("Switched game scene to " + goTo.getSceneID());

            FadeTransition ft = new FadeTransition(Duration.seconds(2), FXGL.getGameScene().getContentRoot());
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();

            PostGameSceneChangeEvent postGameSceneChangeEvent = new PostGameSceneChangeEvent(pre, goTo);
            SACRIFICE.getInstance().getEventManagingService().callEvent(postGameSceneChangeEvent);
        });

    }

    private com.almasb.fxgl.entity.Entity buildBackground() {
        ScrollingBackgroundView scrollingBackgroundView = new ScrollingBackgroundView(getBackground(),
                SACRIFICE.WINDOW_WIDTH, SACRIFICE.WINDOW_HEIGHT);
        return FXGL.entityBuilder().view(scrollingBackgroundView).zIndex(-1).build();
    }

    public abstract EntityFactory getSceneEntityFactory();
}
