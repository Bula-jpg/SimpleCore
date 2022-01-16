package xyz.gnom.simplecore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import xyz.gnom.simplecore.SimpleCore;

public class BlockedCommand implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String prefix = SimpleCore.getInstance().getConfig().getString("prefix");
        String blockedcmd = SimpleCore.getInstance().getConfig().getString("no-permission");
        prefix = prefix.replace("&", "§");
        blockedcmd = blockedcmd.replace("&", "§");
        if (!event.getPlayer().hasPermission("simplecore.blocked-cmds")) {
            for (String cmd : SimpleCore.getInstance().getConfig().getStringList("blocked-cmds")) {
                if (event.getMessage().equalsIgnoreCase(cmd)) {
                    event.getPlayer().sendMessage(prefix + " " + blockedcmd);
                    event.setCancelled(true);
                    //TODO upiększyć i dodać permisje
                }
            }
        }
    }
}
