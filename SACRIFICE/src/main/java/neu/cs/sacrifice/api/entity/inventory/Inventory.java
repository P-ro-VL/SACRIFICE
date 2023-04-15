package neu.cs.sacrifice.api.entity.inventory;

import java.util.List;

public interface Inventory {

    public List<ItemStack> getItems();

    public ItemStack getItemAt(int slot);

    public void clear();

    public void addItem(ItemStack itemStack);

    public boolean hasAtLeast(ItemStack itemStack, int amount);

    public void removeItem(ItemStack itemStack, int amount);

}
