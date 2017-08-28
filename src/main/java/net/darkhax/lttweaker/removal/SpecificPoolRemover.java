package net.darkhax.lttweaker.removal;

public class SpecificPoolRemover implements IRemover {
   
    private final String table;
    private final String pool;
    
    public SpecificPoolRemover(String table, String pool) {
        
        this.table = table;
        this.pool = pool;
    }
    
    @Override
    public boolean removePool(String table, String pool) {
        
        return this.table.equalsIgnoreCase(this.table) && this.pool.equalsIgnoreCase(pool);
    }
    
    @Override
    public String toString() {
        
        return "Table: " + table + " Pool: " + this.pool;
    }
}
