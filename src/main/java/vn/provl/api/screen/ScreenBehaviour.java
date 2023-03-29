package vn.provl.api.screen;

import vn.provl.api.action.Interact;

public interface ScreenBehaviour {

    void onShow();

    void onClose();

    void onInteract(Interact interactType);

}
