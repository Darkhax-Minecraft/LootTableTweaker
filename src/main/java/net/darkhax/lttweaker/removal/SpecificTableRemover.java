package net.darkhax.lttweaker.removal;

public class SpecificTableRemover implements IRemover {

    private final String name;

    public SpecificTableRemover (String table) {

        this.name = table;
    }

    @Override
    public boolean removeTable (String table) {

        return this.name.equalsIgnoreCase(table);
    }

    @Override
    public String toString () {

        return "Table: " + this.name;
    }
}
