package xyz.gnom.simplecore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.gnom.simplecore.SimpleCore;

import java.util.List;

public class Help implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> help = SimpleCore.getInstance().getConfig().getStringList("help");
        for (String msg : help) {
            msg = msg.replace("&", "§");
            sender.sendMessage(msg);
        }
        return false;
    }
}
