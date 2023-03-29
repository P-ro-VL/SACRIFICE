package vn.provl.instance.components.entity.behaviour;

import vn.provl.api.component.entity.Entity;
import vn.provl.api.component.entity.behaviour.EntityBehaviour;
import vn.provl.instance.components.entity.attribute.EntityMovingSpeedAttribute;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.stream.Collectors;

public class EntityMovingBehaviour implements EntityBehaviour {

    private MovingBehaviourKeyboardType movingBehaviourKeyboardType;
    private Entity behaviourOwner;

    public MovingBehaviourKeyboardType getMovingKeyboardType() {
        return movingBehaviourKeyboardType;
    }

    @Override
    public Entity getBehaviourOwner() {
        return behaviourOwner;
    }

    public void move(MovingDirection movingDirection) {
        double movingSpeed = getBehaviourOwner().getAttribute(EntityMovingSpeedAttribute.class).get();
        getBehaviourOwner().setX(getBehaviourOwner().getX()
                + Math.pow(-1, movingDirection.ordinal()) * movingSpeed);
    }

    public MovingDirection getMovingDirection(String keyPressed) {
        if (!movingBehaviourKeyboardType.validate(keyPressed)) return null;
        if (getMovingKeyboardType() == MovingBehaviourKeyboardType.LEFT_RIGHT_ARROW) {
            switch (keyPressed) {
                case "left":
                    return MovingDirection.LEFT;
                case "right":
                    return MovingDirection.RIGHT;
            }
        } else if (getMovingKeyboardType() == MovingBehaviourKeyboardType.A_D_BUTTON) {
            switch (keyPressed) {
                case "A":
                    return MovingDirection.LEFT;
                case "D":
                    return MovingDirection.RIGHT;
            }
        }
        return null;
    }

    public static enum MovingDirection {
        LEFT,
        RIGHT;
    }

    public static enum MovingBehaviourKeyboardType {
        A_D_BUTTON("A", "D"),
        LEFT_RIGHT_ARROW("", ""),
        AUTO(null),
        CONTROLLED(null);

        String[] pressableKeys;

        MovingBehaviourKeyboardType(@Nullable String... pressableKeys) {
            this.pressableKeys = pressableKeys;
        }

        /**
         * Check if the given key is match the moving keyboard type.
         *
         * @param key The key to be checked
         * @return {@code true} if matched, {@code false} otherwise.
         */
        public boolean validate(String key) {
            return pressableKeys != null && Arrays.stream(pressableKeys)
                    .map(String::toLowerCase).toList()
                    .contains(key.toLowerCase());
        }
    }

}
