package neu.cs.sacrifice.api.utils;

import com.almasb.fxgl.texture.Texture;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TextureLoader {

    public static String TEXTURE_DIR = "assets/textures/";

    public static Texture loadTexture(String path) {
        return new Texture(loadImage(path));
    }

    public static Image loadImage(String path) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(TEXTURE_DIR + path));
            return new Image(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
