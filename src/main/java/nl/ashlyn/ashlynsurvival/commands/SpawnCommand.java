package nl.ashlyn.ashlynsurvival.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location loc = new Location(Bukkit.getWorld("world"), -22, 64, -388, 0, 0);
            player.teleport(loc);
            player.sendMessage(ChatColor.DARK_PURPLE + "Welkom terug bij spawn! Ik heb een bloed hekel aan je!");
            Bukkit.getServer().broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.RED + " dacht leuk te zijn en tpd rustig naar spawn, vind en vermoord hem.");
        }
            return true;
    }
}

