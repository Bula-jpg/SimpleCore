package xyz.gnom.simplecore;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.gnom.simplecore.commands.BetterRestart;
import xyz.gnom.simplecore.utils.Utils;

import java.util.Objects;

public final class SimpleCore extends JavaPlugin {
    public static SimpleCore instance;
    @Override
    public void onEnable() {
        instance = this;
        super.onEnable();
        new Utils();
        this.saveDefaultConfig();
        Objects.requireNonNull(getCommand("adminrestart")).setExecutor(new BetterRestart());

        //TODO kick, i może ban :)
    }
    public static SimpleCore getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
