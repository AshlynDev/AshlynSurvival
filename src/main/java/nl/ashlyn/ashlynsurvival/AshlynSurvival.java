package nl.ashlyn.ashlynsurvival;

import nl.ashlyn.ashlynsurvival.commands.BCCoordsCommand;
import nl.ashlyn.ashlynsurvival.commands.CoordsCommand;
import nl.ashlyn.ashlynsurvival.commands.HatCommand;
import nl.ashlyn.ashlynsurvival.listener.PlayerJoinLeaveEvent;
import nl.ashlyn.ashlynsurvival.listener.PlayerSleepEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class AshlynSurvival extends JavaPlugin {
    public void onEnable() {
        AshlynSurvival.log(ChatColor.DARK_PURPLE + "+=========================================+");
        AshlynSurvival.log(ChatColor.DARK_PURPLE + "AshlynSurvival Initiated! (Build: JA-1.0.0)");
        AshlynSurvival.log(ChatColor.DARK_PURPLE + "+=========================================+");
        Bukkit.getPluginManager().registerEvents(new PlayerJoinLeaveEvent(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerSleepEvent(), this);
        Objects.requireNonNull(this.getCommand("hat")).setExecutor(new HatCommand());
        Objects.requireNonNull(this.getCommand("coords")).setExecutor(new CoordsCommand());
        Objects.requireNonNull(this.getCommand("bccoords")).setExecutor(new BCCoordsCommand());
    }
    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
