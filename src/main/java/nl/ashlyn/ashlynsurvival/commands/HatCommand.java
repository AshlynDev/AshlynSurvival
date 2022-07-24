package nl.ashlyn.ashlynsurvival.commands;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.command.CommandExecutor;

public class HatCommand implements CommandExecutor, Listener {
    String setMessage;

    String stackSizeMessage;

    String noPermissionMessage;

    String consoleMessage;


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PlayerInventory inv = player.getInventory();
            ItemStack held = inv.getItemInMainHand();
            ItemStack helm = inv.getHelmet();
            if (held.getAmount() == 1 || held.getType() == Material.AIR) {
                inv.setHelmet(held);
                inv.setItemInMainHand(helm);
                player.updateInventory();
                player.sendMessage(ChatColor.YELLOW + "HatManager " + ChatColor.DARK_GRAY + "> " + ChatColor.GRAY +  "Je ziet er goed uit!");
                return true;
            }
        }
        return false;
    }
}