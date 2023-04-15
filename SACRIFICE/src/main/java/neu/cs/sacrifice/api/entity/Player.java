package neu.cs.sacrifice.api.entity;

import neu.cs.sacrifice.api.entity.inventory.Inventory;

public interface Player extends Entity {
    public static final int PLAYER_WIDTH = 100, PLAYER_HEIGHT = 250;

    void sendMessage(String message);

    void sendTitle(String title);

    void sendNotification(String notificationMessage);

    void sendActionBar(String message);

    Inventory getInventory();

}
