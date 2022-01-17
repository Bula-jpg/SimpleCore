package xyz.gnom.simplecore.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.gnom.simplecore.SimpleCore;
import xyz.gnom.simplecore.utils.Utils;

import java.io.File;
import java.io.IOException;

public class onJoin implements Listener {
    FileConfiguration data;
    @EventHandler
    public void onJoin(PlayerJoinEvent e) throws IOException {
        String joinmsg = SimpleCore.getInstance().getConfig().getString("join");
        if (!joinmsg.equalsIgnoreCase("none")) {
            joinmsg = joinmsg.replace("&", "§");
            joinmsg = joinmsg.replace("%name", e.getPlayer().getName());
            e.setJoinMessage(joinmsg);
        }
        boolean stats = SimpleCore.getInstance().getConfig().getBoolean("stats");
        if (stats) {
            File dataPlik = new File(SimpleCore.getInstance().getDataFolder() + "/data", e.getPlayer().getUniqueId() + ".yml");
            if (!dataPlik.exists()) {
                dataPlik.createNewFile();
                Utils.copy(SimpleCore.getInstance().getResource("data.yml"), dataPlik);
                data = YamlConfiguration.loadConfiguration(dataPlik);
                data.set("name", e.getPlayer().getName());
                int join = data.getInt("joined");
                ++join;
                data.set("joined", join);
                data.set("joined-time", System.currentTimeMillis());
                data.save(dataPlik);
                data = YamlConfiguration.loadConfiguration(dataPlik);
            } else {
                data = YamlConfiguration.loadConfiguration(dataPlik);
                int join = data.getInt("joined");
                ++join;
                data.set("joined", join);
                data.set("joined-time", System.currentTimeMillis());
                data.save(dataPlik);
                data = YamlConfiguration.loadConfiguration(dataPlik);
            }
        }
    }
}
