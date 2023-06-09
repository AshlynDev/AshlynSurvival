package nl.ashlyn.ashlynsurvival.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class RestartCommand implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            String restartcommand = "restart";
            Bukkit.getServer().broadcastMessage(ChatColor.RED + "Server herstart aangevraagt door " + player.getDisplayName());
            Bukkit.dispatchCommand(console, restartcommand);
            return true;
        }
        return true;
    }
}
