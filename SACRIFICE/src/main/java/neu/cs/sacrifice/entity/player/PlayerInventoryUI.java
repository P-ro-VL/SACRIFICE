package neu.cs.sacrifice.entity.player;

import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import neu.cs.sacrifice.SACRIFICE;
import neu.cs.sacrifice.api.entity.Entity;
import neu.cs.sacrifice.api.entity.inventory.Inventory;
import neu.cs.sacrifice.api.entity.inventory.ItemStack;
import neu.cs.sacrifice.api.entity.inventory.ItemStackDetailHover;
import neu.cs.sacrifice.api.event.type.PlayerItemUseEvent;
import neu.cs.sacrifice.api.object.Draggable;
import neu.cs.sacrifice.api.object.GameObject;
import neu.cs.sacrifice.api.scene.GameScene;

import java.util.LinkedList;
import java.util.Optional;

public class PlayerInventoryUI {

    public static final double INVENTORY_WIDTH = 600, INVENTORY_HEIGHT = 60;

    public static final double PREF_WIDTH = INVENTORY_WIDTH / PlayerInventory.INVENTORY_CAPACITY;
    public static final double PREF_HEIGHT = INVENTORY_HEIGHT;

    public static final double STARTING_SLOT_TRANSLATE_X = ((double) SACRIFICE.WINDOW_WIDTH / 2) - (INVENTORY_WIDTH / 2);
    public static final double STARTING_SLOT_TRANSLATE_Y = SACRIFICE.WINDOW_HEIGHT - (INVENTORY_HEIGHT + 32);

    private LinkedList<PlayerInventoryUISlot> uiSlots = new LinkedList<>();

    private boolean hasInited = false;

    public void init() {
        Pane root = FXGL.getSceneService().getCurrentScene().getContentRoot();

        for (int i = 0; i < PlayerInventory.INVENTORY_CAPACITY; i++) {
            int index = i;
            PlayerInventoryUISlot wrapperPanel = new PlayerInventoryUISlot(index);
            wrapperPanel.addEventHandler(MouseEvent.ANY, event -> {
                if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                    mouseHover(index);
                    wrapperPanel.getItemTextureViewer().setScaleX(1.2);
                    wrapperPanel.getItemTextureViewer().setScaleY(1.2);
                    wrapperPanel.setOpacity(1);
                } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                    wrapperPanel.getItemTextureViewer().setScaleX(1.0);
                    wrapperPanel.getItemTextureViewer().setScaleY(1.0);
                    wrapperPanel.setOpacity(0.4);
                }
            });
            Draggable.Nature draggableItem = new Draggable.Nature(wrapperPanel);
            draggableItem.addListener((draggableNature, dragEvent) -> {
                if (dragEvent == Draggable.Event.DragEnd) {
                    wrapperPanel.setTranslateX(wrapperPanel.getOriginalTranslateX());
                    wrapperPanel.setTranslateY(wrapperPanel.getOriginalTranslateY());

                    Point2D mouseWorldPos = FXGL.getInput().getMousePositionWorld();
                    GameScene currentScene = SACRIFICE.getInstance().getCurrentGameScene();
                    Entity entity = currentScene.getEntityAt(mouseWorldPos).orElse(null);
                    GameObject gameObject = currentScene.getGameObjectAt(mouseWorldPos).orElse(null);

                    PlayerItemUseEvent itemUseEvent = new PlayerItemUseEvent(SACRIFICE.getInstance().getPlayer(),
                            wrapperPanel.currentItem, entity, gameObject);
                    SACRIFICE.getInstance().getEventManagingService().callEvent(itemUseEvent);
                }
            });

            root.getChildren().add(wrapperPanel);
            uiSlots.add(wrapperPanel);
        }
    }

    public void update(Inventory inventory) {
        if (!hasInited) {
            init();
            hasInited = true;
        }

        clearItemDetails();
        int index = 0;
        for (ItemStack itemStack : inventory.getItems()) {
            update(itemStack, index);
            index++;
        }
    }

    public void update(ItemStack itemStack, int index) {
        PlayerInventoryUISlot playerInventoryUISlot = uiSlots.get(index);
        playerInventoryUISlot.setCurrentItem(itemStack);
    }

    public void mouseHover(int index) {
        PlayerInventoryUISlot playerInventoryUISlot = uiSlots.get(index);
        ItemStack itemStack = playerInventoryUISlot.currentItem;
        if (itemStack == null) return;
        FXGL.getGameScene().getContentRoot().getChildren().add(ItemStackDetailHover.build(itemStack));
    }

    private void clearItemDetails() {
        FXGL.getGameScene().getContentRoot().getChildren().removeIf(node -> node != null
                && node.getId() != null && node.getId().startsWith("item-detail"));
    }

    public static class PlayerInventoryUISlot extends StackPane {

        private ImageView itemTextureViewer;
        private Text itemAmountViewer;
        private ItemStack currentItem;

        private double originalTranslateX, originalTranslateY;

        public PlayerInventoryUISlot(int slotIndex) {
            double translateX = STARTING_SLOT_TRANSLATE_X + PREF_WIDTH * slotIndex;
            double translateY = STARTING_SLOT_TRANSLATE_Y;

            this.originalTranslateX = translateX;
            this.originalTranslateY = translateY;

            setOpacity(0.4);
            setId("inventory-slot-" + slotIndex);

            setMinSize(PREF_WIDTH, PREF_HEIGHT);
            setTranslateX(translateX);
            setTranslateY(translateY);

            itemTextureViewer = new ImageView();
            itemTextureViewer.setImage(null);

            itemAmountViewer = new Text("");
            itemAmountViewer.setFill(Color.WHITE);
            itemAmountViewer.setTranslateX(((PREF_WIDTH / 2) - 15));
            itemAmountViewer.setTranslateY(((PREF_HEIGHT / 2) - 15));

            getChildren().addAll(itemTextureViewer, itemAmountViewer);
        }

        public void setCurrentItem(ItemStack itemStack) {
            this.currentItem = itemStack;
            if (itemStack == null) {
                this.itemAmountViewer.setText("");
                this.itemTextureViewer.setImage(null);
                return;
            }
            this.itemAmountViewer.setText(String.valueOf(currentItem.getAmount()));
            this.itemTextureViewer.setImage(itemStack.getTexture().getImage());
        }

        public ImageView getItemTextureViewer() {
            return itemTextureViewer;
        }

        public Text getItemAmountViewer() {
            return itemAmountViewer;
        }

        public double getOriginalTranslateX() {
            return originalTranslateX;
        }

        public double getOriginalTranslateY() {
            return originalTranslateY;
        }
    }

}
