package neu.cs.sacrifice.api.entity;

import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import neu.cs.sacrifice.api.scene.GameScene;

import java.util.Map;

public interface Entity {

    public String getID();

    public void addAnimation(ActionType actionType, AnimationChannel animationChannel, boolean isMainTexture);

    public ActionType getCurrentAction();

    Map<ActionType, AnimationChannel> getAnimationMap();

    AnimatedTexture getTexture();

    PhysicsComponent getPhysicsBehaviour();

    com.almasb.fxgl.entity.Entity toFXGLEntity();

    GameScene getScene();

    double getX();

    double getY();

    void remove();

    <T> T getAttribute(EntityAttribute attribute);

    void setScene(GameScene scene);

    void setX(double x);

    void setY(double y);

    void setCurrentAction(ActionType currentAction);

    <T> void setAttribute(EntityAttribute attribute, T value);

    void move(Direction movingDirection);

    void stopMoving();

}
