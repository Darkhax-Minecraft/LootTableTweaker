package net.darkhax.lttweaker;

import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.SystemUtils;

import net.darkhax.lttweaker.libs.Constants;
import net.darkhax.lttweaker.libs.LootEntryItemContext;
import net.darkhax.lttweaker.libs.TableBuilder;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.LootTable;

public class CommandTableDump extends CommandBase {

    @Override
    public String getName () {

        return "dumploot";
    }

    @Override
    public String getUsage (ICommandSender sender) {

        return "none";
    }

    @Override
    public void execute (MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        sender.sendMessage(new TextComponentTranslation("command.loottabletweaks.startdump"));
        for (final Entry<String, LootTable> table : LTTMod.tables.entrySet()) {

            Constants.LOG.info("## " + table.getKey());

            final List<LootPool> thePools = LTTMod.getPools(table.getValue());

            if (thePools.isEmpty()) {
                Constants.LOG.info("No entries for this table!");
            }

            else {

                final TableBuilder<LootEntryItemContext> output = new TableBuilder<>();
                output.addColumn("Pool Name", LootEntryItemContext::getPoolName);
                output.addColumn("Entry Name", LootEntryItemContext::getEntryName);
                output.addColumn("Item ID", LootEntryItemContext::getItemName);
                output.addColumn("Weight", LootEntryItemContext::getWeight);

                for (final LootPool pool : thePools) {

                    for (final LootEntry entry : LTTMod.getLootEntries(pool)) {

                        if (entry instanceof LootEntryItem) {

                            output.addEntry(new LootEntryItemContext(pool, (LootEntryItem) entry));
                        }
                    }
                }

                Constants.LOG.info(SystemUtils.LINE_SEPARATOR + output.createString());
            }

            Constants.LOG.info(SystemUtils.LINE_SEPARATOR);
        }

        sender.sendMessage(new TextComponentTranslation("command.loottabletweaks.enddump"));
    }

    private Object getPool () {

        // TODO Auto-generated method stub
        return null;
    }
}
