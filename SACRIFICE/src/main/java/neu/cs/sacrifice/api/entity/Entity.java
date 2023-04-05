package neu.cs.sacrifice.api.entity;

import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;

import java.util.Map;

public interface Entity {

    Map<ActionType, AnimationChannel> getAnimationMap();

    AnimatedTexture getTexture();

    PhysicsComponent getPhysicsBehaviour();

}
