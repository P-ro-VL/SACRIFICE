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
import neu.cs.sacrifice.api.scene.GameScene;
import neu.cs.sacrifice.entity.EntityBuilder;
import neu.cs.sacrifice.entity.PlayerComponent;
import neu.cs.sacrifice.scene.TestScene;

import java.util.ArrayList;
import java.util.List;

public class SACRIFICE extends GameApplication {

    public static final int WINDOW_WIDTH = 1280, WINDOW_HEIGHT = 756;

    public static Entity player;
    public static Viewport viewport;

    private List<GameScene> gameScenes = new ArrayList<>();

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
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0, WINDOW_HEIGHT);
    }

    @Override
    protected void onPreInit() {
        gameScenes.add(new TestScene());
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

        for (GameScene gameScene : gameScenes) {
            gameScene.initScene();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
