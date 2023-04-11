package neu.cs.sacrifice;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import javafx.scene.input.KeyCode;
import neu.cs.sacrifice.api.entity.Direction;
import neu.cs.sacrifice.api.event.EventManagingService;
import neu.cs.sacrifice.api.plugin.PluginManager;
import neu.cs.sacrifice.api.scene.GameScene;
import neu.cs.sacrifice.entity.EntityBuilder;
import neu.cs.sacrifice.entity.PlayerComponent;
import neu.cs.sacrifice.scene.TestMoi;
import neu.cs.sacrifice.scene.TestScene;
import neu.cs.sacrifice.scene.TestScene2;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SACRIFICE extends GameApplication {

    public static final int WINDOW_WIDTH = 1280, WINDOW_HEIGHT = 756;

    private static SACRIFICE instance;

    private Entity player;

    private Viewport viewport;

    private Map<String, GameScene> gameScenes = new HashMap<>();
    private GameScene currentGameScene;

    private EventManagingService eventManagingService;

    private PluginManager pluginManager;

    @Override
    protected void onPreInit() {
        instance = this;
        this.eventManagingService = new EventManagingService();
        this.pluginManager = new PluginManager();
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(WINDOW_WIDTH);
        gameSettings.setHeight(WINDOW_HEIGHT);

        gameSettings.setTitle("SACRIFICE");
        gameSettings.setApplicationMode(ApplicationMode.DEVELOPER);
    }

    @Override
    protected void initInput() {
        FXGL.getInput().addAction(new UserAction("left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).move(Direction.LEFT);
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stopMoving();
            }
        }, KeyCode.A, VirtualButton.LEFT);

        FXGL.getInput().addAction(new UserAction("right") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).move(Direction.RIGHT);
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stopMoving();
            }
        }, KeyCode.D, VirtualButton.RIGHT);

        FXGL.getInput().addAction(new UserAction("use") {
            @Override
            protected void onActionBegin() {
                currentGameScene.switchScene(gameScenes.get("hihi"));
                FXGL.getNotificationService().pushNotification("Đã chuyển cảnh"
                );
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
            }
        }, KeyCode.X, VirtualButton.X);
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0, WINDOW_HEIGHT);
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new EntityBuilder());
        player = FXGL.spawn("player", 100, 100);
        FXGL.set("player", player);

        viewport = FXGL.getGameScene().getViewport();
        viewport.setBounds(-1500, 0, 250 * 70, FXGL.getAppHeight());
        viewport.bindToEntity(player, FXGL.getAppWidth() / 2.0, FXGL.getAppHeight() / 2.0);
        viewport.setLazy(true);

        getPluginManager().loadAllPlugins();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Entity getPlayer() {
        return player;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public Map<String, GameScene> getAllScenes() {
        return this.gameScenes;
    }

    public GameScene getCurrentGameScene() {
        return currentGameScene;
    }

    public void setGameScenes(GameScene gameScene) {
        currentGameScene = gameScene;
        gameScene.initScene();
    }

    public void registerGameScene(GameScene gameScene) {
        gameScenes.put(gameScene.getSceneID(), gameScene);
    }

    public EventManagingService getEventManagingService() {
        return eventManagingService;
    }

    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public static SACRIFICE getInstance() {
        return instance;
    }

    //////////////////////////////////////////////////

}
