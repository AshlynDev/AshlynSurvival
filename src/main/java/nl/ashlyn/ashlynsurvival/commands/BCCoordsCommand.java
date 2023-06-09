package nl.ashlyn.ashlynsurvival.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class BCCoordsCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Location loc = player.getLocation();

            double x = loc.getX();
            double y = loc.getY();
            double z = loc.getZ();
            Bukkit.getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "Coords van " + player.getDisplayName() +  " zijn: " + ChatColor.RED + "X: " + x + ChatColor.GREEN + " Y: " + y + ChatColor.BLUE + " Z: " + z);
            return true;
        }
        return true;
    }
}
