package vn.provl.instance.components.entity.attribute;

import vn.provl.api.component.entity.attribute.Attribute;
import vn.provl.api.component.entity.attribute.AttributeType;

public class EntityMovingSpeedAttribute implements Attribute<Double> {

    public static final double DEFAULT_MOVING_SPEED = 3d;
    private double movingSpeed;

    @Override
    public void set(Double newValue) {
        this.movingSpeed = newValue;
    }

    @Override
    public void reset() {
        set(DEFAULT_MOVING_SPEED);
    }

    @Override
    public Double get() {
        return movingSpeed;
    }

    @Override
    public Double getDefault() {
        return DEFAULT_MOVING_SPEED;
    }

    @Override
    public AttributeType getType() {
        return AttributeType.MOVING_SPEED;
    }
}
