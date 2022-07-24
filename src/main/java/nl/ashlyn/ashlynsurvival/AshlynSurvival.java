package nl.ashlyn.ashlynsurvival;

import nl.ashlyn.ashlynsurvival.listener.PlayerJoinLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class AshlynSurvival extends JavaPlugin {
    public void onEnable() {
        Bukkit.getLogger().info("Anddddd Ashlyn's plugin is live!");
        Bukkit.getPluginManager().registerEvents(new PlayerJoinLeaveEvent(), this);
    }

    public void onDisable() {
        Bukkit.getLogger().info("Bye bye <3");
    }
}
