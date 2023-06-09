package nl.ashlyn.ashlynsurvival.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onPlayerDeathEvent implements Listener {
    @EventHandler
    public void quitEvent(final PlayerDeathEvent e) {
        String player = e.getEntity().getName();
        String death = e.getDeathMessage();
        e.setDeathMessage(ChatColor.RED + player + " is naar de hemel gegaan aka " + death);
    }
}
