package vn.provl.instance.components.graphics;

import vn.provl.api.component.graphics.AbstractSprite;
import vn.provl.api.component.graphics.SpriteType;

import java.awt.image.BufferedImage;
import java.io.File;

public class SheetSprite extends AbstractSprite {

    private int subWidth;
    private int subHeight;

    public SheetSprite(String filePathFromResources, int subWidth, int subHeight) {
        super(filePathFromResources);
        this.subWidth = subWidth;
        this.subHeight = subHeight;
    }

    public SheetSprite(BufferedImage image, int subWidth, int subHeight) {
        super(image);
        this.subWidth = subWidth;
        this.subHeight = subHeight;
    }

    public SheetSprite(File file, int subWidth, int subHeight) {
        super(file);
        this.subWidth = subWidth;
        this.subHeight = subHeight;
    }

    @Override
    public SpriteType getType() {
        return SpriteType.SHEET;
    }

    public int getSubWidth() {
        return subWidth;
    }

    public int getSubHeight() {
        return subHeight;
    }

    public SingleSprite getSpriteAt(int X, int Y) {
        BufferedImage subImage = getTextureImage().getSubimage(X, Y, getSubWidth(), getSubHeight());
        return new SingleSprite(subImage);
    }

}
