package vn.provl.instance.screen;

import vn.provl.api.screen.IScreenGraphics;

import javax.swing.*;
import java.awt.*;

public class ScreenGraphics extends JPanel implements IScreenGraphics {

    private Graphics2D graphics2D;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.graphics2D = graphics2D;
    }

    @Override
    public Graphics2D getGraphics() {
        return graphics2D;
    }

}
