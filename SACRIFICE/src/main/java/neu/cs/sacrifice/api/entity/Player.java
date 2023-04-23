package neu.cs.sacrifice.api.entity;

import neu.cs.sacrifice.entity.player.PlayerInventory;

public interface Player extends Entity {
    public static final int PLAYER_WIDTH = 100, PLAYER_HEIGHT = 250;

    void sendMessage(String message);

    void sendTitle(String title);

    void sendNotification(String notificationMessage);

    void sendActionBar(String message);

    PlayerInventory getInventory();

}
