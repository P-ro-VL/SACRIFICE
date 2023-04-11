package neu.cs.sacrifice.api.event.type;

import javafx.geometry.Point2D;
import neu.cs.sacrifice.api.entity.Direction;
import neu.cs.sacrifice.api.entity.Player;
import neu.cs.sacrifice.api.event.Cancellable;
import neu.cs.sacrifice.api.event.Event;

public class PlayerMoveEvent extends Event implements Cancellable {
    private final Player player;
    private final Point2D from;
    private final Point2D to;
    private final Direction movingDirection;
    private boolean isCancelled;

    public PlayerMoveEvent(Player player, Point2D from, Point2D to, Direction movingDirection){
        this.player = player;
        this.from = from;
        this.to = to;
        this.movingDirection = movingDirection;
    }

    public Player getPlayer() {
        return player;
    }

    public Point2D getFrom() {
        return from;
    }

    public Point2D getTo() {
        return to;
    }

    public Direction getMovingDirection() {
        return movingDirection;
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
