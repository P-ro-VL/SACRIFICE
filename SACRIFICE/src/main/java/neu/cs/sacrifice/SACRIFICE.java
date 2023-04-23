package neu.cs.sacrifice;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.Viewport;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.input.virtual.VirtualButton;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.input.KeyCode;
import neu.cs.sacrifice.api.entity.Direction;
import neu.cs.sacrifice.api.entity.EntityType;
import neu.cs.sacrifice.api.entity.Player;
import neu.cs.sacrifice.api.entity.inventory.ItemStack;
import neu.cs.sacrifice.api.entity.inventory.StandardItemMeta;
import neu.cs.sacrifice.api.event.EventManagingService;
import neu.cs.sacrifice.api.event.type.PlayerInteractGameObjectEvent;
import neu.cs.sacrifice.api.object.GameObject;
import neu.cs.sacrifice.api.object.InteractType;
import neu.cs.sacrifice.api.plugin.PluginManager;
import neu.cs.sacrifice.api.scene.GameScene;
import neu.cs.sacrifice.entity.EntityBuilder;
import neu.cs.sacrifice.entity.player.PlayerComponent;
import neu.cs.sacrifice.scene.TestMoi;
import org.junit.Assert;
import org.junit.Test;

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
        this.eventManagingService.registerListener(new TestMoi());
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
        initKeyboardInputs();
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0, WINDOW_HEIGHT);

        initCollidingEvents();
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

    public Player getPlayer() {
        return player.getComponent(PlayerComponent.class);
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

    public void setGameScenes(String sceneID){
        GameScene scene = gameScenes.get(sceneID);
        Assert.assertNotNull("The game scene with id '" + sceneID + "' has not been registered!", scene);
        setGameScenes(scene);
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

    private void initKeyboardInputs() {
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
                ItemStack item = new ItemStack("key.png")
                        .setItemMeta(new StandardItemMeta().setDisplayName("Chìa khóa").setLore("Dùng để mở cửa"));
                ItemStack item2 = new ItemStack("amulet.png")
                        .setItemMeta(new StandardItemMeta().setDisplayName("Bùa trấn trạch").setLore("Dùng để ngăn không cho ma quỷ vào nhà"));
                ItemStack item3 = new ItemStack("mantra.png")
                        .setItemMeta(new StandardItemMeta().setDisplayName("Kinh A Di Đà").setLore("Bộ kinh thường dùng để siêu độ các linh hồn..."));
                ItemStack item4 = new ItemStack("incenses.png")
                        .setItemMeta(new StandardItemMeta().setDisplayName("Bó hương").setLore("Còn đang tỏa khói nghi ngút"));

                getPlayer().getInventory().addItem(item);
                getPlayer().getInventory().addItem(item2);
                getPlayer().getInventory().addItem(item3);
                getPlayer().getInventory().addItem(item4);
            }

            @Override
            protected void onActionEnd() {
                super.onActionEnd();
            }
        }, KeyCode.X, VirtualButton.X);
    }

    private void initCollidingEvents() {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.GAME_OBJECT) {
            @Override
            protected void onCollisionBegin(Entity a, Entity b) {
                GameObject gameObject = b.getProperties().getObject("game-object");

                PlayerInteractGameObjectEvent playerInteractGameObjectEvent =
                        new PlayerInteractGameObjectEvent(getPlayer(), gameObject, InteractType.COLLIDE);
                SACRIFICE.getInstance().getEventManagingService().callEvent(playerInteractGameObjectEvent);

                if (playerInteractGameObjectEvent.isCancelled()) return;
                gameObject.onInteract(InteractType.COLLIDE);
            }
        });
    }

}
