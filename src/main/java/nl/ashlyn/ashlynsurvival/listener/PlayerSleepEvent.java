package nl.ashlyn.ashlynsurvival.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class PlayerSleepEvent implements Listener {
    @EventHandler
    public void onPlayerSleep(final PlayerBedEnterEvent e) {
        if (e.isCancelled()) {
            return;
        }
        String player = e.getPlayer().getName();
        Bukkit.getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + player + " gaat lekker slapen");
    }
    @EventHandler
    public void onPlayerWake(final PlayerBedLeaveEvent e) {
        if (e.isCancelled()) {
            return;
        }
        String player = e.getPlayer().getName();
        Bukkit.getServer().broadcastMessage(ChatColor.RED + player + " is uit het bed getyft");
    }
}
