package net.darkhax.lttweaker.removal;

import net.minecraft.item.Item;

public class GlobalItemRemover implements IRemover {
    
    private final String itemId;
    
    public GlobalItemRemover(String table) {
        
        this.itemId = table;
    }
    
    @Override
    public boolean removeItem(String table, String pool, Item item) {
        
        return itemId.equalsIgnoreCase(item.getRegistryName().toString());
    }
    
    @Override
    public String toString() {
        
        return "Global Item: " + this.itemId;
    }
}