package neu.cs.sacrifice.api.event.type;

import neu.cs.sacrifice.api.event.Cancellable;
import neu.cs.sacrifice.api.event.Event;
import neu.cs.sacrifice.api.scene.GameScene;

public class PreGameSceneChangeEvent extends Event implements Cancellable {

    private final GameScene fromScene, toScene;
    private boolean isCancelled;

    public PreGameSceneChangeEvent(GameScene from, GameScene to) {
        this.fromScene = from;
        this.toScene = to;
    }

    public GameScene getFromScene() {
        return fromScene;
    }

    public GameScene getToScene() {
        return toScene;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }
}
