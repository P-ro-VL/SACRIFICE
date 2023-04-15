package neu.cs.sacrifice.entity;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.image.Image;
import javafx.util.Duration;
import neu.cs.sacrifice.SACRIFICE;
import neu.cs.sacrifice.api.entity.AbstractEntity;
import neu.cs.sacrifice.api.entity.ActionType;
import neu.cs.sacrifice.api.entity.Direction;
import neu.cs.sacrifice.api.entity.Player;
import neu.cs.sacrifice.api.scene.GameScene;
import neu.cs.sacrifice.api.utils.TextureLoader;

import java.util.HashMap;
import java.util.Map;

public class PlayerComponent extends AbstractEntity implements Player {

    public PlayerComponent() {
        super("player");

        Image image = new Image("/assets/textures/player.png");
        addAnimation(ActionType.IDLE, new AnimationChannel(image,
                4, PLAYER_WIDTH, PLAYER_HEIGHT, Duration.seconds(1), 0, 0), true);
        addAnimation(ActionType.WALKING, new AnimationChannel(image,
                4, PLAYER_WIDTH, PLAYER_HEIGHT, Duration.seconds(1), 1, 3), false);
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

}
