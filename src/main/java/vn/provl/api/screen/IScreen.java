package vn.provl.api.screen;

import vn.provl.api.component.IComponent;

import javax.swing.*;
import java.awt.*;

public interface IScreen {

    void initializeComponents();

    void add(IComponent component);

    void show();

    void hide();

    void update();

    WindowStartupLocation getStartupLocation();

    JFrame getSwingFrame();

    IScreenGraphics getGraphics();

    String getID();

}
