package vn.provl.instance.components.graphics;

import vn.provl.api.component.graphics.AbstractSprite;
import vn.provl.api.component.graphics.SpriteType;

import java.awt.image.BufferedImage;
import java.io.File;

public class SingleSprite extends AbstractSprite {

    public SingleSprite(String filePathFromResources) {
        super(filePathFromResources);
    }

    public SingleSprite(BufferedImage image) {
        super(image);
    }

    public SingleSprite(File file) {
        super(file);
    }

    @Override
    public SpriteType getType() {
        return SpriteType.SINGLE;
    }
}
