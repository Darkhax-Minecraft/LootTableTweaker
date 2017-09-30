package net.darkhax.lttweaker.removal;

public class ModTableRemover implements IRemover {

    private final String modid;

    public ModTableRemover (String table) {

        this.modid = table;
    }

    @Override
    public boolean removeTable (String table) {

        final String[] parts = table.split(":");
        return this.modid.equalsIgnoreCase(parts[0]);
    }

    @Override
    public String toString () {

        return "Mod Table: " + this.modid;
    }
}