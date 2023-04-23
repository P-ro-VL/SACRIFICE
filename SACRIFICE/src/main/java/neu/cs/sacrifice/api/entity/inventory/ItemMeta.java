package neu.cs.sacrifice.api.entity.inventory;

import java.io.Serializable;
import java.util.List;

public interface ItemMeta extends Serializable {

    public String getDisplayName();

    public List<String> getLore();

    public ItemMeta setDisplayName(String displayName);

    public ItemMeta setLore(List<String> lore);

    public ItemMeta setLore(String...lores);

}
