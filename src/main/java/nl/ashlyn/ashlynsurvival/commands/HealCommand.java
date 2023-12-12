package nl.ashlyn.ashlynsurvival.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players.");
            return true;
        }

        Player player = (Player) sender;

        // Check if the player has permission to use the command
        if (!player.hasPermission("ashlyn.heal")) {
            player.sendMessage("You don't have permission to use this command.");
            return true;
        }

        // Heal the player
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.sendMessage("Je bent gehealed. Lekker fris hé");

        return true;
    }
}