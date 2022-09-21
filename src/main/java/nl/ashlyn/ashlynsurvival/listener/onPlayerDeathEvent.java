package nl.ashlyn.ashlynsurvival.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onPlayerDeathEvent implements Listener {
    @EventHandler
    public void quitEvent(final PlayerDeathEvent e) {
        String entity = e.getEntity().getName();
        String kille = e.getEntity().getKiller().getName();
        e.setDeathMessage(ChatColor.RED + entity + " is naar de hemel gegaan door " + kille);
    }
}
