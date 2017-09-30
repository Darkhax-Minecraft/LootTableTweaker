package net.darkhax.lttweaker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.darkhax.lttweaker.libs.Constants;
import net.darkhax.lttweaker.removal.IRemover;
import net.minecraft.util.Tuple;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

@Mod(modid = Constants.MODID, name = Constants.MOD_NAME, version = "@VERSION@", dependencies = "required-after:crafttweaker")
public class LTTMod {

    @Instance(Constants.MODID)
    public static LTTMod instance;

    private static Field pools;

    private static Field lootEntries;

    public static Map<String, LootTable> tables = new HashMap<>();
    public static List<IRemover> removal = new ArrayList<>();

    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {

        MinecraftForge.EVENT_BUS.register(this);

        pools = ReflectionHelper.findField(LootTable.class, "pools", "field_186466_c", "c");
        lootEntries = ReflectionHelper.findField(LootPool.class, "lootEntries", "field_186453_a", "a");
    }

    @EventHandler
    public void serverStarting (FMLServerStartingEvent event) {

        event.registerServerCommand(new CommandTableDump());
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onTablesLoad (LootTableLoadEvent event) {

        final String tableName = event.getName().toString();
        tables.put(tableName, event.getTable());

        final List<String> poolsToRemove = new ArrayList<>();
        final List<Tuple<LootPool, String>> entriesToRemove = new ArrayList<>();

        for (final IRemover remover : removal) {

            // Handles the removal of entire tables
            if (remover.removeTable(tableName)) {

                event.setCanceled(true);
                break;
            }

            for (final LootPool pool : getPools(event.getTable())) {

                final String poolName = pool.getName();

                // Handles the removal of specific pools
                if (remover.removePool(tableName, tableName)) {

                    poolsToRemove.add(poolName);
                    continue;
                }

                for (final LootEntry entry : getLootEntries(pool)) {

                    final String entryName = entry.getEntryName();

                    // Handles the removal of specific entries
                    if (remover.removeEntry(tableName, poolName, entryName)) {

                        entriesToRemove.add(new Tuple<>(pool, entryName));
                        continue;
                    }

                    // Handles the removal of specific item entries
                    else if (entry instanceof LootEntryItem) {

                        final LootEntryItem itemEntry = (LootEntryItem) entry;

                        if (itemEntry.item != null && itemEntry.item.getRegistryName() != null) {

                            if (remover.removeItem(tableName, poolName, itemEntry.item)) {

                                entriesToRemove.add(new Tuple<>(pool, entryName));
                                continue;
                            }
                        }
                    }
                }
            }
        }

        for (final String pool : poolsToRemove) {

            event.getTable().removePool(pool);
        }

        for (final Tuple<LootPool, String> entry : entriesToRemove) {

            entry.getFirst().removeEntry(entry.getSecond());
        }
    }

    public static List<LootPool> getPools (LootTable table) {

        try {

            return (List<LootPool>) pools.get(table);
        }

        catch (IllegalArgumentException | IllegalAccessException e) {

            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public static List<LootEntry> getLootEntries (LootPool pool) {

        try {
            return (List<LootEntry>) lootEntries.get(pool);
        }
        catch (IllegalArgumentException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}
