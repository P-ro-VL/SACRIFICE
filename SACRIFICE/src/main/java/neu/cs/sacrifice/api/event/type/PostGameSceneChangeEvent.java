package neu.cs.sacrifice.api.event.type;

import neu.cs.sacrifice.api.event.Event;
import neu.cs.sacrifice.api.scene.GameScene;

public class PostGameSceneChangeEvent extends Event {


    private final GameScene fromScene, toScene;

    public PostGameSceneChangeEvent(GameScene from, GameScene to) {
        this.fromScene = from;
        this.toScene = to;
    }

    public GameScene getFromScene() {
        return fromScene;
    }

    public GameScene getToScene() {
        return toScene;
    }

}
