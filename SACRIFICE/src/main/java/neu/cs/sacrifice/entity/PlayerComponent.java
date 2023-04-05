package neu.cs.sacrifice.entity;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.image.Image;
import javafx.util.Duration;
import neu.cs.sacrifice.SACRIFICE;
import neu.cs.sacrifice.api.entity.ActionType;
import neu.cs.sacrifice.api.entity.Direction;
import neu.cs.sacrifice.api.entity.Player;
import neu.cs.sacrifice.api.scene.GameScene;

import java.util.HashMap;
import java.util.Map;
public class PlayerComponent extends Component implements Player {

    private Map<ActionType, AnimationChannel> animationChannelMap = new HashMap<>();
    private PhysicsComponent physicsComponent;
    private AnimatedTexture animatedTexture;

    private GameScene scene;

    public PlayerComponent() {
        Image image = new Image("/assets/textures/player.png");

        animationChannelMap.put(ActionType.IDLE, new AnimationChannel(image,
                4, PLAYER_WIDTH, PLAYER_HEIGHT, Duration.seconds(1), 0, 0));
        animationChannelMap.put(ActionType.WALKING, new AnimationChannel(image,
                4, PLAYER_WIDTH, PLAYER_HEIGHT, Duration.seconds(1), 1, 3));

        this.animatedTexture = new AnimatedTexture(getAnimationMap().get(ActionType.IDLE));
        this.animatedTexture.loop();
    }

    @Override
    public void onAdded() {
        getEntity().getViewComponent().addChild(getTexture());
    }

    @Override
    public Map<ActionType, AnimationChannel> getAnimationMap() {
        return this.animationChannelMap;
    }

    @Override
    public AnimatedTexture getTexture() {
        return animatedTexture;
    }

    @Override
    public PhysicsComponent getPhysicsBehaviour() {
        return physicsComponent;
    }

    @Override
    public Entity toFXGLEntity() {
        return getEntity();
    }

    @Override
    public GameScene getScene() {
        return scene;
    }

    @Override
    public void remove() {
        this.scene.remove(getEntity());
    }

    @Override
    public void setScene(GameScene scene) {
        this.scene = scene;
    }

    @Override
    public void onUpdate(double tpf) {
        getEntity().setY(SACRIFICE.WINDOW_HEIGHT - getEntity().getHeight());
        if (getPhysicsBehaviour().isMovingX()) {
            getTexture().loopAnimationChannel(getAnimationMap().get(ActionType.WALKING));
        } else {
            getTexture().loopAnimationChannel(getAnimationMap().get(ActionType.IDLE));
        }
    }

    @Override
    public void move(Direction direction) {
        switch (direction) {
            case LEFT -> {
                getEntity().setScaleX(-1);
                getPhysicsBehaviour().setVelocityX(-170 * 2);
            }
            case RIGHT -> getPhysicsBehaviour().setVelocityX(170 * 2);
            default -> {
            }
        }
    }

    @Override
    public void stopMoving() {
        getPhysicsBehaviour().setVelocityX(0);
    }
}
