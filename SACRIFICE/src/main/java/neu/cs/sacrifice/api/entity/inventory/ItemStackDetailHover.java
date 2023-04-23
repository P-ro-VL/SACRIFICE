package neu.cs.sacrifice.api.entity.inventory;

import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.UUID;

public class ItemStackDetailHover extends StackPane {

    public static final double POPUP_WIDTH = 280, POPUP_HEIGHT = 179;
    public static final double ITEM_ICON_WIDTH = 100, ITEM_ICON_HEIGHT = 60;
    public static final double ITEM_NAME_WIDTH = 145, ITEM_NAME_HEIGHT = 60;
    public static final double ITEM_LORE_WIDTH = 256, ITEM_LORE_HEIGHT = 75;

    public static ItemStackDetailHover build(ItemStack itemStack) {
        return new ItemStackDetailHover(itemStack);
    }

    private ItemStackDetailHover(ItemStack itemStack) {
        setId("item-detail-" + UUID.randomUUID());
        setPrefSize(POPUP_WIDTH, POPUP_HEIGHT);

        ImageView itemIcon = new ImageView();
        Image image = itemStack.getTexture().getImage();
        itemIcon.setImage(image);
        itemIcon.setFitWidth(ITEM_ICON_WIDTH);
        itemIcon.setFitHeight(ITEM_ICON_HEIGHT);
        itemIcon.setX(12);
        itemIcon.setY(12);

        Text itemName = new Text(itemStack.getItemMeta().getDisplayName());
        itemName.setFill(Color.WHITE);
        itemName.setWrappingWidth(ITEM_NAME_WIDTH);
        itemName.prefWidth(ITEM_NAME_WIDTH);
        itemName.prefHeight(ITEM_NAME_HEIGHT);
        itemName.setX(123);
        itemName.setY(12);

        Separator separator = new Separator();
        separator.setPrefWidth(ITEM_LORE_WIDTH);

        StringBuilder loreBuilder = new StringBuilder();
        itemStack.getItemMeta().getLore().forEach(lore -> loreBuilder.append(lore).append("\n"));
        Text itemLore = new Text(loreBuilder.toString());
        itemLore.setFill(Color.WHITE);
        itemLore.setWrappingWidth(ITEM_LORE_WIDTH);
        itemLore.prefWidth(ITEM_LORE_WIDTH);
        itemLore.prefHeight(ITEM_LORE_HEIGHT);
        itemLore.setX(12);
        itemLore.setY(93);

        getChildren().addAll(itemIcon, itemName, separator, itemLore);
    }

}
