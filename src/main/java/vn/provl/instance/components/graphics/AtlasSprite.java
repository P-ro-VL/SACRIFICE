package vn.provl.instance.components.graphics;

import java.awt.image.BufferedImage;
import java.io.File;

public class AtlasSprite extends SheetSprite {

    public AtlasSprite(String filePathFromResources, int subWidth, int subHeight) {
        super(filePathFromResources, subWidth, subHeight);
    }

    public AtlasSprite(BufferedImage image, int subWidth, int subHeight) {
        super(image, subWidth, subHeight);
    }

    public AtlasSprite(File file, int subWidth, int subHeight) {
        super(file, subWidth, subHeight);
    }

}
