package net.darkhax.lttweaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.ltt.LootTable")
public class LootTableTweaker {

    public static final List<String> tablesToClear = new ArrayList<>();

    public static final Map<String, List<String>> poolsToClear = new HashMap<>();

    public static final Map<String, Map<String, List<String>>> entriesToClear = new HashMap<>();

    @ZenMethod
    public static void clearTable (String tableName) {

        tablesToClear.add(tableName);
    }

    @ZenMethod
    public static void removePool (String tableName, String poolName) {

        List<String> poolNames = poolsToClear.get(tableName);

        if (poolNames == null) {
            poolNames = new ArrayList<>();
        }

        poolNames.add(poolName);

        poolsToClear.put(tableName, poolNames);
    }

    @ZenMethod
    public static void removeEntry (String tableName, String poolName, String entryName) {

        Map<String, List<String>> pools = entriesToClear.get(tableName);

        if (pools == null) {
            pools = new HashMap<>();
        }

        List<String> entries = pools.get(poolName);

        if (entries == null) {
            entries = new ArrayList<>();
        }

        entries.add(entryName);

        pools.put(poolName, entries);
        entriesToClear.put(tableName, pools);
    }
}