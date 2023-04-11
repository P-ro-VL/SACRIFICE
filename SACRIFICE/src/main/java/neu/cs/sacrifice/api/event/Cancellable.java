package neu.cs.sacrifice.api.event;

public interface Cancellable {

    void setCancelled(boolean isCancelled);

    boolean isCancelled();

}
