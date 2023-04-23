package neu.cs.sacrifice.entity.player;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.SubScene;
import neu.cs.sacrifice.api.entity.inventory.Inventory;
import neu.cs.sacrifice.api.entity.inventory.ItemStack;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PlayerInventory implements Inventory {

    public static final int INVENTORY_CAPACITY = 6;

    private ItemStack[] itemStacks = new ItemStack[INVENTORY_CAPACITY];
    private PlayerInventoryUI uiComponent;

    private boolean dirty;

    public PlayerInventory() {
        uiComponent = new PlayerInventoryUI();
    }

    @Override
    public SubScene getUIComponent() {
        return null;
    }

    @Override
    public List<ItemStack> getItems() {
        return Arrays.stream(itemStacks).filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public ItemStack getItemAt(int slot) {
        Assert.assertFalse("The slot must be an integer in range of [0,4]", slot < 0 || slot > 4);
        return itemStacks[slot];
    }

    @Override
    public int getItemSlot(ItemStack itemStack) {
        for (int i = 0; i < INVENTORY_CAPACITY; i++) {
            ItemStack item = itemStacks[i];
            if (item == null) continue;
            if (item.compare(itemStack, false)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int firstEmptySlot() {
        for (int i = 0; i < INVENTORY_CAPACITY; i++)
            if (itemStacks[i] == null) return i;
        return -1;
    }

    @Override
    public boolean isDirty() {
        return dirty;
    }

    @Override
    public void clear() {
        Arrays.fill(itemStacks, null);
    }

    @Override
    public boolean addItem(ItemStack itemStack) {
        ItemStack stackable = findStackable(itemStack);
        if (stackable != null) {
            stackable.add(itemStack.getAmount());
            return true;
        } else {
            int firstEmpty = firstEmptySlot();
            if (firstEmpty != -1) {
                setItem(firstEmpty, itemStack);
                return true;
            }
        }
        return false;
    }

    @Override
    public void setItem(int slot, ItemStack itemStack) {
        Assert.assertFalse("The slot must be an integer in range of [0,4]", slot < 0 || slot > 4);
        itemStacks[slot] = itemStack;
    }

    private ItemStack findStackable(ItemStack o) {
        for (ItemStack itemStack : itemStacks) {
            if (itemStack == null) continue;
            if (itemStack.compare(o, false)) return itemStack;
        }
        return null;
    }

    @Override
    public boolean hasAtLeast(ItemStack itemStack, int amount) {
        int slot = getItemSlot(itemStack);
        if (slot == -1) return false;
        return itemStacks[slot].getAmount() >= amount;
    }

    @Override
    public void removeItem(ItemStack itemStack, int amount) {
        int slot = getItemSlot(itemStack);
        if (slot < 0) return;
        itemStacks[slot].add(-amount);
    }

    @Override
    public void markDirty(boolean dirty) {
        this.dirty = dirty;
    }

    @Override
    public void updateInventory() {
        uiComponent.update(this);
    }
}
