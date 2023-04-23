package neu.cs.sacrifice.api.entity.inventory;

import com.almasb.fxgl.texture.Texture;
import neu.cs.sacrifice.api.utils.TextureLoader;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ItemStack implements Serializable {

    private String rawTextureFile;
    private transient Texture texture;
    private int amount = 1;
    private Map<String, Object> metadataMap = new HashMap<>();
    private ItemMeta itemMeta = new StandardItemMeta();

    public ItemStack(Texture texture) {
        this.texture = texture;
    }

    public ItemStack(String textureFile) {
        this.rawTextureFile = textureFile;
        this.texture = TextureLoader.loadTexture(textureFile);
    }

    public String getRawTextureFile() {
        return rawTextureFile;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getAmount() {
        return amount;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }

    public <T> T getMetadata(String key) {
        return (T) metadataMap.get(key);
    }

    public <T> ItemStack setMetadata(String key, T value) {
        metadataMap.put(key, value);
        return this;
    }

    public ItemStack setTexture(Texture texture) {
        this.texture = texture;
        return this;
    }

    public ItemStack setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public ItemStack add(int quantity){
        setAmount(getAmount() + quantity);
        return this;
    }

    public ItemStack setItemMeta(ItemMeta itemMeta) {
        this.itemMeta = itemMeta;
        return this;
    }

    public boolean compare(@NotNull ItemStack another, boolean checkAmount) {
        boolean amountCheck = true;
        if(checkAmount) amountCheck = (this.getAmount() == another.getAmount());
        boolean textureCheck = this.getRawTextureFile().equals(another.getRawTextureFile());
        return amountCheck && textureCheck && getItemMeta().equals(another.getItemMeta());
    }


}
