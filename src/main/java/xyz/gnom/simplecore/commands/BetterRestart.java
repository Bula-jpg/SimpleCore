package xyz.gnom.simplecore.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.gnom.simplecore.SimpleCore;

import static org.bukkit.Bukkit.getServer;
import static org.bukkit.Bukkit.spigot;


public class BetterRestart implements CommandExecutor {
    public static  String prefix = spigot().getConfig().getString("prefix");
    public static String fiveteen = spigot().getConfig().getString("adminrestart.15s");
    public static String ten = spigot().getConfig().getString("adminrestart.10s");
    public static String five = spigot().getConfig().getString("adminrestart.5s");
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("simplecore.restart")) {
            prefix = prefix.replace("&", "§");
            fiveteen = fiveteen.replace("&", "§");
            ten = ten.replace("&", "§");
            five = five.replace("&", "§");
            getServer().broadcastMessage(prefix + " " + fiveteen);
            Bukkit.getScheduler().scheduleSyncDelayedTask(SimpleCore.instance, new Runnable() {
                @Override
                public void run() {
                    getServer().broadcastMessage(prefix + " " +  ten);
                }
            }, 100L); //20 Tick (1 Second) delay before run() is called

            Bukkit.getScheduler().scheduleSyncDelayedTask(SimpleCore.instance, new Runnable() {
                @Override
                public void run() {
                    getServer().broadcastMessage(prefix + " " + five);
                }
            }, 200L); //20 Tick (1 Second) delay before run() is called

            Bukkit.getScheduler().scheduleSyncDelayedTask(SimpleCore.instance, new Runnable() {
                @Override
                public void run() {
                    runCommandAsConsole("kickall &c&lTrwa restart");
                    getServer().broadcastMessage("§c§lRestart");
                }
            }, 300L); //20 Tick (1 Second) delay before run() is called

            Bukkit.getScheduler().scheduleSyncDelayedTask(SimpleCore.instance, new Runnable() {
                @Override
                public void run() {
                    runCommandAsConsole("restart");
                }
            }, 320L); //20 Tick (1 Second) delay before run() is called

        } else {
            getServer().broadcastMessage("§8§l[§b§lNiebiosa§f§lMC§8]§f >> §4Brak permisji!");
        }

        return false;
    }
    public void runCommandAsConsole(String commandCallback) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commandCallback);
    }
}