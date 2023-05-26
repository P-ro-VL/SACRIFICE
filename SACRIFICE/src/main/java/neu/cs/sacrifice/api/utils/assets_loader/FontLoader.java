package neu.cs.sacrifice.api.utils.assets_loader;

import com.almasb.fxgl.texture.Texture;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FontLoader {

    public static String FONT_DIR = "assets/ui/fonts/";

    public static Font loadFont(String fontFileName, int size, FontWeight weight){
        return new FontLoader().fileName(fontFileName).size(size).weight(weight).build();
    }

    public static Font loadFont(String fontFileName, int size) {
        return loadFont(fontFileName, size, FontWeight.NORMAL);
    }

    private String path;
    private int fontSize = 16;
    private FontWeight fontWeight = FontWeight.NORMAL;

    public FontLoader fileName(String fileName){
        this.path = FONT_DIR + fileName;
        return this;
    }

    public FontLoader path(String path) {
        this.path = path;
        return this;
    }

    public FontLoader size(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public FontLoader weight(FontWeight fontWeight){
        this.fontWeight = fontWeight;
        return this;
    }

    public Font build() {
        try (FileInputStream inputStream = new FileInputStream(path)) {
            Font font = Font.loadFont(inputStream, fontSize);
            inputStream.close();
            return Font.font(font.getFamily(), fontWeight, fontSize);
        } catch (IOException ignored) {
            return null;
        }
    }
}
