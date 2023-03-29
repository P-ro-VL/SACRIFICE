package vn.provl.api.component;

public abstract class AbstractComponent implements IComponent {

    private double x, y;
    private boolean visible;
    private String ID;
    private boolean destroyed = false;

    public AbstractComponent(String ID){
        this.ID = ID;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public String getID() {
        return ID;
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
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void destroy() {
        this.destroyed = true;
    }

    @Override
    public void setID(String ID) {
        this.ID = ID;
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
    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
