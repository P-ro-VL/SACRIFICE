package vn.provl.api.screen;

import vn.provl.Sacrifice;

import java.awt.*;

public enum WindowStartupLocation {

    DEFAULT(){
        @Override
        public int calculateX() {
            return 0;
        }

        @Override
        public int calculateY() {
            return 0;
        }
    },
    CENTER_PARENT {
        @Override
        public int calculateX() {
            return 0;
        }

        @Override
        public int calculateY() {
            return 0;
        }
    },
    CENTER_SCREEN {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        final int x = (int) ((dimension.getWidth() - Sacrifice.SCREEN_RESOLUTION_WIDTH) / 2);
        final int y = (int) ((dimension.getHeight() - Sacrifice.SCREEN_RESOLUTION_HEIGHT) / 2);
        @Override
        public int calculateX() {
            return x;
        }

        @Override
        public int calculateY() {
            return y;
        }
    };

    public abstract int calculateX();

    public abstract int calculateY();

}
