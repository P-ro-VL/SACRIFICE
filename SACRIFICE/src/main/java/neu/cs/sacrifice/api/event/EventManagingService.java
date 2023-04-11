package neu.cs.sacrifice.api.event;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import javafx.util.Pair;
import neu.cs.sacrifice.api.utils.Loggers;

import java.lang.reflect.Method;
import java.util.Collection;

public class EventManagingService {
    private Multimap<String, Pair<Method, Object>> registeredEventListeners = ArrayListMultimap.create();

    public void registerListener(EventListener listener) {
        try {
            for (Method method : listener.getClass().getDeclaredMethods()) {
                EventHandler eventHandler = method.getAnnotation(EventHandler.class);
                if (eventHandler != null) {
                    Class<?> heuristicEventClass = method.getParameterTypes()[0];
                    if (!Event.class.isAssignableFrom(heuristicEventClass)) {
                        Loggers.warning("Cannot register event handler: " + listener.getClass().getName()
                                + "." + method.getName() + " because it's not an event!");
                        continue;
                    }
                    registeredEventListeners.put(heuristicEventClass.getName(), new Pair<>(method, listener));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Collection<Pair<Method, Object>> getAllListenersOf(Class<? extends Event> eventClass) {
        return registeredEventListeners.get(eventClass.getName());
    }

    public void callEvent(Event event) {
        Collection<Pair<Method, Object>> listenerMethods = getAllListenersOf(event.getClass());
        for (Pair<Method, Object> listenerMethod : listenerMethods) {
            try {
                Method method = listenerMethod.getKey();
                Object instance = listenerMethod.getValue();
                if(event instanceof Cancellable cancellable){
                    if(cancellable.isCancelled()) return;
                }
                method.invoke(instance, event);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
