package vn.provl.api.component.graphics;

import org.junit.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class AbstractSprite implements Sprite {

    private BufferedImage textureImage;

    public AbstractSprite(String filePathFromResources) {
        try {
            URL resourceUrl = this.getClass().getResource(filePathFromResources);
            Assert.assertNotNull("Cannot load sprite from path '" + filePathFromResources + "' !", resourceUrl);
            this.textureImage = ImageIO.read(resourceUrl);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public AbstractSprite(BufferedImage image){
        Assert.assertNotNull("Cannot load null sprite image !", image);
        this.textureImage = image;
    }

    public AbstractSprite(File file){
        try {
            ImageIO.read(file);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    @Override
    public BufferedImage getTextureImage() {
        return textureImage;
    }
}
