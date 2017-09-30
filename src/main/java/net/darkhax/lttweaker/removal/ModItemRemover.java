package net.darkhax.lttweaker.removal;

import net.minecraft.item.Item;

public class ModItemRemover implements IRemover {

    private final String modid;

    public ModItemRemover (String table) {

        this.modid = table;
    }

    @Override
    public boolean removeItem (String table, String pool, Item item) {

        return this.modid.equalsIgnoreCase(item.getRegistryName().getResourceDomain());
    }

    @Override
    public String toString () {

        return "Mod Item: " + this.modid;
    }
}