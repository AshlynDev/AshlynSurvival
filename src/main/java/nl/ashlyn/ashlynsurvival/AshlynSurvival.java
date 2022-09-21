package nl.ashlyn.ashlynsurvival;

import nl.ashlyn.ashlynsurvival.commands.*;
import nl.ashlyn.ashlynsurvival.listener.onPlayerJoinLeaveEvent;
import nl.ashlyn.ashlynsurvival.listener.onPlayerSleepEvent;
import nl.ashlyn.ashlynsurvival.listener.onPlayerDeathEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class AshlynSurvival extends JavaPlugin {
    public void onEnable() {
        AshlynSurvival.log(ChatColor.DARK_PURPLE + "+=========================================+");
        AshlynSurvival.log(ChatColor.DARK_PURPLE + "AshlynSurvival Initiated! (Build: JA-1.0.0)");
        AshlynSurvival.log(ChatColor.DARK_PURPLE + "+=========================================+");
        Bukkit.getPluginManager().registerEvents(new onPlayerJoinLeaveEvent(), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerSleepEvent(), this);
        Bukkit.getPluginManager().registerEvents(new onPlayerDeathEvent(), this);
        Objects.requireNonNull(this.getCommand("hat")).setExecutor(new HatCommand());
        Objects.requireNonNull(this.getCommand("coords")).setExecutor(new CoordsCommand());
        Objects.requireNonNull(this.getCommand("bccoords")).setExecutor(new BCCoordsCommand());
        Objects.requireNonNull(this.getCommand("herstart")).setExecutor(new RestartCommand());
        Objects.requireNonNull(this.getCommand("spawn")).setExecutor(new SpawnCommand());
    }
    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
}
