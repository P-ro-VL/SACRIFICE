package neu.cs.sacrifice.api.scene;

import com.almasb.fxgl.dsl.EntityBuilder;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ExpireCleanComponent;
import com.almasb.fxgl.dsl.views.ScrollingBackgroundView;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.animation.FadeTransition;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;
import neu.cs.sacrifice.SACRIFICE;
import neu.cs.sacrifice.api.entity.Entity;
import neu.cs.sacrifice.api.entity.EntityType;
import neu.cs.sacrifice.api.entity.Player;
import neu.cs.sacrifice.api.event.type.PlayerInteractGameObjectEvent;
import neu.cs.sacrifice.api.event.type.PostGameSceneChangeEvent;
import neu.cs.sacrifice.api.event.type.PreGameSceneChangeEvent;
import neu.cs.sacrifice.api.object.GameObject;
import neu.cs.sacrifice.api.object.IGameObject;
import neu.cs.sacrifice.api.object.InteractType;
import neu.cs.sacrifice.api.utils.Loggers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public abstract class GameScene {

    private String sceneID;
    private Image background;
    private Point2D spawnPoint;

    private List<Entity> entities = new ArrayList<>();
    private List<GameObject> gameObjects = new ArrayList<>();

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
        try {
            this.background = new Image(new FileInputStream(new File("assets/textures/" + texturesPath)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    public void remove(GameObject gameObject){
        this.gameObjects.remove(gameObject);
        FXGL.getGameWorld().removeEntity(gameObject.getEntity());
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addObject(GameObject gameObject, double x, double y){
        this.gameObjects.add(gameObject);
        EntityBuilder builder = FXGL.entityBuilder()
                .type(EntityType.GAME_OBJECT)
                .bbox(new HitBox(BoundingShape.box(gameObject.getWidth(), gameObject.getHeight())))
                .with(gameObject)
                .view(gameObject.getTexture())
                .onClick((e) -> {
                    PlayerInteractGameObjectEvent playerInteractGameObjectEvent = new PlayerInteractGameObjectEvent
                            ((Player) SACRIFICE.getInstance().getPlayer(), gameObject, InteractType.MOUSE_CLICK);
                    SACRIFICE.getInstance().getEventManagingService().callEvent(playerInteractGameObjectEvent);

                    if(playerInteractGameObjectEvent.isCancelled()) return;
                    gameObject.onInteract(InteractType.MOUSE_CLICK);
                })
                .at(x,y);
        if(gameObject.getLivingTime() != null){
            builder.with(new ExpireCleanComponent(gameObject.getLivingTime()).animateOpacity());
        }
        if(gameObject.isCollidable()){
            builder.with(new CollidableComponent(true));
        }
        com.almasb.fxgl.entity.Entity entity = builder.build();
        entity.setProperty("game-object", gameObject);
        FXGL.getGameWorld().addEntity(entity);
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
            SACRIFICE.getInstance().getPlayer().toFXGLEntity().setPosition(100, 100);

            Loggers.info("Switched game scene to " + goTo.getSceneID());
            synchronized (FXGL.getGameWorld().getEntities()) {
                List<com.almasb.fxgl.entity.Entity> currentEntities = FXGL.getGameWorld().getEntities();
                for (int i = 0; i < currentEntities.size(); i++)
                    FXGL.getGameWorld().removeEntity(currentEntities.get(i));
            }

            goTo.initScene();
            SACRIFICE.getInstance().getPlayer().toFXGLEntity().setPosition(100, 100);

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
        return FXGL.entityBuilder()
                .type(EntityType.BACKGROUND).view(scrollingBackgroundView).zIndex(-1).build();
    }

    public abstract EntityFactory getSceneEntityFactory();
}
