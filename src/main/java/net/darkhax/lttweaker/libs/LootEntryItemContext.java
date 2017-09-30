package net.darkhax.lttweaker.libs;

import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;

public class LootEntryItemContext {

    private final LootPool pool;

    private final LootEntryItem entry;

    public LootEntryItemContext (LootPool pool, LootEntryItem entry) {

        this.pool = pool;
        this.entry = entry;
    }

    public LootPool getPool () {

        return this.pool;
    }

    public LootEntryItem getEntry () {

        return this.entry;
    }

    // Constants.LOG.info(String.format("|%s|%s|%s|%d|", pool.getName(),
    // entry.getEntryName(),
    // item.item.getRegistryName().toString(), item.weight));

    public String getPoolName () {

        return this.getPool().getName();
    }

    public String getEntryName () {

        return this.getEntry().getEntryName();
    }

    public String getItemName () {

        return this.getEntry().item.getRegistryName().toString();
    }

    public int getWeight () {

        return this.getEntry().weight;
    }
}
