package neu.cs.sacrifice.scene;

import com.almasb.fxgl.entity.EntityFactory;
import neu.cs.sacrifice.api.scene.GameScene;

public class TestScene extends GameScene {

    public TestScene() {
        super("test");

        setBackground("f1.png");
    }

    @Override
    public EntityFactory getSceneEntityFactory() {
        return null;
    }
}
