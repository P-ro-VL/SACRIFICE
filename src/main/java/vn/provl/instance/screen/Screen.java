package vn.provl.instance.screen;

import org.junit.Assert;
import vn.provl.Sacrifice;
import vn.provl.api.action.Interact;
import vn.provl.api.component.IComponent;
import vn.provl.api.screen.IScreen;
import vn.provl.api.screen.IScreenGraphics;
import vn.provl.api.screen.ScreenBehaviour;
import vn.provl.api.screen.WindowStartupLocation;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Screen implements IScreen, ScreenBehaviour {

    private String ID;

    private final Map<String, IComponent> childrenComponents = new ConcurrentHashMap<String, IComponent>();

    private JFrame screenFrame;
    private WindowStartupLocation startupLocation;
    private IScreenGraphics screenGraphics;

    public Screen(String ID){
        this.ID = ID;
    }

    public Screen(WindowStartupLocation startupLocation){
        this.startupLocation = startupLocation;
    }

    @Override
    public WindowStartupLocation getStartupLocation() {
        return startupLocation;
    }

    @Override
    public JFrame getSwingFrame() {
        return screenFrame;
    }

    public final void initializeComponents() {
        childrenComponents.values().stream().filter(component -> !component.isDestroyed())
                .forEach(component -> {
            component.refresh(this);
        });
        ((ScreenGraphics) screenGraphics).repaint();
    }

    public void show() {
        if(getSwingFrame() != null)
            getSwingFrame().setVisible(true);
        else {
            screenFrame = new JFrame();
            screenFrame.setMinimumSize(new
                    Dimension(Sacrifice.SCREEN_RESOLUTION_WIDTH, Sacrifice.SCREEN_RESOLUTION_HEIGHT));
            screenFrame.pack();

            ScreenGraphics graphics = new ScreenGraphics();
            screenFrame.add(graphics);
            this.screenGraphics = graphics;

            screenFrame.setVisible(true);

            if(getStartupLocation() != null){
                screenFrame.setLocation(getStartupLocation().calculateX(),
                        getStartupLocation().calculateY());
            }

            update();
        }
        onShow();
    }

    @Override
    public void hide() {
        if(getSwingFrame() != null){
            getSwingFrame().setVisible(false);
            onClose();
        }
    }

    @Override
    public void update() {
        initializeComponents();
    }

    @Override
    public IScreenGraphics getGraphics() {
        return screenGraphics;
    }

    @Override
    public String getID() {
        return ID;
    }

    public void add(IComponent component) {
        Assert.assertTrue("The component with id '" + component.getID() + " has existed!",
                childrenComponents.containsKey(component.getID()));
        childrenComponents.put(component.getID(), component);
    }

    //////////////////// SCREEN BEHAVIOUR below ////////////////////

    public void onClose() {
    }

    public void onInteract(Interact interactType) {
    }

    public void onShow() {
    }
}
