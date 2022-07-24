package nl.ashlyn.ashlynsurvival.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

public class onPlayerJoinLeaveEvent implements Listener {
    @EventHandler
    public void joinEvent(final PlayerJoinEvent e) {
        String player = e.getPlayer().getName();
                e.setJoinMessage(ChatColor.GREEN + player + " is de kerk gejoined");
    }
    @EventHandler
    public void quitEvent(final PlayerQuitEvent e) {
        String player = e.getPlayer().getName();
        e.setQuitMessage(ChatColor.RED + player + " heeft de kerk verlaten");
    }
}
