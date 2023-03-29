package vn.provl.instance.components.graphics;

import vn.provl.api.component.AbstractComponent;
import vn.provl.api.component.IComponent;
import vn.provl.api.screen.IScreen;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageComponent extends AbstractComponent {

    private BufferedImage source;

    public ImageComponent(String ID, BufferedImage source){
        super(ID);
        this.source = source;
    }

    public BufferedImage getSource() {
        return source;
    }

    @Override
    public void refresh(IScreen parentScreen) {
        Graphics2D graphics2D = parentScreen.getGraphics().getGraphics();
        graphics2D.drawImage(getSource(), null, (int) getX(), (int) getY());
        graphics2D.dispose();
    }

}
