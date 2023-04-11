package neu.cs.sacrifice.api.event.type;

import neu.cs.sacrifice.api.entity.Player;
import neu.cs.sacrifice.api.event.Cancellable;
import neu.cs.sacrifice.api.event.Event;
import neu.cs.sacrifice.api.object.GameObject;
import neu.cs.sacrifice.api.object.InteractType;

public class PlayerInteractGameObjectEvent extends Event implements Cancellable {

    private final GameObject gameObject;
    private final Player player;
    private final InteractType interactType;
    private boolean isCancelled;

    public PlayerInteractGameObjectEvent(Player player, GameObject gameObject, InteractType interactType){
        this.player = player;
        this.gameObject = gameObject;
        this.interactType = interactType;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public Player getPlayer() {
        return player;
    }

    public InteractType getInteractType() {
        return interactType;
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
