package xyz.gnom.simplecore.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.gnom.simplecore.SimpleCore;

import java.io.File;
import java.io.IOException;

public class onLeave implements Listener {
    FileConfiguration data;
    @EventHandler
    public void onLeave(PlayerQuitEvent event) throws IOException {
        String leavemsg = SimpleCore.getInstance().getConfig().getString("leave");
        if (!leavemsg.equalsIgnoreCase("none")) {
            leavemsg = leavemsg.replace("&", "§");
            leavemsg = leavemsg.replace("%name", event.getPlayer().getName());
            event.setQuitMessage(leavemsg);
        }
        boolean stats = SimpleCore.getInstance().getConfig().getBoolean("stats");
        if (stats) {
            File dataPlik = new File(SimpleCore.getInstance().getDataFolder() + "/data", event.getPlayer().getUniqueId() + ".yml");
            data = YamlConfiguration.loadConfiguration(dataPlik);
            int joinedtime = data.getInt("joined-time");
            int leavetime = (int) System.currentTimeMillis();
            int playtime = leavetime - joinedtime;
            data.set("playTime", playtime);
            data.save(dataPlik);
            data = YamlConfiguration.loadConfiguration(dataPlik);
        }
    }
}
