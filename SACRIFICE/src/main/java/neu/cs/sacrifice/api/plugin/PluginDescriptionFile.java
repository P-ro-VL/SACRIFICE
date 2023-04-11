package neu.cs.sacrifice.api.plugin;

import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class PluginDescriptionFile {

    private String pluginName;
    private String pluginVersion;
    private String[] authors;
    private String mainClass;

    public String getPluginName() {
        return pluginName;
    }

    public String getPluginVersion() {
        return pluginVersion;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getMainClass() {
        return mainClass;
    }

    public PluginDescriptionFile(InputStream readerStream) {
        Yaml yaml = new Yaml();
        loadMap(asMap(yaml.load(readerStream)));
    }

    private void loadMap(Map<?, ?> map){
        String[] requireFields = {"name", "version","author", "main"};
        for(String field : requireFields)
            if(!map.containsKey(field))
                throw new IllegalArgumentException("The plugin.yml file is missing '" + field + "' field!");

        this.pluginName = (String) map.get("name");
        this.pluginVersion = (String) map.get("version");
        this.authors = (String[]) map.get("author");
        this.mainClass = (String) map.get("main");
    }

    private @NotNull Map<?, ?> asMap(@NotNull Object object) {
        if (object instanceof Map) {
            return (Map<?, ?>) object;
        } else {
            throw new RuntimeException(object + " is not properly structured.");
        }
    }

}
