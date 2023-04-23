package neu.cs.sacrifice.entity.player;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.scene.image.Image;
import javafx.util.Duration;
import neu.cs.sacrifice.SACRIFICE;
import neu.cs.sacrifice.api.entity.AbstractEntity;
import neu.cs.sacrifice.api.entity.ActionType;
import neu.cs.sacrifice.api.entity.Player;

public class PlayerComponent extends AbstractEntity implements Player {

    private PlayerInventory inventory;

    public PlayerComponent() {
        super("player");

        Image image = new Image("/assets/textures/player.png");
        addAnimation(ActionType.IDLE, new AnimationChannel(image,
                4, PLAYER_WIDTH, PLAYER_HEIGHT, Duration.seconds(1), 0, 0), true);
        addAnimation(ActionType.WALKING, new AnimationChannel(image,
                4, PLAYER_WIDTH, PLAYER_HEIGHT, Duration.seconds(1), 1, 3), false);

        this.inventory = new PlayerInventory();
    }

    @Override
    public void onUpdate(double tpf) {
        getEntity().setY(SACRIFICE.WINDOW_HEIGHT - getEntity().getHeight());
        if (getPhysicsBehaviour().isMovingX()) {
            getTexture().loopAnimationChannel(getAnimationMap().get(ActionType.WALKING));
        } else {
            getTexture().loopAnimationChannel(getAnimationMap().get(ActionType.IDLE));
        }

        getInventory().updateInventory();
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void sendTitle(String title) {

    }

    @Override
    public void sendNotification(String notificationMessage) {

    }

    @Override
    public void sendActionBar(String message) {

    }

    @Override
    public PlayerInventory getInventory() {
        return inventory;
    }
}
