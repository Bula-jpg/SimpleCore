package xyz.gnom.simplecore;

import org.bukkit.plugin.java.JavaPlugin;
import xyz.gnom.simplecore.commands.BetterRestart;
import xyz.gnom.simplecore.commands.Help;
import xyz.gnom.simplecore.commands.StatsCommand;
import xyz.gnom.simplecore.listeners.BlockedCommand;
import xyz.gnom.simplecore.listeners.onJoin;
import xyz.gnom.simplecore.listeners.onLeave;
import xyz.gnom.simplecore.utils.FilesManager;
import xyz.gnom.simplecore.utils.Utils;

import java.util.Objects;

public final class SimpleCore extends JavaPlugin {
    public static SimpleCore instance;
    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        new Utils();
        this.saveDefaultConfig();
        FilesManager.setup();
        registerCommands();
        registerEvents();
        //TODO kick, i może ban :)
    }

    public void registerCommands() {
        Objects.requireNonNull(getCommand("adminrestart")).setExecutor(new BetterRestart());
        Objects.requireNonNull(getCommand("help")).setExecutor(new Help());
        Objects.requireNonNull(getCommand("stats")).setExecutor(new StatsCommand());
    }

    public void registerEvents() {
        getServer().getPluginManager().registerEvents(new BlockedCommand(), this);
        getServer().getPluginManager().registerEvents(new onJoin(), this);
        getServer().getPluginManager().registerEvents(new onLeave(), this);
        //TODO onLeave
    }

    public static SimpleCore getInstance() {
        return instance;
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
}
