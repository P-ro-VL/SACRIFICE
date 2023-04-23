package neu.cs.sacrifice.api.entity.inventory;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StandardItemMeta implements ItemMeta {

    private String displayName;
    private List<String> lore = new ArrayList<>();


    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public List<String> getLore() {
        return this.lore;
    }

    @Override
    public StandardItemMeta setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    @Override
    public StandardItemMeta setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }

    @Override
    public ItemMeta setLore(String... lores) {
        return setLore(Arrays.asList(lores));
    }

    @Override
    public boolean equals(@NotNull Object object) {
        if (!(object instanceof ItemMeta o)) return false;
        boolean displayNameMatch = o.getDisplayName() == null ?
                getDisplayName() == null : o.getDisplayName().equals(this.getDisplayName());
        boolean loreMatch = o.getLore().equals(this.getLore());
        return displayNameMatch == loreMatch;
    }
}
