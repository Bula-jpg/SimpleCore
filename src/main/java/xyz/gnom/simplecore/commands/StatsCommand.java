package xyz.gnom.simplecore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import xyz.gnom.simplecore.SimpleCore;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StatsCommand implements CommandExecutor {
    YamlConfiguration data;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            File dataPlik = new File(SimpleCore.getInstance().getDataFolder() + "/data", p.getUniqueId() + ".yml");
            data = YamlConfiguration.loadConfiguration(dataPlik);
            List<String> msg = SimpleCore.getInstance().getConfig().getStringList("stats-command");
            int playtime = data.getInt("playtime");
            for (String loop : msg ) {
                loop = loop.replace("&", "§");
                loop = loop.replace("%name", p.getName());
                loop = loop.replace("%joins", String.valueOf(data.getInt("joined")));
                loop = loop.replace("%hours", String.valueOf(TimeUnit.MILLISECONDS.toHours(playtime)));
                loop = loop.replace("%minutes", String.valueOf(TimeUnit.MILLISECONDS.toMinutes(playtime%60)));
                loop = loop.replace("%seconds", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(playtime%60)));
                p.sendMessage(loop);
            }
        } else {
            sender.sendMessage("nope");
        }
        return false;
    }
}
