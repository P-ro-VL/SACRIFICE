package neu.cs.sacrifice.api.entity;

import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import neu.cs.sacrifice.api.scene.GameScene;

import java.util.Map;

public interface Entity {

    Map<ActionType, AnimationChannel> getAnimationMap();

    AnimatedTexture getTexture();

    PhysicsComponent getPhysicsBehaviour();

    com.almasb.fxgl.entity.Entity toFXGLEntity();

    GameScene getScene();

    void remove();

    void setScene(GameScene scene);

}
