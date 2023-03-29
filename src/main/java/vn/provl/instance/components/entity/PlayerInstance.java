package vn.provl.instance.components.entity;

import vn.provl.api.component.entity.Player;
import vn.provl.api.component.entity.attribute.Attribute;
import vn.provl.api.component.entity.behaviour.EntityBehaviour;
import vn.provl.api.component.graphics.Sprite;
import vn.provl.api.screen.IScreen;

public class PlayerInstance implements Player {

    // Position
    private double x;
    private double y;

    // Sprite
    private Sprite playerSprite;

    // State
    private boolean visible;
    private boolean destroyed = false;

    public PlayerInstance(Sprite sprite){
        setSprite(sprite);
    }

    @Override
    public void refresh(IScreen parentScreen) {

    }

    @Override
    public void destroy() {
        setVisible(false);
        destroyed = true;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void setID(String Id) {
        throw new UnsupportedOperationException("Cannot change Player component ID");
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public String getID() {
        return "PLAYER";
    }

    @Override
    public void setSprite(Sprite sprite) {
        this.playerSprite = sprite;
    }

    @Override
    public Sprite getSprite() {
        return playerSprite;
    }

    @Override
    public <T extends Attribute> T getAttribute(Class<T> attributeType) {
        return null;
    }

    @Override
    public <T extends EntityBehaviour> T getBehaviour(Class<T> behaviourType) {
        return null;
    }

}
