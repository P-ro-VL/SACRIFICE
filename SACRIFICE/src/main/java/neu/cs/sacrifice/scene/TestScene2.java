package neu.cs.sacrifice.scene;

import com.almasb.fxgl.entity.EntityFactory;
import neu.cs.sacrifice.api.scene.GameScene;

public class TestScene2 extends GameScene {
    public TestScene2() {
        super("hihi");

        setBackground("f2.png");
    }

    @Override
    public EntityFactory getSceneEntityFactory() {
        return null;
    }
}
