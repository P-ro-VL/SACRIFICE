package vn.provl.instance.screen;

import vn.provl.api.action.Interact;
import vn.provl.api.action.KeyboardInteract;
import vn.provl.api.action.MouseInteract;
import vn.provl.api.component.entity.Monster;
import vn.provl.api.component.entity.Player;
import vn.provl.api.screen.ScreenThread;
import vn.provl.instance.components.entity.behaviour.EntityMovingBehaviour;
import vn.provl.instance.components.graphics.ImageComponent;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameScreen extends Screen {

    private Player player;
    private List<Monster> monsters = Collections.synchronizedList(new ArrayList<>());

    private BufferedImage background;

    private ScreenThread screenThread;

    public GameScreen(String ID) {
        super(ID);
    }

    public void setBackground(BufferedImage background) {
        this.background = background;
    }

    public BufferedImage getBackground() {
        return background;
    }

    public Player getPlayer(){
        return player;
    }

    @Override
    public void onInteract(Interact interactType) {
        if(interactType instanceof MouseInteract){
            MouseInteract mouseInteract = (MouseInteract) interactType;

        } else {
            KeyboardInteract keyboardInteract = (KeyboardInteract) interactType;

            checkPlayerMovingBehaviour(keyboardInteract);
        }
    }

    private void checkPlayerMovingBehaviour(KeyboardInteract keyboardInteract){
        EntityMovingBehaviour playerMovingBehaviour = getPlayer().getBehaviour(EntityMovingBehaviour.class);
        String pressedData = keyboardInteract.getInteractData();
        if(playerMovingBehaviour.getMovingKeyboardType().validate(pressedData)) {
            EntityMovingBehaviour.MovingDirection movingDirection = playerMovingBehaviour
                    .getMovingDirection(pressedData);
            playerMovingBehaviour.move(movingDirection);
        }
    }

    @Override
    public void onShow() {
        super.onShow();

        this.screenThread = new ScreenThread(getID()) {
            @Override
            public void clock() {
                update();
            }
        };
    }

    private void initializeGameScreenEssentialComponents(){
        add(new ImageComponent("background", getBackground()));
    }


}
