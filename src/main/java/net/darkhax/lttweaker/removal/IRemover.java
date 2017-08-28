package net.darkhax.lttweaker.removal;

import net.minecraft.item.Item;

public interface IRemover {
    
    default boolean removeTable(String table) {
        
        return false;
    }
    
    default boolean removePool(String table, String pool) {
        
        return false;
    }
    
    default boolean removeEntry(String table, String pool, String entry) {
        
        return false;
    }
    
    default boolean removeItem(String table, String pool, Item item) {
        
        return false;
    }
}
