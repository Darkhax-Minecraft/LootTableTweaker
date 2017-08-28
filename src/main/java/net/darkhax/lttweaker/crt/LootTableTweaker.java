package net.darkhax.lttweaker.crt;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import net.darkhax.lttweaker.removal.GlobalItemRemover;
import net.darkhax.lttweaker.removal.ModEntryRemover;
import net.darkhax.lttweaker.removal.ModItemRemover;
import net.darkhax.lttweaker.removal.ModTableRemover;
import net.darkhax.lttweaker.removal.SpecificEntryRemover;
import net.darkhax.lttweaker.removal.SpecificItemRemover;
import net.darkhax.lttweaker.removal.SpecificPoolRemover;
import net.darkhax.lttweaker.removal.SpecificTableRemover;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.ltt.LootTable")
public class LootTableTweaker {

    @ZenMethod
    public void removeTable(String table) {
        
        CraftTweakerAPI.apply(new ActionRemoval(new SpecificTableRemover(table)));
    }
       
    @ZenMethod
    public void removePool(String table, String pool) {
        
        CraftTweakerAPI.apply(new ActionRemoval(new SpecificPoolRemover(table, pool)));
    }
    
    @ZenMethod
    public void removeEntry(String table, String pool, String entry) {
        
        CraftTweakerAPI.apply(new ActionRemoval(new SpecificEntryRemover(table, pool, entry)));
    }
    
    @ZenMethod
    public void removeItem(String table, String pool, String item) {
        
        CraftTweakerAPI.apply(new ActionRemoval(new SpecificItemRemover(table, pool, item)));
    }
    
    @ZenMethod
    public void removeModEntry(String modid) {
        
        CraftTweakerAPI.apply(new ActionRemoval(new ModEntryRemover(modid)));
    }
    
    @ZenMethod
    public void removeModItem(String modid) {
        
        CraftTweakerAPI.apply(new ActionRemoval(new ModItemRemover(modid)));
    }
    
    @ZenMethod
    public void removeModTable(String modid) {
        
        CraftTweakerAPI.apply(new ActionRemoval(new ModTableRemover(modid)));
    }
    
    @ZenMethod
    public void removeGlobalItem(String itemId) {
        
        CraftTweakerAPI.apply(new ActionRemoval(new GlobalItemRemover(itemId)));
    }
}