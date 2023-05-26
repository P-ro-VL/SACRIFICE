package neu.cs.sacrifice.scene;

import com.almasb.fxgl.dsl.FXGL;
import neu.cs.sacrifice.api.entity.Player;
import neu.cs.sacrifice.api.entity.inventory.ItemStack;
import neu.cs.sacrifice.api.event.EventHandler;
import neu.cs.sacrifice.api.event.EventListener;
import neu.cs.sacrifice.api.event.type.PlayerItemUseEvent;
import neu.cs.sacrifice.api.object.GameObject;

public class TestMoi implements EventListener {

    @EventHandler
    public void onSceneChange(PlayerItemUseEvent e){
        Player p = e.getPlayer();
        ItemStack i = e.getItemStack();
        GameObject g = e.getGameObject();

        FXGL.getNotificationService().pushNotification("Interact " + g.getID() + " by " + i.getItemMeta().getDisplayName());

    }

}
