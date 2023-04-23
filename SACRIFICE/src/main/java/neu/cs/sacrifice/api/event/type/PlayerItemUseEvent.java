package neu.cs.sacrifice.api.event.type;

import neu.cs.sacrifice.api.entity.Entity;
import neu.cs.sacrifice.api.entity.Player;
import neu.cs.sacrifice.api.entity.inventory.ItemStack;
import neu.cs.sacrifice.api.event.Cancellable;
import neu.cs.sacrifice.api.event.Event;
import neu.cs.sacrifice.api.object.GameObject;

public class PlayerItemUseEvent extends Event implements Cancellable {

    private final Player player;
    private final ItemStack itemStack;
    private final Entity entity;
    private final GameObject gameObject;
    private boolean isCancelled;

    public PlayerItemUseEvent(Player player, ItemStack itemStack, Entity entity, GameObject gameObject){
        this.player = player;
        this.itemStack = itemStack;
        this.entity = entity;
        this.gameObject = gameObject;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Entity getEntity() {
        return entity;
    }

    public GameObject getGameObject() {
        return gameObject;
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
