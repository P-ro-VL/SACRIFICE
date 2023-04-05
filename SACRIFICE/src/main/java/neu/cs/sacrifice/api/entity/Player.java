package neu.cs.sacrifice.api.entity;

public interface Player extends Entity {
    public static final int PLAYER_WIDTH = 100, PLAYER_HEIGHT = 250;

    void move(Direction direction);

    void stopMoving();

}
