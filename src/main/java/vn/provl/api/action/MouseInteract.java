package vn.provl.api.action;

public enum MouseInteract implements Interact {

    LEFT_CLICK,
    RIGHT_CLICK,
    MIDDLE_CLICK,
    HOVER,
    ENTER,
    EXIT;

    @Override
    public String getInteractData() {
        return this.toString();
    }
}
