package net.darkhax.lttweaker.removal;

public class SpecificEntryRemover implements IRemover {

    private final String table;
    private final String pool;
    private final String entry;

    public SpecificEntryRemover (String table, String pool, String entry) {

        this.table = table;
        this.pool = pool;
        this.entry = entry;
    }

    @Override
    public boolean removeEntry (String table, String pool, String entry) {

        return this.table.equalsIgnoreCase(table) && this.pool.equals(pool) && this.entry.equalsIgnoreCase(entry);
    }

    @Override
    public String toString () {

        return "Table: " + this.table + " Pool: " + this.pool + " Entry: " + this.entry;
    }
}