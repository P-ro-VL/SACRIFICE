package vn.provl.api.action;

public class KeyboardInteract implements Interact {

    private String keyboardData;

    public KeyboardInteract(String keyboardData){
        this.keyboardData = keyboardData;
    }

    @Override
    public String getInteractData() {
        return keyboardData;
    }
}
