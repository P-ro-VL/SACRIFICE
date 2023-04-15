package neu.cs.sacrifice.api.entity;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import neu.cs.sacrifice.SACRIFICE;
import neu.cs.sacrifice.api.event.type.EntityMoveEvent;
import neu.cs.sacrifice.api.scene.GameScene;

import java.util.HashMap;
import java.util.Map;

public class AbstractEntity extends Component implements Entity {
    private Map<ActionType, AnimationChannel> animationChannelMap = new HashMap<>();
    private PhysicsComponent physicsComponent;
    private AnimatedTexture animatedTexture;

    private GameScene scene;

    private ActionType currentAction;

    private Map<EntityAttribute, Object> attributeMaps = new HashMap<>();

    private String ID;

    public AbstractEntity(String ID){
        this.ID = ID;

        setAttribute(EntityAttribute.MOVING_SPEED, 2d);
    }

    @Override
    public void onAdded() {
        getEntity().getViewComponent().addChild(getTexture());
    }

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public void addAnimation(ActionType actionType, AnimationChannel animationChannel, boolean isMainTexture) {
        animationChannelMap.put(actionType, animationChannel);
        if(isMainTexture){
            this.animatedTexture = new AnimatedTexture(animationChannel);

            setCurrentAction(actionType);
        }
    }

    @Override
    public ActionType getCurrentAction() {
        return currentAction;
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
    public com.almasb.fxgl.entity.Entity toFXGLEntity() {
        return getEntity();
    }

    @Override
    public GameScene getScene() {
        return scene;
    }

    @Override
    public double getX() {
        return getEntity().getX();
    }

    @Override
    public double getY() {
        return getEntity().getY();
    }

    @Override
    public void remove() {
        getEntity().removeFromWorld();
    }

    @Override
    public <T> T getAttribute(EntityAttribute attribute) {
        return (T) attributeMaps.get(attribute);
    }

    @Override
    public void setScene(GameScene scene) {
        this.scene = scene;
    }

    @Override
    public void setX(double x) {
        getEntity().setX(x);
    }

    @Override
    public void setY(double y) {
        getEntity().setY(y);
    }

    @Override
    public void setCurrentAction(ActionType currentAction) {
        this.currentAction = currentAction;
        if(getAnimationMap().containsKey(currentAction))
        {
            AnimationChannel animationChannel = getAnimationMap().get(currentAction);
            getTexture().loopAnimationChannel(animationChannel);
        }
    }

    @Override
    public <T> void setAttribute(EntityAttribute attribute, T value) {
        this.attributeMaps.put(attribute, value);
    }

    @Override
    public void move(Direction direction) {
        Point2D pos = getEntity().getPosition();
        Point2D toOrigin = new Point2D(pos.getX(), pos.getY());
        toOrigin.add(new Point2D((direction == Direction.LEFT ? -170 : 170) * 2, getPhysicsBehaviour().getVelocityY()));
        EntityMoveEvent moveEvent = new EntityMoveEvent(this, getEntity().getPosition(), toOrigin, direction);
        SACRIFICE.getInstance().getEventManagingService().callEvent(moveEvent);

        if (moveEvent.isCancelled()) return;

        switch (direction) {
            case LEFT -> {
                getEntity().setScaleX(-1);
                getPhysicsBehaviour().setVelocityX(-170 * (double)getAttribute(EntityAttribute.MOVING_SPEED));
            }
            case RIGHT -> getPhysicsBehaviour().setVelocityX(170 * (double)getAttribute(EntityAttribute.MOVING_SPEED));
            default -> {
            }
        }
    }

    @Override
    public void stopMoving() {
        getPhysicsBehaviour().setVelocityX(0);
    }
}
