package net.darkhax.lttweaker;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import minetweaker.MineTweakerAPI;
import minetweaker.MineTweakerImplementationAPI;
import minetweaker.mc1102.brackets.ItemBracketHandler;
import minetweaker.runtime.providers.ScriptProviderDirectory;
import net.darkhax.lttweaker.libs.Constants;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@Mod(modid = Constants.MODID, name = Constants.MOD_NAME, version = Constants.VERSION_NUMBER, dependencies = "required-after:MineTweaker3")
public class LTTMod {

    @Instance(Constants.MODID)
    public static LTTMod instance;

    private Field pools;

    private Field lootEntries;

    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {

        final File scriptDir = new File("Scripts", "LootTableTweaker");

        if (!scriptDir.exists()) {
            scriptDir.mkdir();
        }

        if (scriptDir.exists()) {

            MineTweakerAPI.registerBracketHandler(new ItemBracketHandler());
            ItemBracketHandler.rebuildItemRegistry();
            MineTweakerAPI.registerClass(LootTableTweaker.class);
            MineTweakerAPI.tweaker.setScriptProvider(new ScriptProviderDirectory(scriptDir));
            MineTweakerImplementationAPI.reload();
        }

        MinecraftForge.EVENT_BUS.register(this);
        MineTweakerAPI.registerClass(LootTableTweaker.class);

        this.pools = ReflectionHelper.findField(LootTable.class, "pools");
        this.lootEntries = ReflectionHelper.findField(LootPool.class, "lootEntries");
    }

    @SubscribeEvent
    public void onTablesLoad (LootTableLoadEvent event) {

        final String tableName = event.getName().toString();

        if (LootTableTweaker.tablesToClear.contains(tableName)) {

            this.getPools(event.getTable()).clear();
        }

        if (LootTableTweaker.poolsToClear.containsKey(tableName)) {

            for (final String poolName : LootTableTweaker.poolsToClear.get(tableName)) {

                event.getTable().removePool(poolName);
            }
        }

        if (LootTableTweaker.entriesToClear.containsKey(tableName)) {

            final Map<String, List<String>> pools = LootTableTweaker.entriesToClear.get(tableName);

            for (final String poolName : pools.keySet()) {

                final LootPool pool = event.getTable().getPool(poolName);

                if (pool != null) {
                    for (final String entryName : pools.get(pool.getName())) {

                        pool.removeEntry(entryName);
                    }
                }
            }
        }
    }

    private List<LootPool> getPools (LootTable table) {

        try {

            return (List<LootPool>) this.pools.get(table);

        }

        catch (IllegalArgumentException | IllegalAccessException e) {

            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public List<LootEntry> getLootEntries (LootPool pool) {

        try {
            return (List<LootEntry>) this.lootEntries.get(pool);
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}