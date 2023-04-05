package neu.cs.sacrifice.api.entity;

public interface Player extends Entity {

    void move(Direction direction);

    void stopMoving();

}
