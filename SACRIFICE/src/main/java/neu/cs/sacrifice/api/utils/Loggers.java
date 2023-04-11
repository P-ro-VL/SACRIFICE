package neu.cs.sacrifice.api.utils;

import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.dsl.FXGL;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Loggers {

    /**
     * If this variable is switched to {@code true} then every logging messages will be
     * printed to console in every {@link com.almasb.fxgl.app.ApplicationMode}.<br>
     * Default value is {@code false}.
     */
    public static boolean bypassDeveloperDebug = false;
    public static String LOGGING_PREFIX = "[SACRIFICE - " + FXGL.getSettings().getVersion() + "] ";
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static void log(String message, LoggingLevel loggingLevel) {
        if (!checkLoggingCondition()) return;
        System.out.println(DATE_FORMAT.format(new Date()) + " [" + loggingLevel.toString() + "] " + LOGGING_PREFIX + message);
    }

    public static void info(String message) {
        log(message, LoggingLevel.INFO);
    }

    public static void warning(String message) {
        log(message, LoggingLevel.WARNING);
    }

    public static void severe(String message) {
        log(message, LoggingLevel.SEVERE);
    }

    private static boolean checkLoggingCondition() {
        if (bypassDeveloperDebug) return true;
        return FXGL.getSettings().getApplicationMode() == ApplicationMode.DEVELOPER;
    }

    public static enum LoggingLevel {
        INFO,
        WARNING,
        SEVERE
    }

}
