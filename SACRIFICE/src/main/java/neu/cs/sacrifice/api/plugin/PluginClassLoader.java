package neu.cs.sacrifice.api.plugin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Assert;
import org.yaml.snakeyaml.error.YAMLException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PluginClassLoader extends URLClassLoader{

    private SacrificePlugin plugin;

    public SacrificePlugin getLoadedPlugin() {
        return plugin;
    }

    public PluginClassLoader(File file, ClassLoader parent) throws IOException, MalformedURLException {
        super(new URL[]{file.toURI().toURL()},parent);
        PluginDescriptionFile description = getPluginDescription(file);

        JarFile jarFile;
        try {
            Method runtimeVersionMethod = JarFile.class.getMethod("runtimeVersion");
            Object runtimeVersion = runtimeVersionMethod.invoke((Object)null);
            Constructor<JarFile> constructor = JarFile.class.getConstructor(File.class, Boolean.TYPE, Integer.TYPE, runtimeVersion.getClass());
            jarFile = (JarFile)constructor.newInstance(file, true, 1, runtimeVersion);
        } catch (Exception var15) {
            jarFile = new JarFile(file);
        }

        try {
            Class<?> jarClass;
            try {
                jarClass = Class.forName(description.getMainClass(), true, this);
            } catch (ClassNotFoundException var12) {
                throw new FileNotFoundException("Cannot find main class `" + description.getMainClass() + "'");
            }

            Class<?> pluginClass;
            try {
                pluginClass = jarClass.asSubclass(SacrificePlugin.class);
            } catch (ClassCastException var11) {
                throw new ClassCastException("main class `" + description.getMainClass() + "' does not extend SacrificePlugin");
            }

            this.plugin = (SacrificePlugin) pluginClass.newInstance();
            this.plugin.setPluginDescriptionFile(description);
        } catch (IllegalAccessException var13) {
            throw new UnknownError("No public constructor");
        } catch (InstantiationException var14) {
            throw new UnknownError("Abnormal plugin type");
        }
    }


    public @NotNull PluginDescriptionFile getPluginDescription(File file) {
        Assert.assertNotNull("File cannot be null", file);
        JarFile jar = null;
        InputStream stream = null;

        PluginDescriptionFile var5 = null;
        try {
            jar = new JarFile(file);
            JarEntry entry = jar.getJarEntry("plugin.yml");
            if (entry == null) {
                throw new FileNotFoundException("Jar does not contain plugin.yml");
            }

            stream = jar.getInputStream(entry);
            var5 = new PluginDescriptionFile(stream);
        } catch (IOException | YAMLException var18) {
            var18.printStackTrace();
        } finally {
            if (jar != null) {
                try {
                    jar.close();
                } catch (IOException ignored) {
                }
            }

            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ignored) {
                }
            }
        }

        return var5;
    }
}
