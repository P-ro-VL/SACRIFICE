package neu.cs.sacrifice.api.plugin;

import com.google.common.collect.Maps;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class PluginManager {

    private Map<String, SacrificePlugin> loadedPlugins = Maps.newConcurrentMap();

    public void loadPlugin(File file) throws IOException {
        PluginClassLoader pluginClassLoader = new PluginClassLoader(file, this.getClass().getClassLoader());
        SacrificePlugin loadedPlugin = pluginClassLoader.getLoadedPlugin();
        loadedPlugins.put(loadedPlugin.getPluginDescriptionFile().getPluginName(), loadedPlugin);
    }

    public void loadAllPlugins() {
        File directory = new File("plugins");
        if (directory.listFiles() == null) return;
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory() || !file.getName().endsWith(".jar")) continue;
            try {
                loadPlugin(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
