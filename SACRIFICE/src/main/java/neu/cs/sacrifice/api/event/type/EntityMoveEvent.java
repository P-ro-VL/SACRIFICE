package neu.cs.sacrifice.api.event.type;

import javafx.geometry.Point2D;
import neu.cs.sacrifice.api.entity.Direction;
import neu.cs.sacrifice.api.entity.Entity;
import neu.cs.sacrifice.api.entity.Player;
import neu.cs.sacrifice.api.event.Cancellable;
import neu.cs.sacrifice.api.event.Event;

public class EntityMoveEvent extends Event implements Cancellable {
    private final Entity entity;
    private final Point2D from;
    private final Point2D to;
    private final Direction movingDirection;
    private boolean isCancelled;

    public EntityMoveEvent(Entity entity, Point2D from, Point2D to, Direction movingDirection){
        this.entity = entity;
        this.from = from;
        this.to = to;
        this.movingDirection = movingDirection;
    }

    public Entity getPlayer() {
        return entity;
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
