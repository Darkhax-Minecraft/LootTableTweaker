package net.darkhax.lttweaker.removal;

public class ModEntryRemover implements IRemover {

    private final String modid;

    public ModEntryRemover (String table) {

        this.modid = table;
    }

    @Override
    public boolean removeEntry (String table, String pool, String entry) {

        final String[] parts = entry.split(":");
        return this.modid.equalsIgnoreCase(parts[0]);
    }

    @Override
    public String toString () {

        return "Mod Entry: " + this.modid;
    }
}