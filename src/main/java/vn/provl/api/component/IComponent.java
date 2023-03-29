package vn.provl.api.component;

import vn.provl.api.screen.IScreen;

public interface IComponent {

    void refresh(IScreen parentScreen);

    void destroy();

    void setVisible(boolean visible);

    void setPosition(double x, double y);

    void setX(double x);

    void setY(double y);

    void setID(String Id);

    boolean isDestroyed();

    boolean isVisible();

    double getX();

    double getY();

    String getID();

}
