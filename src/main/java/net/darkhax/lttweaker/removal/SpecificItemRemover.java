package net.darkhax.lttweaker.removal;

import net.minecraft.item.Item;

public class SpecificItemRemover implements IRemover {

    private final String table;
    private final String pool;
    private final String item;

    public SpecificItemRemover (String table, String pool, String item) {

        this.table = table;
        this.pool = pool;
        this.item = item;
    }

    @Override
    public boolean removeItem (String table, String pool, Item item) {

        return this.table.equalsIgnoreCase(table) && this.pool.equalsIgnoreCase(pool) && this.item.equalsIgnoreCase(item.getRegistryName().toString());
    }

    @Override
    public String toString () {

        return "Table: " + this.table + " Pool: " + this.pool + " Item: " + this.item;
    }
}