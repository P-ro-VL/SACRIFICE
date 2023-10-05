package neu.cs.sacrifice.api.utils.assets_loader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TextLoader {

    public static String TEXT_DIR = "assets/text/";

    public static String loadText(String filePath) {
        try {
            return Files.readString(new File(filePath).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
