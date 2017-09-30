package net.darkhax.lttweaker.crt;

import crafttweaker.IAction;
import net.darkhax.lttweaker.LTTMod;
import net.darkhax.lttweaker.removal.IRemover;

public class ActionRemoval implements IAction {

    private final IRemover remover;

    public ActionRemoval (IRemover remover) {

        this.remover = remover;
    }

    @Override
    public void apply () {

        LTTMod.removal.add(this.remover);
    }

    @Override
    public String describe () {

        return "Added loot removal for " + this.remover.toString();
    }
}
