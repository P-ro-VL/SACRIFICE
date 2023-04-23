package neu.cs.sacrifice.api.entity.inventory;

import com.almasb.fxgl.scene.SubScene;

import java.util.List;
public interface Inventory {

    public SubScene getUIComponent();

    public List<ItemStack> getItems();

    public ItemStack getItemAt(int slot);

    public int getItemSlot(ItemStack itemStack);

    public int firstEmptySlot();

    public boolean isDirty();

    public void clear();

    public boolean addItem(ItemStack itemStack);

    public void setItem(int slot, ItemStack itemStack);

    public boolean hasAtLeast(ItemStack itemStack, int amount);

    public void removeItem(ItemStack itemStack, int amount);

    public void markDirty(boolean dirty);

    public void updateInventory();

}
