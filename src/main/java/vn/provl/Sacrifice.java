package vn.provl;

import vn.provl.api.screen.WindowStartupLocation;
import vn.provl.sacrifice.screen.MainMenuScreen;

import javax.swing.*;

public class Sacrifice extends JFrame {

    public static final int SCREEN_RESOLUTION_WIDTH = 1224;
    public static final int SCREEN_RESOLUTION_HEIGHT = 756;

    public static final int FPS_LIMIT = 60;

    public static void main(String[] args) {
        MainMenuScreen mainMenuScreen = new MainMenuScreen(WindowStartupLocation.CENTER_SCREEN);
        mainMenuScreen.show();
    }
}
