package nl.ashlyn.ashlynsurvival;

import nl.ashlyn.ashlynsurvival.commands.HatCommand;
import nl.ashlyn.ashlynsurvival.listener.PlayerJoinLeaveEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class AshlynSurvival extends JavaPlugin {
    public void onEnable() {
        AshlynSurvival.log(ChatColor.GREEN+"+=========================================+");
        AshlynSurvival.log(ChatColor.GREEN+"AshlynSurvival Initiated! (Build: JA-1.0.0)");
        AshlynSurvival.log(ChatColor.GREEN+"+=========================================+");
        Bukkit.getPluginManager().registerEvents(new PlayerJoinLeaveEvent(), this);
        Objects.requireNonNull(this.getCommand("hat")).setExecutor(new HatCommand());
    }
    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
